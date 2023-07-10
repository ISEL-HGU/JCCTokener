package isel.csee.jcctokener.parser;

import org.checkerframework.checker.units.qual.A;
import org.eclipse.jdt.core.dom.*;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ASTChecker extends ASTVisitor {
    private List<String> classList = new ArrayList<>();
    private List<String> methodDeclarationList = new ArrayList<>();
    private List<String> variableList = new ArrayList<>();
    private List<String> methodInvocationList = new ArrayList<>();
    private List<String> totalVariableList = new ArrayList<>();
    private List<String> argumentList = new ArrayList<>();

    @Override
    public boolean visit(ForStatement node) {
        System.out.println("For init" + node.initializers());
        System.out.println("For condition: " + node.getExpression());
        System.out.println("For updater: " + node.updaters());
        System.out.println("For body: " + node.getBody());
        return super.visit(node);
    }

    @Override
    public boolean visit(ThrowStatement node) { // 확인 필요
        System.out.println("Throw statement: " + node.getExpression());
        return super.visit(node);
    }

    @Override
    public boolean visit(CatchClause node) { // 확인 필요
        System.out.println("Catch clause: " + node.getException());
        return super.visit(node);
    }

    @Override
    public boolean visit(SingleVariableDeclaration node) { // 확인 필요
        System.out.println("Single variable declaration: " + node.getName());
        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationFragment node) { // 확인 필요
        return super.visit(node);
    }

    @Override
    public boolean visit(ArrayType node) { // 확인 필요
        return super.visit(node);
    }

    @Override
    public boolean visit(ParameterizedType node) { // 확인 필요
        return super.visit(node);
    }

//    @Override
//    public boolean visit(SimpleType node) { // 확인 필요
//        return super.visit(node);
//    }

    @Override
    public boolean visit(EnhancedForStatement node) {
        System.out.println("For condition: " + node.getExpression());
        System.out.println("For body: " + node.getBody());
        return super.visit(node);
    }

    @Override
    public boolean visit(Block node) {
        System.out.println("Block: " + node.statements().toString());

        return super.visit(node);
    }

    @Override
    public boolean visit(TryStatement node) {
        Block tryBlock = node.getBody();

        for(Object statement : tryBlock.statements()) {
            System.out.println(statement);
            if (statement instanceof ExpressionStatement) {
                ExpressionStatement expressionStatement = (ExpressionStatement) statement;
                Expression expression = expressionStatement.getExpression(); // for loop 안의 statement = ExpressionStatement 먼저 취급 / 따라서 ExpressionStatement 만든 뒤 변환하는 과정을 거쳐야 한다
                if (expression instanceof Assignment) {
                    Assignment assignment = (Assignment) expression;
                    Expression leftHandSide = assignment.getLeftHandSide();
                    Expression rightHandSide = assignment.getRightHandSide();

                    if (leftHandSide instanceof SimpleName) {
                        SimpleName simpleName = (SimpleName) leftHandSide;
                        totalVariableList.add(simpleName.getIdentifier());
                    }
                    if (rightHandSide instanceof SimpleName) {
                        SimpleName simpleName = (SimpleName) rightHandSide;
                        totalVariableList.add(simpleName.getIdentifier());
                    } else if (rightHandSide instanceof MethodInvocation) {
                        MethodInvocation methodInvocation = (MethodInvocation) rightHandSide;
                        SimpleName methodName = methodInvocation.getName();
                        methodInvocationList.add(methodName.getIdentifier());

                        for (Object argumentObject : methodInvocation.arguments()) {
                            Expression argument = (Expression) argumentObject;

                            if (argument instanceof SimpleName) {
                                SimpleName simpleName = (SimpleName) argument;
                                argumentList.add(simpleName.getIdentifier());
                            }
                        }
                    }
                }
            }
        }

        return super.visit(node);
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        System.out.println("Class name: " + node.getName().getIdentifier()); // 클래스인 경우에 name, identifier print
        classList.add(node.getName().getIdentifier());
        return super.visit(node);
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        System.out.println("Field name: " + node.fragments()); // 클래스인 경우에 name, identifier print
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        System.out.println("Method name: " + node.getName().getIdentifier());
        methodDeclarationList.add(node.getName().getIdentifier());

//        System.out.println(node.getBody());

        Block methodBody = node.getBody();

//        if(methodBody != null) {
//            methodBody.accept(this);
//        }
        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationStatement node) {
        System.out.println("Variable: " + node.fragments().get(0));
        variableList.add(node.fragments().toString());
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) { // method 사용 부분
        System.out.println("Method Invocation: " + node.getName().getIdentifier());
        methodInvocationList.add(node.getName().getIdentifier());
        return super.visit(node);
    }

    @Override
    public boolean visit(SwitchCase node) {
        System.out.println("Switch Case: " + node.toString());
        return super.visit(node);
    }

    @Override
    public boolean visit(SwitchStatement node) {
        System.out.println("Switch Statement: " + node.getExpression().toString());
        return super.visit(node);
    }

    @Override
    public boolean visit(ExpressionStatement node) {
        Expression expression = node.getExpression();
        System.out.println(expression.toString());

        return super.visit(node);
    }


    @Override
    public boolean visit(Assignment node) {
        Expression leftHandSide = node.getLeftHandSide();
        System.out.println("Left side: " + leftHandSide);

        Expression rightHandSide = node.getRightHandSide();
        System.out.println("Right side: " + rightHandSide);

        return super.visit(node);
    }

    @Override
    public boolean visit(SimpleName node) {
//        System.out.println("Simple name: " + node.getIdentifier());

        return super.visit(node);
    }



    public List<String> getClassList() {
        return classList;
    }

    public void setClassList(List<String> classList) {
        this.classList = classList;
    }

    public List<String> getMethodDeclarationList() {
        return methodDeclarationList;
    }

    public void setMethodDeclarationList(List<String> methodDeclarationList) {
        this.methodDeclarationList = methodDeclarationList;
    }

    public List<String> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<String> variableList) {
        this.variableList = variableList;
    }

    public List<String> getMethodInvocationList() {
        return methodInvocationList;
    }

    public void setMethodInvocationList(List<String> methodInvocationList) {
        this.methodInvocationList = methodInvocationList;
    }

    public List<String> getTotalVariableList() {
        return totalVariableList;
    }

    public void setTotalVariableList(List<String> totalVariableList) {
        this.totalVariableList = totalVariableList;
    }

    public List<String> getArgumentList() {
        return argumentList;
    }

    public void setArgumentList(List<String> argumentList) {
        this.argumentList = argumentList;
    }
}

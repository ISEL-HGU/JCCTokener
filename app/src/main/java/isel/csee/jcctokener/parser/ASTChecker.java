package isel.csee.jcctokener.parser;


import org.eclipse.jdt.core.dom.*;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



// getParent()를 통해서 부모 노드를 가져와서 사용이 가능 - recursion 구현?
// 하나의 파일 내부에 같은 이름의 변수는 같은 변수인가
// 다른 Structure Vector로 생각해서 Semantic Vector를 만들어야 하는가
// 다른 Vector로 생각하는 게 맞을 듯,,? 변수의 이름에 신경쓰지 않으려면 다른 Vector로 생각해야 할 듯,,,,?




public class ASTChecker extends ASTVisitor {
    private List<String> classList = new ArrayList<>();
    private List<String> methodDeclarationList = new ArrayList<>();
    private List<String> variableList = new ArrayList<>();
    private List<String> methodInvocationList = new ArrayList<>();
    private List<String> totalVariableList = new ArrayList<>();
    private List<String> argumentList = new ArrayList<>();
    private List<String> operatorList = new ArrayList<>();
    private List<int[]> structureVector = new ArrayList<>();


    @Override
    public boolean visit(ForStatement node) { // For Loop / 내부적으로 조건 및 본문 부분을 가져오는 것이 가능
        System.out.println("For init" + node.initializers());
        System.out.println("For condition: " + node.getExpression());
        System.out.println("For updater: " + node.updaters());
        System.out.println("For body: " + node.getBody());
        return super.visit(node);
    }

    @Override
    public boolean visit(ArrayAccess node) { // 배열에서 인덱스 부분 ex) a[1] -> [1] 이 부분
        return super.visit(node);
    }

    @Override
    public boolean visit(ThrowStatement node) { // 확인 필요
        System.out.println("Throw statement: " + node.getExpression());
        return super.visit(node);
    }

    @Override
    public boolean visit(CatchClause node) { // Catch 부분을 가져올 수 있음 / 근데 TryStatement에서 통합적으로 수행 가능
        System.out.println("Catch clause: " + node.getException());
        return super.visit(node);
    }

    @Override
    public boolean visit(SingleVariableDeclaration node) { // 확인 필요
        System.out.println("Single variable declaration: " + node.getName());
        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationFragment node) { // 변수 이름과 초기화 식을 포함하는 개념 / 변수의 선언 부분은 사용하지 않음
        return super.visit(node);
    }

    @Override
    public boolean visit(ArrayType node) { // 배열의 dimension / data type을 확인
        Type elementType = node.getElementType(); // data type 추출
        int dimensions = node.getDimensions(); // dimension 추출

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
    public boolean visit(EnhancedForStatement node) { // Enhanced For Loop
        System.out.println("For condition: " + node.getExpression());
        System.out.println("For body: " + node.getBody());
        return super.visit(node);
    }

    @Override
    public boolean visit(Block node) {
//        System.out.println("Block: " + node.statements().toString());

        return super.visit(node);
    }

    @Override
    public boolean visit(TryStatement node) { // 이 부분에서 Try-Catch-Finally block 모두 추출 가능
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
    public boolean visit(ConstructorInvocation node) { // constructor 사용 부분
        return super.visit(node);
    }

    @Override
    public boolean visit(LambdaExpression node) { // 람다식에 대한 처리 부분 -> 이 부분은 필요한지에 대해 더 생각
        return super.visit(node);
    }

    @Override
    public boolean visit(IfStatement node) { // If 조건문의 조건 부분 / getThenStatement method를 통해서 If 조건문의 Body 부분을 가져올 수 있음
        // If 문의 조건식 추출
        Expression condition = node.getExpression();

        // If 문의 본문 추출
        Statement thenStatement = node.getThenStatement();
        Statement elseStatement = node.getElseStatement();

        // Else 문이 있는 경우
        if (elseStatement != null) { // else / else if 구분
            if(elseStatement instanceof IfStatement) { // else if block / 재귀적으로 처리
                visit((IfStatement) elseStatement);
            } else { // else block

            }
        }

        return super.visit(node);
    }
    @Override
    public boolean visit(ArrayCreation node) { // Array 생성 부분
        return super.visit(node);
    }

    @Override
    public boolean visit(ReturnStatement node) { // return statement 사용
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
        System.out.println("Field name: " + node.fragments()); // Field 경우에 name, identifier print
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodDeclaration node) { // Method Definition
        System.out.println("Method name: " + node.getName().getIdentifier());

        int a[] = {1,2,3,4,5,6,7,8,9,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0}; // testq

        structureVector.add(a);
        methodDeclarationList.add(node.getName().getIdentifier());

//        System.out.println(node.getBody());

        Block methodBody = node.getBody();

//        if(methodBody != null) {
//            methodBody.accept(this);
//        }
        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationStatement node) { // 변수의 선언을 포함하는 statement
        List<VariableDeclarationFragment> variableDeclarationFragmentList = node.fragments();

        variableList.add(variableDeclarationFragmentList.get(0).getName().toString());
        System.out.println("Variable: " + variableDeclarationFragmentList.get(0).getName().toString());
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) { // method 사용 부분 / 호출을 의미 -> Callee
        System.out.println("Method Invocation: " + node.getName().getIdentifier());
        methodInvocationList.add(node.getName().getIdentifier());
        return super.visit(node);
    }


    @Override
    public boolean visit(InfixExpression node) { // Operator -> 이 method 내부에서 operator의 차이를 두어야 함 / Numeric, Logical, Condition etc.
        System.out.println("Operator: " + node.getOperator().toString());
        operatorList.add(node.getOperator().toString());

        return super.visit(node);
    }

    @Override
    public boolean visit(SwitchCase node) { // switch case / switch 안에 존재하는 여러가지 case 각각을 의미
        System.out.println("Switch Case: " + node.toString());
        return super.visit(node);
    }

    @Override
    public boolean visit(SwitchStatement node) { // switch statement / switch 전체를 의미
        System.out.println("Switch Statement: " + node.getExpression().toString());
        return super.visit(node);
    }

    @Override
    public boolean visit(ExpressionStatement node) {
        Expression expression = node.getExpression();
        System.out.println(expression.toString());
//        totalVariableList.add(expression.toString());

        return super.visit(node);
    }


    @Override
    public boolean visit(Assignment node) {
        Expression leftHandSide = node.getLeftHandSide();
        System.out.println("Left side: " + leftHandSide);
        totalVariableList.add(leftHandSide.toString());

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

    public List<String> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(List<String> operatorList) {
        this.operatorList = operatorList;
    }

    public List<int[]> getStructureVector() {
        return structureVector;
    }

    public void setStructureVector(List<int[]> structureVector) {
        this.structureVector = structureVector;
    }
}

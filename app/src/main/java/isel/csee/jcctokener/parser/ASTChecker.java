package isel.csee.jcctokener.parser;


import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;
import org.w3c.dom.Node;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    public boolean visit(ForStatement node) { // For Loop / 내부적으로 조건 및 본문 부분을 가져오는 것이 가능 -> Type 4, 5
//        System.out.println("For init" + node.initializers());
//        System.out.println("For condition: " + node.getExpression());
//        System.out.println("For updater: " + node.updaters());
//        System.out.println("For body: " + node.getBody());
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(ArrayAccess node) { // 배열에서 인덱스 부분 ex) a[1] -> [1] 이 부분 -> Type 6
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(ThrowStatement node) { // Type 19
        System.out.println("Throw statement: " + node.getExpression());
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(CatchClause node) { // Catch 부분을 가져올 수 있음 -> Type 21q
        System.out.println("Catch clause: " + node.getException());
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(SingleVariableDeclaration node) { // 확인 필요
        System.out.println("Single variable declaration: " + node.getName());
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

//    @Override
//    public boolean visit(VariableDeclarationFragment node) { // 변수 이름과 초기화 식을 포함하는 개념 / 변수의 선언 부분은 사용하지 않음
//        System.out.println(NodeType.searchType(node));
//        return super.visit(node);
//    }

    @Override
    public boolean visit(ArrayType node) { // 배열의 dimension / data type을 확인
        Type elementType = node.getElementType(); // data type 추출
        int dimensions = node.getDimensions(); // dimension 추출
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(ParameterizedType node) { // generic type
//        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(ClassInstanceCreation node) { // Class to Instance / 24? 25?
        System.out.println(NodeType.searchType(node));
        System.out.println("Test");
        return super.visit(node);
    }



//    @Override
//    public boolean visit(SimpleType node) { // 확인 필요
//        return super.visit(node);
//    }

    @Override
    public boolean visit(EnhancedForStatement node) { // Enhanced For Loop / type 4, 5
        System.out.println("For condition: " + node.getExpression());
        System.out.println("For body: " + node.getBody());
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

//    @Override
//    public boolean visit(Block node) { // 자바 코드 블럭을 의미 / 일단 사용 보류
////        System.out.println("Block: " + node.statements().toString());
//        System.out.println(NodeType.searchType(node));
//        return super.visit(node);
//    }

    @Override
    public boolean visit(TryStatement node) { // 이 부분에서 Try-Catch-Finally block 모두 추출 가능 // Type 20
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
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(ConstructorInvocation node) { // constructor 사용 부분 Type 24
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(LambdaExpression node) { // 람다식에 대한 처리 부분 -> 이 부분은 필요한지에 대해 더 생각 Type 23
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(IfStatement node) { // If 조건문의 조건 부분 / getThenStatement method를 통해서 If 조건문의 Body 부분을 가져올 수 있음 Type 1
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
//        System.out.println(NodeType.searchType(node));

        return super.visit(node);
    }
    @Override
    public boolean visit(ArrayCreation node) { // Array 생성 부분 / Type 25
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(ReturnStatement node) { // return statement 사용 Type 12
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(TypeDeclaration node) { // Class 이름
        System.out.println("Class name: " + node.getName().getIdentifier()); // 클래스인 경우에 name, identifier print
        classList.add(node.getName().getIdentifier());
        System.out.println(NodeType.searchType(node));

        return super.visit(node);
    }

    @Override
    public boolean visit(FieldDeclaration node) { // Field Define -> Type에 추가해야함
        System.out.println("Field name: " + node.fragments()); // Field 경우에 name, identifier print
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodDeclaration node) { // Method Definition Type 3
        System.out.println("Method name: " + node.getName().getIdentifier());

        methodDeclarationList.add(node.getName().getIdentifier());

//        System.out.println(node.getBody());

        Block methodBody = node.getBody();

//        if(methodBody != null) {
//            methodBody.accept(this);
//        }
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationExpression node) { // 변수의 선언을 포함하는 statement
        List<VariableDeclarationFragment> variableDeclarationFragmentList = node.fragments();

        variableList.add(variableDeclarationFragmentList.get(0).getName().toString());
        System.out.println("Variable: " + variableDeclarationFragmentList.get(0).getName().toString());
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) { // method 사용 부분 / 호출을 의미 -> Callee Type 11
        System.out.println("Method Invocation: " + node.getName().getIdentifier());
        methodInvocationList.add(node.getName().getIdentifier());
        System.out.println(NodeType.searchType(node));

        System.out.println("");

        ASTNode temp = node;

        int[] vector = new int[30];

        Arrays.fill(vector, 0);

        for(int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
        }

        while(!(temp instanceof TypeDeclaration)) {
            vector[NodeType.searchType(temp)] += 1;
            System.out.println(ASTNode.nodeClassForType(temp.getNodeType()).getSimpleName());

            temp = temp.getParent();
        }

        for(int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
        }


        return super.visit(node);
    }


    @Override
    public boolean visit(InfixExpression node) { // Operator -> 이 method 내부에서 operator의 차이를 두어야 함 / Numeric, Logical, Condition etc. Type 7,8,9
        System.out.println("Operator: " + node.getOperator().toString());
        operatorList.add(node.getOperator().toString());
        System.out.println(NodeType.searchType(node));

        ASTNode temp = node;

        int[] vector = new int[30];
        Arrays.fill(vector, 0);
        System.out.println("");

        while(!(temp instanceof TypeDeclaration)) {
            vector[NodeType.searchType(temp)] += 1;
//            System.out.println(NodeType.searchType(temp));
            System.out.println(NodeType.searchType(temp));
            System.out.println(ASTNode.nodeClassForType(temp.getNodeType()).getSimpleName());
            temp = temp.getParent();

        }

        structureVector.add(vector);

        for(int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]);
        }

        return super.visit(node);
    }

    @Override
    public boolean visit(SwitchCase node) { // switch case / switch 안에 존재하는 여러가지 case 각각을 의미 Type 14
        System.out.println("Switch Case: " + node.toString());
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(SwitchStatement node) { // switch statement / switch 전체를 의미 Type 15
        System.out.println("Switch Statement: " + node.getExpression().toString());
        System.out.println(NodeType.searchType(node));
        return super.visit(node);
    }

    @Override
    public boolean visit(ExpressionStatement node) { // 잠깐 보류
        Expression expression = node.getExpression();
        System.out.println(expression.toString());
        totalVariableList.add(expression.toString());
        System.out.println(NodeType.searchType(node));

        return super.visit(node);
    }


    @Override
    public boolean visit(Assignment node) { // Type 10
        Expression leftHandSide = node.getLeftHandSide();
        System.out.println("Left side: " + leftHandSide);
        totalVariableList.add(leftHandSide.toString());

        Expression rightHandSide = node.getRightHandSide();
        System.out.println("Right side: " + rightHandSide);

        System.out.println(NodeType.searchType(node));

        return super.visit(node);
    }

    @Override
    public boolean visit(SimpleName node) { // 보류
//        System.out.println("Simple name: " + node.getIdentifier());
        System.out.println(NodeType.searchType(node));

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

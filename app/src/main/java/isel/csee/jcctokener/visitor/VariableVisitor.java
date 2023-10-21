package isel.csee.jcctokener.visitor;

import isel.csee.jcctokener.node.jCCNode;
import org.eclipse.jdt.core.dom.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VariableVisitor extends ASTVisitor { // 중복으로 방문이 이루어질 수 있음
    // 좌변 우변을 나누기 보다는 식 전체를 아우르는 node를 찾고, 그 해당 노드에서는 중복으로 node를 가져오지 않도록 코드를 작성하는 게 맞아 보임
    private List<jCCNode> jCCNodeList = new ArrayList<>();
    @Override
    public boolean visit(ArrayAccess node) {
        Expression arrayExpression = node.getArray();
        Expression indexExpression = node.getIndex();

        if(arrayExpression instanceof SimpleName) {
            jCCNode arrayNode = new jCCNode();

            arrayNode.setVariableName(((SimpleName) arrayExpression).getIdentifier());
            arrayNode.setNode(node);
            arrayNode.setStartPosition(arrayExpression.getStartPosition());

            jCCNodeList.add(arrayNode);
        }

        if(indexExpression instanceof SimpleName) {
            jCCNode indexNode = new jCCNode();

            indexNode.setVariableName(((SimpleName) indexExpression).getIdentifier());
            indexNode.setNode(node);
            indexNode.setStartPosition(indexExpression.getStartPosition());

            jCCNodeList.add(indexNode);
        }
        return super.visit(node);
    }

    @Override
    public boolean visit(ArrayCreation node) { // int[] arr1 = new int[5]; / variableDeclaration node의 우변에 해당하는 듯

        return super.visit(node);
    }

    @Override
    public boolean visit(AssertStatement node) { // 다른 노드를 통해서 assert의 조건 부분을 가져올 수 있을 듯

        return super.visit(node);
    }

    @Override
    public boolean visit(Assignment node) { // 식의 우변, 좌변 모두를 포함하는 노드
        Expression leftOperand = node.getLeftHandSide();
        Expression rightOperand = node.getRightHandSide();

        if(leftOperand instanceof SimpleName) {
            jCCNode leftNode = new jCCNode();

            leftNode.setVariableName(((SimpleName) leftOperand).getIdentifier());
            leftNode.setNode(node);
            leftNode.setStartPosition(leftOperand.getStartPosition());

            jCCNodeList.add(leftNode);
        }

        if(rightOperand instanceof SimpleName) {
            jCCNode rightNode = new jCCNode();

            rightNode.setVariableName(((SimpleName) rightOperand).getIdentifier());
            rightNode.setNode(node);
            rightNode.setStartPosition(rightOperand.getStartPosition());

            jCCNodeList.add(rightNode);
        }
        return super.visit(node);
    }

    @Override
    public boolean visit(EnhancedForStatement node) {
        SingleVariableDeclaration parameter = node.getParameter();
        Expression expression = node.getExpression();

        jCCNode parameterNode = new jCCNode();
        parameterNode.setVariableName(parameter.getName().toString());
        parameterNode.setNode(node);
        parameterNode.setStartPosition(parameter.getStartPosition());
        jCCNodeList.add(parameterNode);

        jCCNode initialNode = new jCCNode();
        initialNode.setVariableName(expression.toString());
        initialNode.setNode(node);
        initialNode.setStartPosition(expression.getStartPosition());
        jCCNodeList.add(initialNode);

        return super.visit(node);
    }

    @Override
    public boolean visit(ClassInstanceCreation node) { // class를 instaance로 나타내는 부분 / new keyword 이후에 열거되는 우변에 해당하는 node
        List argumentList = node.arguments();

        for(int i = 0; i < argumentList.size(); i++) {
            jCCNode argumentNode = new jCCNode();
            ASTNode astNode = (ASTNode) argumentList.get(i);

            argumentNode.setVariableName(argumentList.get(i).toString());
            argumentNode.setNode(node);
            argumentNode.setStartPosition(astNode.getStartPosition());

            jCCNodeList.add(argumentNode);
        }

        return super.visit(node);
    }

    @Override
    public boolean visit(ConditionalExpression node) { // Expression ? Expression : Expression / 3항 연산에 해당하는 부분
        Expression conditionExpression = node.getExpression();
        Expression trueExpression = node.getThenExpression();
        Expression falseExpression = node.getThenExpression();

        if(conditionExpression instanceof SimpleName) {
            jCCNode conditionNode = new jCCNode();

            conditionNode.setVariableName(((SimpleName) conditionExpression).getIdentifier());
            conditionNode.setNode(node);
            conditionNode.setStartPosition(conditionExpression.getStartPosition());

            jCCNodeList.add(conditionNode);
        }

        if(trueExpression instanceof SimpleName) {
            jCCNode trueNode = new jCCNode();

            trueNode.setVariableName(((SimpleName) trueExpression).getIdentifier());
            trueNode.setNode(node);
            trueNode.setStartPosition(trueExpression.getStartPosition());

            jCCNodeList.add(trueNode);
        }

        if(falseExpression instanceof SimpleName) {
            jCCNode falseNode = new jCCNode();

            falseNode.setVariableName(((SimpleName) falseExpression).getIdentifier());
            falseNode.setNode(node);
            falseNode.setStartPosition(falseExpression.getStartPosition());

            jCCNodeList.add(falseNode);
        }

        return super.visit(node);
    }

    @Override
    public boolean visit(ConstructorInvocation node) { // 해당 클래스의 constructor가 아니라 다른 클래스의 constructor를 호출하여 사용하는 경우에 해당함
        // 생성자 내에서 다른 생성자를 호출하는 경우에 해당하는 type
        return super.visit(node);
    }

    @Override
    public boolean visit(FieldAccess node) {
        Expression fieldExpression = node.getExpression();
        Expression fieldName = node.getName();


        if(fieldExpression instanceof SimpleName) {
            jCCNode fieldExpressionNode = new jCCNode();

            fieldExpressionNode.setVariableName(((SimpleName) fieldExpression).getIdentifier());
            fieldExpressionNode.setNode(node);
            fieldExpressionNode.setStartPosition(fieldExpression.getStartPosition());

            jCCNodeList.add(fieldExpressionNode);
        }

        if(fieldName instanceof SimpleName) {
            jCCNode fieldNameNode = new jCCNode();

            fieldNameNode.setVariableName(((SimpleName) fieldName).getIdentifier());
            fieldNameNode.setNode(node);
            fieldNameNode.setStartPosition(fieldName.getStartPosition());

            jCCNodeList.add(fieldNameNode);
        }


        return super.visit(node);
    }

    @Override
    public boolean visit(SwitchStatement node) { // switch의 조건에 해당하는 값을 가져옴
        jCCNode switchCase = new jCCNode();

        switchCase.setVariableName(node.getExpression().toString());
        switchCase.setNode(node);
        switchCase.setStartPosition(node.getStartPosition());

        jCCNodeList.add(switchCase);

        return super.visit(node);
    }

    @Override
    public boolean visit(CatchClause node) { // catch 부분에 존재하는 error는 가지고 올 필요가 없어 보임
        jCCNode catchNode = new jCCNode();

        catchNode.setVariableName(node.getException().getName().toString());
        catchNode.setNode(node);
        catchNode.setStartPosition(node.getException().getName().getStartPosition());

        jCCNodeList.add(catchNode);

        return super.visit(node);
    }

    // QualifiedName node가 필요해지면 가져와서 사용
//    @Override
//    public boolean visit(QualifiedName node) { // Instance의 field에 접근할 경우에 사용해야 함
//        if(node.getParent().getNodeType() != 35) { // Package 선언에 해당하지 않는 경우
//            Expression leftOperand = node.getQualifier();
//            Expression rightOperand = node.getName();
//
//            jCCNode leftNode = new jCCNode();
//            leftNode.setVariableName(((SimpleName) leftOperand).getIdentifier());
//            leftNode.setNode(node);
//            leftNode.setStartPosition(leftOperand.getStartPosition());
//
//            jCCNodeList.add(leftNode);
//
//
//            if(rightOperand instanceof SimpleName) { // SimpleName으로 한정하는 것이 맞으려나?
//                jCCNode rightNode = new jCCNode();
//                rightNode.setVariableName(((SimpleName) rightOperand).getIdentifier());
//                rightNode.setNode(node);
//                rightNode.setStartPosition(rightOperand.getStartPosition());
//
//
//                jCCNodeList.add(rightNode);
//            }
//        }
//
//
//        return super.visit(node);
//    }

    @Override
    public boolean visit(InfixExpression node) {
        Expression leftOperand = node.getLeftOperand();
        Expression rightOperand = node.getRightOperand();
        List extendedOperandList = node.extendedOperands();

        // 양 옆 노드가 SimpleName node에 해당하는 경우만 가지고 오는 현상 발생
        if(leftOperand instanceof SimpleName) {
            jCCNode leftOperandNode = new jCCNode();

            leftOperandNode.setVariableName(((SimpleName) leftOperand).getIdentifier());
            leftOperandNode.setNode(node);
            leftOperandNode.setStartPosition(leftOperand.getStartPosition());

            jCCNodeList.add(leftOperandNode);
        }

        if(rightOperand instanceof SimpleName) {
            jCCNode rightOperandNode = new jCCNode();

            rightOperandNode.setVariableName(((SimpleName) rightOperand).getIdentifier());
            rightOperandNode.setNode(node);
            rightOperandNode.setStartPosition(rightOperand.getStartPosition());

            jCCNodeList.add(rightOperandNode);
        }

        for(int i = 0; i < extendedOperandList.size(); i++) {
            if(extendedOperandList.get(i) instanceof SimpleName) {
                SimpleName astNode = (SimpleName) extendedOperandList.get(i);
                jCCNode extendedOperandNode = new jCCNode();

                extendedOperandNode.setVariableName(astNode.getIdentifier());
                extendedOperandNode.setNode(node);
                extendedOperandNode.setStartPosition(astNode.getStartPosition());

                jCCNodeList.add(extendedOperandNode);
            }
        }

        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) {
        Expression methodInstance = node.getExpression();
        List argumentList = node.arguments();

        if(methodInstance instanceof SimpleName) { // method를 실행시키는 instance
            jCCNode methodInstanceNode = new jCCNode();

            methodInstanceNode.setVariableName(((SimpleName) methodInstance).getIdentifier());
            methodInstanceNode.setNode(node);
            methodInstanceNode.setStartPosition(methodInstance.getStartPosition());

            jCCNodeList.add(methodInstanceNode);
        }

        for(int i = 0; i < argumentList.size(); i++) {
            if(argumentList.get(i) instanceof SimpleName) {
                jCCNode argumentNode = new jCCNode();
                SimpleName simpleName = (SimpleName) argumentList.get(i);

                argumentNode.setVariableName(simpleName.getIdentifier());
                argumentNode.setNode(node);
                argumentNode.setStartPosition(simpleName.getStartPosition());

                jCCNodeList.add(argumentNode);
            }
        }

        return super.visit(node);
    }

    @Override
    public boolean visit(ReturnStatement node) {
        Expression returnExpression = node.getExpression();

        if(returnExpression instanceof SimpleName) {
            jCCNode returnNode = new jCCNode();

            returnNode.setVariableName(((SimpleName) returnExpression).getIdentifier());
            returnNode.setNode(node);
            returnNode.setStartPosition(returnExpression.getStartPosition());

            jCCNodeList.add(returnNode);
        }
        return super.visit(node);
    }

    @Override
    public boolean visit(SingleVariableDeclaration node) { // method argument, catch block, enhanced for loop 등에서 사용됨

        return super.visit(node);
    }

    @Override
    public boolean visit(SuperConstructorInvocation node) {

        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationFragment node) {
        SimpleName variableName = node.getName();
        Expression initializeExpression = node.getInitializer();


        if(initializeExpression instanceof SimpleName) {
            jCCNode initialNode = new jCCNode();


            initialNode.setVariableName(((SimpleName) initializeExpression).getIdentifier());
            initialNode.setNode(node);
            initialNode.setStartPosition(initializeExpression.getStartPosition());

            jCCNodeList.add(initialNode);
        }

        jCCNode variableNode = new jCCNode();
        variableNode.setVariableName(((SimpleName) variableName).getIdentifier());
        variableNode.setNode(node);
        variableNode.setStartPosition(variableName.getStartPosition());

        jCCNodeList.add(variableNode);

        return super.visit(node);
    }

    @Override
    public boolean visit(LambdaExpression node) {

        return super.visit(node);
    }


    public void handleExpression(Expression expression, ASTNode astNode) {
        if(expression == null) {
            return;
        }

        if(expression instanceof SimpleName) {

        }
    }

    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

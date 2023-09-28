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

            jCCNodeList.add(arrayNode);
        }

        if(indexExpression instanceof SimpleName) {
            jCCNode indexNode = new jCCNode();

            indexNode.setVariableName(((SimpleName) arrayExpression).getIdentifier());

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

            jCCNodeList.add(leftNode);
        }

        if(rightOperand instanceof SimpleName) {
            jCCNode rightNode = new jCCNode();

            rightNode.setVariableName(((SimpleName) rightOperand).getIdentifier());

            jCCNodeList.add(rightNode);
        }
        return super.visit(node);
    }

    @Override
    public boolean visit(ClassInstanceCreation node) { // class를 instaance로 나타내는 부분 / new keyword 이후에 열거되는 우변에 해당하는 node
        List argumentList = node.arguments();

        for(int i = 0; i < argumentList.size(); i++) {
            jCCNode argumentNode = new jCCNode();

            argumentNode.setVariableName(argumentList.get(i).toString());

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

            jCCNodeList.add(conditionNode);
        }

        if(trueExpression instanceof SimpleName) {
            jCCNode trueNode = new jCCNode();

            trueNode.setVariableName(((SimpleName) trueExpression).getIdentifier());

            jCCNodeList.add(trueNode);
        }

        if(falseExpression instanceof SimpleName) {
            jCCNode falseNode = new jCCNode();

            falseNode.setVariableName(((SimpleName) falseExpression).getIdentifier());

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
        Expression filedExpression = node.getExpression();
        Expression fieldName = node.getName();
        System.out.println("@@@@");

        if(filedExpression instanceof SimpleName) {
            jCCNode fieldExpressionNode = new jCCNode();

            fieldExpressionNode.setVariableName(((SimpleName) filedExpression).getIdentifier());

            jCCNodeList.add(fieldExpressionNode);
        }

        if(fieldName instanceof SimpleName) {
            jCCNode fieldNameNode = new jCCNode();

            fieldNameNode.setVariableName(((SimpleName) fieldName).getIdentifier());

            jCCNodeList.add(fieldNameNode);
        }


        return super.visit(node);
    }

    @Override
    public boolean visit(InfixExpression node) {
        Expression leftOperand = node.getLeftOperand();
        Expression rightOperand = node.getRightOperand();
        List extendedOperandList = node.extendedOperands();

        if(leftOperand instanceof SimpleName) {
            jCCNode leftOperandNode = new jCCNode();

            leftOperandNode.setVariableName(((SimpleName) leftOperand).getIdentifier());

            jCCNodeList.add(leftOperandNode);
        }

        if(rightOperand instanceof SimpleName) {
            jCCNode rightOperandNode = new jCCNode();

            rightOperandNode.setVariableName(((SimpleName) rightOperand).getIdentifier());

            jCCNodeList.add(rightOperandNode);
        }

        for(int i = 0; i < extendedOperandList.size(); i++) {
            if(extendedOperandList.get(i) instanceof SimpleName) {
                jCCNode extendedOperandNode = new jCCNode();

                extendedOperandNode.setVariableName(((SimpleName) extendedOperandList.get(i)).getIdentifier());

                jCCNodeList.add(extendedOperandNode);
            }
        }

        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) {
        Expression methodInstance = node.getExpression();
        SimpleName methodName = node.getName();
        List argumentList = node.arguments();

        if(methodInstance instanceof SimpleName) {
            jCCNode methodInstanceNode = new jCCNode();

            methodInstanceNode.setVariableName(((SimpleName) methodInstance).getIdentifier());

            jCCNodeList.add(methodInstanceNode);
        }

        jCCNode methodNameNode = new jCCNode();
        methodNameNode.setVariableName(((SimpleName) methodName).getIdentifier());
        jCCNodeList.add(methodNameNode);

        for(int i = 0; i < argumentList.size(); i++) {
            if(argumentList.get(i) instanceof SimpleName) {
                jCCNode argumentNode = new jCCNode();

                argumentNode.setVariableName(((SimpleName) argumentList.get(i)).getIdentifier());

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

            jCCNodeList.add(initialNode);
        }

        jCCNode variableNode = new jCCNode();
        variableNode.setVariableName(((SimpleName) variableName).getIdentifier());
        jCCNodeList.add(variableNode);

        return super.visit(node);
    }

    @Override
    public boolean visit(LambdaExpression node) {

        return super.visit(node);
    }


    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

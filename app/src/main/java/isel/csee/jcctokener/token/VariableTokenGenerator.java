package isel.csee.jcctokener.token;

import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;

// Assignment node 방문 시에 문제 발생 / InfixExpression 인식 오류
// 여러 개의 항을 가지는 InfixExpression node 방문 시에 처리하는 method를 하나 더 추가해서 재귀적으로 구현
// 하나는 Infix인 경우에 2개로 나누는 method이고, 다른 하나는 semantic vector 처리해주는 용도로 구현


public class VariableTokenGenerator extends ASTVisitor {
    private List<jCCNode> jCCNodeList;


    @Override
    public boolean visit(VariableDeclarationFragment node) { // 선언과 동시에 초기화 / 선언과 동시에 초기화를 진행하기 때문에 변수의 semantic vector는 고려하지 않아도 된다
        // 변수를 할당해주는 부분의 semantic vector만 고려해서 계산
        Expression rightHand = node.getInitializer();
        SimpleName leftHand = node.getName();

        SimpleName variableName = node.getName();
        int[] structureVector = new int[25];

        ASTNode tempNode = node;
        jCCNode targetNode = new jCCNode();

        while(tempNode != null) {
            structureVector = NodeType.searchType(tempNode, structureVector);

            if(tempNode instanceof MethodDeclaration) {
                // method declaration case
                targetNode.setMethodName(((MethodDeclaration) tempNode).getName().toString());
            }

            if(tempNode instanceof TypeDeclaration) {
                // class declaration case
                targetNode.setClassName(((TypeDeclaration) tempNode).getName().toString());
            }
            tempNode = tempNode.getParent();
        } // structure vector 생성
        targetNode.setVariableName(leftHand.toString());
        targetNode.setStructureVector(structureVector);
        // 변수의 선언 부분에서 왼쪽 변수에 해당하는 node 생성

        int[] semanticVector = getTargetNodeSemanticVector(targetNode);

        if(rightHand instanceof InfixExpression) {
            InfixExpression infixExpression = (InfixExpression) rightHand;
            semanticVector = processInfixExpression(infixExpression, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());

            updateSemanticVector(targetNode, semanticVector);

        } else if(rightHand instanceof MethodInvocation) {
            MethodInvocation methodInvocation = (MethodInvocation) rightHand;
            semanticVector = processMethodInvocation(methodInvocation, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());

            updateSemanticVector(targetNode, semanticVector);

        } else if(rightHand instanceof SimpleName) {
            SimpleName simpleName = (SimpleName) rightHand;
            semanticVector = processSimpleName(simpleName, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());

            updateSemanticVector(targetNode, semanticVector);
        }
        targetNode.setSemanticVector(semanticVector);

        applyToOtherNodes(targetNode);

        return super.visit(node);
    }

    @Override
    public boolean visit(Assignment node) { // 선언 한 이후에 따로 초기화
        Expression rightHand = node.getRightHandSide();
        Expression leftHand = node.getLeftHandSide();
        int[] structureVector = new int[25];



        ASTNode tempNode = node;
        jCCNode targetNode = new jCCNode();

        while(tempNode != null) {
            structureVector = NodeType.searchType(tempNode, structureVector);

            if(tempNode instanceof MethodDeclaration) {
                // method declaration case
                targetNode.setMethodName(((MethodDeclaration) tempNode).getName().toString());
            }

            if(tempNode instanceof TypeDeclaration) {
                // class declaration case
                targetNode.setClassName(((TypeDeclaration) tempNode).getName().toString());
            }
            tempNode = tempNode.getParent();
        } // structure vector 생성
        targetNode.setVariableName(leftHand.toString());
        targetNode.setStructureVector(structureVector);
        // left 부분이 target node

        int[] semanticVector = getTargetNodeSemanticVector(targetNode);

        if(rightHand instanceof InfixExpression) {
            InfixExpression infixExpression = (InfixExpression) rightHand;
            semanticVector = processInfixExpression(infixExpression, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());

            updateSemanticVector(targetNode, semanticVector);
        } else if(rightHand instanceof MethodInvocation) {
            MethodInvocation methodInvocation = (MethodInvocation) rightHand;
            semanticVector = processMethodInvocation(methodInvocation, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());
            updateSemanticVector(targetNode, semanticVector);

        } else if(rightHand instanceof SimpleName) {
            SimpleName simpleName = (SimpleName) rightHand;
            semanticVector = processSimpleName(simpleName, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());
            updateSemanticVector(targetNode, semanticVector);
        }
        targetNode.setSemanticVector(semanticVector);
        applyToOtherNodes(targetNode);


        return super.visit(node);
    }

    public int[] processSimpleName(SimpleName node, String className, String methodName, int[] structureVector) {
        int[] semanticVector = new int[25];
        jCCNode targetNode = new jCCNode(className, methodName, node.getIdentifier().toString(), structureVector);

        semanticVector = addSemanticVector(targetNode, semanticVector);

        return semanticVector;
    }

    public int[] processMethodInvocation(MethodInvocation node, String className, String methodName, int[] structureVector) {
        List<Expression> argumentList = node.arguments();
        Expression instanceName = node.getExpression();
        int[] semanticVector = new int[25];

        jCCNode instanceNode = new jCCNode(className, methodName, instanceName.toString(), structureVector);

        semanticVector = addSemanticVector(instanceNode, semanticVector);

        // argument list에 대한 semantic vector 다 추가

        for(int i = 0; i < argumentList.size(); i++) {
            String argumentName = argumentList.get(i).toString();
            jCCNode argumentNode = new jCCNode(className, methodName, argumentName, structureVector);

            semanticVector = addSemanticVector(argumentNode, semanticVector);
        }

        return semanticVector;
    }

    public int[] processInfixExpression(InfixExpression node, String className, String methodName, int[] structureVector) {
        Expression leftHands = node.getLeftOperand();
        Expression rightHands = node.getRightOperand();
        System.out.println(leftHands + " " + rightHands);
        int[] semanticVector = new int[25];

        if(leftHands instanceof SimpleName) { // 해당 값의 semantic vector를 가지고 와야함
            jCCNode tempNode = new jCCNode(className, methodName, ((SimpleName) leftHands).getIdentifier(), structureVector);
            System.out.println(leftHands + " is simple name");
            semanticVector = addSemanticVector(tempNode, semanticVector);
        }

        if(rightHands instanceof SimpleName) {
            jCCNode tempNode = new jCCNode(className, methodName, ((SimpleName) rightHands).getIdentifier(), structureVector);

            semanticVector = addSemanticVector(tempNode, semanticVector);

        } else if(rightHands instanceof InfixExpression) {
            int[] tempArray = processInfixExpression((InfixExpression) rightHands, className, methodName, structureVector);


            for(int i = 0; i < 25; i++) {
                semanticVector[i] += tempArray[i];
            }
        }

        return semanticVector;
    }

    public int[] extractSemanticVector(Expression expression, String className, String methodName, int[] structureVector) {


        if(expression instanceof SimpleName) {
            jCCNode tempNode = new jCCNode(className, methodName, ((SimpleName) expression).getIdentifier(), structureVector);
        } else if(expression instanceof InfixExpression) {

        }



        return null;
    }

    public int[] getTargetNodeSemanticVector(jCCNode targetNode) {
        int[] semanticVector = new int[25];

        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getClassName().equals(targetNode.getClassName())) { // 클래스 이름
                if (jCCNodeList.get(i).getMethodName().equals(targetNode.getMethodName())) { // 메소드 이름
                    if (jCCNodeList.get(i).getVariableName().equals(targetNode.getVariableName())) { // 변수 이름
                        if (Arrays.equals(jCCNodeList.get(i).getStructureVector(), targetNode.getStructureVector())) {
                            semanticVector = jCCNodeList.get(i).getSemanticVector();
                            break;
                        }
                    }
                }
            }
        }
        return semanticVector;
    }

    public void updateSemanticVector(jCCNode node, int[] semanticVector) { // 리스트에서 해당 노드를 찾고, semantic vector 수정해주는 method

        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getClassName().equals(node.getClassName())) { // 클래스 이름
                if (jCCNodeList.get(i).getMethodName().equals(node.getMethodName())) { // 메소드 이름
                    if (jCCNodeList.get(i).getVariableName().equals(node.getVariableName())) { // 변수 이름
                        if (Arrays.equals(jCCNodeList.get(i).getStructureVector(), node.getStructureVector())) {
                            jCCNodeList.get(i).setSemanticVector(semanticVector);
                            break;
                        }
                    }
                }
            }
        }
    }

    public int[] addSemanticVector(jCCNode targetNode, int[] semanticVector) {
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getClassName().equals(targetNode.getClassName())) { // 클래스 이름
                if (jCCNodeList.get(i).getMethodName().equals(targetNode.getMethodName())) { // 메소드 이름
                    if (jCCNodeList.get(i).getVariableName().equals(targetNode.getVariableName())) { // 변수 이름
                        if (Arrays.equals(jCCNodeList.get(i).getStructureVector(), targetNode.getStructureVector())) {
                            for(int k = 0; k < 25; k++) {
                                semanticVector[k] += jCCNodeList.get(i).getSemanticVector()[k];
                            }
                            break;
                        }
                    }
                }
            }
        }

        return semanticVector;
    }

    public void applyToOtherNodes(jCCNode node) {
        int index = findNodeIndex(node);

        for(int i = index; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getClassName().equals(node.getClassName())) { // 클래스 이름
                if (jCCNodeList.get(i).getMethodName().equals(node.getMethodName())) { // 메소드 이름
                    if (jCCNodeList.get(i).getVariableName().equals(node.getVariableName())) { // 변수 이름
                        if(jCCNodeList.get(i).getNodeType().equals("Assignment")) {
                            break;
                        } else {
                            jCCNodeList.get(i).setSemanticVector(node.getSemanticVector());
                        }
                    }
                }
            }
        }
    }

    public int findNodeIndex(jCCNode node) {
        int index = 0;

        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getClassName().equals(node.getClassName())) { // 클래스 이름
                if (jCCNodeList.get(i).getMethodName().equals(node.getMethodName())) { // 메소드 이름
                    if (jCCNodeList.get(i).getVariableName().equals(node.getVariableName())) { // 변수 이름
                        if (Arrays.equals(jCCNodeList.get(i).getStructureVector(), node.getStructureVector())) {
                            index = i;
                            break;
                        }
                    }
                }
            }
        }

        return index;
    }

    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

    public VariableTokenGenerator(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

}

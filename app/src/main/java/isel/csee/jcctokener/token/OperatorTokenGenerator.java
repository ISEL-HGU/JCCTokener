package isel.csee.jcctokener.token;

import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;

import java.util.Arrays;
import java.util.List;

public class OperatorTokenGenerator extends ASTVisitor {
    private List<jCCNode> jCCNodeList;

    @Override
    public boolean visit(InfixExpression node) {
        String variableName = node.getOperator().toString();
        int[] structureVector = new int[25];

        ASTNode tempNode = node;

        jCCNode targetOperator = new jCCNode();

        while(tempNode != null) {
            structureVector = NodeType.searchType(tempNode, structureVector);

            if(tempNode instanceof MethodDeclaration) {
                // method declaration case
                targetOperator.setMethodName(((MethodDeclaration) tempNode).getName().toString());
            }

            if(tempNode instanceof TypeDeclaration) {
                // class declaration case
                targetOperator.setClassName(((TypeDeclaration) tempNode).getName().toString());
            }
            tempNode = tempNode.getParent();
        } // structure vector 생성

        targetOperator.setVariableName(variableName);
        targetOperator.setStructureVector(structureVector);

        int[] semanticVector = processInfixExpression(node, targetOperator.getClassName(), targetOperator.getMethodName(), targetOperator.getStructureVector());

        updateSemanticVector(targetOperator, semanticVector);
        return super.visit(node);
    }

    public int[] processInfixExpression(InfixExpression node, String className, String methodName, int[] structureVector) {
        Expression leftHands = node.getLeftOperand();
        Expression rightHands = node.getRightOperand();

        int[] semanticVector = new int[25];

        if(leftHands instanceof SimpleName) { // 해당 값의 semantic vector를 가지고 와야함
            jCCNode tempNode = new jCCNode(className, methodName, ((SimpleName) leftHands).getIdentifier(), structureVector);

            semanticVector = addSemanticVector(tempNode, semanticVector);
        }

        if(rightHands instanceof SimpleName) {
            jCCNode tempNode = new jCCNode(className, methodName, ((SimpleName) rightHands).getIdentifier(),structureVector);

            semanticVector = addSemanticVector(tempNode, semanticVector);

        } else if(rightHands instanceof InfixExpression) {
            int[] tempArray = new int[25];
            tempArray = processInfixExpression((InfixExpression) rightHands, className, methodName, structureVector);

            for(int i = 0; i < 25; i++) {
                semanticVector[i] += tempArray[i];
            }
        }

        return semanticVector;
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

    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

    public OperatorTokenGenerator(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

package isel.csee.jcctokener.token;

import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;

// 파싱은 차례대로 진행 -> 정렬되지 않은 리스트에서 데이터를 찾을 때, 해당 데이터를 만나면 종료하는 로직으로 구현
// 그게 가장 효율적인 방법인지 / 이름을 가지고 분류 / HashMap 사용(?)
// 차례대로 가져오는 것으로 생각
// structure vector 구한 다음 vector-vector 동일(?) / 동일한 지점에 도착하면 반복문 종료 그 전까지 반복문을 사용해서 모든 변수 다 더해서 가져오기 / 재귀적으로 구현해서 우변의 값들도 다 가져올 수 있도록 구현
// AST Visitor 하나 더 생성해서 차례대로 돌린다고 생각..?
// semantic vector를 쭉 더해준다고 생각
// 1번 케이스에서 semantic vector를 어떤 케이스마다 만들어서 넣어 줄 것인 지에 대해서도 생각
// 처음 선언하는 부분에서 semantic vector 부분에 structure vector 넣어주기 (vector 초기화의 개념)
// 전체적인 프로세스는 이전에 선언 되어있는 같은 이름의 변수가 가지는 semantic vector를 가지고 와서 더해주는 개념
// 좌변의 값은 제일 위 쪽 method에서 사용
// 식의 left 부분의 semantic vector 추가해주기


public class VariableTokenGenerator extends ASTVisitor {
    private List<jCCNode> jCCNodeList;


    @Override
    public boolean visit(VariableDeclarationFragment node) { // 선언과 동시에 초기화 / 선언과 동시에 초기화를 진행하기 때문에 변수의 semantic vector는 고려하지 않아도 된다
        // 변수를 할당해주는 부분의 semantic vector만 고려해서 계산
        Expression rightHand = node.getInitializer();

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
        targetNode.setVariableName(node.getName().toString());
        targetNode.setStructureVector(structureVector);
        // 변수의 선언 부분에서 왼쪽 변수에 해당하는 node 생성


        if(rightHand instanceof InfixExpression) {
            InfixExpression infixExpression = (InfixExpression) rightHand;
            int[] semanticVector = processInfixExpression(infixExpression, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());
            findTargetNode(targetNode, semanticVector);

        } else if(rightHand instanceof MethodInvocation) {
            MethodInvocation methodInvocation = (MethodInvocation) rightHand;
            int[] semanticVector = processMethodInvocation(methodInvocation, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());







        } else if(rightHand instanceof SimpleName) {

        }




        return super.visit(node);
    }

    @Override
    public boolean visit(Assignment node) { // 선언 한 이후에 따로 초기화

        return super.visit(node);
    }




    public void processSimpleName(SimpleName node) {

    }

    public int[] processMethodInvocation(MethodInvocation node, String className, String methodName, int[] structureVector) {
        List<Expression> argumentList = new ArrayList<>();
        Expression instanceName = node.getExpression();
        int[] semanticVector = new int[25];

        jCCNode instanceNode = new jCCNode(className, methodName, instanceName.toString(), structureVector);

        for(int i = 0; i < jCCNodeList.size(); i++) { // method를 사용하는 instance에 대한 부분
            if(instanceNode.getClassName().equals(jCCNodeList.get(i).getClassName())) {
                if(instanceNode.getMethodName().equals(jCCNodeList.get(i).getMethodName())) {
                    if(instanceNode.getVariableName().equals(jCCNodeList.get(i).getVariableName())) {
                        if(Arrays.equals(instanceNode.getStructureVector(), jCCNodeList.get(i).getStructureVector())) {
                            for(int k = 0; k < 25; k++) {
                                semanticVector[k] += jCCNodeList.get(i).getSemanticVector()[k];
                            }
                        }
                    }
                }
            }
        }

        // argument list에 대한 semantic vector 다 추가

        for(int i = 0; i < argumentList.size(); i++) {
            String temp = argumentList.get(i).toString();
            for(int k = 0; k < jCCNodeList.size(); k++) {
                if(className.equals(jCCNodeList.get(k).getClassName())) {
                    if(methodName.equals(jCCNodeList.get(k).getMethodName())) {
                        if(temp.equals(jCCNodeList.get(k).getVariableName())) {
                            if(Arrays.equals(structureVector, jCCNodeList.get(k).getStructureVector())) {
                                for(int m = 0; m < 25; m++) {
                                    semanticVector[m] += jCCNodeList.get(k).getSemanticVector()[m];
                                }
                            }
                        }
                    }
                }
            }
        }

        return semanticVector;
    }

    public int[] processInfixExpression(InfixExpression node, String className, String methodName, int[] structureVector) {
        Expression leftHands = node.getLeftOperand();
        Expression rightHands = node.getRightOperand();

        int[] semanticVector = new int[25];

        if(leftHands instanceof SimpleName) { // 해당 값의 semantic vector를 가지고 와야함
            jCCNode tempNode = new jCCNode(className, methodName, ((SimpleName) leftHands).getIdentifier(), structureVector);



            for(int i = 0; i < jCCNodeList.size(); i++) {
                if(tempNode.getClassName().equals(jCCNodeList.get(i).getClassName())) {
                    if(tempNode.getMethodName().equals(jCCNodeList.get(i).getMethodName())) {
                        if(tempNode.getVariableName().equals(jCCNodeList.get(i).getVariableName())) {
                            if(Arrays.equals(tempNode.getStructureVector(), jCCNodeList.get(i).getStructureVector())) {
                                for(int k = 0; k < 25; k++) {
                                    semanticVector[k] += jCCNodeList.get(i).getSemanticVector()[k];
                                }
                            }
                        }
                    }
                }
            }
        }

        if(rightHands instanceof SimpleName) {
            jCCNode tempNode = new jCCNode(className, methodName, ((SimpleName) rightHands).getIdentifier(),structureVector);


            for(int i = 0; i < jCCNodeList.size(); i++) {
                if(tempNode.getClassName().equals(jCCNodeList.get(i).getClassName())) {
                    if(tempNode.getMethodName().equals(jCCNodeList.get(i).getMethodName())) {
                        if(tempNode.getVariableName().equals(jCCNodeList.get(i).getVariableName())) {
                            if(Arrays.equals(tempNode.getStructureVector(), jCCNodeList.get(i).getStructureVector())) {
                                for(int k = 0; k < 25; k++) {
                                    semanticVector[k] += jCCNodeList.get(i).getSemanticVector()[k];
                                }
                            }
                        }
                    }
                }
            }

        } else if(rightHands instanceof InfixExpression) {
            int[] tempArray = new int[25];
            tempArray = processInfixExpression((InfixExpression) rightHands, className, methodName, structureVector);

            for(int i = 0; i < 25; i++) {
                semanticVector[i] += tempArray[i];
            }
        }

        return semanticVector;
    }

    public int[] createSemanticVector(jCCNode targetNode) {
        // target node와 동일한 클래스, 메소드, 이름을 가지는 노드 찾기
        int[] semanticVector = new int[25];

        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getClassName().equals(targetNode.getClassName())) { // 클래스 이름
                if(jCCNodeList.get(i).getMethodName().equals(targetNode.getMethodName())) { // 메소드 이름
                    if(jCCNodeList.get(i).getVariableName().equals(targetNode.getVariableName())) { // 변수 이름
                        if(Arrays.equals(jCCNodeList.get(i).getStructureVector(), targetNode.getStructureVector())) {
                            // structure vector까지 동일하면 break
                            break;
                        }
                        for(int k = 0; k < 25; k++) {
                            semanticVector[k] += jCCNodeList.get(i).getSemanticVector()[k];
                        }
                    }
                }
            }
        }

        return semanticVector;
    }

    public void findTargetNode(jCCNode node, int[] semanticVector) { // 리스트에서 해당 노드를 찾고, semantic vector 수정해주는 method

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

    public VariableTokenGenerator(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

}

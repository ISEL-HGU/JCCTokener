package isel.csee.jcctokener.token;

import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;

// 파싱은 차례대로 진행 -> 정렬되지 않은 리스트에서 데이터를 찾을 때, 해당 데이터를 만나면 종료하는 로직으로 구현
// structure vector 구한 다음 vector-vector 동일(?) / 동일한 지점에 도착하면 반복문 종료 그 전까지 반복문을 사용해서 모든 변수 다 더해서 가져오기 / 재귀적으로 구현해서 우변의 값들도 다 가져올 수 있도록 구현
// AST Visitor 하나 더 생성해서 차례대로 돌린다고 생각..?
// semantic vector를 쭉 더해준다고 생각
// 1번 케이스에서 semantic vector를 어떤 케이스마다 만들어서 넣어 줄 것인 지에 대해서도 생각
// 처음 선언하는 부분에서 semantic vector 부분에 structure vector 넣어주기 (vector 초기화의 개념)
// 전체적인 프로세스는 이전에 선언 되어있는 같은 이름의 변수가 가지는 semantic vector를 가지고 와서 더해주는 개념
// 좌변의 값은 제일 위 쪽 method에서 사용

// type 1의 semantic vector는 이 class 내부적으로만 추가해줘야 함 / 만약에 a의 semantic vector를 만들어 주고, 그 다음 a를 사용하는 경우에는 사용되는 a의 semantic vector는 이전의 semantic vector와 같은 구조를 가져야 함
// 결론적으로 모든 node는 semantic vector를 가져야 하는데, 여기서 이 class에서 다루지 않는 부분은 이전에 사용했던 semantic vector와 같은 구조를 가지고 있어야 함
// method invocation의 경우에서는 argument로 사용된 변수는 semantic vector가 변하지 않아야 함
// 모든 노드에 대해서 처음 선언 부분에서 semantic vector를 structure vector로 초기화 해주는 것이 옳은 방법인지 판단 필요
// semantic vector를 더해주는 방법은 괜찮아 보이는데 여기서 어떻게 semantic vector를 업데이트 해줄 것인지 -> 이 부분이 중요하다고 판단
// 반복문을 사용해서 이후에 값의 structure vector가 비어있다면, 이전의 값을 찾아서 대입해주는 방식? - 시간이 오래 걸릴 듯
// 선언 및 할당 해주는 부분이 아닌 다른 부분에서는 이전에 사용했던 semantic vector값을 그대로 사용해야 됨

// Assignment node 부분은 여러가지 case 분리해서 생각해야 됨 / arr[i]인 경우에는 어디에 값을 추가해줄 것인지?
//


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
        targetNode.setVariableName(node.getName().toString());
        targetNode.setStructureVector(structureVector);
        // 변수의 선언 부분에서 왼쪽 변수에 해당하는 node 생성

        if(rightHand instanceof InfixExpression) {
            InfixExpression infixExpression = (InfixExpression) rightHand;
            int[] semanticVector = processInfixExpression(infixExpression, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());
            semanticVector = addSemanticVector(targetNode, semanticVector);
            updateSemanticVector(targetNode, semanticVector);

        } else if(rightHand instanceof MethodInvocation) {
            MethodInvocation methodInvocation = (MethodInvocation) rightHand;
            int[] semanticVector = processMethodInvocation(methodInvocation, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());
            semanticVector = addSemanticVector(targetNode, semanticVector);
            updateSemanticVector(targetNode, semanticVector);

        } else if(rightHand instanceof SimpleName) {
            SimpleName simpleName = (SimpleName) rightHand;
            int[] semanticVector = processSimpleName(simpleName, targetNode.getClassName(), targetNode.getMethodName(), targetNode.getStructureVector());
            semanticVector = addSemanticVector(targetNode, semanticVector);
            updateSemanticVector(targetNode, semanticVector);
        }
        return super.visit(node);
    }

    @Override
    public boolean visit(Assignment node) { // 선언 한 이후에 따로 초기화

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

    public VariableTokenGenerator(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

}

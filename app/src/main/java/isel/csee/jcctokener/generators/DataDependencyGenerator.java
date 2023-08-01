package isel.csee.jcctokener.generators;

import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.checkerframework.checker.units.qual.A;
import org.eclipse.jdt.core.dom.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
이 노드가 이 노드가 맞는 지 여부는 어떻게 확인하지? -> startPosition 사용해서 확인하기!

이 클래스가 수행해야 되는 업무는 DataDependency 측면에서 관련된 값들을 묶어주는 리스트를 각 노드마다 생성해내는 것 + 각 노드 별로 Assignment, VariableDeclarationFragment node가 아닐 경우에는 이전의 값을 업데이트 해주는 방식
전체에 걸쳐서 업데이트를 해주고, Assignment node 방문 시에, 다시 처음부터 끝까지 업데이트 해주는 방식으로 사용하면 될 듯

Operator / MethodInvocation 이 2가지 case에 대해서도 edge를 만들어 줘야 함 -> Method 이름에 대해서
Operator는 structureVector도 비교해서 동일한지 여부 파악해야함 -> InfixExpression 내부에서 여러 개의 Operator가 존재할 경우에, 같은 Position을 가지게 되는데,

getArray는 array의 이름, getIndex는 array에서 사용된 index 변수 이름

Case의 분리는 5가지로 -> 1. Number 2. SimpleName 3. InfixExpression 4. ArrayAccess 5. MethodInvocation 6. ClassInstanceCreation

노드의 semantic type을 어떻게 정해줄 것인가?
    -> update 부분에도 넣어줘서 type을 다 update 해줘야 함

SimpleName node를 가지고 오기 때문에 Assignment node에서 operator도 싹 다 가지고 와지는데, 이 operator들도 semantic vector를 만들어 줘야 하는지?

그리고 좌변에 들어갈 수 있는 값에는 어떤 것들이 있지? -> 1. SimpleName 2. ArrayAccess 3.

Expression의 형태로 전달 해줘야지 깨지지 않고 제대로 전달이 된다

이거 왜 +만 있으면 못 가지고 오고 -가 존재하면 가지고 와지는 거지? / 같은 연산자가 여러개 있는 case는 가지고 오지를 못하네
처음에 파싱해서 노드를 추가해줄 때 하나만 추가를 하게 해놔서 그렇게 되는 듯

근데 처음에 파싱할 때 operator에서 양 옆 value를 구분할 수가 없을텐데 .. getStartPosition을 operator 기준에서 해줄 수가 있으려나

아 그럼 처음에 operator의 경우에는 양 옆 value를 추가해줄 수 있나?

Assignment node에서 operator를 따로 가지고 와서 사용해야 하려나?

parent node를 사용하는 것이 맞나? / 그냥 제일 처음 파싱을 진행할 때 SimpleName node로만 파싱하는 게 아니라 SimpleName 말고 모든 visit method에서 가져올 수 있는
값들을 다 가져와서 파싱을 진행해야 하나?
 */

public class DataDependencyGenerator {
    private List<jCCNode> jCCNodeList;
    private final int type1 = 1;
    private final int type2 = 2;
    private final int type3 = 3;

    public void generateDataDependency() {
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getNode() instanceof InfixExpression) { // operator

            } else if(jCCNodeList.get(i).getNode() instanceof Assignment) { // operator and left variable
                Assignment node = (Assignment) jCCNodeList.get(i).getNode();

                if(node.getLeftHandSide().toString().equals(jCCNodeList.get(i).getVariableName())) { // i번째 노드가 left hand

                }

            } else if(jCCNodeList.get(i).getNode() instanceof MethodInvocation) { // method
                MethodInvocation node = (MethodInvocation) jCCNodeList.get(i).getNode();

                if(node.getName().equals(jCCNodeList.get(i).getVariableName())) { // method callee 부분
                    int index = findTargetNode(jCCNodeList.get(i).getStartPosition(), jCCNodeList.get(i).getVariableName());
                    List<Integer> edgeList = null;
                    edgeList = processMethodInvocation(node, edgeList);

                    jCCNodeList.get(i).setIndexListOfEdges(edgeList);
                    updateRelatedNodeList(jCCNodeList.get(i), index, type3);
                }

            } else if(jCCNodeList.get(i).getNode() instanceof VariableDeclarationFragment) { // variable
                VariableDeclarationFragment node = (VariableDeclarationFragment) jCCNodeList.get(i).getNode();
            }
        }
    }

    public void updateRelatedNodeList(jCCNode node, int targetIndex, int semanticType) { // 해당 노드 다음에 나오는 같은 이름의 노드들을 다 업데이트 해주는 노드
        for(int i = targetIndex + 1; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getVariableName().equals(node.getVariableName())) { // 이름이 동일
                if(jCCNodeList.get(i).getMethodName().equals(node.getMethodName())) {
                    jCCNodeList.get(i).setIndexListOfEdges(node.getIndexListOfEdges());
                    jCCNodeList.get(i).setSemanticType(semanticType);
                }
            }
        }
    }

    public int findTargetNode(int startPosition, String variableName) { // 해당 노드가 list 안에서 어디에 위치해 있는지 index 반환
        int index = 0;

        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getStartPosition() == startPosition) {
                if(jCCNodeList.get(i).getVariableName().equals(variableName)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public int findTargetNodeWithStructureVector(int startPosition, String variableName, int[] structureVector) {
        int index = 0;

        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getStartPosition() == startPosition) {
                if(jCCNodeList.get(i).getVariableName().equals(variableName)) {
                    if(Arrays.equals(jCCNodeList.get(i).getStructureVector(), structureVector)) {
                        index = i;
                        break;
                    }
                }
            }
        }

        return index;
    }

    public List<Integer> processInfixExpression(InfixExpression node, List<Integer> edgeList) { // 좌변과 우변의 값이 SimpleName node가 아닐 수도 있기 때문에 Case의 분리가 필요
        System.out.println("node: " + node);
        System.out.println("temp : " + node.getLeftOperand());
        Expression temp = node.getRightOperand();
        System.out.println(temp);


        if(node.getRightOperand() instanceof SimpleName) {
            edgeList = processSimpleName((SimpleName) node.getRightOperand(), edgeList);
        } else if(node.getRightOperand() instanceof ArrayAccess) {
            edgeList = processArrayAccess((ArrayAccess) node.getRightOperand(), edgeList);
        } else if(node.getRightOperand() instanceof MethodInvocation) {
            edgeList = processMethodInvocation((MethodInvocation) node.getRightOperand(), edgeList);
        } else if(node.getRightOperand() instanceof ClassInstanceCreation) {
            edgeList = processClassInstanceCreation((ClassInstanceCreation) node.getLeftOperand(), edgeList);
        } if(node.getRightOperand() instanceof InfixExpression) {
            edgeList = processInfixExpression((InfixExpression) node.getRightOperand(), edgeList);
        }

        if(node.getLeftOperand() instanceof InfixExpression) {
            edgeList = processInfixExpression((InfixExpression) node.getLeftOperand(), edgeList);
            System.out.println(node.getLeftOperand());
        } else if(node.getLeftOperand() instanceof SimpleName) {
            edgeList = processSimpleName((SimpleName) node.getLeftOperand(), edgeList);
        } else if(node.getLeftOperand() instanceof ArrayAccess) {
            edgeList = processArrayAccess((ArrayAccess) node.getLeftOperand(), edgeList);
        } else if(node.getLeftOperand() instanceof MethodInvocation) {
            edgeList = processMethodInvocation((MethodInvocation) node.getLeftOperand(), edgeList);
        } else if(node.getLeftOperand() instanceof ClassInstanceCreation) {
            edgeList = processClassInstanceCreation((ClassInstanceCreation) node.getLeftOperand(), edgeList);
        }

        return edgeList;
    }

    public List<Integer> processSimpleName(SimpleName node, List<Integer> edgeList) { // 기존의 List를 업데이트 해서 제공해주는 method / SimpleName node를 edgeList에 추가해서 edgeList return
        int simpleNamePosition = node.getStartPosition();
        int simpleNameIndex = findTargetNode(simpleNamePosition, node.toString());

        edgeList.add(simpleNameIndex);

        return edgeList;
    }

    public List<Integer> processClassInstanceCreation(ClassInstanceCreation node, List<Integer> edgeList) {
        List<Expression> argumentList = node.arguments();

        for(int i = 0; i < argumentList.size(); i++) {
            if(argumentList.get(i) instanceof InfixExpression) {
                edgeList = processInfixExpression((InfixExpression) argumentList.get(i), edgeList);
            } else if(argumentList.get(i) instanceof SimpleName) {
                edgeList = processSimpleName((SimpleName) argumentList.get(i), edgeList);
            } else if(argumentList.get(i) instanceof MethodInvocation) {
                edgeList = processMethodInvocation((MethodInvocation) argumentList.get(i), edgeList);
            } else if(argumentList.get(i) instanceof ArrayAccess) {
                edgeList = processArrayAccess((ArrayAccess) argumentList.get(i), edgeList);
            } else if(argumentList.get(i) instanceof ClassInstanceCreation) {
                edgeList = processClassInstanceCreation((ClassInstanceCreation) argumentList.get(i), edgeList);
            }
        }

        return edgeList;
    }

    public List<Integer> processMethodInvocation(MethodInvocation node, List<Integer> edgeList) { // assignment node에서 사용 / type 1에서 사용되는 부분이기 때문에 method name에 대해서는 추가할 필요 X
        Expression methodInstance = node.getExpression();
        List<Expression> argumentList = node.arguments();

        edgeList.add(findTargetNode(methodInstance.getStartPosition(), methodInstance.toString()));

        for(int i = 0; i < argumentList.size(); i++) {
            if(argumentList.get(i) instanceof SimpleName) {
                edgeList = processSimpleName((SimpleName) argumentList.get(i), edgeList);
            } else if(argumentList.get(i) instanceof MethodInvocation) {
                edgeList = processMethodInvocation((MethodInvocation) argumentList.get(i), edgeList);
            } else if(argumentList.get(i) instanceof ArrayAccess) {
                edgeList = processArrayAccess((ArrayAccess) argumentList.get(i), edgeList);
            } else if(argumentList.get(i) instanceof InfixExpression) {
                edgeList = processInfixExpression((InfixExpression) argumentList.get(i), edgeList);
            } else if(argumentList.get(i) instanceof ClassInstanceCreation) {
                edgeList = processClassInstanceCreation((ClassInstanceCreation) argumentList.get(i), edgeList);
            }
        }

        return edgeList;
    }

    public List<Integer> processArrayAccess(ArrayAccess node, List<Integer> edgeList) { // array 변수에 대한 부분 + index 변수에 대한 부분
        Expression arrayVariable = node.getArray();
        Expression indexVariable = node.getIndex();

        edgeList.add(findTargetNode(arrayVariable.getStartPosition(), arrayVariable.toString()));

        if(indexVariable instanceof SimpleName) {
            edgeList = processSimpleName((SimpleName) indexVariable, edgeList);
        } else if(indexVariable instanceof ArrayAccess) {
            edgeList = processArrayAccess((ArrayAccess) indexVariable, edgeList);
        } else if(indexVariable instanceof InfixExpression) {
            edgeList = processInfixExpression((InfixExpression) indexVariable, edgeList);
        } else if(indexVariable instanceof MethodInvocation) {
            edgeList = processMethodInvocation((MethodInvocation) indexVariable, edgeList);
        } else if(indexVariable instanceof ClassInstanceCreation) {
            edgeList = processClassInstanceCreation((ClassInstanceCreation) indexVariable, edgeList);
        }

        return edgeList;
    }

    public void semanticTypeCreator(String variableName, int startPosition, int semanticType) {
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getVariableName().equals(variableName)) {
                if(jCCNodeList.get(i).getStartPosition() == startPosition) {
                    jCCNodeList.get(i).setSemanticType(semanticType);
                }
            }
        }
    }


    public DataDependencyGenerator(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

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
index는 i지 않나?

InfixExpression node에서 oeprator 뽑아오는 부분 구현 / Assignment node에서 operator 뽑아오는 부분 구현

Assignment operator의 종류에 따라서 data dependency 노드를 삭제할 수도 있고, 추가해줄 수도 있게끔 구현 수정 해줘야 함
 */

public class DataDependencyGenerator {
    private List<jCCNode> jCCNodeList;
    private final int type1 = 1;
    private final int type2 = 2;
    private final int type3 = 3;
    private final List<String> operatorType = Arrays.asList(">>>=",">>=","<<=","%=","^=","|=","&=","/=",
            "*=","-=","+=","<<","--","++","||","&&","!=",
            ">=","<=","==","%","^","|","&","/","*","-",
            "+",":","?","~","!","<",">","=","...","->","::");

    public void generateDataDependency() {
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getNode() instanceof InfixExpression) { // operator
                InfixExpression node = (InfixExpression) jCCNodeList.get(i).getNode();
                List<Integer> edgeList = new ArrayList<>(); // operator인 case를 찾아야 함

                if(operatorType.contains(jCCNodeList.get(i).getVariableName())) { // operator인 경우에 실행되는 if문
                    if(node.getLeftOperand() instanceof ArrayAccess) {
                        edgeList = processArrayAccess((ArrayAccess) node.getLeftOperand(), edgeList);
                    } else if(node.getLeftOperand() instanceof SimpleName) {
                        edgeList = processSimpleName((SimpleName) node.getLeftOperand(), edgeList);
                    } else if(node.getLeftOperand() instanceof MethodInvocation) {
                        edgeList = processMethodInvocation((MethodInvocation) node.getLeftOperand(), edgeList);
                    } else if(node.getLeftOperand() instanceof InfixExpression) {
                        edgeList = processInfixExpression((InfixExpression) node.getLeftOperand(), edgeList);
                    } else if(node.getLeftOperand() instanceof ClassInstanceCreation) {
                        edgeList = processClassInstanceCreation((ClassInstanceCreation) node.getLeftOperand(), edgeList);
                    }

                    if(node.getRightOperand() instanceof ArrayAccess) {
                        edgeList = processArrayAccess((ArrayAccess) node.getRightOperand(), edgeList);
                    } else if(node.getRightOperand() instanceof SimpleName) {
                        edgeList = processSimpleName((SimpleName) node.getRightOperand(), edgeList);
                    } else if(node.getRightOperand() instanceof MethodInvocation) {
                        edgeList = processMethodInvocation((MethodInvocation) node.getRightOperand(), edgeList);
                    } else if(node.getRightOperand() instanceof Assignment) {
                        edgeList = processInfixExpression((InfixExpression) node.getRightOperand(), edgeList);
                    } else if(node.getRightOperand() instanceof ClassInstanceCreation) {
                        edgeList = processClassInstanceCreation((ClassInstanceCreation) node.getRightOperand(), edgeList);
                    }

                    jCCNodeList.get(i).setIndexListOfEdges(edgeList);
                    jCCNodeList.get(i).setSemanticType(type2);
                }


            } else if(jCCNodeList.get(i).getNode() instanceof Assignment) { // operator and left variable
                Assignment node = (Assignment) jCCNodeList.get(i).getNode();
                List<Integer> edgeList = new ArrayList<>();

                if(node.getLeftHandSide().toString().equals(jCCNodeList.get(i).getVariableName())) { // i번째 노드가 left hand
                    if(node.getRightHandSide() instanceof InfixExpression) {
                        edgeList = processInfixExpression((InfixExpression) node.getRightHandSide(), edgeList);
                    } else if(node.getRightHandSide() instanceof ArrayAccess) {
                        edgeList = processArrayAccess((ArrayAccess) node.getRightHandSide(), edgeList);
                    } else if(node.getRightHandSide() instanceof MethodInvocation) {
                        edgeList = processMethodInvocation((MethodInvocation) node.getRightHandSide(), edgeList);
                    } else if(node.getRightHandSide() instanceof SimpleName) {
                        edgeList = processSimpleName((SimpleName) node.getRightHandSide(), edgeList);
                    } else if(node.getRightHandSide() instanceof ClassInstanceCreation) {
                        edgeList = processClassInstanceCreation((ClassInstanceCreation) node.getRightHandSide(), edgeList);
                    }

                    jCCNodeList.get(i).setIndexListOfEdges(edgeList);
                    jCCNodeList.get(i).setSemanticType(type1);
                    updateRelatedNodeList(jCCNodeList.get(i), i, type1);
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
                List<Integer> edgeList = new ArrayList<>();

                if(node.getName().equals(jCCNodeList.get(i).getVariableName())) {
                    if(node.getInitializer() instanceof InfixExpression) {

                    } else if(node.getInitializer() instanceof ArrayAccess) {
                        edgeList = processArrayAccess((ArrayAccess) node.getInitializer(), edgeList);
                    } else if(node.getInitializer() instanceof MethodInvocation) {
                        edgeList = processMethodInvocation((MethodInvocation) node.getInitializer(), edgeList);
                    } else if(node.getInitializer() instanceof ClassInstanceCreation) {
                        edgeList = processClassInstanceCreation((ClassInstanceCreation) node.getInitializer(), edgeList);
                    } else if(node.getInitializer() instanceof SimpleName) {
                        edgeList = processSimpleName((SimpleName) node.getInitializer(), edgeList);
                    }

                    jCCNodeList.get(i).setIndexListOfEdges(edgeList);
                    jCCNodeList.get(i).setSemanticType(type1);
                    updateRelatedNodeList(jCCNodeList.get(i), i, type1);
                }
            }
        }
    }

    public void updateRelatedNodeList(jCCNode node, int targetIndex, int semanticType) { // 해당 노드 다음에 나오는 같은 이름의 노드들을 다 업데이트 해주는 노드
        for(int i = targetIndex + 1; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getVariableName().equals(node.getVariableName())) { // 이름이 동일
                if(jCCNodeList.get(i).getMethodName().equals(node.getMethodName())) { // method 이름까지 동일해야 같은 block 내부에 존재한다고 생각하고 update
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
        Expression temp = node.getRightOperand();
        // extendedOperands() method 생각해서 다시 작성해야됨
        // InfixExpression node에서 extendedOperand를 가지고 있으면 더 이상 파싱이 안 되나? / left, right가
        // extendedOperands의 return value -> List<Expression>

        if(node.hasExtendedOperands()) { // other node를 가지고 있는 상태 -> 그냥 가지고 있으면 가지고 있는 값들을 다 가지고 와서 더해주는 개념으로 사용하면 될 듯
            for(int i = 0; i < node.extendedOperands().size(); i++) {
                if(node.extendedOperands().get(i) instanceof InfixExpression) {
                    edgeList = processInfixExpression((InfixExpression) node.extendedOperands().get(i), edgeList);
                } else if(node.extendedOperands().get(i) instanceof SimpleName) {
                    edgeList = processSimpleName((SimpleName) node.extendedOperands().get(i), edgeList);
                } else if(node.extendedOperands().get(i) instanceof ArrayAccess) {
                    edgeList = processArrayAccess((ArrayAccess) node.extendedOperands().get(i), edgeList);
                } else if(node.extendedOperands().get(i) instanceof ClassInstanceCreation) {
                    edgeList = processClassInstanceCreation((ClassInstanceCreation) node.extendedOperands().get(i), edgeList);
                } else if(node.extendedOperands().get(i) instanceof MethodInvocation) {
                    edgeList = processMethodInvocation((MethodInvocation) node.extendedOperands().get(i), edgeList);
                }
            }
        }
        if(node.getRightOperand() instanceof SimpleName) {
            edgeList = processSimpleName((SimpleName) node.getRightOperand(), edgeList);
        } else if(node.getRightOperand() instanceof ArrayAccess) {
            edgeList = processArrayAccess((ArrayAccess) node.getRightOperand(), edgeList);
        } else if(node.getRightOperand() instanceof MethodInvocation) {
            edgeList = processMethodInvocation((MethodInvocation) node.getRightOperand(), edgeList);
        } else if(node.getRightOperand() instanceof ClassInstanceCreation) {
            edgeList = processClassInstanceCreation((ClassInstanceCreation) node.getLeftOperand(), edgeList);
        } else if(node.getRightOperand() instanceof InfixExpression) {
            edgeList = processInfixExpression((InfixExpression) node.getRightOperand(), edgeList);
        }

        if(node.getLeftOperand() instanceof InfixExpression) {
            edgeList = processInfixExpression((InfixExpression) node.getLeftOperand(), edgeList);
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

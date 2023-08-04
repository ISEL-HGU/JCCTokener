package isel.csee.jcctokener.generators;

import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.checkerframework.checker.units.qual.A;
import org.eclipse.jdt.core.dom.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataDependencyGenerator {
    private List<jCCNode> jCCNodeList;
    private final int type1 = 1;
    private final int type2 = 2;
    private final int type3 = 3;
    private final List<String> operatorType = Arrays.asList(">>>=",">>=","<<=","%=","^=","|=","&=","/=",
            "*=","-=","+=","<<","--","++","||","&&","!=",
            ">=","<=","==","%","^","|","&","/","*","-",
            "+",":","?","~","!","<",">","=","...","->","::");
    private final List<String> assignmentOperator = Arrays.asList("%=", "^=", "/=", "*=", "-=", "+=");

    public void generateDataDependency() {
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getNode() instanceof InfixExpression) {
                InfixExpression node = (InfixExpression) jCCNodeList.get(i).getNode();
                List<Integer> edgeList = new ArrayList<>();

                if(node.hasExtendedOperands()) {
                    for(int k = 0; k < node.extendedOperands().size(); k++) {
                        if(node.extendedOperands().get(k) instanceof InfixExpression) {
                            edgeList = processInfixExpression((InfixExpression) node.extendedOperands().get(k), edgeList);
                        } else if(node.extendedOperands().get(k) instanceof SimpleName) {
                            edgeList = processSimpleName((SimpleName) node.extendedOperands().get(k), edgeList);
                        } else if(node.extendedOperands().get(k) instanceof ArrayAccess) {
                            edgeList = processArrayAccess((ArrayAccess) node.extendedOperands().get(k), edgeList);
                        } else if(node.extendedOperands().get(k) instanceof ClassInstanceCreation) {
                            edgeList = processClassInstanceCreation((ClassInstanceCreation) node.extendedOperands().get(k), edgeList);
                        } else if(node.extendedOperands().get(k) instanceof MethodInvocation) {
                            edgeList = processMethodInvocation((MethodInvocation) node.extendedOperands().get(k), edgeList);
                        }
                    }
                }

                if(operatorType.contains(jCCNodeList.get(i).getVariableName())) {
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
            } else if(jCCNodeList.get(i).getNode() instanceof Assignment) {
                Assignment node = (Assignment) jCCNodeList.get(i).getNode();
                List<Integer> edgeList = new ArrayList<>();

                if(assignmentOperator.contains(node.getOperator().toString())) {
                    for(int k = 0; k < jCCNodeList.get(i).getIndexListOfEdges().size(); k++) {
                        edgeList.add(jCCNodeList.get(i).getIndexListOfEdges().get(k));
                    }
                }

                if(node.getLeftHandSide().toString().equals(jCCNodeList.get(i).getVariableName())) {
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

                    int endPosition = node.getRightHandSide().getStartPosition() + node.getRightHandSide().getLength() - 1;

                    jCCNodeList.get(i).setIndexListOfEdges(edgeList);
                    jCCNodeList.get(i).setSemanticType(type1);
                    updateRelatedNodeList(jCCNodeList.get(i), i, type1, endPosition);
                }

            } else if(jCCNodeList.get(i).getNode() instanceof MethodInvocation) {
                MethodInvocation node = (MethodInvocation) jCCNodeList.get(i).getNode();

                if(node.getName().toString().equals(jCCNodeList.get(i).getVariableName())) {
                    int index = findTargetNode(jCCNodeList.get(i).getStartPosition(), jCCNodeList.get(i).getVariableName());

                    List<Integer> edgeList = new ArrayList<>();
                    edgeList = processMethodInvocation(node, edgeList);

                    jCCNodeList.get(i).setSemanticType(type3);
                    jCCNodeList.get(i).setIndexListOfEdges(edgeList);
                }

            } else if(jCCNodeList.get(i).getNode() instanceof VariableDeclarationFragment) {
                VariableDeclarationFragment node = (VariableDeclarationFragment) jCCNodeList.get(i).getNode();
                List<Integer> edgeList = new ArrayList<>();

                if(node.getInitializer() instanceof InfixExpression) {
                    edgeList = processInfixExpression((InfixExpression) node.getInitializer(), edgeList);
                } else if(node.getInitializer() instanceof ArrayAccess) {
                    edgeList = processArrayAccess((ArrayAccess) node.getInitializer(), edgeList);
                } else if(node.getInitializer() instanceof MethodInvocation) {
                    edgeList = processMethodInvocation((MethodInvocation) node.getInitializer(), edgeList);
                } else if(node.getInitializer() instanceof ClassInstanceCreation) {
                    edgeList = processClassInstanceCreation((ClassInstanceCreation) node.getInitializer(), edgeList);
                } else if(node.getInitializer() instanceof SimpleName) {
                    edgeList = processSimpleName((SimpleName) node.getInitializer(), edgeList);
                }

                int endPosition = node.getInitializer().getStartPosition() + node.getLength() - 1;

                jCCNodeList.get(i).setIndexListOfEdges(edgeList);
                jCCNodeList.get(i).setSemanticType(type1);
                updateRelatedNodeList(jCCNodeList.get(i), i, type1, endPosition);
            }
        }
    }

    public void updateRelatedNodeList(jCCNode node, int targetIndex, int semanticType, int endPosition) { // 해당 노드 다음에 나오는 같은 이름의 노드들을 다 업데이트 해주는 노드
        for(int i = targetIndex; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getVariableName().equals(node.getVariableName())) { // 이름이 동일
                if(jCCNodeList.get(i).getMethodName().equals(node.getMethodName())) { // method 이름까지 동일해야 같은 block 내부에 존재한다고 생각하고 update
                    if(jCCNodeList.get(i).getStartPosition() > endPosition) {
                        jCCNodeList.get(i).setIndexListOfEdges(node.getIndexListOfEdges());
                        jCCNodeList.get(i).setSemanticType(semanticType);
                    }
                }
            }
//            System.out.println(i + " " + node.getIndexListOfEdges());
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

    public List<Integer> processInfixExpression(InfixExpression node, List<Integer> edgeList) {
        if(node.hasExtendedOperands()) {
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

package isel.csee.jcctokener.generators;


import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;


import java.util.ArrayList;
import java.util.List;


// 의존성을 어떻게 파싱할 수 있는 지 -> Assignment node + VariableDeclarationFragment 사용
// 그 곳에 해당하는 Structure Vector 어떻게 가져올 것인지
// Semantic Vector type 3가지 모두 어떻게 파싱할 것인지
// 정확성을 높이기 위해서 코드 블럭 단위를 어떻게 설정할 것인지 / Class, Method
// getNodeArrayAtPosition

// line 기준으로 사용? 코드 내부에서 몇 번 째 라인에 존재하는 지에 대한 정보를 가지고 vector 값 가져오기?

// visit method 실행 순서는 JDT 내부적으로 구현 / 보통 구체적인 명시가 되어있는 부분 먼저 실행이 된다 - Assignment -> SimpleName

// method 내부에서 InfixExpression node 우변의 값을 하나만 가지고 오는 문제가 있는데, 이 부분 해결해야 함



public class StructureVectorGenerator extends ASTVisitor {
    private final int semanticType1 = 1;
    private final int semanticType2 = 2;
    private final int semanticType3 = 3;
    private List<int[]> structureVectorList = new ArrayList<>();
    private List<jCCNode> jCCNodeList = new ArrayList<>();


    @Override
    public boolean visit(SimpleName node) {
        int[] structureVector = new int[25];
        ASTNode tempNode = node;
        jCCNode jCCNode = new jCCNode();

        if(tempNode.getParent() instanceof QualifiedName) {
            return super.visit(node);
        }


        while(tempNode != null) {
            System.out.println(tempNode.getClass().toString());
            structureVector = NodeType.searchType(tempNode, structureVector);

            if(tempNode instanceof MethodDeclaration) {
                // method declaration case
                jCCNode.setMethodName(((MethodDeclaration) tempNode).getName().toString());
            }

            if(tempNode instanceof TypeDeclaration) {
                // class declaration case
                jCCNode.setClassName(((TypeDeclaration) tempNode).getName().toString());
            }

            tempNode = tempNode.getParent();
        }

        if(jCCNode.getMethodName() == null) {
            jCCNode.setMethodName(jCCNode.getClassName());
        }


        jCCNode.setVariableName(node.getIdentifier());
        jCCNode.setStructureVector(structureVector);
        jCCNode.setNodeType(ASTNode.nodeClassForType(node.getParent().getNodeType()).getSimpleName());



        tempNode = node;

        if(tempNode.getParent() instanceof VariableDeclarationFragment) {
            if(((VariableDeclarationFragment) tempNode.getParent()).getName() == tempNode) {
                jCCNode.setSemanticVector(structureVector);
            }
        }
        tempNode = node;

        if(tempNode.getParent() instanceof MethodInvocation) {
            jCCNode.setSemanticType(semanticType3);
        } else {
            jCCNode.setSemanticType(semanticType1);
        }
        tempNode = node;

        if(tempNode.getParent() instanceof Assignment) {
            if(node.equals(((Assignment) tempNode.getParent()).getLeftHandSide())) {
                jCCNode.setUpdatePossibility(false);
            } else {
                jCCNode.setUpdatePossibility(true);
            }
        } else {
            jCCNode.setUpdatePossibility(true);
        }

        jCCNodeList.add(jCCNode);
        structureVectorList.add(structureVector);

        tempNode = node;

        return super.visit(node);
    }

    @Override
    public boolean visit(InfixExpression node) {
        int[] structureVector = new int[25];
        jCCNode jCCNode = new jCCNode();
        Expression leftExpression = node.getLeftOperand();
        Expression expression = node.getRightOperand();

        ASTNode tempNode = node;

        while(tempNode != null) {
            structureVector = NodeType.searchType(tempNode, structureVector);

            if(tempNode instanceof MethodDeclaration) {
                // method declaration case
                jCCNode.setMethodName(((MethodDeclaration) tempNode).getName().toString());
            }
            if(tempNode instanceof TypeDeclaration) {
                // class declaration case
                jCCNode.setClassName(((TypeDeclaration) tempNode).getName().toString());
            }
            tempNode = tempNode.getParent();
        }

        jCCNode.setStructureVector(structureVector);
        jCCNode.setVariableName(node.getOperator().toString());
        jCCNode.setSemanticType(semanticType2);

        jCCNodeList.add(jCCNode);
        structureVectorList.add(structureVector);

        return super.visit(node);
    }

    @Override
    public boolean visit(Assignment node) {
        return super.visit(node);
    }

    public List<int[]> getStructureVectorList() {
        return structureVectorList;
    }

    public void setStructureVectorList(List<int[]> structureVectorList) {
        this.structureVectorList = structureVectorList;
    }

    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

package isel.csee.jcctokener.generators;


import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;


import java.util.ArrayList;
import java.util.List;

/*
의존성을 어떻게 파싱할 수 있는 지 -> Assignment node + VariableDeclarationFragment 사용
그 곳에 해당하는 Structure Vector 어떻게 가져올 것인지
Semantic Vector type 3가지 모두 어떻게 파싱할 것인지
정확성을 높이기 위해서 코드 블럭 단위를 어떻게 설정할 것인지 / Class, Method
getNodeArrayAtPosition

line 기준으로 사용? 코드 내부에서 몇 번 째 라인에 존재하는 지에 대한 정보를 가지고 vector 값 가져오기?

visit method 실행 순서는 JDT 내부적으로 구현 / 보통 구체적인 명시가 되어있는 부분 먼저 실행이 된다 - Assignment -> SimpleName

method 내부에서 InfixExpression node 우변의 값을 하나만 가지고 오는 문제가 있는데, 이 부분 해결해야 함

같은 이름이라도 data dependency가 다른 경우가 존재할 수 있음
나중에는 semantic Vector도 다 추출해야 되는데 .. 그냥 variable의 사용 부분에서는 이전의 edges를 가지고 와야 하나?

그럼 이 List 안에 존재하는 데이터의 업데이트는 어떻게 해줄 수 있을까? 전체 리스트에서 index의 정보를 각 노드에 다 넣어두고 업데이트 해주는 방식으로 하면 될 듯
변수의 이름을 사용 해야 하는가?
그냥 변수의 경우는 어떻게 해야되지?
Data Dependency는 VariableDeclarationFragment, Assignment node에서 바뀔 수 있음
-> 이 2가지 노드의 경우에는 Data Dependency를 바꿔줘야 함 / 이전의 Data Dependency에 추가해줄 수도 있고, 아예 변경이 될 수도 있을 듯 싶은데,,,
-> Assignment node의 경우에는 Case를 조금 나눠서 생각 해봐야 할 것 같음
    -> =+ -= 등과 같은 경우에는 이전의 Data Dependency에 새로운 Data Dependency가 추가되는 형태로 구현이 되어야 하고 그게 아닐 경우에는 그냥 새롭게 만들어주면 될 것 같음

이제 포인트는 이 List를 언제 만드냐 인데,, 객체 자체를 리스트에 넣어주는 것이기 때문에 객체가 생성된 이후에 수행해야 하는 것이 맞다고 생각
-> 모든 Structure Vector를 만들어주고, 그 다음 semanticVector를 만들어주기 전에 수행해야 할 듯

!!!! 각 노드에 해당하는 변수의 position을 node에 추가해줘서 구분할 수 있겠다 !!!!
 */


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

//        System.out.println(node.getIdentifier() + "   " + node.getStartPosition());


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
        jCCNode.setStartPosition(node.getStartPosition());

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
        jCCNode.setNodeType(ASTNode.nodeClassForType(node.getParent().getNodeType()).getSimpleName());

        jCCNode.setStructureVector(structureVector);
        jCCNode.setVariableName(node.getOperator().toString()); // InfixExpression node에서 operator 추출
        jCCNode.setSemanticType(semanticType2);
        jCCNode.setStartPosition(node.getStartPosition());

        jCCNodeList.add(jCCNode);
        structureVectorList.add(structureVector);


        return super.visit(node);
    }

    @Override
    public boolean visit(Assignment node) {
        int[] structureVector = new int[25];
        jCCNode jCCNode = new jCCNode();

        ASTNode tempNode = node;

        System.out.println(node);
        System.out.println(node.getLeftHandSide());
        System.out.println(node.getRightHandSide().getStartPosition());

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
        jCCNode.setNodeType(ASTNode.nodeClassForType(node.getParent().getNodeType()).getSimpleName());
        jCCNode.setSemanticType(semanticType2);
        jCCNode.setStartPosition(node.getStartPosition());

        jCCNodeList.add(jCCNode);
        structureVectorList.add(structureVector);
        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationFragment node) {

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

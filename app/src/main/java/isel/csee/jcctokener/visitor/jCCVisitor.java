package isel.csee.jcctokener.visitor;


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

Simple Name 노드로 처음 파싱을 진행하기 때문에 흐음 .. SimpleName 노드로는 semantic type 1,2,3가 구분이 어려울 것 같기는 한데
    -> 파일에 대한 유사도 계산 부분에서 사용해야 되는데 type을 구분하지 않으려면 파싱을 진행하면서 나눠야 하나?
    -> SimpleName에서 구분하는 건 무리인 거 같고 차라리 dependency 계산 해주면서 나눠야겠다
    -> 어차피 dependency 부분에서 아무 dependency가 없는 값들은 semantic vector가 싹 0일 것이고... 그렇게 되면 실제 계산에서 사용하지도 않을 것이기 때문에

InfixExpression node에서 operator가 교차로 등장하는 경우는 InfixExpression node로 분할이 되기 때문에 자동적으로 모든 값들을 재귀적으로 가지고 올 수 있는데
그렇지 않고 하나의 Operator만 등장하는 경우에는 재귀적으로 모든 값들을 가지고 올 수 없음

따라서 이 때는 반복문을 사용해서 값을 가지고 와야 하는데, operator는 어떻게 가지고 올 지 생각
일단 left / right가 InfixExpression node인지 먼저 확인
extendedOperands() method 사용해서 모든 operand를 가지고 오고 left / right 모두가 InfixExpression node가 아닐 경우에는 operator 개수와 operand 개수를 비교
여기서 차이가 날 경우에는 하나의 InfixExpression node에 여러 개의 같은 operator가 존재한다고 생각

-> 여기서 해줘야 하는 것은 이 InfixExpression node가 하나의 operator를 여러 번 사용하는 노드인지 아닌지 여부를 파악해줘야 함
1. 2개의 operand, 1개의 operator 2. 여러개의 operand, 1종류의 operator 3. 여러개의 operand, 여러 종류의 operator

길이와 상관 없이 InfixExpression node에 operator가 한 가지 종류만 있으면 문제가 발생
-> 즉 a + b + c + d + e - f 라는 식이 있다면 left 부분에 a + b + c + d + e / right 부분에 f 이렇게 식이 나뉘고 좌변이 다시 나뉠 때 a, b 이 두 가지 변수만 가지고 오게 된다
조금 더 생각 정리 필요
 */


public class jCCVisitor extends ASTVisitor {
    private List<int[]> structureVectorList = new ArrayList<>();
    private List<jCCNode> jCCNodeList = new ArrayList<>();

    @Override
    public boolean visit(ExpressionStatement node) {

        return super.visit(node);
    }
    @Override
    public boolean visit(SimpleName node) {
        int[] structureVector = new int[25];
        ASTNode tempNode = node;
        jCCNode jCCNode = new jCCNode();

//        if(ASTNode.nodeClassForType(node.getParent().getNodeType()).getSimpleName().equals("InfixExpression")) {
//            System.out.println(node);
//            System.out.println("parent: " + node.getParent());
//            System.out.println("parent + parent: " + node.getParent().getParent());
//        }

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
        if(node.getParent() != null) {
            jCCNode.setNode(node.getParent());
        }
        jCCNodeList.add(jCCNode);
        structureVectorList.add(structureVector);

        tempNode = node;

        return super.visit(node);
    }

    @Override
    public boolean visit(InfixExpression node) {
        int[] structureVector = new int[25];
        String methodName = null;
        String className = null;
        System.out.println(node);
        System.out.println(node.getOperator());


        ASTNode tempNode = node;

        tempNode = node;

        while(tempNode != null) {
            structureVector = NodeType.searchType(tempNode, structureVector);

            if(tempNode instanceof MethodDeclaration) {
                methodName = ((MethodDeclaration) tempNode).getName().toString();
            }

            if(tempNode instanceof TypeDeclaration) {
                className = ((TypeDeclaration) tempNode).getName().toString();
            }

            tempNode = tempNode.getParent();
        }
        // operator 생성
        jCCNode jCCNode = new jCCNode();

        jCCNode.setNodeType(ASTNode.nodeClassForType(node.getNodeType()).getSimpleName());
        jCCNode.setStructureVector(structureVector);
        jCCNode.setMethodName(methodName);
        jCCNode.setClassName(className);
        jCCNode.setVariableName(node.getOperator().toString());
        jCCNode.setStartPosition(node.getStartPosition());
        jCCNode.setNode(node);

        jCCNodeList.add(jCCNode);
        structureVectorList.add(structureVector);

        return super.visit(node);
    }

    @Override
    public boolean visit(Assignment node) {
        int[] structureVector = new int[25];
        jCCNode jCCNode = new jCCNode();

        ASTNode tempNode = node;

//        System.out.println("Assignment node: " + node);
//        System.out.println("Assignment parent node: " + tempNode.getParent());

        tempNode = node;

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
        jCCNode.setNodeType(ASTNode.nodeClassForType(node.getNodeType()).getSimpleName());
        jCCNode.setStartPosition(node.getStartPosition());
        jCCNode.setNode(node);

        jCCNodeList.add(jCCNode);
        structureVectorList.add(structureVector);

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

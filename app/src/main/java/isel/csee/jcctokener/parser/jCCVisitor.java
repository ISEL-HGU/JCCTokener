package isel.csee.jcctokener.parser;


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



public class jCCVisitor extends ASTVisitor {
    private final int semanticType1 = 1;
    private final int semanticType2 = 2;
    private final int semanticType3 = 3;
    private List<int[]> structureVectorList = new ArrayList<>();
    private List<jCCNode> jCCNodeList = new ArrayList<>();


    @Override
    public boolean visit(ForStatement node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(ArrayAccess node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(ThrowStatement node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(CatchClause node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(SingleVariableDeclaration node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(ArrayType node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(ParameterizedType node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(ClassInstanceCreation node) {
        return super.visit(node);
    }


    @Override
    public boolean visit(EnhancedForStatement node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(TryStatement node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(ConstructorInvocation node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(LambdaExpression node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(IfStatement node) {
        return super.visit(node);
    }
    @Override
    public boolean visit(ArrayCreation node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(ReturnStatement node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationFragment node) {
//        System.out.println(node.getName());
//        System.out.println(node.getInitializer());
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(SwitchCase node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(SwitchStatement node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(ExpressionStatement node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(SimpleName node) {
        int[] structureVector = new int[25];
        ASTNode tempNode = node;
        jCCNode jCCNode = new jCCNode();

        if(tempNode.getParent() instanceof QualifiedName) {
            return super.visit(node);
        }
//
//        if(tempNode.getParent() instanceof InfixExpression) {
//            return super.visit(node);
//        }


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

        if(tempNode.getParent() instanceof VariableDeclarationFragment) { // 변수의 선언 부분에서는 structure vector를 그대로 사용 ,, ?
            if(tempNode == ((VariableDeclarationFragment) tempNode.getParent()).getName()) { // 이 부분 조금 더 생각
                jCCNode.setSemanticVector(structureVector);
            }
        }

        if(tempNode.getParent() instanceof MethodInvocation) {
            jCCNode.setSemanticType(semanticType3);
        } else {
            jCCNode.setSemanticType(semanticType1);
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

        System.out.println("left: " + node.getLeftOperand());
        System.out.println("right: " + node.getRightOperand());

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
        System.out.println("node: " + node);
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

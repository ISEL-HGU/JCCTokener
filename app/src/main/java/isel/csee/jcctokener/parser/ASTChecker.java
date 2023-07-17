package isel.csee.jcctokener.parser;


import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;
import org.w3c.dom.Node;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// 의존성을 어떻게 파싱할 수 있는 지 -> Assignment node + VariableDeclarationFragment 사용
// 그 곳에 해당하는 Structure Vector 어떻게 가져올 것인지
// Semantic Vector type 3가지 모두 어떻게 파싱할 것인지
// 정확성을 높이기 위해서 코드 블럭 단위를 어떻게 설정할 것인지 / Class, Method
// getNodeArrayAtPosition

// line 기준으로 사용? 코드 내부에서 몇 번 째 라인에 존재하는 지에 대한 정보를 가지고 vector 값 가져오기?



public class ASTChecker extends ASTVisitor {
    private List<int[]> structureVectorList = new ArrayList<>();


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
    public boolean visit(VariableDeclarationExpression node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) {
        return super.visit(node);
    }


    @Override
    public boolean visit(InfixExpression node) {
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
    public boolean visit(Assignment node) {
        return super.visit(node);
    }

    @Override
    public boolean visit(SimpleName node) {
        int[] structureVector = new int[26];
        ASTNode tempNode = node;

        if(!(node.getParent() instanceof PackageDeclaration)) {
            while(tempNode != null && !(tempNode instanceof TypeDeclaration)) {
                structureVector = NodeType.searchType(tempNode, structureVector);

                tempNode = tempNode.getParent();
            }

            if(structureVector[25] != 0) {
                System.out.println("Simple name: " + node.getIdentifier());
                for(int i : structureVector) {
                    System.out.print(i + " ");
                }
                System.out.println("");

                structureVectorList.add(structureVector);
            }
        }
        return super.visit(node);
    }

    public List<int[]> getStructureVectorList() {
        return structureVectorList;
    }

    public void setStructureVectorList(List<int[]> structureVectorList) {
        this.structureVectorList = structureVectorList;
    }
}

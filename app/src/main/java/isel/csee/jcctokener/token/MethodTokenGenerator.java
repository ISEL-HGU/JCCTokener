package isel.csee.jcctokener.token;

import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class MethodTokenGenerator extends ASTVisitor {
    private List<jCCNode> jCCNodeList;

    @Override
    public boolean visit(MethodInvocation node) {
        Expression methodInstance = node.getExpression();
        SimpleName methodName = node.getName();
        List<Expression> methodArgumentList = node.arguments();
        ASTNode tempNode = node;


        jCCNode targetNode = new jCCNode();
        int[] structureVector = new int[25];

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













        return super.visit(node);
    }




    public MethodTokenGenerator(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

package isel.csee.jcctokener.token;

import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.SimpleName;

import java.util.Arrays;
import java.util.List;

public class OperatorTokenGenerator extends ASTVisitor {
    private List<jCCNode> jCCNodeList;

    @Override
    public boolean visit(InfixExpression node) {
        String variableName = node.getOperator().toString();
        int[] structureVector = new int[25];

        ASTNode tempNode = node;

        while(tempNode != null) {
            structureVector = NodeType.searchType(tempNode, structureVector);

            tempNode = tempNode.getParent();
        } // structure vector 생성

        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getVariableName().equals(variableName) && Arrays.equals(jCCNodeList.get(i).getStructureVector(), structureVector)) {

            }
        }




        return super.visit(node);
    }





    public OperatorTokenGenerator(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

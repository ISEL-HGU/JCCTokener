package isel.csee.jcctokener.visitor;

import isel.csee.jcctokener.node.jCCNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;

import java.util.List;

public class MethodVisitor extends ASTVisitor {
    private List<jCCNode> jCCNodeList;

    @Override
    public boolean visit(MethodInvocation node) {
        Expression methodNode = node.getName();

        jCCNode methodNameNode = new jCCNode();

        methodNameNode.setVariableName(((SimpleName) methodNode).getIdentifier());
        methodNameNode.setNode(node);
        methodNameNode.setStartPosition(methodNode.getStartPosition());


        jCCNodeList.add(methodNameNode);


        return super.visit(node);
    }


    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

    public MethodVisitor(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

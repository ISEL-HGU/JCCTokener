package isel.csee.jcctokener.node;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;

import java.util.List;

public class OtherNodeFinder extends ASTVisitor {
    private List<jCCNode> jCCNodeList;
    private jCCNode targetNode;

    @Override
    public boolean visit(SimpleName node) {


        return super.visit(node);
    }



    public OtherNodeFinder(List<jCCNode> jCCNodeList, jCCNode jCCNode) {
        this.jCCNodeList = jCCNodeList;
        this.targetNode = jCCNode;
    }
}

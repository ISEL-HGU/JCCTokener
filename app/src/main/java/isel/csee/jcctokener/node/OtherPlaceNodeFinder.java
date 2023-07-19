package isel.csee.jcctokener.node;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;

import java.util.List;

public class OtherPlaceNodeFinder extends ASTVisitor {
    private List<jCCNode> jCCNodeList;
    private jCCNode targetNode;
    private List<jCCNode> relatedNodeList;

    @Override
    public boolean visit(SimpleName node) {

        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getClassName().equals(targetNode.getClassName())) { // 클래스 이름
                if(jCCNodeList.get(i).getMethodName().equals(targetNode.getMethodName())) { // 메소드 이름
                    if(jCCNodeList.get(i).getVariableName().equals(targetNode.getVariableName())) { // 변수 이름
                        relatedNodeList.add(jCCNodeList.get(i));

                    }
                }
            }
        }


        return super.visit(node);
    }



    public OtherPlaceNodeFinder(List<jCCNode> jCCNodeList, jCCNode jCCNode) {
        this.jCCNodeList = jCCNodeList;
        this.targetNode = jCCNode;
    }
}

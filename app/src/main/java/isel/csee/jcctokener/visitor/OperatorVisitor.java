package isel.csee.jcctokener.visitor;

import isel.csee.jcctokener.node.jCCNode;
import org.eclipse.jdt.core.dom.*;

import java.util.List;

// Assignment, InfixExpression / PostfixExpression, PrefixExpression node는 어떻게 처리를 해줘야 하지?
public class OperatorVisitor extends ASTVisitor {
    private List<jCCNode> jCCNodeList;

    @Override
    public boolean visit(Assignment node) {
        String operator = node.getOperator().toString();

        jCCNode operatorNode = new jCCNode();

        operatorNode.setVariableName(operator);
        operatorNode.setNode(node);
//        operatorNode.setStartPosition(node.getStartPosition());

        jCCNodeList.add(operatorNode);

        return super.visit(node);
    }

    @Override
    public boolean visit(InfixExpression node) {
        String operator = node.getOperator().toString(); // node의 시작 부분을 operator의 position으로 가져옴


        jCCNode operatorNode = new jCCNode();

        operatorNode.setVariableName(operator);
        operatorNode.setNode(node);
//        operatorNode.setStartPosition(node.getStartPosition());

        jCCNodeList.add(operatorNode);

        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationFragment node) {
        if(node.getInitializer() != null) {
            String operator = "=";

            jCCNode operatorNode = new jCCNode();

            operatorNode.setVariableName(operator);
            operatorNode.setNode(node);
//            operatorNode.setStartPosition(node.getStartPosition());

            jCCNodeList.add(operatorNode);

            return super.visit(node);
        }

        return super.visit(node);
    }

    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

    public OperatorVisitor(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

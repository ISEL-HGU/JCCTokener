package isel.csee.jcctokener.infix;

import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;

public class InfixExpressionComputer {
    InfixExpression node;

    // hasExtendedOperands() method의 return value가 0이 아니라고 가정하고 사용
    public InfixExpression remakeInfixExpressionNode(InfixExpression node) {
        Expression rightNode = node.getRightOperand();
        Expression leftNode = node.getLeftOperand();
        InfixExpression.Operator operator = node.getOperator();

        InfixExpression tempInfixExpression;


        return node;
    }

    public InfixExpressionComputer(InfixExpression node) {
        this.node = node;
    }
}

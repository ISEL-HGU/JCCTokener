package isel.csee.jcctokener.types;

import org.eclipse.jdt.core.dom.*;

public class NodeType {
    public static int searchType(ASTNode astNode) {
        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getExpression() == astNode) { // Type 1 (If Condition)
            return 1;
        }

        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getThenStatement() == astNode) { // type 2 (If Body)
            return 2;
        }

        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getElseStatement() == astNode) { // type 2 (Else Body)
            return 2;
        }

        if(astNode instanceof MethodDeclaration && ((MethodDeclaration) astNode).isConstructor() == false) { // type 3 (Method Definition)
            return 3;
        }

        if(astNode.getParent() instanceof ForStatement && ((ForStatement) astNode.getParent()).getExpression() == astNode) { // type 4 (For Loop Condition)
            return 4;
        }

        if(astNode.getParent() instanceof EnhancedForStatement && ((EnhancedForStatement) astNode.getParent()).getExpression() == astNode) { // type 4 (Enhanced For Loop Condition)
            return 4;
        }

        if(astNode.getParent() instanceof WhileStatement && ((WhileStatement) astNode.getParent()).getExpression() == astNode) { // type 4 (While Loop Condition)
            return 4;
        }

        if(astNode.getParent() instanceof ForStatement && ((ForStatement) astNode.getParent()).getBody() == astNode) { // type 5 (For Loop Body)
            return 5;
        }

        if(astNode.getParent() instanceof EnhancedForStatement && ((EnhancedForStatement) astNode.getParent()).getBody() == astNode) { // type 5 (Enhanced For Loop Body)
            return 5;
        }

        if(astNode.getParent() instanceof WhileStatement && ((WhileStatement) astNode.getParent()).getBody() == astNode) { // type 5 (While Loop Body)
            return 5;
        }

        if(astNode instanceof ArrayAccess) { // type 6 (Array Selector)
            return 6;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.EQUALS) { // type 7 (Logical Expression)
            return 7;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.NOT_EQUALS) { // type 7 (Logical Expression)
            return 7;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.GREATER) { // type 7 (Logical Expression)
            return 74;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.LESS) { // type 7 (Logical Expression)
            return 7;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.LESS_EQUALS) { // type 7 (Logical Expression)
            return 7;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.GREATER_EQUALS) { // type 7 (Logical Expression)
            return 7;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.AND) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.DIVIDE) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.LEFT_SHIFT) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.RIGHT_SHIFT_SIGNED) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.RIGHT_SHIFT_UNSIGNED) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.MINUS) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.OR) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.PLUS) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.REMAINDER) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.TIMES) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.XOR) { // type 8 (Numeric Expression)
            return 8;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.CONDITIONAL_AND) { // type 9 (Condition Expression)
            return 9;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.CONDITIONAL_OR) { // type 9 (Condition Expression)
            return 9;
        }

        if(astNode instanceof Assignment) { // type 10 (Assignment Expression)
            return 10;
        }

        if(astNode instanceof MethodInvocation) { // type 11 (Method Invocation)
            return 11;
        }

        if(astNode instanceof ReturnStatement) { // type 12 (Return Statement)
            return 12;
        }

        if(astNode instanceof SwitchCase) { // type 13 (Switch Case Body)
            return 13;
        }

        if(astNode instanceof SwitchStatement) { // type 14 (Switch Body)
            return 14;
        }

        if(astNode instanceof SwitchExpression) { // type 15 (Switch Condition)
            return 15;
        }

        if(astNode instanceof VariableDeclarationExpression) { // type 16 (Variable Declaration)
            return 16;
        }

        if(astNode.getParent() instanceof AssertStatement && ((AssertStatement) astNode.getParent()).getExpression() == astNode) { // type 17 (Assert Condition)
            return 17;
        }

        if(astNode.getParent() instanceof AssertStatement && ((AssertStatement) astNode.getParent()).getMessage() == astNode) { // type 18 (Assert Body)
            return 18;
        }

        if(astNode.getParent() instanceof ThrowStatement && ((ThrowStatement) astNode.getParent()).getExpression() == astNode) { // type 19 (Throw Body)
            return 19;
        }

        if(astNode.getParent() instanceof TryStatement && ((TryStatement) astNode.getParent()).getBody() == astNode) { // type 20 (Try Body)
            return 20;
        }

        if(astNode.getParent() instanceof CatchClause && ((CatchClause) astNode.getParent()).getBody() == astNode) { // type 21 (Catch Body)
            return 21;
        }

        if(astNode.getParent() instanceof TryStatement && ((TryStatement) astNode.getParent()).getFinally() == astNode) { // type 22 (Finally Body)
            return 22;
        }

        if(astNode instanceof LambdaExpression) { // type 23 (Lambda Expression)
            return 23;
        }

        if(astNode instanceof ConstructorInvocation) { // type 24 (Constructor Invocation)
            return 24;
        }

        if(astNode instanceof MethodDeclaration && ((MethodDeclaration) astNode).isConstructor() == true) { // type 25 (Class Creator)
            return 25;
        }

        if(astNode instanceof ArrayCreation) { // type 25 (Array Creator)
            return 25;
        }

        return 0;
    }
}

package isel.csee.jcctokener.types;

import org.eclipse.jdt.core.dom.*;

public class NodeType {
    public static int[] searchType(ASTNode astNode, int[] arr) {
        if(astNode instanceof IfStatement && ((IfStatement) astNode).getExpression() == astNode) { // Type 1 (If Condition)
            arr[1] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getThenStatement() == astNode) { // type 2 (If Body)
            arr[2] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getElseStatement() == astNode) { // type 2 (Else Body)
            arr[2] += 1;
            return arr;
        }

        if(astNode instanceof MethodDeclaration && ((MethodDeclaration) astNode).isConstructor() == false) { // type 3 (Method Definition)
            arr[3] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof ForStatement && ((ForStatement) astNode.getParent()).getExpression() == astNode) { // type 4 (For Loop Condition)
            arr[4] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof EnhancedForStatement && ((EnhancedForStatement) astNode.getParent()).getExpression() == astNode) { // type 4 (Enhanced For Loop Condition)
            arr[4] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof WhileStatement && ((WhileStatement) astNode.getParent()).getExpression() == astNode) { // type 4 (While Loop Condition)
            arr[4] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof ForStatement && ((ForStatement) astNode.getParent()).getBody() == astNode) { // type 5 (For Loop Body)
            arr[5] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof EnhancedForStatement && ((EnhancedForStatement) astNode.getParent()).getBody() == astNode) { // type 5 (Enhanced For Loop Body)
            arr[5] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof WhileStatement && ((WhileStatement) astNode.getParent()).getBody() == astNode) { // type 5 (While Loop Body)
            arr[5] += 1;
            return arr;
        }

        if(astNode instanceof ArrayAccess) { // type 6 (Array Selector)
            arr[6] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.EQUALS) { // type 7 (Logical Expression)
            arr[7] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.NOT_EQUALS) { // type 7 (Logical Expression)
            arr[7] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.GREATER) { // type 7 (Logical Expression)
            arr[7] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.LESS) { // type 7 (Logical Expression)
            arr[7] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.LESS_EQUALS) { // type 7 (Logical Expression)
            arr[7] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.GREATER_EQUALS) { // type 7 (Logical Expression)
            arr[7] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.AND) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.DIVIDE) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.LEFT_SHIFT) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.RIGHT_SHIFT_SIGNED) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.RIGHT_SHIFT_UNSIGNED) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.MINUS) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.OR) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.PLUS) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.REMAINDER) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.TIMES) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.XOR) { // type 8 (Numeric Expression)
            arr[8] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.CONDITIONAL_AND) { // type 9 (Condition Expression)
            arr[9] += 1;
            return arr;
        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.CONDITIONAL_OR) { // type 9 (Condition Expression)
            arr[9] += 1;
            return arr;
        }

        if(astNode instanceof Assignment) { // type 10 (Assignment Expression)
            arr[10] += 1;
            return arr;
        }

        if(astNode instanceof MethodInvocation) { // type 11 (Method Invocation)
            arr[11] += 1;
            return arr;
        }

        if(astNode instanceof ReturnStatement) { // type 12 (Return Statement)
            arr[12] += 1;
            return arr;
        }

//        if(astNode instanceof SwitchCase) { // type 13 (Switch Case Body)
//            return 13;
//        }

        if(astNode instanceof SwitchStatement) { // type 14 (Switch Body)
            arr[14] += 1;
            return arr;
        }

        if(astNode instanceof SwitchExpression) { // type 15 (Switch Condition)
            arr[15] += 1;
            return arr;
        }

        if(astNode instanceof VariableDeclarationExpression) { // type 16 (Variable Declaration)
            arr[16] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof AssertStatement && ((AssertStatement) astNode.getParent()).getExpression() == astNode) { // type 17 (Assert Condition)
            arr[17] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof AssertStatement && ((AssertStatement) astNode.getParent()).getMessage() == astNode) { // type 18 (Assert Body)
            arr[18] += 1;
            return arr;
        }

        if(astNode instanceof ThrowStatement) { // type 19 (Throw Body)
            arr[19] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof TryStatement && ((TryStatement) astNode.getParent()).getBody() == astNode) { // type 20 (Try Body)
            arr[20] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof CatchClause && ((CatchClause) astNode.getParent()).getBody() == astNode) { // type 21 (Catch Body)
            arr[21] += 1;
            return arr;
        }

        if(astNode.getParent() instanceof TryStatement && ((TryStatement) astNode.getParent()).getFinally() == astNode) { // type 22 (Finally Body)
            arr[22] += 1;
            return arr;
        }

        if(astNode instanceof LambdaExpression) { // type 23 (Lambda Expression)
            arr[23] += 1;
            return arr;
        }

        if(astNode instanceof ConstructorInvocation) { // type 24 (Constructor Invocation)
            arr[24] += 1;
            return arr;
        }

        if(astNode instanceof MethodDeclaration && ((MethodDeclaration) astNode).isConstructor() == true) { // type 25 (Class Creator)
            arr[25] += 1;
            return arr;
        }

        if(astNode instanceof ArrayCreation) { // type 25 (Array Creator)
            arr[25] += 1;
            return arr;
        }

        return arr;
    }
}

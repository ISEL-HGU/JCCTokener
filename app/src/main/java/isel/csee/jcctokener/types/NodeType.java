package isel.csee.jcctokener.types;

import org.eclipse.jdt.core.dom.*;

public class NodeType {
    private static final int type1 = 0;
    private static final int type2 = 1;
    private static final int type3 = 2;
    private static final int type4 = 3;
    private static final int type5 = 4;
    private static final int type6 = 5;
    private static final int type7 = 6;
    private static final int type8 = 7;
    private static final int type9 = 8;
    private static final int type10 = 9;
    private static final int type11 = 10;
    private static final int type12 = 11;
    private static final int type13 = 12;
    private static final int type14 = 13;
    private static final int type15 = 14;
    private static final int type16 = 15;
    private static final int type17 = 16;
    private static final int type18 = 17;
    private static final int type19 = 18;
    private static final int type20 = 19;
    private static final int type21 = 20;
    private static final int type22 = 21;
    private static final int type23 = 22;
    private static final int type24 = 23;
    private static final int type25 = 24;

    public static double[] searchType(ASTNode astNode, double[] arr) { // parent로 해주는 이유는 방문하는 노드가 SimpleName node이기 때문
        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getExpression() == astNode) { // Type 1 (If Condition)
            arr[type1] += 1;
        } // 여기서는 parent가 IfStatement이고 parent의 조건이 해당 astNode인 경우를 의미함

        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getThenStatement() == astNode) { // type 2 (If Body)
            arr[type2] += 1;
        } // 여기서는 본문을 의미함

        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getElseStatement() == astNode) { // type 2 (Else Body)
            arr[type2] += 1;

        }

        if(astNode instanceof MethodDeclaration && ((MethodDeclaration) astNode).isConstructor() == false) { // type 3 (Method Definition)
            arr[type3] += 1;

        }

        if(astNode.getParent() instanceof ForStatement && ((ForStatement) astNode.getParent()).getExpression() == astNode) { // type 4 (For Loop Condition)
            arr[type4] += 1;

        }

        if(astNode.getParent() instanceof EnhancedForStatement && ((EnhancedForStatement) astNode.getParent()).getExpression() == astNode) { // type 4 (Enhanced For Loop Condition)
            arr[type4] += 1;

        }

        if(astNode.getParent() instanceof WhileStatement && ((WhileStatement) astNode.getParent()).getExpression() == astNode) { // type 4 (While Loop Condition)
            arr[type4] += 1;

        }

        if(astNode.getParent() instanceof ForStatement && ((ForStatement) astNode.getParent()).getBody() == astNode) { // type 5 (For Loop Body)
            arr[type5] += 1;

        }

        if(astNode.getParent() instanceof EnhancedForStatement && ((EnhancedForStatement) astNode.getParent()).getBody() == astNode) { // type 5 (Enhanced For Loop Body)
            arr[type5] += 1;

        }

        if(astNode.getParent() instanceof WhileStatement && ((WhileStatement) astNode.getParent()).getBody() == astNode) { // type 5 (While Loop Body)
            arr[type5] += 1;

        }

        if(astNode instanceof ArrayAccess) { // type 6 (Array Selector)
            arr[type6] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.EQUALS) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.NOT_EQUALS) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.GREATER) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.LESS) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.LESS_EQUALS) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.GREATER_EQUALS) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.AND) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.DIVIDE) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.LEFT_SHIFT) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.RIGHT_SHIFT_SIGNED) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.RIGHT_SHIFT_UNSIGNED) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.MINUS) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.OR) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.PLUS) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.REMAINDER) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.TIMES) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.XOR) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.CONDITIONAL_AND) { // type 9 (Condition Expression)
            arr[type9] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator() == InfixExpression.Operator.CONDITIONAL_OR) { // type 9 (Condition Expression)
            arr[type9] += 1;

        }

        if(astNode instanceof Assignment) { // type 10 (Assignment Expression)
            arr[type10] += 1;

        }

        if(astNode instanceof MethodInvocation) { // type 11 (Method Invocation)
            arr[type11] += 1;

        }

        if(astNode instanceof ReturnStatement) { // type 12 (Return Statement)
            arr[type12] += 1;

        }

//        if(astNode instanceof SwitchCase) { // type 13 (Switch Case Body)
//            return 13;
//        }

        if(astNode instanceof SwitchStatement) { // type 14 (Switch Body)
            arr[type14] += 1;

        }

        if(astNode instanceof SwitchExpression) { // type 15 (Switch Condition)
            arr[type15] += 1;

        }

        if(astNode instanceof VariableDeclarationExpression) { // type 16 (Variable Declaration)
            arr[type16] += 1;

        }

        if(astNode.getParent() instanceof AssertStatement && ((AssertStatement) astNode.getParent()).getExpression() == astNode) { // type 17 (Assert Condition)
            arr[type17] += 1;

        }

        if(astNode.getParent() instanceof AssertStatement && ((AssertStatement) astNode.getParent()).getMessage() == astNode) { // type 18 (Assert Body)
            arr[type18] += 1;

        }

        if(astNode instanceof ThrowStatement) { // type 19 (Throw Body)
            arr[type19] += 1;

        }

        if(astNode.getParent() instanceof TryStatement && ((TryStatement) astNode.getParent()).getBody() == astNode) { // type 20 (Try Body)
            arr[type20] += 1;

        }

        if(astNode.getParent() instanceof CatchClause && ((CatchClause) astNode.getParent()).getBody() == astNode) { // type 21 (Catch Body)
            arr[type21] += 1;

        }

        if(astNode.getParent() instanceof TryStatement && ((TryStatement) astNode.getParent()).getFinally() == astNode) { // type 22 (Finally Body)
            arr[type22] += 1;

        }

        if(astNode instanceof LambdaExpression) { // type 23 (Lambda Expression)
            arr[type23] += 1;

        }

        if(astNode instanceof ConstructorInvocation) { // type 24 (Constructor Invocation)
            arr[type24] += 1;

        }

        if(astNode instanceof MethodDeclaration && ((MethodDeclaration) astNode).isConstructor() == true) { // type 25 (Class Creator)
            arr[type25] += 1;

        }

        if(astNode instanceof ArrayCreation) { // type 25 (Array Creator)
            arr[type25] += 1;

        }

        return arr;
    }
}

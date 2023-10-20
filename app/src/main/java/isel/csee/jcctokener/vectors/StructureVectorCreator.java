package isel.csee.jcctokener.vectors;

import org.eclipse.jdt.core.dom.*;

public class StructureVectorCreator {
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

    public static double[] searchType(ASTNode astNode, double[] arr) {
        // IfCondition과 IfBody를 따로 가져오는 node type이 존재하지 않기 때문에 parent를 사용해서 비교를 진행
        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getExpression().equals(astNode)) { // Type 1 (If Condition)
            arr[type1] += 1;
        }

        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getThenStatement().equals(astNode)) { // type 2 (If Body)
            arr[type2] += 1;
        } // true일 경우에 실행되는 body에 해당

        if(astNode.getParent() instanceof IfStatement && ((IfStatement) astNode.getParent()).getElseStatement().equals(astNode)) { // type 2 (Else Body)
            arr[type2] += 1;
        } // false일 경우에 실행되는 body에 해당
        // 만약에 이 else if가 존재하는 구문일 경우에는 이 부분에 새로운 IfStatement가 존재하게 됨 -> IfStatement일 경우에 다시 이 부분을 visit 해줘야 함

        if(astNode instanceof MethodDeclaration && ((MethodDeclaration) astNode).isConstructor() == false) { // type 3 (Method Definition)
            arr[type3] += 1;
        } // methodDeclaration node이지만 constructor가 아닌 경우에 해당함

        if(astNode.getParent() instanceof ForStatement && ((ForStatement) astNode.getParent()).getExpression().equals(astNode)) { // type 4 (For Loop Condition)
            arr[type4] += 1; // For loop condition 자체의 노드가 존재하지 않아, parent 사용
        }

        if(astNode.getParent() instanceof EnhancedForStatement && ((EnhancedForStatement) astNode.getParent()).getExpression().equals(astNode)) { // type 4 (Enhanced For Loop Condition)
            arr[type4] += 1; // Enhanced For loop Condition 자체를 제공하는 노드 존재 x, parent 사용
        }

        if(astNode.getParent() instanceof WhileStatement && ((WhileStatement) astNode.getParent()).getExpression().equals(astNode)) { // type 4 (While Loop Condition)
            arr[type4] += 1; // While loop condition 자체를 제공하는 노드 X, parent 사용
        }

        if(astNode.getParent() instanceof ForStatement && ((ForStatement) astNode.getParent()).getBody().equals(astNode)) { // type 5 (For Loop Body)
            arr[type5] += 1;
        } // body는 block node를 거쳐서 올라오게 됨

        if(astNode.getParent() instanceof EnhancedForStatement && ((EnhancedForStatement) astNode.getParent()).getBody().equals(astNode)) { // type 5 (Enhanced For Loop Body)
            arr[type5] += 1;
        }

        if(astNode.getParent() instanceof WhileStatement && ((WhileStatement) astNode.getParent()).getBody().equals(astNode)) { // type 5 (While Loop Body)
            arr[type5] += 1;
        }

        if(astNode instanceof ArrayAccess) { // type 6 (Array Selector)
            arr[type6] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.EQUALS)) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.NOT_EQUALS)) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.GREATER)) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.LESS)) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.LESS_EQUALS)) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.GREATER_EQUALS)) { // type 7 (Logical Expression)
            arr[type7] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.AND)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.DIVIDE)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.LEFT_SHIFT)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.RIGHT_SHIFT_SIGNED)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.RIGHT_SHIFT_UNSIGNED)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.MINUS)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.OR)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.PLUS)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.REMAINDER)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.TIMES)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.XOR)) { // type 8 (Numeric Expression)
            arr[type8] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.CONDITIONAL_AND)) { // type 9 (Condition Expression)
            arr[type9] += 1;

        }

        if(astNode instanceof InfixExpression && ((InfixExpression) astNode).getOperator().equals(InfixExpression.Operator.CONDITIONAL_OR)) { // type 9 (Condition Expression)
            arr[type9] += 1;

        }

        if(astNode instanceof Assignment) { // type 10 (Assignment Expression)
            arr[type10] += 1;

        }

        if(astNode instanceof MethodInvocation) { // type 11 (Method Invocation)
            arr[type11] += 1;

        } // Constructor는 ClassInsatanceCreation node 방문을 통해서 확인 가능

        if(astNode instanceof ReturnStatement) { // type 12 (Return Statement)
            arr[type12] += 1;

        }

        if(astNode instanceof SwitchCase) { // type 13 (Switch Case Body)
            arr[type13] += 1;

            // 해당 type은 switch case 각각의 case 자체를 가지고 오는 부분
        }

        if(astNode instanceof SwitchStatement) { // type 14 (Switch Body)
            arr[type14] += 1;
            // switch case 각각이 아니라, switch expression의 전체 body를 의미
        }

        if(astNode instanceof SwitchExpression) { // type 15 (Switch Condition)
            arr[type15] += 1;

        }

        if(astNode instanceof VariableDeclarationFragment) { // type 16 (Variable Declaration)
            arr[type16] += 1;

        }

        if(astNode.getParent() instanceof AssertStatement && ((AssertStatement) astNode.getParent()).getExpression().equals(astNode)) { // type 17 (Assert Condition)
            arr[type17] += 1;

        }

        if(astNode.getParent() instanceof AssertStatement && ((AssertStatement) astNode.getParent()).getMessage().equals(astNode)) { // type 18 (Assert Body)
            arr[type18] += 1;

        }

        if(astNode.getParent() instanceof ThrowStatement && ((ThrowStatement) astNode.getParent()).getExpression().equals(astNode)) { // type 19 (Throw Body)
            arr[type19] += 1;

        }

        if(astNode.getParent() instanceof TryStatement && ((TryStatement) astNode.getParent()).getBody().equals(astNode)) { // type 20 (Try Body)
            arr[type20] += 1;

        }

        if(astNode.getParent() instanceof CatchClause && ((CatchClause) astNode.getParent()).getBody().equals(astNode)) { // type 21 (Catch Body)
            arr[type21] += 1;

        }

        if(astNode.getParent() instanceof TryStatement && ((TryStatement) astNode.getParent()).getFinally().equals(astNode)) { // type 22 (Finally Body)
            arr[type22] += 1;

        }

        if(astNode instanceof LambdaExpression) { // type 23 (Lambda Expression)
            arr[type23] += 1;

        }

        if(astNode instanceof ConstructorInvocation) { // type 24 (Constructor Invocation) / constructor invocation의 type 중 하나
            arr[type24] += 1;

        }

        if(astNode instanceof ClassInstanceCreation) {
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

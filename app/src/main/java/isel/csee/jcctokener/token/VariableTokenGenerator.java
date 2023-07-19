package isel.csee.jcctokener.token;

import com.sun.jdi.Method;
import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;

import java.util.*;

// 파싱은 차례대로 진행 -> 정렬되지 않은 리스트에서 데이터를 찾을 때, 해당 데이터를 만나면 종료하는 로직으로 구현
// 그게 가장 효율적인 방법인지 / 이름을 가지고 분류 / HashMap 사용(?)
// 차례대로 가져오는 것으로 생각
// structure vector 구한 다음 vector-vector 동일(?) / 동일한 지점에 도착하면 반복문 종료 그 전까지 반복문을 사용해서 모든 변수 다 더해서 가져오기 / 재귀적으로 구현해서 우변의 값들도 다 가져올 수 있도록 구현
// AST Visitor 하나 더 생성해서 차례대로 돌린다고 생각..?
// semantic vector를 쭉 더해준다고 생각?


public class VariableTokenGenerator extends ASTVisitor {
    private List<jCCNode> jCCNodeList;


    @Override
    public boolean visit(VariableDeclarationFragment node) { // 선언과 동시에 초기화
        Expression rightHand = node.getInitializer();

        SimpleName variableName = node.getName();
        int[] structureVector = new int[25];

        ASTNode tempNode = node;

        while(tempNode != null) {
            structureVector = NodeType.searchType(tempNode, structureVector);

            tempNode = tempNode.getParent();
        } // structure vector 생성


        if(rightHand instanceof InfixExpression) {

        }

        if(rightHand instanceof MethodInvocation) {

        }




        return super.visit(node);
    }

    @Override
    public boolean visit(Assignment node) { // 선언 한 이후에 따로 초기화

        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) { // method 사용 부분

        return super.visit(node);
    }






    public void processMethodInvocation(MethodInvocation node) {

    }
    public SimpleName processInfixExpression(InfixExpression node) {
        Expression leftHands = node.getLeftOperand();
        Expression rightHands = node.getRightOperand();

        if(leftHands instanceof SimpleName) {

        }

        if(rightHands instanceof SimpleName) {

        } else if(rightHands instanceof InfixExpression) {
            processInfixExpression((InfixExpression) rightHands);
        }

        return null;
    }

    public VariableTokenGenerator(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

}

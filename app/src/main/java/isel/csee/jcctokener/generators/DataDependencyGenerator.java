package isel.csee.jcctokener.generators;

import isel.csee.jcctokener.node.jCCNode;
import org.checkerframework.checker.units.qual.A;
import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;


/*
이 노드가 이 노드가 맞는 지 여부는 어떻게 확인하지? -> startPosition 사용해서 확인하기!

이 클래스가 수행해야 되는 업무는 DataDependency 측면에서 관련된 값들을 묶어주는 리스트를 각 노드마다 생성해내는 것 + 각 노드 별로 Assignment, VariableDeclarationFragment node가 아닐 경우에는 이전의 값을 업데이트 해주는 방식
전체에 걸쳐서 업데이트를 해주고, Assignment node 방문 시에, 다시 처음부터 끝까지 업데이트 해주는 방식으로 사용하면 될 듯

Operator / MethodInvocation 이 2가지 case에 대해서도 edge를 만들어 줘야 함
 */

public class DataDependencyGenerator extends ASTVisitor {
    private List<jCCNode> jCCNodeList;


    @Override
    public boolean visit(VariableDeclarationFragment node) {
        int startPosition = node.getName().getStartPosition();
        String variableName = node.getName().toString();

        int targetIndex = findTargetNode(startPosition, variableName);

        if(node.getInitializer() instanceof InfixExpression) {
            List<Integer> tempList = new ArrayList<>();

            tempList = processInfixExpression((InfixExpression) node.getInitializer(), tempList);
            jCCNodeList.get(targetIndex).setIndexListOfEdges(tempList);
            updateRelatedNodeList(jCCNodeList.get(targetIndex), targetIndex);

        } else if(node.getInitializer() instanceof MethodInvocation) { // 조금 더 생각

        } else if(node.getInitializer() instanceof SimpleName) {
            SimpleName simpleName = (SimpleName) node.getInitializer();
            List<Integer> tempList = new ArrayList<>();
            tempList = processSimpleName(simpleName, tempList);
            jCCNodeList.get(targetIndex).setIndexListOfEdges(tempList);
            updateRelatedNodeList(jCCNodeList.get(targetIndex), targetIndex);
        }


        return super.visit(node);
    }

    @Override
    public boolean visit(Assignment node) {
        int startPosition = node.getLeftHandSide().getStartPosition();
        String variableName = node.getLeftHandSide().toString();

        int targetIndex = findTargetNode(startPosition, variableName); // 왼쪽에 해당하는 변수의 index를 가져온다

        if(node.getRightHandSide() instanceof InfixExpression) {
            List<Integer> tempList = new ArrayList<>();

            tempList = processInfixExpression((InfixExpression) node.getRightHandSide(), tempList);
            jCCNodeList.get(targetIndex).setIndexListOfEdges(tempList);
            updateRelatedNodeList(jCCNodeList.get(targetIndex), targetIndex);

        } else if(node.getRightHandSide() instanceof SimpleName) {
            SimpleName simpleName = (SimpleName) node.getRightHandSide();
            List<Integer> tempList = new ArrayList<>();
            tempList = processSimpleName(simpleName, tempList);
            jCCNodeList.get(targetIndex).setIndexListOfEdges(tempList);
            updateRelatedNodeList(jCCNodeList.get(targetIndex), targetIndex);
        } else if(node.getRightHandSide() instanceof MethodInvocation) {

        }

        return super.visit(node);
    }

    public void updateRelatedNodeList(jCCNode node, int targetIndex) { // 해당 노드 다음에 나오는 같은 이름의 노드들을 다 업데이트 해주는 노드

        for(int i = targetIndex; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getVariableName().equals(node.getVariableName())) { // 이름이 동일
                if(jCCNodeList.get(i).getStartPosition() > node.getStartPosition()) { // 해당 노드 이후에 나오는 같은 이름의 노드
                    jCCNodeList.get(i).setIndexListOfEdges(node.getIndexListOfEdges());
                }
            }
        }
    }

    public int findTargetNode(int startPosition, String variableName) { // 해당 노드가 list 안에서 어디에 위치해 있는지 index 반환
        int index = 0;

        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getStartPosition() == startPosition) {
                if(jCCNodeList.get(i).getVariableName().equals(variableName)) {
                    index = i;
                    break;
                }
            }
        }
        
        return index;
    }

    public List<Integer> processInfixExpression(InfixExpression node, List<Integer> edgeList) {

        if(node.getRightOperand() instanceof SimpleName) {
            edgeList.add(findTargetNode(node.getRightOperand().getStartPosition(), ((SimpleName) node.getRightOperand()).getIdentifier().toString()));
        }

        if(node.getLeftOperand() instanceof InfixExpression) {
            processInfixExpression((InfixExpression) node.getLeftOperand(), edgeList);
        } else if(node.getLeftOperand() instanceof SimpleName) {
            edgeList.add(findTargetNode(node.getLeftOperand().getStartPosition(), ((SimpleName) node.getLeftOperand()).getIdentifier().toString()));
        }

        return edgeList;
    }

    public List<Integer> processSimpleName(SimpleName node, List<Integer> edgeList) { // 기존의 List를 업데이트 해서 제공해주는 method

        int simpleNamePosition = node.getStartPosition();
        int simpleNameIndex = findTargetNode(simpleNamePosition, node.toString());

        edgeList.add(simpleNameIndex);

        return edgeList;
    }


}

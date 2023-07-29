package isel.csee.jcctokener.generators;

import isel.csee.jcctokener.node.jCCNode;

import java.util.*;

/*
Assignment node에서 semantic vector 업데이트를 어떻게 해줄 것인지
이거를 Visitor로 만들어 주는 것이 맞나? -> Visitor 말고 그냥 Class로 만들어서 사용해도 될 듯
    -> 이미 structure vector 정보는 다 존재하기 때문에 상관 X
    -> variable 간의 의존도를 파싱할 때, 유의미한 데이터(semantic vector 생성해줘야 하는 값들)는 이미 다 가져온 상태
근데 여기서 몇 step 올라갈 수 있는 걸 어떻게 정해줄 수 있을까? -> 재귀적으로 하면 이게 조금 어려울 수도 있나?

재귀적으로 어떻게 구현할지 조금 더 생각 해야될 것 같은데
 */



public class SemanticVectorGenerator {
    private List<jCCNode> jCCNodeList;
    private final int type1 = 1;
    private final int type2 = 2;
    private final int type3 = 3;

    public void createVariableSemanticVector(int countStep) { // type 1에 대한 semantic vector 생성
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getSemanticType() == type1) {
                int temp = 0;
                int[] semanticVector = new int[25];

                while(temp < countStep) {

                }
            }
        }
    }

    public void createOperatorSemanticVector() { // type 2에 대한 semantic vector 생성

    }

    public void createMethodSemanticVector() { // type 3에 대한 semantic vector 생성

    }

    public void getRelatedValues(jCCNode node, int stepNumber) { // 재귀적으로 코드를 사용하기 위한 method

    }


    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

    public SemanticVectorGenerator(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

}

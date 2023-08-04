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

반복문으로 사용하는 것이 의미가 있는지? 반복문 말고 차라리 재귀적으로 구현을 해야하나?
반복문 / 재귀적 구현

type 1은 관련이 있는 값들의 structure vector를 모두 더해줘야 함 / 현재 자신의 structure vector 값을 사용할 것인지 여부에 대해서 생각
    -> 자기 자신의 structure vector는 더하지 않는다고 생각하고 계산 / semantic vector를 더해주는 것이 아님

하나의 클래스를 기준으로 파싱을 진행 -> 여러개의 method가 같은 클래스 파일 안에 존재할 수 있고, 이로 인해 variable의 이름이 겹치는 경우가 존재할 수 있음
    -> 이를 방지하기 위해서 method name을 비교해서 같은 variable인지 아닌지 여부를 파악하고, data dependency를 추가해줘야 함

structure vector를 더해주는 과정이기 때문에 코드 작성 시에 유의해야 함!!
 */



public class SemanticVectorGenerator {
    private List<jCCNode> jCCNodeList;
    private final int type1 = 1;
    private final int type2 = 2;
    private final int type3 = 3;
    private int countStep; // 몇 개로 나눠서 계산 할 것인지

    public void createVariableSemanticVector() { // type 1에 대한 semantic vector 생성
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getSemanticType() == type1) {
                int tempStep = 0;
                int[] semanticVector = new int[25];

                semanticVector = getRelatedValues(jCCNodeList.get(i), tempStep, countStep, semanticVector);

                jCCNodeList.get(i).setSemanticVector(semanticVector);
            }
        }
    }

    public void createOperatorSemanticVector() { // type 2에 대한 semantic vector 생성
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getSemanticType() == type2) {
                int[] semanticVector = new int[25];

                for(int k = 0; k < jCCNodeList.get(i).getIndexListOfEdges().size(); k++) { // 관련 있는 값들의 semantic vector 모두 더해주기
                    for(int t = 0; t < 25; t++) {
                        semanticVector[t] += jCCNodeList.get(jCCNodeList.get(i).getIndexListOfEdges().get(k)).getSemanticVector()[t];
                    }
                }

                jCCNodeList.get(i).setSemanticVector(semanticVector);

            }
        }
    }

    public void createMethodSemanticVector() { // type 3에 대한 semantic vector 생성
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getSemanticType() == type3) {
                int[] semanticVector = new int[25];

                for(int k = 0; k < jCCNodeList.get(i).getIndexListOfEdges().size(); k++) { // 관련 있는 값들의 semantic vector 모두 더해주기
                    for(int t = 0; t < 25; t++) {
                        semanticVector[t] += jCCNodeList.get(jCCNodeList.get(i).getIndexListOfEdges().get(k)).getSemanticVector()[t];
                    }
                }

                for(int k = 0; k < 25; k++) {
                    semanticVector[k] += jCCNodeList.get(i).getStructureVector()[k];
                }

                jCCNodeList.get(i).setSemanticVector(semanticVector);
            }
        }

    }
    // 해당 step까지 올라가서 존재하는 모든 variable structure vector 다 가져와서 더해주기
    public int[] getRelatedValues(jCCNode node, int tempCount, int totalCount, int[] semanticVector) {
        for(int i = 0; i < 25; i++) { // 현재 노드의 structure vector 더해주기
            semanticVector[i] += node.getStructureVector()[i];
        }

        if(node.getIndexListOfEdges().isEmpty()) {
            return semanticVector;
        }

        if(tempCount == totalCount) {

            return semanticVector;
        } else {
            for(int i = 0; i < node.getIndexListOfEdges().size(); i++) {
                semanticVector = getRelatedValues(jCCNodeList.get(node.getIndexListOfEdges().get(i)),
                        tempCount + 1, totalCount, semanticVector);
            }
        }

        return semanticVector;
    }

    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

    public SemanticVectorGenerator(List<jCCNode> jCCNodeList, int countStep) {
        this.jCCNodeList = jCCNodeList;
        this.countStep = countStep;
    }

}

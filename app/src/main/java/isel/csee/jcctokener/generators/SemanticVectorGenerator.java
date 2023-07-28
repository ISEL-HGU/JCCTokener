package isel.csee.jcctokener.generators;

import isel.csee.jcctokener.node.jCCNode;

import java.util.*;

// Assignment node에서 semantic vector 업데이트를 어떻게 해줄 것인지
// 이거를 Visitor로 만들어 주는 것이 맞나?


public class SemanticVectorGenerator {
    private List<jCCNode> jCCNodeList;

    public void createVariableSemanticVector() {

    }

    public void createOperatorSemanticVector() {

    }

    public void createMethodSemanticVector() {

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

package isel.csee.jcctokener.parser;

import isel.csee.jcctokener.calculate.HashValueRepository;
import isel.csee.jcctokener.node.jCCNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentFileData { // 실제로 사용하는 것들은 이 Class 객체
    private String filePath;
    private String methodName;
    private List<jCCNode> jCCNodeList;
    private List<double[]> type1SemanticVector = new ArrayList<>();
    private List<double[]> type2SemanticVector = new ArrayList<>();
    private List<double[]> type3SemanticVector = new ArrayList<>();
    private List<String> actionTokenList = new ArrayList<>();
    private List<HashValueRepository> hashValueRepositoryList;

    public void analyzeStudentFile() {
        try {
            System.out.println(filePath);
            String source = new String(Files.readAllBytes(Paths.get(filePath)));
            jCCParser jCCParser = new jCCParser(source);

            jCCParser.parseCodes();
            actionTokenList = jCCParser.getActionTokenList();
            jCCNodeList = jCCParser.getjCCNodeList();
            methodName = jCCParser.getMethodName();

            sortSemanticVector();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sortSemanticVector() {
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getSemanticType() == 1) {
                type1SemanticVector.add(jCCNodeList.get(i).getSemanticVector());
            } else if(jCCNodeList.get(i).getSemanticType() == 2) {
                type2SemanticVector.add(jCCNodeList.get(i).getSemanticVector());
            } else if(jCCNodeList.get(i).getSemanticType() == 3) {
                type3SemanticVector.add(jCCNodeList.get(i).getSemanticVector());
            } else {
                type1SemanticVector.add(jCCNodeList.get(i).getSemanticVector());
            }
        }
    }

    public StudentFileData(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

    public List<String> getActionTokenList() {
        return actionTokenList;
    }

    public void setActionTokenList(List<String> actionTokenList) {
        this.actionTokenList = actionTokenList;
    }

    public List<HashValueRepository> getHashValueRepositoryList() {
        return hashValueRepositoryList;
    }

    public void setHashValueRepositoryList(List<HashValueRepository> hashValueRepositoryList) {
        this.hashValueRepositoryList = hashValueRepositoryList;
    }

    public List<double[]> getType1SemanticVector() {
        return type1SemanticVector;
    }

    public void setType1SemanticVector(List<double[]> type1SemanticVector) {
        this.type1SemanticVector = type1SemanticVector;
    }

    public List<double[]> getType2SemanticVector() {
        return type2SemanticVector;
    }

    public void setType2SemanticVector(List<double[]> type2SemanticVector) {
        this.type2SemanticVector = type2SemanticVector;
    }

    public List<double[]> getType3SemanticVector() {
        return type3SemanticVector;
    }

    public void setType3SemanticVector(List<double[]> type3SemanticVector) {
        this.type3SemanticVector = type3SemanticVector;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}

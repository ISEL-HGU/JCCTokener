package isel.csee.jcctokener.parser;

import isel.csee.jcctokener.calculate.HashValueRepository;
import isel.csee.jcctokener.node.jCCNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentFileAnalyzer { // 실제로 사용하는 것들은 이 Class 객체
    private String filePath;
    private List<jCCNode> jCCNodeList;
    private List<String> actionTokenList;
    private List<HashValueRepository> hashValueRepositoryList;

    public void analyzeStudentFile() {
        try {
            String source = new String(Files.readAllBytes(Paths.get(filePath)));
            jCCParser jCCParser = new jCCParser(source);

            jCCParser.parseCodes();
            actionTokenList = jCCParser.getActionTokenList();
            jCCNodeList = jCCParser.getjCCNodeList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public StudentFileAnalyzer(String filePath) {
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
}

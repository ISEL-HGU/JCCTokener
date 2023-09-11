package isel.csee.jcctokener.parser;

import isel.csee.jcctokener.calculate.HashValueRepository;
import isel.csee.jcctokener.node.jCCNode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentFileAnalyzer { // 실제로 사용하는 것들은 이 Class 객체
    private String filePath;
    private String methodName;
    private List<jCCNode> jCCNodeList;
    private List<double[]> type1SemanticVector = new ArrayList<>();
    private List<double[]> type2SemanticVector = new ArrayList<>();
    private List<double[]> type3SemanticVector = new ArrayList<>();
    private List<String> actionTokenList;
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

//            File file = new File("/Users/kimdong-gyu/Desktop/javaTempFile/semantic " + filePath.substring(filePath.lastIndexOf(".") - 13, filePath.lastIndexOf(".")) + ".txt");
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
//
//            File file2 = new File("/Users/kimdong-gyu/Desktop/javaTempFile/structure " + filePath.substring(filePath.lastIndexOf(".") - 13, filePath.lastIndexOf(".")) + ".txt");
//            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2, true));
//            bufferedWriter.newLine();
//            bufferedWriter.newLine();
//            bufferedWriter.newLine();
//
//            bufferedWriter2.newLine();
//            bufferedWriter2.newLine();
//            bufferedWriter2.newLine();
//            bufferedWriter.flush();
//            bufferedWriter2.flush();
//
//
//            for(int i = 0; i < jCCNodeList.size(); i++) {
//                if(jCCNodeList.get(i).getSemanticType() != 3)
//                    continue;
//
//                if(i == 0) {
//                    bufferedWriter.write(filePath);
//                    bufferedWriter2.write(filePath);
//                }
//                bufferedWriter.write(jCCNodeList.get(i).getVariableName());
//                bufferedWriter.newLine();
//
//                bufferedWriter2.write(jCCNodeList.get(i).getVariableName());
//                bufferedWriter2.newLine();
//
//                String temp = "";
//                String temp2 = "";
//                for(int j = 0; j < 25; j++) {
//                    temp += (int)jCCNodeList.get(i).getSemanticVector()[j] + " ";
//                    temp2 += (int)jCCNodeList.get(i).getStructureVector()[j] + " ";
//                }
//                bufferedWriter.write(temp);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//
//                bufferedWriter2.write(temp);
//                bufferedWriter2.newLine();
//                bufferedWriter2.flush();
//            }

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

package isel.csee.jcctokener.parser;

import isel.csee.jcctokener.node.jCCNode;

import java.io.File;
import java.util.List;


/*
이 파서 클래스에서 파싱이 시작
학번으로 이루어진 directory를 가지고 와서 그 부분에서 부터 파싱을 시작해주는 형태

ActionToken 만들어줘야 함 / parsing 하는 과정에서 따로 뽑아 와야 할 듯
 */
public class StudentFileParser {
    private String fileInputPath; // 이 inputPath는 전체 디렉토리의 주소가 아닌 학번이 들어있는 directory의 주소를 의미
    private String studentId;
    private List<List<jCCNode>> jCCNodeList;

    public void parseStudentFile() {
        File file = new File(fileInputPath);

        studentId = fileInputPath.substring(fileInputPath.lastIndexOf("_") + 1);
        StudentFileAnalyzer studentFileAnalyzer = new StudentFileAnalyzer(studentId);

        studentFileAnalyzer.analyzeStudentFile(fileInputPath);

        jCCNodeList = studentFileAnalyzer.getjCCNodeList(); // 해당 학번 학생에 대한 모든 자바 파일 정보가 들어있게 됨

    }

    public StudentFileParser(String fileInputPath) {
        this.fileInputPath = fileInputPath;
    }

    public String getFileInputPath() {
        return fileInputPath;
    }

    public void setFileInputPath(String fileInputPath) {
        this.fileInputPath = fileInputPath;
    }

    public List<List<jCCNode>> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<List<jCCNode>> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

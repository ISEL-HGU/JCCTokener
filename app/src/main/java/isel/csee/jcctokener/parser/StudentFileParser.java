package isel.csee.jcctokener.parser;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/*
이 파서 클래스에서 파싱이 시작
학번으로 이루어진 directory를 가지고 와서 그 부분에서 부터 파싱을 시작해주는 형태

ActionToken 만들어줘야 함 / parsing 하는 과정에서 따로 뽑아 와야 할 듯
 */
public class StudentFileParser {
    private List<StudentFileAnalyzer> studentFileAnalyzerList = new ArrayList<>();

    public void parseStudentFile(String fileInputPath) {
        File file = new File(fileInputPath);
        File[] fileList = file.listFiles();

        if(fileList != null) {
            for(int i = 0; i < fileList.length; i++) {
                if(fileList[i].isDirectory()) {
                    parseStudentFile(fileList[i].getPath());
                } else {
                    if(fileList[i].getName().substring(fileList[i].getName().lastIndexOf(".") + 1).equals("java")) {
                        StudentFileAnalyzer studentFileAnalyzer = new StudentFileAnalyzer(fileList[i].getPath());
                        studentFileAnalyzer.analyzeStudentFile();

                        studentFileAnalyzerList.add(studentFileAnalyzer);
                    }
                }
            }
        }
    }

    public List<StudentFileAnalyzer> getStudentFileAnalyzerList() {
        return studentFileAnalyzerList;
    }

    public void setStudentFileAnalyzerList(List<StudentFileAnalyzer> studentFileAnalyzerList) {
        this.studentFileAnalyzerList = studentFileAnalyzerList;
    }
}

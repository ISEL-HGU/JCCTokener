package isel.csee.jcctokener.parser;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


/*
이 파서 클래스에서 파싱이 시작
학번으로 이루어진 directory를 가지고 와서 그 부분에서 부터 파싱을 시작해주는 형태

ActionToken 만들어줘야 함 / parsing 하는 과정에서 따로 뽑아 와야 할 듯
 */
public class StudentFileFinder {
    private List<StudentFileData> studentFileDataList = new ArrayList<>();

    public static int sum(int a, int b){
        return a + b;
    }

    public void parseStudentFile(String fileInputPath) {
        File file = new File(fileInputPath);
        File[] fileList = file.listFiles();

        if(fileList != null) {
            for(int i = 0; i < fileList.length; i++) {
                if(fileList[i].isDirectory()) {
                    parseStudentFile(fileList[i].getPath());
                } else {
                    if(fileList[i].getName().substring(fileList[i].getName().lastIndexOf(".") + 1).equals("java")) {
                        StudentFileData studentFileData = new StudentFileData(fileList[i].getPath());
                        studentFileData.analyzeStudentFile();

                        studentFileDataList.add(studentFileData);
                    }
                }
            }
        } else {
            if(file.isFile()) { // file을 찾은 경우에는 parsing 실행
                StudentFileData studentFileData = new StudentFileData(fileInputPath);
                studentFileData.analyzeStudentFile();

                studentFileDataList.add(studentFileData);
            }
        }
    }

    public List<StudentFileData> getStudentFileAnalyzerList() {
        return studentFileDataList;
    }

    public void setStudentFileAnalyzerList(List<StudentFileData> studentFileDataList) {
        this.studentFileDataList = studentFileDataList;
    }
}

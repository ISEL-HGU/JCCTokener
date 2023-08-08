package isel.csee.jcctokener.parser;

import isel.csee.jcctokener.node.jCCNode;
import org.eclipse.jdt.core.dom.ASTParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudentFileAnalyzer {
    private String studentId;
    private List<List<jCCNode>> jCCNodeList = new ArrayList<>();

    public void analyzeStudentFile(String directoryPath) {
        try {
            File targetDirectory = new File(directoryPath);
            File[] relatedFolderList = targetDirectory.listFiles(); // 해당 path 안에 존재하는 파일의 리스트

            if (relatedFolderList != null) {
                for (int i = 0; i < relatedFolderList.length; i++) {
                    if (relatedFolderList[i].isDirectory()) { // 폴더인 경우
                        analyzeStudentFile(relatedFolderList[i].getPath()); // recursion / 재귀적으로 다시 폴더 탐색
                    } else { // 폴더가 아니라 파일인 경우
                        if(relatedFolderList[i].getName().substring(relatedFolderList[i].getName().lastIndexOf(".") + 1).equals("java")) {
                            System.out.println("StudentId: " + studentId + " ClassName: " + relatedFolderList[i].getName());
                            String source = new String(Files.readAllBytes(Paths.get(relatedFolderList[i].getPath())));
                            jCCParser jCCParser = new jCCParser(source);
                            jCCParser.parserCodes();
                            jCCNodeList.add(jCCParser.getjCCNodeList());
                        }
                    }
                }
            }
        } catch (IOException e) {

        }

    }

    public StudentFileAnalyzer(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public List<List<jCCNode>> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<List<jCCNode>> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

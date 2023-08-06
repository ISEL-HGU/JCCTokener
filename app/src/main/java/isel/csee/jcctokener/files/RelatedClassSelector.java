package isel.csee.jcctokener.files;

import org.eclipse.jdt.internal.core.util.CodeSnippetParsingUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/*
File의 이름을 가지고 분류하는 것은 무의미 한 것처럼 보일 듯 -> 사람마다 파일의 이름을 다르게 지어서 업로드 하기 때문
그냥 들어온 모든 파일에 대해서 분석한 다음, 파일에 해당하는 학번을 입력 해주면 될 듯? - 같은 학번으로 여러개의 이름을 가진 파일들이 생성될 것 같긴 함
-> 리스트로 만들어서 사용? MultiValueMap 사용해서 값을 다 가지고 오면 될 듯
    -> 이 방법을 사용할 경우 filtering 부분이 무조건 필요해질 듯 / 같은 기능을 하는 파일이 아니라 모든 파일에 대해서 비교를 진행해야 하기 때문에

그럼 진행 순서는 디렉토리의 주소를 받은 후, 그 디렉토리 내부에 존재하는 학생들의 디렉토리를 전부 순회해서 MultiValueMap에 학번과 해당 파일을 파싱한 결과를 넣어주면 될 듯 / List를 넣어줘야 하는 건가
 */

public class RelatedClassSelector {
    private String inputFilePath;
    private String studentId;
    private String outputFilePath; // 이 file path는 클래스 이름의 폴더를 모아놓은 곳

    public void selectRelatedClass(String directoryPath) {
        File targetDirectory = new File(directoryPath);
        File[] relatedFolderList = targetDirectory.listFiles(); // 해당 path 안에 존재하는 파일의 리스트
        extractStudentId();

        if(relatedFolderList != null) {
            for(int i = 0; i < relatedFolderList.length; i++) {
                if(relatedFolderList[i].isDirectory()) { // 폴더인 경우
                    selectRelatedClass(relatedFolderList[i].getPath()); // recursion / 재귀적으로 다시 폴더 탐색
                } else { // 폴더가 아니라 파일인 경우
                    if(relatedFolderList[i].getName().substring(relatedFolderList[i].getName().lastIndexOf(".") + 1).equals("java")) {

                    } else {
                        continue;
                    }

                    System.out.println("Test: " + relatedFolderList[i].getName());
                    File outputFileDirectory = new File(outputFilePath);
                    System.out.println(outputFilePath);
                    File[] outputFolderList = outputFileDirectory.listFiles(); // 클래스 이름의 폴더를 모아놓은 리스트
//                    System.out.println("HERE");
//                    for(int k = 0; k < outputFolderList.length; k++) {
//                        System.out.println(outputFolderList[k].getName());
//                    }
//                    System.out.println("");
//                    System.out.println("");
//                    System.out.println("");

                    boolean checkFolder = false;
                    String fileName = null;
                    String targetOutputPath = null;


                    for(int k = 0; k < outputFolderList.length; k++) {
                        if(outputFolderList[k].getName().equals(relatedFolderList[i].getName())) { // 클래스 이름을 가지는 폴더가 이미 존재하는 경우
                            checkFolder = true;

                            targetOutputPath = outputFolderList[k].getPath();
                            targetOutputPath = targetOutputPath.substring(0, targetOutputPath.lastIndexOf("."));
                        }
                    }

                    if(checkFolder) {
                        System.out.println("true");
                    } else {
                        System.out.println("false");
                    }

                    fileName = relatedFolderList[i].getName();

                    if(checkFolder) { // 같은 이름을 가지는 폴더가 존재하는 경우
//                        System.out.println(outputFilePath);
//                        System.out.println(targetOutputPath);
                        File studentFile = new File(targetOutputPath, studentId);
                        System.out.println("target output path: " + targetOutputPath);
                        studentFile.mkdir();

                        copyTargetFile(relatedFolderList[i].getPath(), studentFile.getPath());
                    } else { // 존재하지 않는 경우

                        File createdFile = new File(outputFilePath, fileName);
                        createdFile.mkdir();

                        File studentFile = new File(createdFile.getPath(), studentId);
                        studentFile.mkdir();

                        copyTargetFile(relatedFolderList[i].getPath(), studentFile.getPath());
                    }
                }
            }
        }
    }

    public void copyTargetFile(String sourceFilePath, String directoryFilePath) {
        try {
            Path filePath = Paths.get(sourceFilePath);
            Path directoryPath = Paths.get(directoryFilePath);

            Files.copy(filePath, directoryPath);
        } catch (IOException e) {

        }
    }

    public void extractStudentId() {
        studentId = inputFilePath.substring(inputFilePath.lastIndexOf("_") + 1);
    }

    public RelatedClassSelector(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }
}

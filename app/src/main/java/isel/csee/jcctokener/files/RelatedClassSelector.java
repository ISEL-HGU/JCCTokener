package isel.csee.jcctokener.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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
                    System.out.println("Test: " + relatedFolderList[i].getName());
                    File outputFileDirectory = new File(outputFilePath);
                    File[] outputFolderList = outputFileDirectory.listFiles(); // 클래스 이름의 폴더를 모아놓은 리스트
                    boolean checkFolder = false;
                    String fileName = null;
                    String targetOutputPath = null;


                    for(int k = 0; k < outputFolderList.length; k++) {
                        if(outputFolderList[k].getName().equals(relatedFolderList[i].getName())) { // 클래스 이름을 가지는 폴더가 이미 존재하는 경우
                            checkFolder = true;

                            targetOutputPath = outputFolderList[k].getPath();
                        }
                    }

                    fileName = relatedFolderList[i].toString();

                    if(checkFolder) { // 같은 이름을 가지는 폴더가 존재하는 경우
                        System.out.println("존재");
                        File studentFile = new File(targetOutputPath, studentId);
                        studentFile.mkdir();

                        copyTargetFile(relatedFolderList[i].getPath(), studentFile.getPath());
                    } else { // 존재하지 않는 경우
                        System.out.println("존재 X");
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

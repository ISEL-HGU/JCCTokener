package isel.csee.jcctokener;

import isel.csee.jcctokener.parser.StudentFileData;
import isel.csee.jcctokener.parser.StudentFileFinder;
import isel.csee.jcctokener.parser.jCCParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class jCCTokener {
    // method 단위로 파싱을 진행할 수는 없지만, method마다 각각 jCCNode를 생성해주면 method끼리 따로 계산하는 것이 가능해질 수도
    // 여기서 getter / setter를 어떻게 구분할 것인지가 조금 의문

    public static void main(String[] args) throws IOException {
        jCCTokener jCCTokener = new jCCTokener();

//        jCCTokener.run(args);
        File file = new File("/Users/kimdong-gyu/Desktop/HGU/jCCTokener/JCCTokener/app/src/main/resources/test/isel/codes/Division.java");
        File[] files = file.listFiles();
        List<StudentFileData> studentFileDataList = new ArrayList<>();

        if(file.isDirectory()) {
            for(int i = 0; i < files.length; i++) {
                StudentFileFinder studentFileFinder = new StudentFileFinder();
                studentFileFinder.parseStudentFile(files[i].getPath());

                studentFileDataList.addAll(studentFileFinder.getStudentFileAnalyzerList());
            }
        } else {
            StudentFileFinder studentFileFinder = new StudentFileFinder();
            studentFileFinder.parseStudentFile(file.getPath());

            studentFileDataList.addAll(studentFileFinder.getStudentFileAnalyzerList());
        }

//
//        SimilarityCalculator similarityCalculator = new SimilarityCalculator(studentFileAnalyzerList);
//        similarityCalculator.calculateSimilarity();



    }

    public void run(String[] args) throws IOException {
        String temp;
        String filePath = args[0];
        String source = new String(Files.readAllBytes(Paths.get(filePath)));

//        jCCTokener.fileListGetter("/Users/kimdong-gyu/Desktop/JChecker/JCCTokener/app/src/main/java");


        jCCParser jCCParser = new jCCParser(source);
        jCCParser.parseCodes();
    }

    public static void fileListGetter(String directoryPath) {
        File path = new File(directoryPath);
        File[] fileList = path.listFiles();

        for(int i = 0; i < fileList.length; i++) {
            if(fileList[i].isDirectory()) {
                fileListGetter(fileList[i].getPath());
            } else {

            }
        }

    }
}

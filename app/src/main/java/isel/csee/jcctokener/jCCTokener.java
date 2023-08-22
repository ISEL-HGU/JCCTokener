package isel.csee.jcctokener;

import isel.csee.jcctokener.calculate.SimilarityCalculator;
import isel.csee.jcctokener.parser.StudentFileAnalyzer;
import isel.csee.jcctokener.parser.StudentFileParser;
import isel.csee.jcctokener.parser.jCCParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class jCCTokener {

    public static void main(String[] args) throws IOException {
        jCCTokener jCCTokener = new jCCTokener();

//        jCCTokener.run(args);

        File file = new File("/Users/kimdong-gyu/Desktop/HGU/JChecker/JCCTokener/2023-1-java/SourceCodes");
//        File file = new File("/Users/kimdong-gyu/Desktop/javaTempFile");
        File[] files = file.listFiles();
        List<StudentFileAnalyzer> studentFileAnalyzerList = new ArrayList<>();

        for(int i = 0; i < files.length; i++) {
            StudentFileParser studentFileParser = new StudentFileParser();
            studentFileParser.parseStudentFile(files[i].getPath());

            studentFileAnalyzerList.addAll(studentFileParser.getStudentFileAnalyzerList());
        }

        SimilarityCalculator similarityCalculator = new SimilarityCalculator(studentFileAnalyzerList);
        similarityCalculator.calculateSimilarity();



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

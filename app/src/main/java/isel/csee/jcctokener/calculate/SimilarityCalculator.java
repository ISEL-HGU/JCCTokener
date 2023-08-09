package isel.csee.jcctokener.calculate;

import isel.csee.jcctokener.parser.StudentFileAnalyzer;
import isel.csee.jcctokener.parser.StudentFileParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimilarityCalculator {
    private List<StudentFileAnalyzer> studentFileAnalyzerList;
    private int k = 3;

    public void calculateSimilarity() {
        try {
            ActionTokenLocator actionTokenLocator = new ActionTokenLocator(studentFileAnalyzerList);
            SimilarityVerifier similarityVerifier = new SimilarityVerifier(0.01);
            File file = new File("/Users/kimdong-gyu/Desktop/JChecker/JCCTokener/2023-1-java/clone-b.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

            actionTokenLocator.setAllActionTokens();
            studentFileAnalyzerList = actionTokenLocator.getStudentFileAnalyzerList();


            for(int i = 0; i < studentFileAnalyzerList.size(); i++) { // 모든 k-tokens 생성 / i번째 값을 가지고 계산
                List<HashValueRepository> tempList = actionTokenLocator.createAllKTokens(k, studentFileAnalyzerList.get(i).getActionTokenList(), studentFileAnalyzerList.get(i).getFilePath());
                studentFileAnalyzerList.get(i).setHashValueRepositoryList(tempList);


                List<HashValueRepository> relatedHashValueList = actionTokenLocator.locateActionToken(studentFileAnalyzerList.get(i));
                List<StudentFileAnalyzer> relatedStudentList = convertStudentFileAnalyzerList(relatedHashValueList);

                ActionTokenFilter actionTokenFilter = new ActionTokenFilter(relatedStudentList, studentFileAnalyzerList.get(i), 0.3, 0.3);
                List<StudentFileAnalyzer> filterdList = actionTokenFilter.filterActionTokens();
                System.out.println(filterdList.size());

                for(int j = 0; j < filterdList.size(); j++) {
                    System.out.println("test");
                    double type1Similarity = similarityVerifier.verifySimilarity(studentFileAnalyzerList.get(i), filterdList.get(j), 1);
                    double type2Similarity = similarityVerifier.verifySimilarity(studentFileAnalyzerList.get(i), filterdList.get(j), 2);
                    double type3Similarity = similarityVerifier.verifySimilarity(studentFileAnalyzerList.get(i), filterdList.get(j), 3);
                    double average = (type1Similarity + type2Similarity + type3Similarity) / 3;

                    String tempString = studentFileAnalyzerList.get(i).getFilePath() + "," + filterdList.get(j).getFilePath() + "," + average;
                    System.out.println(tempString);

                    bufferedWriter.write(studentFileAnalyzerList.get(i).getFilePath() + "," + filterdList.get(j).getFilePath() + "," + average);
                    bufferedWriter.write("\n");
                    bufferedWriter.flush();

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public List<StudentFileAnalyzer> convertStudentFileAnalyzerList(List<HashValueRepository> hashValueRepositoryList) {
        List<StudentFileAnalyzer> tempList = new ArrayList<>();

        for(int i = 0; i < hashValueRepositoryList.size(); i++) {
            for(int j = 0; j < studentFileAnalyzerList.size(); j++) {
                if(hashValueRepositoryList.get(i).getFilePath().equals(studentFileAnalyzerList.get(j).getFilePath())) {
                    tempList.add(studentFileAnalyzerList.get(j));
                    break;
                }
            }
        }

        return tempList;
    }

    public SimilarityCalculator(List<StudentFileAnalyzer> studentFileAnalyzerList) {
        this.studentFileAnalyzerList = studentFileAnalyzerList;
    }

    public List<StudentFileAnalyzer> getStudentFileAnalyzerList() {
        return studentFileAnalyzerList;
    }

    public void setStudentFileAnalyzerList(List<StudentFileAnalyzer> studentFileAnalyzerList) {
        this.studentFileAnalyzerList = studentFileAnalyzerList;
    }
}

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
            File file = new File("/Users/kimdong-gyu/Desktop/JChecker/JCCTokener/2023-1-java/clone-b.csv"); // 해당 파일에 output 생성
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

            actionTokenLocator.setAllActionTokens(); // 각 StudentFileAnalyzer 안에 존재하는 Action Token들을 정렬
            studentFileAnalyzerList = actionTokenLocator.getStudentFileAnalyzerList(); // Field value 다시 할당
            System.out.println(studentFileAnalyzerList.size());

            for(int i = 0; i < studentFileAnalyzerList.size(); i++) { // 왜 모든 파일이 다 돌아가지 않는 거지?
                List<HashValueRepository> tempList = actionTokenLocator.createAllKTokens(k, studentFileAnalyzerList.get(i).getActionTokenList(), studentFileAnalyzerList.get(i).getFilePath());
                studentFileAnalyzerList.get(i).setHashValueRepositoryList(tempList);
            }


            for(int i = 0; i < studentFileAnalyzerList.size(); i++) { // 모든 k-tokens 생성 / i번째 값을 가지고 계산
//                List<HashValueRepository> tempList = actionTokenLocator.createTargetKTokens(k, studentFileAnalyzerList.get(i).getActionTokenList(), studentFileAnalyzerList.get(i).getFilePath());
//                // 비교할 대상의 모든 토큰을 생성 -> 이 부분에서 문제가 생기는 듯 하네
//                studentFileAnalyzerList.get(i).setHashValueRepositoryList(tempList);


                List<HashValueRepository> relatedHashValueList = actionTokenLocator.locateActionToken(studentFileAnalyzerList.get(i));
                List<StudentFileAnalyzer> relatedStudentList = convertStudentFileAnalyzerList(relatedHashValueList);

                ActionTokenFilter actionTokenFilter = new ActionTokenFilter(relatedStudentList, studentFileAnalyzerList.get(i), 0.5, 0.4);
                List<StudentFileAnalyzer> filterdList = actionTokenFilter.filterActionTokens(); // 이 부분도 문제가 있는 듯 싶긴 함
                System.out.println(filterdList.size());

                for(int j = 0; j < filterdList.size(); j++) {
                    double type1Similarity = similarityVerifier.verifySimilarity(studentFileAnalyzerList.get(i), filterdList.get(j), 1);
                    double type2Similarity = similarityVerifier.verifySimilarity(studentFileAnalyzerList.get(i), filterdList.get(j), 2);
                    double type3Similarity = similarityVerifier.verifySimilarity(studentFileAnalyzerList.get(i), filterdList.get(j), 3);
                    double average = (type1Similarity + type2Similarity + type3Similarity) / 3;
                    if(average > 0.65) {
                        String tempString = studentFileAnalyzerList.get(i).getFilePath() + "," + filterdList.get(j).getFilePath() + "," + average;

                        bufferedWriter.write(studentFileAnalyzerList.get(i).getFilePath() + "," + filterdList.get(j).getFilePath() + "," + average);
                        bufferedWriter.write("\n");
                        bufferedWriter.flush();
                    }

                    if(type3Similarity > 1) {
                        System.out.println("HERE: " + type1Similarity + " " + type2Similarity + " " + type3Similarity);
                    }
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

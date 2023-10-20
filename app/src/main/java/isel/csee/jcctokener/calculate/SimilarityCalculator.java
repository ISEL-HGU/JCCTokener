package isel.csee.jcctokener.calculate;

import isel.csee.jcctokener.parser.StudentFileData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimilarityCalculator {
    private List<StudentFileData> studentFileDataList;
    private int k = 3;

    public void calculateSimilarity() {
        try {
            ActionTokenLocator actionTokenLocator = new ActionTokenLocator(studentFileDataList);
            SimilarityVerifier similarityVerifier = new SimilarityVerifier(0.1);
            File file = new File("/Users/kimdong-gyu/Desktop/HGU/JChecker/JCCTokener/app/src/main/resources/2023-1-java/testByMethod.csv"); // 해당 파일에 output 생성
//            File file = new File("/Users/kimdong-gyu/Desktop/2023-1-java/SelectedCodes/output.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

            actionTokenLocator.sortAllActionTokens(); // 각 StudentFileAnalyzer 안에 존재하는 Action Token들을 정렬
            studentFileDataList = actionTokenLocator.getStudentFileAnalyzerList(); // Field value 다시 할당

            for(int i = 0; i < studentFileDataList.size(); i++) { // 왜 모든 파일이 다 돌아가지 않는 거지?
                List<HashValueRepository> tempList = actionTokenLocator.createAllKTokens(k, studentFileDataList.get(i).getActionTokenList(), studentFileDataList.get(i).getFilePath());
                studentFileDataList.get(i).setHashValueRepositoryList(tempList);
            }


            for(int i = 0; i < studentFileDataList.size(); i++) { // 모든 k-tokens 생성 / i번째 값을 가지고 계산
                List<HashValueRepository> relatedHashValueList = actionTokenLocator.locateActionToken(studentFileDataList.get(i));
                List<StudentFileData> relatedStudentList = convertStudentFileAnalyzerList(relatedHashValueList);

                ActionTokenFilter actionTokenFilter = new ActionTokenFilter(relatedStudentList, studentFileDataList.get(i), 0.5, 0.4);
                List<StudentFileData> filterdList = actionTokenFilter.filterActionTokens(); // 이 부분도 문제가 있는 듯 싶긴 함



                for(int j = 0; j < filterdList.size(); j++) {
                    double type1Similarity = similarityVerifier.verifySimilarity(studentFileDataList.get(i), filterdList.get(j), 1);
                    double type2Similarity = similarityVerifier.verifySimilarity(studentFileDataList.get(i), filterdList.get(j), 2);
                    double type3Similarity = similarityVerifier.verifySimilarity(studentFileDataList.get(i), filterdList.get(j), 3);
                    double average = (type1Similarity + type2Similarity + type3Similarity) / 3.0;
                    if(average >= 0.65) {
                        if(!(studentFileDataList.get(i).getFilePath().equals(filterdList.get(j).getFilePath()))) {
                            System.out.println(studentFileDataList.get(i).getFilePath());
                            bufferedWriter.write(studentFileDataList.get(i).getFilePath() + "/" + studentFileDataList.get(i).getMethodName() + ","
                                    + "," + filterdList.get(j).getFilePath() + "/" + filterdList.get(j).getMethodName() + "," + average);
                            bufferedWriter.write("\n");
                            bufferedWriter.flush();
                        }
                    }
//                    System.out.println(studentFileAnalyzerList.get(i).getFilePath());
//                    System.out.println(filterdList.get(j).getFilePath());
//                    System.out.println(type1Similarity + " " + type2Similarity + " " + type3Similarity);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public List<StudentFileData> convertStudentFileAnalyzerList(List<HashValueRepository> hashValueRepositoryList) {
        List<StudentFileData> tempList = new ArrayList<>();

        for(int i = 0; i < hashValueRepositoryList.size(); i++) {
            for(int j = 0; j < studentFileDataList.size(); j++) {
                if(hashValueRepositoryList.get(i).getFilePath().equals(studentFileDataList.get(j).getFilePath())) {
                    tempList.add(studentFileDataList.get(j));
                    break;
                }
            }
        }

        return tempList;
    }

    public SimilarityCalculator(List<StudentFileData> studentFileDataList) {
        this.studentFileDataList = studentFileDataList;
    }

    public List<StudentFileData> getStudentFileAnalyzerList() {
        return studentFileDataList;
    }

    public void setStudentFileAnalyzerList(List<StudentFileData> studentFileDataList) {
        this.studentFileDataList = studentFileDataList;
    }

    public void testNode() {

    }
}

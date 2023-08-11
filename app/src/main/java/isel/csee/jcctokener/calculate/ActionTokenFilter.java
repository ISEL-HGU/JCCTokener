package isel.csee.jcctokener.calculate;

import isel.csee.jcctokener.parser.StudentFileAnalyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActionTokenFilter {
    private List<StudentFileAnalyzer> studentFileAnalyzerList; // 같은 hash value를 가지는 값들의 HashMap
    private StudentFileAnalyzer targetFile; // 비교하고자 하는 값
    private double actionTokenRatioReferencePoint;
    private double tokenRationReferencePoint;

    public List<StudentFileAnalyzer> filterActionTokens() {
        List<StudentFileAnalyzer> filterationList = new ArrayList<>();

        for(int i = 0; i < studentFileAnalyzerList.size(); i++) {
            int count = countSameActionTokens(targetFile, studentFileAnalyzerList.get(i));
            int max = Math.max(targetFile.getHashValueRepositoryList().size(), studentFileAnalyzerList.get(i).getHashValueRepositoryList().size());
            int min = Math.min(targetFile.getHashValueRepositoryList().size(), studentFileAnalyzerList.get(i).getHashValueRepositoryList().size());
            double actionTokenRation = count / min;
            double tokenRation = min / max;

            if(actionTokenRation >= actionTokenRatioReferencePoint && tokenRation >= tokenRationReferencePoint) {
                if(filterationList.contains(studentFileAnalyzerList.get(i))) {

                } else {
                    filterationList.add(studentFileAnalyzerList.get(i));
                }

            }
        }
        if(filterationList.size() == 0) { // 0으로 잡히는 파일들은 다 HashValue가 없다고 나오게 됨 + action token도 없다고 나오거나 or action token의 개수 만으로는 k-token을 만들 수가 없음
            System.out.println(targetFile.getFilePath() + "  " + targetFile.getHashValueRepositoryList().size() + "   " + targetFile.getActionTokenList().size());
            System.out.println(targetFile.getjCCNodeList().size());
        }


        return filterationList;
    }

    public int countSameActionTokens(StudentFileAnalyzer firstAnalyzer, StudentFileAnalyzer secondAnalyzer) {
        int count = 0;

        for(int i = 0; i < firstAnalyzer.getHashValueRepositoryList().size(); i++) {
            for(int j = 0; j < secondAnalyzer.getHashValueRepositoryList().size(); j++) {
                if(firstAnalyzer.getHashValueRepositoryList().get(i).getHashValue().equals(secondAnalyzer.getHashValueRepositoryList().get(j).getHashValue())) {
                    count++;
                }
            }
        }

        return count;
    }

    public ActionTokenFilter(List<StudentFileAnalyzer> studentFileAnalyzerList, StudentFileAnalyzer targetFile, double actionTokenRatioReferencePoint, double tokenRationReferencePoint) {
        this.studentFileAnalyzerList = studentFileAnalyzerList;
        this.targetFile = targetFile;
        this.actionTokenRatioReferencePoint = actionTokenRatioReferencePoint;
        this.tokenRationReferencePoint = tokenRationReferencePoint;
    }

    public List<StudentFileAnalyzer> getStudentFileAnalyzerList() {
        return studentFileAnalyzerList;
    }

    public void setStudentFileAnalyzerList(List<StudentFileAnalyzer> studentFileAnalyzerList) {
        this.studentFileAnalyzerList = studentFileAnalyzerList;
    }
}

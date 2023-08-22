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
            double count = countSameActionTokens(targetFile, studentFileAnalyzerList.get(i));
            double max = Math.max((double) targetFile.getHashValueRepositoryList().size(), (double) studentFileAnalyzerList.get(i).getHashValueRepositoryList().size());
            double min = Math.min((double) targetFile.getHashValueRepositoryList().size(), (double) studentFileAnalyzerList.get(i).getHashValueRepositoryList().size());
            double actionTokenRation = count / min;
            double tokenRation = min / max;

            if(actionTokenRation >= actionTokenRatioReferencePoint && tokenRation >= tokenRationReferencePoint) {
                if(filterationList.contains(studentFileAnalyzerList.get(i))) {

                } else {
                    filterationList.add(studentFileAnalyzerList.get(i));
                }

            }
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

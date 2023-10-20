package isel.csee.jcctokener.calculate;

import isel.csee.jcctokener.parser.StudentFileData;

import java.util.ArrayList;
import java.util.List;

public class ActionTokenFilter {
    private List<StudentFileData> studentFileDataList; // 같은 hash value를 가지는 값들의 HashMap
    private StudentFileData targetFile; // 비교하고자 하는 값
    private double actionTokenRatioReferencePoint;
    private double tokenRationReferencePoint;

    public List<StudentFileData> filterActionTokens() {
        List<StudentFileData> filterationList = new ArrayList<>();

        for(int i = 0; i < studentFileDataList.size(); i++) {
            double count = countSameActionTokens(targetFile, studentFileDataList.get(i));
            double max = Math.max((double) targetFile.getHashValueRepositoryList().size(), (double) studentFileDataList.get(i).getHashValueRepositoryList().size());
            double min = Math.min((double) targetFile.getHashValueRepositoryList().size(), (double) studentFileDataList.get(i).getHashValueRepositoryList().size());
            double actionTokenRation = count / min;
            double tokenRation = min / max;

            if(actionTokenRation >= actionTokenRatioReferencePoint && tokenRation >= tokenRationReferencePoint) {
                if(filterationList.contains(studentFileDataList.get(i))) {

                } else {
                    filterationList.add(studentFileDataList.get(i));
                }

            }
        }

        return filterationList;
    }

    public int countSameActionTokens(StudentFileData firstAnalyzer, StudentFileData secondAnalyzer) {
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

    public ActionTokenFilter(List<StudentFileData> studentFileDataList, StudentFileData targetFile, double actionTokenRatioReferencePoint, double tokenRationReferencePoint) {
        this.studentFileDataList = studentFileDataList;
        this.targetFile = targetFile;
        this.actionTokenRatioReferencePoint = actionTokenRatioReferencePoint;
        this.tokenRationReferencePoint = tokenRationReferencePoint;
    }

    public List<StudentFileData> getStudentFileAnalyzerList() {
        return studentFileDataList;
    }

    public void setStudentFileAnalyzerList(List<StudentFileData> studentFileDataList) {
        this.studentFileDataList = studentFileDataList;
    }
}

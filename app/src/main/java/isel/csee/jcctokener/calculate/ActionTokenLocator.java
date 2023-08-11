package isel.csee.jcctokener.calculate;

import isel.csee.jcctokener.parser.StudentFileAnalyzer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
시간 복잡도 측면에서 그냥 MultiValueMap이나 HashMap 사용하는 것이 이득일 듯 -> 비교 횟수가 너무 많아짐
차라리 이 HashMap 내부에서 같은 Hash를 공유하는 값끼리 계산 해버리는 건 어떨까 / 아 근데 그러면 계산의 중복이 많이 생길 듯

생각 해보니까 이거 method 단위로 잘라서 해야되는데 ..
String을 key value로 하는 HashMap을 사용하는 게 더 낫지 싶기도 하고
 */
public class ActionTokenLocator {
    private List<StudentFileAnalyzer> studentFileAnalyzerList;
    private HashMap<String, List<HashValueRepository>> hashValueMap = new HashMap<>(); // 모든 파일에 대한 hash / k-token이 다 들어가있는 list / filePath를 가져와서 사용해야 할 듯

    private int kValue = 3;

    public void setAllActionTokens() { // 이 method는 모든 studentFileAnalyzer에 대해서 실행 / locate 부분의 일부만 진행?
        for(int i = 0; i < studentFileAnalyzerList.size(); i++) { // lexical order 정렬
            studentFileAnalyzerList.get(i).setActionTokenList(sortActionTokens(studentFileAnalyzerList.get(i).getActionTokenList()));
            studentFileAnalyzerList.get(i).setHashValueRepositoryList(createAllKTokens(kValue, studentFileAnalyzerList.get(i).getActionTokenList(), studentFileAnalyzerList.get(i).getFilePath()));
        }

        System.out.println(studentFileAnalyzerList.size());
        // 이 method는 한 번 실행이 되기만 하면 되는 구조
    }

    public List<HashValueRepository> locateActionToken(StudentFileAnalyzer studentFileAnalyzer) { // 하나의 파일에 대해 다른 모든 파일들을 비교해주는 과정
        studentFileAnalyzer.setActionTokenList(sortActionTokens(studentFileAnalyzer.getActionTokenList())); // actionToken을 sort해서 다시 저장
        List<HashValueRepository> targetList = createTargetKTokens(kValue, studentFileAnalyzer.getActionTokenList(), studentFileAnalyzer.getFilePath()); // target node에 대해서 분석한 파일
        List<HashValueRepository> relateFileList = new ArrayList<>();

        for(int i = 0; i < targetList.size(); i++) {
            relateFileList.addAll(hashValueMap.get(targetList.get(i).getHashValue()));
        }
        // 동일한 hash값을 가지는 후보군 추려내는 과정
        // 이 함수가 실행되기 전에는 hashValueMap에 모든 hash 값이 들어가 있어야 함

        return relateFileList;
    }

    public List<HashValueRepository> createAllKTokens(int k, List<String> actionTokenList, String filePath) { // 하나의 파일에 대해서만 진행되는 과정
        List<HashValueRepository> returnList = new ArrayList<>();
        if(actionTokenList != null) {
            for(int i = 0; i < actionTokenList.size() - k + 1; i++) {
                StringBuilder stringBuilder = new StringBuilder();

                for(int j = 0; j < k; j++) {
                    if(j == k - 1) {
                        stringBuilder.append(actionTokenList.get(i + j)); // 이러면 -1이 잡히게 되네
                        stringBuilder.append(",");
                    } else {
                        stringBuilder.append(actionTokenList.get(i + j));
                    }
                }

                String tempString = stringBuilder.toString();
                String hashValue = convertStringToHashValue(tempString);
                HashValueRepository hashValueRepository = new HashValueRepository(tempString, hashValue, filePath);

                List<HashValueRepository> hashValueRepositoryList = hashValueMap.get(hashValue);
                if(hashValueRepositoryList == null) {
                    hashValueRepositoryList = new ArrayList<>();
                }
                hashValueRepositoryList.add(hashValueRepository);

                hashValueMap.put(hashValue, hashValueRepositoryList); // HashValueMap에 값을 넣는 것이 이 부분에서 수행해야 되는 부분인지?

                returnList.add(hashValueRepository);
            }
        }

        return returnList;
    }

    public List<HashValueRepository> createTargetKTokens(int k, List<String> actionTokenList, String filePath) { // 하나의 파일에 대해서만 진행되는 과정
        List<HashValueRepository> returnList = new ArrayList<>();
        if(actionTokenList != null) {
            for(int i = 0; i < actionTokenList.size() - k + 1; i++) {
                StringBuilder stringBuilder = new StringBuilder();

                for(int j = 0; j < k; j++) {
                    if(j == k - 1) {
                        stringBuilder.append(actionTokenList.get(i + j));
                        stringBuilder.append(",");
                    } else {
                        stringBuilder.append(actionTokenList.get(i + j));
                    }
                }

                String tempString = stringBuilder.toString();
                String hashValue = convertStringToHashValue(tempString);
                HashValueRepository hashValueRepository = new HashValueRepository(tempString, hashValue, filePath);

                returnList.add(hashValueRepository);
            }
        }

        return returnList;
    }

    public List<String> sortActionTokens(List<String> targetList) {
        if(targetList != null) {
            Collections.sort(targetList);
        }

        return targetList;
    }

    public String convertStringToHashValue(String targetString) {
        String returnValue = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(targetString.getBytes());
            StringBuilder stringBuilder = new StringBuilder();

            for (byte b : hashBytes) {
                stringBuilder.append(String.format("%02x", b & 0xff));
            }

            returnValue = stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {

        }

        return returnValue;
    }

    public ActionTokenLocator(List<StudentFileAnalyzer> studentFileAnalyzerList) {
        this.studentFileAnalyzerList = studentFileAnalyzerList;
    }

    public List<StudentFileAnalyzer> getStudentFileAnalyzerList() {
        return studentFileAnalyzerList;
    }

    public void setStudentFileAnalyzerList(List<StudentFileAnalyzer> studentFileAnalyzerList) {
        this.studentFileAnalyzerList = studentFileAnalyzerList;
    }

    public HashMap<String, List<HashValueRepository>> getHashValueMap() {
        return hashValueMap;
    }

    public void setHashValueMap(HashMap<String, List<HashValueRepository>> hashValueMap) {
        this.hashValueMap = hashValueMap;
    }
}

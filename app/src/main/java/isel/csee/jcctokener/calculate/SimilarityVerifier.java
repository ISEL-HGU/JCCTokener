package isel.csee.jcctokener.calculate;

import isel.csee.jcctokener.parser.StudentFileAnalyzer;

import java.util.Arrays;
import java.util.List;

public class SimilarityVerifier {
    private double decreaseStep;

    public double verifySimilarity(StudentFileAnalyzer firstStudentFile, StudentFileAnalyzer secondStudentFile, int type) {
        double totalSimilarity = 0;
        List<int[]> firstVectorCollection;
        List<int[]> secondVectorCollection;
        if(type == 2) {
            firstVectorCollection = firstStudentFile.getType2SemanticVector();
            secondVectorCollection = secondStudentFile.getType2SemanticVector();
        } else if(type == 3) {
            firstVectorCollection = firstStudentFile.getType3SemanticVector();
            secondVectorCollection = secondStudentFile.getType3SemanticVector();
        } else {
            firstVectorCollection = firstStudentFile.getType1SemanticVector();
            secondVectorCollection = secondStudentFile.getType1SemanticVector();
        }

        int[] markFirstVector = new int[firstVectorCollection.size()];
        int[] markSecondVector = new int[secondVectorCollection.size()];
        int maxVectorSize;

        if(firstVectorCollection.size() < secondVectorCollection.size()) {
            maxVectorSize = firstVectorCollection.size();
        } else {
            maxVectorSize = secondVectorCollection.size();
        }

        if(maxVectorSize == 0) {
            return 0;
        }

        Arrays.fill(markFirstVector, 0);
        Arrays.fill(markSecondVector, 0);

        for(double i = 1; i > 0; i -= decreaseStep) {
            for(int k = 0; k < firstVectorCollection.size(); k++) {
                if(markFirstVector[k] == 1) continue;

                for(int j = 0; j < secondVectorCollection.size(); j++) {
                    if(markSecondVector[j] == 1) continue;

                    double similarity = calculateCosineSimilarity(firstVectorCollection.get(k), secondVectorCollection.get(j));

                    if(similarity > i) {
                        totalSimilarity += similarity;
                        markFirstVector[k] = 1;
                        markSecondVector[j] = 1;
                    }
                }
            }
        }

        return totalSimilarity / maxVectorSize;
    }

    public double calculateCosineSimilarity(int[] firstVector, int[] secondVector) {
        int firstSize = 0;
        int secondSize = 0;
        double totalSize = 0;
        int totalInnerProduct = 0;
        double returnValue;

        for(int i = 0; i < 25; i++) {
            firstSize += firstVector[i] * firstVector[i];
            secondSize += secondVector[i] * secondVector[i];
            totalInnerProduct += firstVector[i] * secondVector[i];
        }

        totalSize = Math.sqrt(firstSize) * Math.sqrt(secondSize);

        returnValue = totalInnerProduct / totalSize;

        return returnValue;
    }

    public SimilarityVerifier(double decreaseStep) {
        this.decreaseStep = decreaseStep;
    }
}

package isel.csee.jcctokener.calculate;

import java.util.Arrays;
import java.util.List;

public class SimilarityCalculator {
    private double decreaseStep;

    public double calculateTotalSimilarity(List<int[]> firstVectorCollection, List<int[]> secondVectorCollection) {
        double totalSimilarity = 0;
        int[] markFirstVector = new int[firstVectorCollection.size()];
        int[] markSecondVector = new int[secondVectorCollection.size()];
        int maxVectorSize;

        if(firstVectorCollection.size() < secondVectorCollection.size()) {
            maxVectorSize = firstVectorCollection.size();
        } else {
            maxVectorSize = secondVectorCollection.size();
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
            firstSize += firstVector[i] ^ 2;
            secondSize += secondVector[i] ^ 2;
            totalInnerProduct += firstVector[i] * secondVector[i];
        }

        totalSize = Math.sqrt(firstSize) * Math.sqrt(secondSize);

        returnValue = totalInnerProduct / totalSize;

        return returnValue;
    }
}

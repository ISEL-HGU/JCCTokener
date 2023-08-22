package isel.csee.jcctokener.calculate;

import isel.csee.jcctokener.parser.StudentFileAnalyzer;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import java.util.Arrays;
import java.util.List;

public class SimilarityVerifier {
    private double decreaseStep;

    public double verifySimilarity(StudentFileAnalyzer firstStudentFile, StudentFileAnalyzer secondStudentFile, int type) {
        double totalSimilarity = 0;
        List<double[]> firstVectorCollection;
        List<double[]> secondVectorCollection;
        if(type == 2) { // type에 따라서 vector를 다르게 받아옴
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
        double maxVectorSize;

        if(firstVectorCollection.size() < secondVectorCollection.size()) {
            maxVectorSize = secondVectorCollection.size();
        } else {
            maxVectorSize = firstVectorCollection.size();
        }

        if(maxVectorSize == 0) { // type2에 자주 발생하는 case
            return 0;
        }

        Arrays.fill(markFirstVector, 0);
        Arrays.fill(markSecondVector, 0);

        for(double i = 1; i > 0; i -= decreaseStep) {
            for(int k = 0; k < firstVectorCollection.size(); k++) {
                if(markFirstVector[k] == 1) continue; // 이미 마킹 되어있으면 stop

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

    public double calculateCosineSimilarity(double[] firstVector, double[] secondVector) {
        RealVector vector1 = new ArrayRealVector(firstVector);
        RealVector vector2 = new ArrayRealVector(secondVector);

        double dotProduct = vector1.dotProduct(vector2);
        double normVector1 = vector1.getNorm();
        double normVector2 = vector2.getNorm();


        return dotProduct/(normVector1 * normVector2);
    }

    public SimilarityVerifier(double decreaseStep) {
        this.decreaseStep = decreaseStep;
    }
}

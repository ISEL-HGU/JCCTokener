package isel.csee.jcctokener.node;

import org.eclipse.jdt.core.dom.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class jCCNode {
    private String nodeType;
    private String className;
    private String methodName;
    private String variableName;
    private ASTNode node;
    private int startPosition;
    private int semanticType;
    private double[] structureVector;
    private double[] semanticVector = new double[25];
    private List<Integer> IndexListOfEdges = new ArrayList<>();



    public jCCNode(String className, String methodName, String variableName, double[] structureVector) {
        this.className = className;
        this.methodName = methodName;
        this.variableName = variableName;
        this.structureVector = structureVector;
    }


    public jCCNode() {
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public double[] getStructureVector() {
        return structureVector;
    }

    public void setStructureVector(double[] structureVector) {
        this.structureVector = structureVector;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double[] getSemanticVector() {
        return semanticVector;
    }

    public void setSemanticVector(double[] semanticVector) {
        this.semanticVector = semanticVector;
    }

    public int getSemanticType() {
        return semanticType;
    }

    public void setSemanticType(int semanticType) {
        this.semanticType = semanticType;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }


    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public List<Integer> getIndexListOfEdges() {
        return IndexListOfEdges;
    }

    public void setIndexListOfEdges(List<Integer> indexListOfEdges) {
        IndexListOfEdges = indexListOfEdges;
    }

    public ASTNode getNode() {
        return node;
    }

    public void setNode(ASTNode node) {
        this.node = node;
    }
}

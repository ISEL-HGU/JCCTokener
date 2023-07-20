package isel.csee.jcctokener.node;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;

public class jCCNode {
    private String parentNodeType;
    private String className;
    private String methodName;
    private String variableName;
    private int semanticType;
    private int[] structureVector;
    private int[] semanticVector = new int[25];



    public jCCNode(String className, String methodName, String variableName, int[] structureVector) {
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

    public int[] getStructureVector() {
        return structureVector;
    }

    public void setStructureVector(int[] structureVector) {
        this.structureVector = structureVector;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int[] getSemanticVector() {
        return semanticVector;
    }

    public void setSemanticVector(int[] semanticVector) {
        this.semanticVector = semanticVector;
    }

    public int getSemanticType() {
        return semanticType;
    }

    public void setSemanticType(int semanticType) {
        this.semanticType = semanticType;
    }

    public String getParentNodeType() {
        return parentNodeType;
    }

    public void setParentNodeType(String parentNodeType) {
        this.parentNodeType = parentNodeType;
    }
}

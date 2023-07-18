package isel.csee.jcctokener.node;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;

public class jCCNode {
    private String className;
    private String methodName;
    private String variableName;
    private int[] structureVector;





    public jCCNode(String methodName, String variableName, int[] structureVector) {
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
}
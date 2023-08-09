package isel.csee.jcctokener.visitor;


import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;


import java.util.ArrayList;
import java.util.List;

public class jCCVisitor extends ASTVisitor {
    private List<int[]> structureVectorList = new ArrayList<>();
    private List<jCCNode> jCCNodeList = new ArrayList<>();
    private List<String> actionTokenList = new ArrayList<>();

    @Override
    public boolean visit(SimpleName node) {
        int[] structureVector = new int[25];
        ASTNode tempNode = node;
        jCCNode jCCNode = new jCCNode();

        if(tempNode.getParent() instanceof QualifiedName) {
            return super.visit(node);
        }

        while(tempNode != null) {
            structureVector = NodeType.searchType(tempNode, structureVector);

            if(tempNode instanceof MethodDeclaration) {
                jCCNode.setMethodName(((MethodDeclaration) tempNode).getName().toString());
            }

            if(tempNode instanceof TypeDeclaration) {
                jCCNode.setClassName(((TypeDeclaration) tempNode).getName().toString());
            }

            tempNode = tempNode.getParent();
        }

        if(jCCNode.getMethodName() == null) {
            jCCNode.setMethodName(jCCNode.getClassName());
        }

        jCCNode.setVariableName(node.getIdentifier());
        jCCNode.setStructureVector(structureVector);
        jCCNode.setNodeType(ASTNode.nodeClassForType(node.getParent().getNodeType()).getSimpleName());

        tempNode = node;

        if(tempNode.getParent() instanceof VariableDeclarationFragment) {
            if(((VariableDeclarationFragment) tempNode.getParent()).getName() == tempNode) {
                jCCNode.setSemanticVector(structureVector);
            }
        }

        if(tempNode.getParent() instanceof Assignment) {
            if(node.equals(((Assignment) tempNode.getParent()).getLeftHandSide())) {
                jCCNode.setUpdatePossibility(false);
            } else {
                jCCNode.setUpdatePossibility(true);
            }
        } else {
            jCCNode.setUpdatePossibility(true);
        }
        jCCNode.setStartPosition(node.getStartPosition());
        if(node.getParent() != null) {
            jCCNode.setNode(node.getParent());
        }
        jCCNodeList.add(jCCNode);
        structureVectorList.add(structureVector);

        tempNode = node;

        return super.visit(node);
    }

    @Override
    public boolean visit(InfixExpression node) {
        int[] structureVector = new int[25];
        String methodName = null;
        String className = null;
        ASTNode tempNode;
        tempNode = node;

        while(tempNode != null) {
            structureVector = NodeType.searchType(tempNode, structureVector);

            if(tempNode instanceof MethodDeclaration) {
                methodName = ((MethodDeclaration) tempNode).getName().toString();
            }

            if(tempNode instanceof TypeDeclaration) {
                className = ((TypeDeclaration) tempNode).getName().toString();
            }

            tempNode = tempNode.getParent();
        }

        jCCNode jCCNode = new jCCNode();

        jCCNode.setNodeType(ASTNode.nodeClassForType(node.getNodeType()).getSimpleName());
        jCCNode.setStructureVector(structureVector);
        jCCNode.setMethodName(methodName);
        jCCNode.setClassName(className);
        jCCNode.setVariableName(node.getOperator().toString());
        jCCNode.setStartPosition(node.getStartPosition());
        jCCNode.setNode(node);

        jCCNodeList.add(jCCNode);
        structureVectorList.add(structureVector);

        return super.visit(node);
    }

    @Override
    public boolean visit(VariableDeclarationFragment node) {
        if(node.getParent() instanceof VariableDeclarationStatement) {
            VariableDeclarationStatement parentNode = (VariableDeclarationStatement) node.getParent();
            Type type = parentNode.getType();

            actionTokenList.add(type.toString());
        }


        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) {
        actionTokenList.add(node.getName().toString());

        return super.visit(node);
    }

//    @Override
//    public boolean visit(Assignment node) {
//        int[] structureVector = new int[25];
//        jCCNode jCCNode = new jCCNode();
//
//        ASTNode tempNode;
//
//        tempNode = node;
//
//        while(tempNode != null) {
//            structureVector = NodeType.searchType(tempNode, structureVector);
//
//            if(tempNode instanceof MethodDeclaration) {
//                jCCNode.setMethodName(((MethodDeclaration) tempNode).getName().toString());
//            }
//            if(tempNode instanceof TypeDeclaration) {
//                jCCNode.setClassName(((TypeDeclaration) tempNode).getName().toString());
//            }
//            tempNode = tempNode.getParent();
//        }
//
//        jCCNode.setStructureVector(structureVector);
//        jCCNode.setVariableName(node.getOperator().toString());
//        jCCNode.setNodeType(ASTNode.nodeClassForType(node.getNodeType()).getSimpleName());
//        jCCNode.setStartPosition(node.getStartPosition());
//        jCCNode.setNode(node);
//
//        jCCNodeList.add(jCCNode);
//        structureVectorList.add(structureVector);
//
//        return super.visit(node);
//    }

    public List<int[]> getStructureVectorList() {
        return structureVectorList;
    }

    public void setStructureVectorList(List<int[]> structureVectorList) {
        this.structureVectorList = structureVectorList;
    }

    public List<String> getActionTokenList() {
        return actionTokenList;
    }

    public void setActionTokenList(List<String> actionTokenList) {
        this.actionTokenList = actionTokenList;
    }

    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

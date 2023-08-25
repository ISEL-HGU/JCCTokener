package isel.csee.jcctokener.visitor;


import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;


import java.util.ArrayList;
import java.util.List;

public class jCCVisitor extends ASTVisitor {
    private List<double[]> structureVectorList = new ArrayList<>();
    private List<jCCNode> jCCNodeList = new ArrayList<>();
    private List<String> actionTokenList = new ArrayList<>();

    @Override
    public boolean visit(SimpleName node) {
        double[] structureVector = new double[25];
        ASTNode tempNode = node;
        jCCNode jCCNode = new jCCNode();

        if(tempNode.getParent() instanceof QualifiedName) { // 이 부분은 package의 값을 제거하기 위해서 만들어준 부분
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
            jCCNode.setNode(node.getParent()); // parent를 node로 집어넣어 주게 되면 SimpleName node의 하나 윗단계에 존재하게 되는 노드의 값을 가져오게 됨
        }

        int count = 0;

        for(int i = 0; i < 25; i++) {
            count += (int)jCCNode.getStructureVector()[i];
        }

        if(node.getParent() instanceof VariableDeclarationFragment) {
            VariableDeclarationFragment fragment = (VariableDeclarationFragment) node.getParent();
            if(fragment.getParent() instanceof VariableDeclarationStatement) {
                VariableDeclarationStatement statement = (VariableDeclarationStatement) fragment.getParent();

                Type type = statement.getType();

                if(type.toString().equals(node.getIdentifier())) {
                    return super.visit(node); // 해당 SimpleName node가 type인 경우에는 jCCNodeList에 추가하지 않고 return
                }
            }
        }

        if(count > 0) {
            jCCNodeList.add(jCCNode);
            structureVectorList.add(structureVector);
        } // vector의 count가 0일 경우에는 List에 넣어주지 않음
        // 이 부분에서 field로 선언된 부분은 가져와지지 않는 것 같음 -> structure vector가 0

        return super.visit(node);
    }

    @Override
    public boolean visit(InfixExpression node) {
        double[] structureVector = new double[25];
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

    public List<double[]> getStructureVectorList() {
        return structureVectorList;
    }

    public void setStructureVectorList(List<double[]> structureVectorList) {
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

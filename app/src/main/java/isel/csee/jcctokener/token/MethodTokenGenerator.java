package isel.csee.jcctokener.token;

import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.*;

import java.util.Arrays;
import java.util.List;

public class MethodTokenGenerator extends ASTVisitor {
    private List<jCCNode> jCCNodeList;

    @Override
    public boolean visit(MethodInvocation node) {
        Expression methodInstance = node.getExpression();
        SimpleName methodName = node.getName();
        List<Expression> methodArgumentList = node.arguments();
        ASTNode tempNode = node;

        // method instance + method arguments semantic vector와 method name의 structure vector를 더해줘야 함

        String nodeClassName = null;
        String nodeMethodName = null;
        String methodInstanceName;



        int[] structureVector = new int[25];

        while(tempNode != null) {
            structureVector = NodeType.searchType(tempNode, structureVector);

            if(tempNode instanceof MethodDeclaration) {
                // method declaration case

                nodeMethodName = ((MethodDeclaration) tempNode).getName().toString();
            }

            if(tempNode instanceof TypeDeclaration) {
                // class declaration case

                nodeClassName = ((TypeDeclaration) tempNode).getName().toString();
            }
            tempNode = tempNode.getParent();
        } // structure vector 생성


        methodInstanceName = methodInstance.toString();


        int[] semanticVector = new int[25];

        semanticVector = addSemanticVector(nodeClassName, nodeMethodName, methodInstanceName, structureVector, semanticVector);
        semanticVector = addSemanticVector(nodeClassName, nodeMethodName, methodName.toString(), structureVector, semanticVector);

        for(int i = 0; i < methodArgumentList.size(); i++) {
            semanticVector = addSemanticVector(nodeClassName, nodeMethodName, methodArgumentList.get(i).toString(), structureVector, semanticVector);
        }

        for(int i = 0; i < 25; i++) {
            semanticVector[i] += structureVector[i];
        }

        jCCNode jCCNode = new jCCNode(nodeClassName, nodeMethodName, methodName.toString(), structureVector);
        updateSemanticVector(jCCNode, semanticVector);

        return super.visit(node);
    }


    public int[] addSemanticVector(String className, String methodName, String variableName, int[] structureVector, int[] semanticVector) {
        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getClassName().equals(className)) { // 클래스 이름
                if (jCCNodeList.get(i).getMethodName().equals(methodName)) { // 메소드 이름
                    if (jCCNodeList.get(i).getVariableName().equals(variableName)) { // 변수 이름
                        if (Arrays.equals(jCCNodeList.get(i).getStructureVector(), structureVector)) {
                            for(int k = 0; k < 25; k++) {
                                semanticVector[k] += jCCNodeList.get(i).getSemanticVector()[k];
                            }
                            break;
                        }
                    }
                }
            }
        }

        return semanticVector;
    }

    public void updateSemanticVector(jCCNode node, int[] semanticVector) { // 리스트에서 해당 노드를 찾고, semantic vector 수정해주는 method

        for(int i = 0; i < jCCNodeList.size(); i++) {
            if(jCCNodeList.get(i).getClassName().equals(node.getClassName())) { // 클래스 이름
                if (jCCNodeList.get(i).getMethodName().equals(node.getMethodName())) { // 메소드 이름
                    if (jCCNodeList.get(i).getVariableName().equals(node.getVariableName())) { // 변수 이름
                        if (Arrays.equals(jCCNodeList.get(i).getStructureVector(), node.getStructureVector())) {
                            jCCNodeList.get(i).setSemanticVector(semanticVector);
                            break;
                        }
                    }
                }
            }
        }
    }



    public MethodTokenGenerator(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }
}

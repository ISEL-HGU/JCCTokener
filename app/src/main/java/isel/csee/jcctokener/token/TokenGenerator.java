package isel.csee.jcctokener.token;


import isel.csee.jcctokener.types.NodeType;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TokenGenerator extends ASTVisitor {
    private List<int[]> structureVectorList;

    public List<int[]> toLexicalOrder() {
        Collections.sort(structureVectorList, (arr1, arr2) ->  {
            String str1 = Arrays.toString(arr1);
            String str2 = Arrays.toString(arr2);

            return str1.compareTo(str2);
        });

        return structureVectorList;
    }

    @Override
    public boolean visit(SimpleName node) {


        return super.visit(node);
    }

    public TokenGenerator(List<int[]> structureVectorList) {
        this.structureVectorList = structureVectorList;
    }

}

package isel.csee.jcctokener.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class DataTypeVisitor extends ASTVisitor {
    // ArrayType node도 type을 가질 수 있지만, 아래 두 method로 커버가 가능하다고 생각 되어 생략
    private List<String> actionTokenList;

    @Override
    public boolean visit(PrimitiveType node) {
        String typeName = node.getPrimitiveTypeCode().toString();

        actionTokenList.add(typeName);

        return super.visit(node);
    }

    @Override
    public boolean visit(SimpleType node) {
        String typeName = node.getName().toString();

        actionTokenList.add(typeName);
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodInvocation node) {
        String methodName = node.getExpression().toString();

        return super.visit(node);
    }

    public DataTypeVisitor(List<String> actionTokenList) {
        this.actionTokenList = actionTokenList;
    }

    public List<String> getActionTokenList() {
        return actionTokenList;
    }

    public void setActionTokenList(List<String> actionTokenList) {
        this.actionTokenList = actionTokenList;
    }
}

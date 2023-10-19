package isel.csee.jcctokener.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class DataTypeVisitor extends ASTVisitor {

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

package isel.csee.jcctokener.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class MethodVisitor extends ASTVisitor {

    @Override
    public boolean visit(MethodInvocation node) {
        return super.visit(node);
    }


}

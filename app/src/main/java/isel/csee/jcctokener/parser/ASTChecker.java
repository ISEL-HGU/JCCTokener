package isel.csee.jcctokener.parser;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;

public class ASTChecker extends ASTVisitor {
    private List<String> classList = new ArrayList<>();
    private List<String> methodDeclarationList = new ArrayList<>();
    private List<String> variableList = new ArrayList<>();
    private List<String> methodInvocationList = new ArrayList<>();

    @Override
    public boolean visit(TypeDeclaration node) {
        System.out.println("Class name: " + node.getName().getIdentifier()); // 클래스인 경우에 name, identifier print
        classList.add(node.getName().getIdentifier());
        return true;
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        System.out.println("Method name: " + node.getName().getIdentifier());
        methodDeclarationList.add(node.getName().getIdentifier());
        return true;
    }

    @Override
    public boolean visit(VariableDeclarationStatement node) {
        System.out.println("Variable: " + node.fragments().get(0));
        variableList.add(node.fragments().toString());
        return true;
    }

    @Override
    public boolean visit(MethodInvocation node) { // method 사용 부분
        System.out.println("Method Invocation: " + node.getName().getIdentifier());
        methodInvocationList.add(node.getName().getIdentifier());
        return true;
    }

    @Override
    public boolean visit(SwitchCase node) {
        System.out.println("Switch Case : " + node.toString());
        return true;
    }

    @Override
    public boolean visit(SwitchStatement node) {
        System.out.println("Switch Statement : " + node.getExpression().toString());
        return true;
    }

    @Override
    public boolean visit(ForStatement node) {
        System.out.println("For Statement : " + node.getExpression());
        return true;
    }


    public List<String> getClassList() {
        return classList;
    }

    public void setClassList(List<String> classList) {
        this.classList = classList;
    }

    public List<String> getMethodDeclarationList() {
        return methodDeclarationList;
    }

    public void setMethodDeclarationList(List<String> methodDeclarationList) {
        this.methodDeclarationList = methodDeclarationList;
    }

    public List<String> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<String> variableList) {
        this.variableList = variableList;
    }

    public List<String> getMethodInvocationList() {
        return methodInvocationList;
    }

    public void setMethodInvocationList(List<String> methodInvocationList) {
        this.methodInvocationList = methodInvocationList;
    }
}

package isel.csee.jcctokener.parser;

import isel.csee.jcctokener.generators.DataDependencyGenerator;
import isel.csee.jcctokener.visitor.*;
import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.generators.SemanticVectorGenerator;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;

import java.io.IOException;
import java.util.*;

/*
lexical order로 정렬은 이미 되어있는 상황 - startPosition

이 parser는 하나의 파일에 대해 파싱을 해주는 역할을 수행
한 사람에 대해서 여러 개의 파서가 나올 수 있음 / 이 parser는 여러 개가 생기는 용도
method를 코드 블럭 단위로 파싱해서 사용해야 하는데, 그러려면 어떻게 해야될까 ~~
 */
public class jCCParser {
    private String sourceCodes;
    private ASTParser parser;
    private String methodName;
    private List<jCCNode> jCCNodeList;
    private List<String> actionTokenList = new ArrayList<>();
    private jCCVisitor jCCVisitor = new jCCVisitor();
    private SemanticVectorGenerator semanticVectorGenerator;
    private DataDependencyGenerator dataDependencyGenerator;
    public void parseCodes() throws IOException {
        char[] contents = sourceCodes.toCharArray();

        parser = ASTParser.newParser(AST.JLS_Latest); // JLS15는 java source code version 의미
        parser.setSource(contents); // setSource - 파싱 할 소스코드를 정해주는 method
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        // setKind method - ASTParser 파싱 타입을 정해주는 method
        // K_COMPILATION_UNIT - 컴파일 단위를 나타내는 상수, 일반적인 소스코드 단일 파일을 의미(일반적으로 사용)
        // K_CLASS_BODY_DECLARATIONS - 클래스 본문 선언을 나타내는 상수, 클래스 내부 선언들을 파싱
        // K_STATEMENTS - 문장을 나타내는 상수, 코드 블록 내의 문장들을 파싱
        // K_EXPRESSION - 표현식을 나타내는 상수, 단일 표현식을 파싱

        Map<String, String> options = JavaCore.getOptions();
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);

        parser.setResolveBindings(true);
        parser.setCompilerOptions(options);

        parser.setIgnoreMethodBodies(false);
        parser.setBindingsRecovery(true);

        CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);

        VariableVisitor variableVisitor = new VariableVisitor(); // type1에 해당하는 노드 추출
        compilationUnit.accept(variableVisitor);
        jCCNodeList = variableVisitor.getjCCNodeList();

        OperatorVisitor operatorVisitor = new OperatorVisitor(jCCNodeList); // type2에 해당하는 노드 추출
        compilationUnit.accept(operatorVisitor);
        jCCNodeList = operatorVisitor.getjCCNodeList();

        MethodVisitor methodVisitor = new MethodVisitor(jCCNodeList); // type3에 해당하는 노드 추출
        compilationUnit.accept(methodVisitor);
        jCCNodeList = methodVisitor.getjCCNodeList();

        Collections.sort(jCCNodeList, new Comparator<jCCNode>() { // sorting
            @Override
            public int compare(jCCNode o1, jCCNode o2) {
                return Integer.compare(o1.getStartPosition(), o2.getStartPosition());
            }
        });

        DataTypeVisitor dataTypeVisitor = new DataTypeVisitor(actionTokenList); // Action Token에 해당하는 노드 추출
        compilationUnit.accept(dataTypeVisitor);
        actionTokenList = dataTypeVisitor.getActionTokenList();

    }

    public List<jCCNode> getjCCNodeList() {
        return jCCNodeList;
    }

    public void setjCCNodeList(List<jCCNode> jCCNodeList) {
        this.jCCNodeList = jCCNodeList;
    }

    public List<String> getActionTokenList() {
        return actionTokenList;
    }

    public void setActionTokenList(List<String> actionTokenList) {
        this.actionTokenList = actionTokenList;
    }

    public jCCParser(String sourceCodes) {
        this.sourceCodes = sourceCodes;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}

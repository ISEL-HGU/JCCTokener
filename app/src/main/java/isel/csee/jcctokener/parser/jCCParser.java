package isel.csee.jcctokener.parser;

import isel.csee.jcctokener.generators.DataDependencyGenerator;
import isel.csee.jcctokener.generators.StructureVectorGenerator;
import isel.csee.jcctokener.node.jCCNode;
import isel.csee.jcctokener.generators.TokenGenerator;
import isel.csee.jcctokener.generators.VariableTokenGenerator;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.List;
import java.util.Map;

public class jCCParser {
    private String sourceCodes;
    private ASTParser parser;
    private List<jCCNode> jCCNodeList;
    private StructureVectorGenerator structureVectorGenerator= new StructureVectorGenerator();
    private VariableTokenGenerator variableTokenGenerator;
    private DataDependencyGenerator dataDependencyGenerator;
    private TokenGenerator tokenGenerator;

    public ASTParser parserCodes() {
        char[] contents = sourceCodes.toCharArray();

        parser = ASTParser.newParser(AST.JLS15); // JLS15는 java source code version 의미
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

        compilationUnit.accept(structureVectorGenerator);

        tokenGenerator = new TokenGenerator(structureVectorGenerator.getStructureVectorList());

        List<int[]> structureVectorList = tokenGenerator.toLexicalOrder();

        jCCNodeList = structureVectorGenerator.getjCCNodeList();


        for(jCCNode tempNode : jCCNodeList) {
            System.out.println("variable: " + tempNode.getVariableName() + " Position: " + tempNode.getStartPosition() + " Type: " +
                    tempNode.getNodeType());
            for(int i : tempNode.getStructureVector()) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }


        dataDependencyGenerator = new DataDependencyGenerator(jCCNodeList);

        compilationUnit.accept(dataDependencyGenerator);
        jCCNodeList = dataDependencyGenerator.getjCCNodeList();

        for(int i = 0; i < jCCNodeList.size(); i++) {
            System.out.println(jCCNodeList.get(i).getVariableName());
            for(int k = 0; k < jCCNodeList.get(i).getIndexListOfEdges().size(); k++) {
                System.out.println("Dependency Node: " + jCCNodeList.get(jCCNodeList.get(i).getIndexListOfEdges().get(k)).getVariableName());
            }
        }







//        variableTokenGenerator = new VariableTokenGenerator(structureVectorGenerator.getjCCNodeList());

//        compilationUnit.accept(variableTokenGenerator);
//        for(jCCNode tempNode : variableTokenGenerator.getjCCNodeList()) {
//            System.out.println("variable: " + tempNode.getVariableName());
//            for(int i : tempNode.getSemanticVector()) {
//                System.out.print(i + " ");
//            }
//            System.out.println("");
//        }





        return parser;
    }


    public jCCParser(String sourceCodes) {
        this.sourceCodes = sourceCodes;
    }
}

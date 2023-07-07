package isel.csee.jcctokener.parser;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Statement;

public class ASTMaker {
    private String sourceCodes;
    private ASTParser parser;
    private ASTChecker astChecker = new ASTChecker();

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

        parser.setSource(contents);

        CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);

        compilationUnit.accept(astChecker);

        System.out.println("---");
        System.out.println("Class List -> " + astChecker.getClassList());
        System.out.println("Method Declaration List -> " + astChecker.getMethodDeclarationList());
        System.out.println("Variable List -> " + astChecker.getVariableList());
        System.out.println("Method Invocation List -> " + astChecker.getMethodInvocationList());
        System.out.println("Total Variable -> " + astChecker.getTotalVariableList());
        System.out.println("Argument -> " + astChecker.getArgumentList());


        return parser;
    }


    public ASTMaker(String sourceCodes) {
        this.sourceCodes = sourceCodes;
    }
}

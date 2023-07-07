package isel.csee.jcctokener;

import isel.csee.jcctokener.parser.ASTMaker;
import org.eclipse.jdt.core.dom.ASTParser;

public class jCCTokener {


    public static void main(String[] args) {
        jCCTokener jCCTokener = new jCCTokener();

        jCCTokener.run(args);

    }

    public void run(String[] args) {
        ASTMaker astMaker = new ASTMaker("package edu.handong.csee.java.hw3;\n" +
                "\n" +
                "import edu.handong.csee.java.hw3.engines.*;\n" +
                "\n" +
                "/**\n" +
                " * This is the main class of this program\n" +
                " */\n" +
                "public class Calculator {\n" +
                "    /**\n" +
                "     * This is the main method\n" +
                "     * @param args\n" +
                "     */\n" +
                "    public static void main(String[] args) {\n" +
                "\n" +
                "        Calculator myCalculator = new Calculator();\n" +
                "\n" +
                "        myCalculator.run(args);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * This is the run method\n" +
                "     * @param args\n" +
                "     */\n" +
                "    public void run(String[] args) {\n" +
                "        if(args.length == 0) {\n" +
                "            System.out.println(\"Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL.\");\n" +
                "            System.out.println(\"For example, java -cp [your classpath] edu.handong.csee.java.hw2.Calculator MAX 12 4 5 45 100\");\n" +
                "            System.exit(0);\n" +
                "        }\n" +
                "        String engineName = args[0].toUpperCase();\n" +
                "\n" +
                "        Computable engine = null;\n" +
                "\n" +
                "        switch(engineName) {\n" +
                "            case \"LCM\":\n" +
                "                engine = new LCMEngine();\n" +
                "                break;\n" +
                "            case \"GCD\":\n" +
                "                engine = new GCDEngine();\n" +
                "                break;\n" +
                "            case \"SQRT\":\n" +
                "                engine = new SQRTEngine();\n" +
                "                break;\n" +
                "            case \"FACTORIAL\":\n" +
                "                engine = new FactorialEngine();\n" +
                "                break;\n" +
                "            case \"FIBONACCI\":\n" +
                "                engine = new FibonacciEngine();\n" +
                "                break;\n" +
                "            case \"MAX\":\n" +
                "                engine = new MaxEngine();\n" +
                "                break;\n" +
                "            case \"MIN\":\n" +
                "                engine = new MinEngine();\n" +
                "                break;\n" +
                "            case \"CUBEVOL\":\n" +
                "                engine = new CubeVolEngine();\n" +
                "                break;\n" +
                "            case \"SPHEREVOL\":\n" +
                "                engine = new SphereVolEngine();\n" +
                "                break;\n" +
                "            default:\n" +
                "                System.out.println(\"Invalid command: \" + engineName);\n" +
                "                System.exit(0);\n" +
                "        }\n" +
                "\n" +
                "        engine.setInput(args);\n" +
                "        engine.compute();\n" +
                "\n" +
                "        System.out.println(\"The result of \" +  engineName + \" is \" + engine.getResult() + \".\");\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}");

        astMaker.parserCodes();
    }
}

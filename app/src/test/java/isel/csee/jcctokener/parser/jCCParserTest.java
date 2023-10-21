package isel.csee.jcctokener.parser;

import isel.csee.jcctokener.node.jCCNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class jCCParserTest {

    @Test
    void testTaxFruit() { // TaxFruit 소스 파일에서 원하는 노드를 다 추출할 수 있는 지 여부를 테스트 해주는 코드
        try {
            String source = new String(Files.readAllBytes(Paths.get("/Users/kimdong-gyu/Desktop/HGU/jCCTokener/JCCTokener/app/src/main/resources/test/isel/codes/TaxFruit.java")));

            jCCParser jCCParser = new jCCParser(source);


            jCCParser.parseCodes();
            List<jCCNode> jCCNodeList = jCCParser.getjCCNodeList();

            String testString = "";

            for(jCCNode node : jCCNodeList) {
                testString += node.getVariableName();
                testString += ", ";
            }
            testString = testString.substring(0, testString.length()-2);
            assertEquals("percent, a, =, b, =, returnValue, =, tax, <, a, percent, =, i, =, i, <, fruitsPrice, length, returnValue, +=, percent, *, fruitsPrice, i, +, fruitsName," +
                    " i, a, <=, tax, &&, tax, <, b, percent, =, i, =, i, <, fruitsPrice, length, returnValue, +=, percent, *, fruitsPrice, i, +, fruitsName," +
                    " i, percent, =, i, =, i, <, fruitsPrice, length, returnValue, +=, percent, *, fruitsPrice, i, +, fruitsName, i, returnValue", testString);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void testDivision() { // Division 소스 파일에서 원하는 노드를 다 추출할 수 있는 지 여부를 테스트 해주는 코드
        try {
            String source = new String(Files.readAllBytes(Paths.get("/Users/kimdong-gyu/Desktop/HGU/jCCTokener/JCCTokener/app/src/main/resources/test/isel/codes/Division.java")));

            jCCParser jCCParser = new jCCParser(source);


            jCCParser.parseCodes();
            List<jCCNode> jCCNodeList = jCCParser.getjCCNodeList();

            String testString = "";

            for(jCCNode node : jCCNodeList) {
                testString += node.getVariableName();
                testString += ", ";
            }
            testString = testString.substring(0, testString.length()-2);
            assertEquals("sum, =, numbers, =, number, numbers, number, %, ==, sum, +=, doubleValue, number, sum, +=, number, number, map, put, number, map, put, number, map, put, number, " +
                    "e, println, +, e, getMessage, sum", testString);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void testFileWriter() { // FileWriter 소스 파일에서 원하는 노드를 다 추출할 수 있는 지 여부를 테스트 해주는 코드
        try {
            String source = new String(Files.readAllBytes(Paths.get("/Users/kimdong-gyu/Desktop/HGU/jCCTokener/JCCTokener/app/src/main/resources/test/isel/codes/FileWriter.java")));

            jCCParser jCCParser = new jCCParser(source);


            jCCParser.parseCodes();
            List<jCCNode> jCCNodeList = jCCParser.getjCCNodeList();

            String testString = "";

            for(jCCNode node : jCCNodeList) {
                testString += node.getVariableName();
                testString += ", ";
            }
            testString = testString.substring(0, testString.length()-2);
            assertEquals("percent, a, =, b, =, returnValue, =, tax, <, a, percent, =, i, =, i, <, fruitsPrice, length, returnValue, +=, percent, *, fruitsPrice, i, +, fruitsName," +
                    " i, a, <=, tax, &&, tax, <, b, percent, =, i, =, i, <, fruitsPrice, length, returnValue, +=, percent, *, fruitsPrice, i, +, fruitsName," +
                    " i, percent, =, i, =, i, <, fruitsPrice, length, returnValue, +=, percent, *, fruitsPrice, i, +, fruitsName, i, returnValue", testString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
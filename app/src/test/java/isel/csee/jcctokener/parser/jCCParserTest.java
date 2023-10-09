package isel.csee.jcctokener.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class jCCParserTest {

    @Test
    void testTaxFruit() { // TaxFruit 소스 파일에서 원하는 노드를 다 추출할 수 있는 지 여부를 테스트 해주는 코드
        try {
            String source = new String(Files.readAllBytes(Paths.get("/Users/kimdong-gyu/Desktop/HGU/jCCTokener/JCCTokener/app/src/main/resources/test/isel/codes/TaxFruit.java")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(0, jCCParser.testParseCode("test"));
    }

    @Test
    void testDivision() { // Division 소스 파일에서 원하는 노드를 다 추출할 수 있는 지 여부를 테스트 해주는 코드
        try {
            String source = new String(Files.readAllBytes(Paths.get("/Users/kimdong-gyu/Desktop/HGU/jCCTokener/JCCTokener/app/src/main/resources/test/isel/codes/Division.java")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(0, jCCParser.testParseCode("test"));
    }

    @Test
    void testFileWriter() { // FileWriter 소스 파일에서 원하는 노드를 다 추출할 수 있는 지 여부를 테스트 해주는 코드
        try {
            String source = new String(Files.readAllBytes(Paths.get("/Users/kimdong-gyu/Desktop/HGU/jCCTokener/JCCTokener/app/src/main/resources/test/isel/codes/FileWriter.java")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(0, jCCParser.testParseCode("test"));
    }
}
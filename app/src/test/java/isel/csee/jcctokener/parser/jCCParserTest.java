package isel.csee.jcctokener.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class jCCParserTest {

    @Test
    void testParseCode() {
        assertEquals(0, jCCParser.testParseCode("test"));
    }
}
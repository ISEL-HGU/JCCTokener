package isel.csee.jcctokener.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentFileParserTest {

    @Test
    void parseStudentFile() {
//        assert (StudentFileParser.sum(1, 2));
        assertEquals(9, StudentFileParser.sum(1, 2));
    }
}
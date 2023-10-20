package isel.csee.jcctokener.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentFileFinderTest {

    @Test
    void parseStudentFile() {
//        assert (StudentFileParser.sum(1, 2));
        assertEquals(9, StudentFileFinder.sum(1, 2));
    }
}
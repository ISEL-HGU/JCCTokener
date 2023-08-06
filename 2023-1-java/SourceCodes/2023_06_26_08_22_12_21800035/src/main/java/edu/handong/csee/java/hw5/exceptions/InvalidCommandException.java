package edu.handong.csee.java.hw5.exceptions;

/**
 * Custom exception class for handling invalid command errors.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs an {InvalidCommandException} with the specified engine name.
     *
     * the invalid engine name specified by the user
     */
    public InvalidCommandException(String engineName) {
        super("Exception-01: Invalid command: " + engineName +
                "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. "
                +
                "For example, ./app MAX 12 4 5 45 100");
    }
}

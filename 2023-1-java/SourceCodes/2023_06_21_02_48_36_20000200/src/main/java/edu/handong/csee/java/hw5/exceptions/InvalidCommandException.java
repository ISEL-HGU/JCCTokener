package edu.handong.csee.java.hw5.exceptions;

/**
 * InvalidCommandException class represents an exception that is thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs an InvalidCommandException with the specified detail message.
     * 
     * @param message The detail message of the exception.
     */
    public InvalidCommandException(String message) {
        super("Exception-01: Invalid command: \r\n"
                + "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100\r\n");
    }
}

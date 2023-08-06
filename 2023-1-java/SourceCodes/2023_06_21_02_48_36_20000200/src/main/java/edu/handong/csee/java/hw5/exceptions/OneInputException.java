package edu.handong.csee.java.hw5.exceptions;

/**
 * OneInputException class represents an exception that is thrown when only one input value is required.
 */
public class OneInputException extends Exception {
    /**
     * Constructs a OneInputException with the specified detail message.
     * 
     * @param message The detail message of the exception.
     */
    public OneInputException(String message) {
        super("Exception-04: You need one input value for " + message);
    }
}

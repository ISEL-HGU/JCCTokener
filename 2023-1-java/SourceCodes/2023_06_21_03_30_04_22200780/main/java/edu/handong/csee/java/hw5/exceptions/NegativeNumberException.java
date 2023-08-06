package edu.handong.csee.java.hw5.exceptions;

/**
 * NegativeNumberException class represents an exception that is thrown when the input value is negative.
 */
public class NegativeNumberException extends Exception {
    /**
     * Constructs a NegativeNumberException with the specified detail message.
     * 
     * @param message The detail message of the exception.
     */
    public NegativeNumberException(String message) {
        super("Exception-03: The input value cannot be negative for " + message);
    }
}

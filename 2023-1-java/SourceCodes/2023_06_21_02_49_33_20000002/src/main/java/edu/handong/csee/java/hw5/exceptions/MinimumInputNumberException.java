package edu.handong.csee.java.hw5.exceptions;

/**
 * MinimumInputNumberException class represents an exception that is thrown when the minimum number of input values is not met.
 */
public class MinimumInputNumberException extends Exception {
    /**
     * Constructs a MinimumInputNumberException with the specified detail message.
     * 
     * @param message The detail message of the exception.
     */
    public MinimumInputNumberException(String message) {
        super("Exception-02: You need at least 2 input values for " + message);
    }
}

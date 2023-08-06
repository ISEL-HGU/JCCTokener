package edu.handong.csee.java.hw5.exceptions;
/**
 * The MinimumInputNumberException is a custom exception class that represents an insufficient number of inputs.
 * It is thrown when a program requires at least two input values, but an insufficient number of inputs is provided.
 */
public class MinimumInputNumberException extends Exception{
    /**
     * Constructs a MinimumInputNumberException with a default error message.
     */
    public MinimumInputNumberException() {
        super("Exception-02");
    }
    /**
     * Constructs a MinimumInputNumberException with a specified error message.
     * 
     * @param message the additional information describing the required input values
     */
    public MinimumInputNumberException(String message) {
        super("Exception-02: You need at least 2 input values for " + message + ".");
    }
}

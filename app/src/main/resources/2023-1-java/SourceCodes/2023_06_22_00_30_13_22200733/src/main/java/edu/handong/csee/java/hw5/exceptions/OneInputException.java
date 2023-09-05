package edu.handong.csee.java.hw5.exceptions;
/**
 * The OneInputException is a custom exception class that represents an insufficient number of inputs.
 * It is thrown when a program requires exactly one input value, but an insufficient number of inputs is provided.
 */
public class OneInputException extends Exception{
    /**
     * Constructs an OneInputException with a default error message.
     */
    public OneInputException() {
        super("Exception-04");
    }

    /**
     * Constructs an OneInputException with a specified error message.
     * 
     * @param message the additional information describing the required input value
     */
    public OneInputException(String message) {
        super("Exception-04: You need one input value for " + message + ".");
    }
}

package edu.handong.csee.java.hw5.exceptions;
/**
 * The NegativeNumberException is a custom exception class that represents a negative number exception.
 * It is thrown when a negative number is encountered where it is not allowed.
 */
public class NegativeNumberException extends Exception{
    /**
     * Constructs a NegativeNumberException with a default error message.
     */
    public NegativeNumberException() {
        super("Exception-03");
    }
    /**
     * Constructs a NegativeNumberException with a specified error message.
     * 
     * @param message the additional information describing where the negative number is not allowed
     */
    public NegativeNumberException(String message) {
        super("Exception-03: The input value cannot be negative for " + message + ".");
    }
}

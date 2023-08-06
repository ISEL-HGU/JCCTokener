package edu.handong.csee.java.hw5.exceptions;

/**
 * This exception is thrown when a negative number is encountered where it is not allowed.
 */
public class NegativeNumberException extends Exception {
	  /**
     * Constructs a NegativeNumberException with a default error message.
     * The error message indicates that a negative number error has occurred.
     */
	public NegativeNumberException() {
        super("Negative number error occurred.");
	}
	 /**
     * Constructs a NegativeNumberException with a custom error message.
     * The error message includes the provided message and indicates that the input value cannot be negative.
     *
     * @param message Additional message specifying the reason for the exception.
     */
    public NegativeNumberException(String message) {
        super("Exception-03: The input value cannot be negative for " + message + ".");
    }

    
}

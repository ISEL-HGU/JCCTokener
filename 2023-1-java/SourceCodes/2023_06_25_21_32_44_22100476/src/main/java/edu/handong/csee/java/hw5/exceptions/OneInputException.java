package edu.handong.csee.java.hw5.exceptions;
/**
 * This exception is thrown when an error occurs due to expecting only one input value.
 */
public class OneInputException extends Exception{
	  /**
     * Constructs a OneInputException with a default error message.
     * The error message indicates that a one input error has occurred.
     */
	public OneInputException() {
        super("One input error occurred.");
	}
	/**
     * Constructs a OneInputException with a custom error message.
     * The error message includes the provided message and indicates that one input value is required.
     *
     * @param message Additional message specifying the reason for the exception.
     */
    public OneInputException(String message) {
        super("Exception-04: You need one input value for "+ message + ".");
    }
    
}

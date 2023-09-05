package edu.handong.csee.java.hw5.exceptions;
/**
 * This exception is a custom extension of NumberFormatException.
 * It is thrown when a number format error specific to the application occurs.
 */
public class MyNumberFormatException extends NumberFormatException{
	/**
     * Constructs a MyNumberFormatException with a default error message.
     * The error message indicates that a custom number format error has occurred.
     */
	public MyNumberFormatException() {
        super("My number format error occurred.");
    }

    /**
     * Constructs a MyNumberFormatException with a custom error message.
     *
     * @param message Additional message specifying the reason for the exception.
     */
    public MyNumberFormatException(String message) {
        super(message);
    }
    
}

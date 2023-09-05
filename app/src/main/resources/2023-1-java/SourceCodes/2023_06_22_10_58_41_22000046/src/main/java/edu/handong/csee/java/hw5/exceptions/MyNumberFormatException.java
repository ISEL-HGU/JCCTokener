package edu.handong.csee.java.hw5.exceptions;

/**
 * The MyNumberFormatClass class is a custom exception that is thrown when that is thrown when a program tries to convert a non-numeric string to a number.
 * This class extends the built-in NumberFormatException class.
 */
public class MyNumberFormatException extends NumberFormatException {
	
	/**
	 * Constructs a new MyNumberFormatException with no detail message.
	 */
	public MyNumberFormatException() {
		super() ;
	}
	
	/**
	 * Constructs a new MyNumberFormatException with the specified detail message, which includes the name of the engine that is attempting the conversion and the input value that is causing the exception.
	 * @param engineName the name of the engine that is attempting the conversion
	 * @param value the input value that is causing the exception
	 */
	public MyNumberFormatException(String engineName, String value) {
		super("Exception-05: The input value should be converted into a number. (" + value + " is not a number value for " + engineName + ".)") ;
	}
}
package edu.handong.csee.java.hw5.exceptions;

/**
 * The MyNumberFormatException class represents an exception that is thrown
 * when a string cannot be converted into a number for a specific computing engine.
 * This exception extends the NumberFormatException class and provides additional information about the error.
*/
public class MyNumberFormatException extends NumberFormatException{
	
	/*
	 * Default Constructor for MyNumberFormatException
	 *
	 * */
	public MyNumberFormatException() {
		super();
	}
	
	/**
	 * Constructs a MyNumberFormatException object with the specified engine and input.
	 * 
	 * @param the detailed error message 
	 */
	public MyNumberFormatException(String message) {
		super(message);
	}
}

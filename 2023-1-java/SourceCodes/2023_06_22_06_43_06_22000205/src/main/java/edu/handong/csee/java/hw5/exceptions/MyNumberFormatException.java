package edu.handong.csee.java.hw5.exceptions;

/**
 * Default constructor, called when an instance is created.
 */
public class MyNumberFormatException extends NumberFormatException {
	
	/**
	 * Default constructor, called when an instance is created.
	 * Code to terminate the program is implemented.
	 */
	public MyNumberFormatException() {
		super("The input value should be converted into a number. (aaa is not a number value for LCM.)");
        System.exit(0);
    }

	/**
	 * Constructor that takes a message, called when an instance is created. 
	 * Outputs the message received, and handles exceptions by calling the constructor of the super class, exception class. 
	 */
    public MyNumberFormatException(String message) {
        super(message);
    }
}
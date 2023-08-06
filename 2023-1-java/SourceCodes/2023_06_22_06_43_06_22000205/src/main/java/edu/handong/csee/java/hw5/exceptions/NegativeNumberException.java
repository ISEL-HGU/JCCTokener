package edu.handong.csee.java.hw5.exceptions;

/**
 * Exception when a negative number is entered
 */
public class NegativeNumberException extends Exception {
	
	/**
	 * Default constructor, called when an instance is created.
	 * Code to terminate the program is implemented.
	 */
	public NegativeNumberException() {
        super("The input value cannot be negative for LCM.");
        System.exit(0);
    }
	
	/**
	 * Constructor that takes a message, called when an instance is created. 
	 * Outputs the message received, and handles exceptions by calling the constructor of the super class, exception class. 
	 */
    public NegativeNumberException(String message) {
        super(message);
    }
}
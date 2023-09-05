package edu.handong.csee.java.hw5.exceptions;

/**
 * Exception when more than one value is entered
 */
public class OneInputException extends Exception {
	
	/**
	 * Default constructor, called when an instance is created.
	 * Code to terminate the program is implemented.
	 */
	public OneInputException() {
        super("You need one input value for SQRT.");
        System.exit(0);
    }

	/**
	 * Constructor that takes a message, called when an instance is created. 
	 * Outputs the message received, and handles exceptions by calling the constructor of the super class, exception class. 
	 */
    public OneInputException(String message) {
        super(message);
    }
}
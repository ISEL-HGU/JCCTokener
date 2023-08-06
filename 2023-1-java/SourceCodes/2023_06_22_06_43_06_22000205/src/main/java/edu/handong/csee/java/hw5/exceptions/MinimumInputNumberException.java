package edu.handong.csee.java.hw5.exceptions;

/**
 * exception when at least two values are not entered
 */
public class MinimumInputNumberException extends Exception {

	/**
	 * Default constructor, called when an instance is created.
	 * Code to terminate the program is implemented.
	 */
	public MinimumInputNumberException() {
        super("You need at least 2 input values for LCM.");
        System.exit(0);
    }

	/**
	 * Constructor that takes a message, called when an instance is created. 
	 * Outputs the message received, and handles exceptions by calling the constructor of the super class, exception class. 
	 */
    public MinimumInputNumberException(String message) {
        super(message);
    }
}
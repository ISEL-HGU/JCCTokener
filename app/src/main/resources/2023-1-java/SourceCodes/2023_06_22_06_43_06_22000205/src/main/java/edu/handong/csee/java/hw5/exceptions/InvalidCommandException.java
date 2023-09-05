package edu.handong.csee.java.hw5.exceptions;

/**
 * exception when engine option is not entered properly
 */
public class InvalidCommandException extends Exception {
	
	/**
	 * Default constructor, called when an instance is created.
	 * Code to terminate the program is implemented.
	 */
	public InvalidCommandException() {
        super("Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL.");
        System.exit(0);
    }
	
	/**
	 * Constructor that takes a message, called when an instance is created. 
	 * Outputs the message received, and handles exceptions by calling the constructor of the super class, exception class. 
	 */
    public InvalidCommandException(String message) {
        super(message);
    }
}
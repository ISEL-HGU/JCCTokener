package edu.handong.csee.java.hw5.exceptions;

/**
 * The InvalidCommandException class is a custom exception that is thrown when an invalid command is given to a program.
 * This class extends the built-in Exception class.
 */
public class InvalidCommandException extends Exception {
	

	/**
	 * Constructs a new InvalidCommandException with no detail message
	 */
	public InvalidCommandException() {
		super() ;
	}
	
	/**
	 * Constructs a new InvalidCommandException with the specified detail message, which includes the name of the engine that received the invalid command.
	 * @param engineName the name of the engine that received the invalid command
	 */
	public InvalidCommandException(String engineName) {
		super("Exception-01: Invalid command: " + engineName ) ;
	}
}
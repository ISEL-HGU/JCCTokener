package edu.handong.csee.java.hw5.exceptions;

/**
 * The InvalidCommandException class represents an exception that is thrown when an invalid command is provided.
 * This exception extends the Exception class and provides additional information about the error.
*/
public class InvalidCommandException extends Exception{
	/**
	 * Default constructor for InvalidCommandException.
	 */
	public InvalidCommandException() {
		super();
	}
	
	/**
	 * Constructs an InvalidCommandException object with the specified input.
	 * 
	 * @param the detailed error message 
	 */
	public InvalidCommandException(String message) {
		super(message);
	}

}

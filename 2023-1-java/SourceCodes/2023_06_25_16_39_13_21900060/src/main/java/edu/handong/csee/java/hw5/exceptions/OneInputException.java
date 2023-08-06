package edu.handong.csee.java.hw5.exceptions;

/**
 * The OneInputException class represents an exception that is thrown
 * when only one input value is required for a specific computing engine, but multiple inputs are provided.
 * This exception extends the Exception class and provides additional information about the error.
*/
public class OneInputException extends Exception{
	/**
	 * Default constructor for OneInputException 
	 */
	public OneInputException() {
		super();
	}
	
	/**
	 * Constructs an OneInputException object with the specified engine.
	 * 
	 * @param the detailed error message 
	 */
	public OneInputException(String message) {
		super(message);
	}
}

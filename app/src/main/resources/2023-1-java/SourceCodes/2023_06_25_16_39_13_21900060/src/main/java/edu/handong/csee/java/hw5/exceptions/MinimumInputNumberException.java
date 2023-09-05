package edu.handong.csee.java.hw5.exceptions;

/**
 * The MinimumInputNumberException class represents an exception that is thrown
 * when the minimum required number of inputs is not met for a specific computing engine.
 * This exception extends the Exception class and provides additional information about the error.
*/
public class MinimumInputNumberException extends Exception {
	
	/**
	 * Default constructor for MinimumInputNumberException.
	 */
	public MinimumInputNumberException() {
		super();
	}
	
	/**
	 * Constructs a MinimumInputNumberException object with the specified engine.
	 * 
	 * @param the detailed error message 
	 */
	public MinimumInputNumberException(String message) {
		super(message);
	}
}

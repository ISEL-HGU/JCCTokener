package edu.handong.csee.java.hw5.exceptions;


/**
 * The NegativeNumberException class represents an exception that is thrown
 * when a negative input value is provided for a specific computation.
 * This exception extends the Exception class and provides additional information about the error.
*/
public class NegativeNumberException extends Exception{
	/**
	 * Default constructor for NegativeNumberException
	 */
	public NegativeNumberException() {
		super();
	}
	
	/**
	 * Constructs a NegativeNumberException object with the specified input.
	 * 
	 * @param the detailed error message 
	 */
	public NegativeNumberException(String message) {
		super(message);
	}
}

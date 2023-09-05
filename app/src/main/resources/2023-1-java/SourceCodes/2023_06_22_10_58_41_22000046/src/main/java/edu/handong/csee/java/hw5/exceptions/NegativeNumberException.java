package edu.handong.csee.java.hw5.exceptions;

/**
 * The NegativeNumberException class is a custom exception that is thrown when a program receives negative number.
 * This class extends the built-in Exception class.
 */
public class NegativeNumberException extends Exception {

	/**
	 * Constructs a new NegativeNumberException with no detail message.
	 */
	public NegativeNumberException() {
		super() ;
	}
	
	/**
	 * Constructs a new NegativeNumberException with the specified detail message, which includes the name of the engine that receives negative number.
	 * @param engineName the name of the engine that is attempting the conversion
	 */
	public NegativeNumberException(String engineName) {
		super("Exception-03: The input value cannot be negative for " + engineName+ ".") ;
	}
}


package edu.handong.csee.java.hw5.exceptions;

/**
 * The MinimumInputNumberException class is a custom exception that is thrown when an invalid command is given to a program.
 * This class extends the built-in Exception class.
 */
public class MinimumInputNumberException extends Exception {
		
	/**
	 * Constructs a new MinimumInputNumberException with no detail message
	 */
	public MinimumInputNumberException() {
		super() ;
	}
	
	/**
	 * Constructs a new MinimumInputNumberException with the specified detail message, which includes the name of the engine that requires a minimum number of input values.
	 * @param engineName the name of the engine that requires a minimum number of input values
	 */
	public MinimumInputNumberException(String engineName) {
		super("Exception-02: You need at least 2 input values for " + engineName+ ".") ;
	}
}
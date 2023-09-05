package edu.handong.csee.java.hw5.exceptions;

/**
 * The OneInputExceptionException class is a custom exception that is thrown when a program receives multiple inputs rather than one input.
 * This class extends the built-in Exception class.
 */
public class OneInputException extends Exception {
	
	/**
	 * Constructs a new OneInputException with no detail message.
	 */
	public OneInputException() {
		super() ;
	}
	
	/**
	 * Constructs a new OneInputException with the specified detail message, which includes the name of the engine that receives multiple inputs.
	 * @param engineName the name of the engine that receives multiple inputs.
	 */
	public OneInputException(String engineName) {
		super("Exception-04: You need one input value for " + engineName+ ".") ;
	}

}

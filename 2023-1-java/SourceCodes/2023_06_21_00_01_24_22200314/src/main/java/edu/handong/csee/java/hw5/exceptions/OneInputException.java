package edu.handong.csee.java.hw5.exceptions;

/**
 * This class is a exception class about not giving One Input for some specific engines that needs one when using the calculator.
 */
public class OneInputException extends Exception {
	/**
	 * This constructor calls Exception class' methods.
	 */
	public OneInputException() {
		super();
	}
	/**
	 * This constructor gives Exception-04 Message to Exception class with the engine name.
	 * @param message The engine name needs 1 input value.
	 */
	public OneInputException(String message) {
		super("Exception-04: You need one input value for " + message + ".");
	}
}

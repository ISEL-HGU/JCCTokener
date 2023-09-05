package edu.handong.csee.java.hw5.exceptions;

/**
 * This class is a exception class about Minimum Input needed for some specific engines when using the calculator.
 */
public class MinimumInputNumberException extends Exception {
	/**
	 * This constructor calls Exception class' methods.
	 */
	public MinimumInputNumberException() {
		super();
	}
	/**
	 * This constructor gives Exception-02 Message to Exception class with the engine name.
	 * @param message The engine name needs at least 2 input values.
	 */
	public MinimumInputNumberException(String message) {
		super("Exception-02: You need at least 2 input values for " + message + ".");
	}
}

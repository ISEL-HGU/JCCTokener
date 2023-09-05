package edu.handong.csee.java.hw5.exceptions;

/**
 * This class is a exception class about giving Negative Input for some specific engines when using the calculator.
 */
public class NegativeNumberException extends Exception {
	/**
	 * This constructor calls NumberFormatException class' methods.
	 */
	public NegativeNumberException() {
		super();
	}
	/**
	 * This constructor gives Exception-03 Message to Exception class with the engine name.
	 * @param message The engine name cannot use negative values.
	 */
	public NegativeNumberException(String message) {
		super("Exception-03: The input value cannot be negative for " + message + ".");
	}
}

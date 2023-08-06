package edu.handong.csee.java.hw5.exceptions;
/**
 * Thrown to indicate that input value was negative number.
 * @author kim hongchan
 *
 */
public class NegativeNumberException extends Exception{
	/**
	 * Constructs a NegativeNumberException with no detail message.
	 */
	public NegativeNumberException() {
		super("Exception-03");
	}
	/**
	 * Constructs a NegativeNumberException with the specified detail message.
	 * @param engineName
	 */
	public NegativeNumberException(String engineName) {
		super("Exception-03: The input value cannot be negative for "
				+ engineName + ".\n");
	}
}

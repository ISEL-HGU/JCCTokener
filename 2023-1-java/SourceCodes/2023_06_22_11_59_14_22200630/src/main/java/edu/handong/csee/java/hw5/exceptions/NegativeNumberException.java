package edu.handong.csee.java.hw5.exceptions;

/**
 * Class for exception about Negative Number Exception
 *
 */
public class NegativeNumberException extends Exception{
	/**
	 * method that print message
	 * @param message
	 */
	public NegativeNumberException(String message) {
		super("Exception-03: The input value cannot be negative for "+ message +".");
	}
}

package edu.handong.csee.java.hw5.exceptions;

/**
 * This class represents an exception that is thrown when the provided argument is a negative number.
 */
public class NegativeNumberException extends Exception{
	
	/**
	 * Constructor of NegativeNumberException that informs about the exception caused by a negative argument.
	 * 
	 * @param engineName name of the entered command
	 */
	public NegativeNumberException(String engineName) {
		super("Exception-03: The input value cannot be negative for " + engineName + ".");
	}
}
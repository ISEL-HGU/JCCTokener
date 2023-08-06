package edu.handong.csee.java.hw5.exceptions;
/**
 * This is class of MyNumberFormatException
 */
public class OneInputException extends Exception{
	/**
	 * This is the part that executes exception with 2 parameter.
	 */
	public OneInputException(String engineName, int n) {
		super("Exception-04: You need one input value for "+engineName+".");
	}
	/**
	 * This is the part that executes exception with 1 parameter.
	 */
	public OneInputException(String message) {
		super(message);
	}
}
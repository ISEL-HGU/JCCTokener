package edu.handong.csee.java.hw5.exceptions;
/**
 * This is class of MyNumberFormatException
 */
public class NegativeNumberException extends Exception{
	/**
	 * This is the part that executes exception with 2 parameter.
	 */
	public NegativeNumberException(String engineName, int n){
		super("Exception-03: The input value cannot be negative for "+engineName+".");
	}
	/**
	 * This is the part that executes exception with 1 parameter.
	 */
	public NegativeNumberException(String message) {
		super(message);
	}
}
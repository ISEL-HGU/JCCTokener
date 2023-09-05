package edu.handong.csee.java.hw5.exceptions;
/**
 * Thrown to indicate that argument of engine was inappropriate.
 * @author kim hongchan
 *
 */
public class MyNumberFormatException extends NumberFormatException{
	/**
	 * Constructs a MyNumberFormatException with no detail message.
	 */
	public MyNumberFormatException() {
		super("Exception-05");
	}
	/**
	 * Constructs a MyNumberFormatException with the specified detail message.
	 * @param input
	 * @param engineName
	 */
	public MyNumberFormatException(String input, String engineName) {
		super("Exception-05: The input value should be converted into a number. ("
				+ input + " is not a number value for " 
				+ engineName +".)\n");
	}
}

package edu.handong.csee.java.hw5.exceptions;
/**
 * Thrown to indicate that the number of input value was exceeded.
 * @author kim hongchan
 *
 */
public class OneInputException extends Exception{
	/**
	 * Constructs a OneInputException with no detail message.
	 */
	public OneInputException () {
		super("Exception-04");
	}
	/**
	 * Constructs a OneInputException with the specified detail message.
	 * @param engineName
	 */
	public OneInputException (String engineName) {
		super("Exception-04: You need one input value for "
				+ engineName +".\n");
	}
}

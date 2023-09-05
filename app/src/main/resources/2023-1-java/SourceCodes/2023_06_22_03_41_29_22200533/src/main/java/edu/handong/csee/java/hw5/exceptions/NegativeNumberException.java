package edu.handong.csee.java.hw5.exceptions;

/**
 *class that handles exceptions when a negative number comes in.
 */
public class NegativeNumberException extends Exception{

	/**
	 * Method to pawn error messages to the parent class.
	 */
	public NegativeNumberException(String a) {
		super("Exception-03: The input value cannot be negative for " + a + ".");	
	}

}

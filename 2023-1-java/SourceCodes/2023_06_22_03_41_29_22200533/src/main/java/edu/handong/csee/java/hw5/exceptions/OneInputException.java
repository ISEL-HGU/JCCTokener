package edu.handong.csee.java.hw5.exceptions;

/**
 * A class that handles exceptions when more than one input is received.
 */
public class OneInputException extends Exception{

	/**
	 * Method to pawn error messages to the parent class.
	 */
	public OneInputException(String a) {
		super("Exception-04: You need one input value for " + a + ".");
	}

}

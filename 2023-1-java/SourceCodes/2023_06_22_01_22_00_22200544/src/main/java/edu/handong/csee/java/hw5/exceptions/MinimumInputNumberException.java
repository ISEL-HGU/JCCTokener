package edu.handong.csee.java.hw5.exceptions;

/**
 * A class that handles exceptions when less than two inputs are received.
 */
public class MinimumInputNumberException extends Exception {

	/**
	 * Method to pawn error messages to the parent class.
	 */
	public MinimumInputNumberException(String a) {
		super("Exception-02: You need at least 2 input values for " + a + ".");
	}
	

}

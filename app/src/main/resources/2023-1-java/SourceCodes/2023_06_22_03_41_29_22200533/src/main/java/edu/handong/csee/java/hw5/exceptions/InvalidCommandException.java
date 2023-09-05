package edu.handong.csee.java.hw5.exceptions;

/**
 * A class that handles exceptions when an incorrect command is received.
 */
public class InvalidCommandException extends Exception{

	/**
	 * Method to pawn error messages to the parent class.
	 */
	public  InvalidCommandException(String a) {
		super("Exception-01: Invalid command: " + a + "\n" + "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100\n");
	}
	
}

package edu.handong.csee.java.hw5.exceptions;

/**
 * Class for exception about invalid command exception
 *
 */
public class InvalidCommandException extends Exception{
	/**
	 * method that print message
	 */
	public InvalidCommandException() {
		super("Exception-01: Invalid command: " + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}
	/**
	 * method that print message
	 * @param message
	 */
	public InvalidCommandException(String message) {
		super("Exception-01: Invalid command: "+ message + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}
	
}

package edu.handong.csee.java.hw5.exceptions;

/**
 * This class is a exception class about Invalid Commands given to the calculator.
 *
 */
public class InvalidCommandException extends Exception {
	/**
	 * This constructor gives Exception-01 Message to Exception class.
	 */
	public InvalidCommandException() {
		super("Exception-01: Invalid command: \nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}
	
	/**
	 * This constructor gives Exception-01 Message to Exception class with the message that has given the Error.
	 * @param message The string value that made this exception happen.
	 */
	public InvalidCommandException(String message) {
		super("Exception-01: Invalid command: " + message + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
		
	}
}

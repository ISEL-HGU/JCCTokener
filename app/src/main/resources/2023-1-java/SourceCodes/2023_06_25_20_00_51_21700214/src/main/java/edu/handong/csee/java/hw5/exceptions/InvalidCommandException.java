package edu.handong.csee.java.hw5.exceptions;
/**
 * Thrown to indicate that a input value about engine name was inappropriate.
 * @author kim hongchan
 *
 */
public class InvalidCommandException extends Exception{
	/**
	 * Constructs a InvalidCommandException with no detail message.
	 */
	public InvalidCommandException () {
		super("Exception-01");
	}
	/**
	 * Constructs a InvalidCommandException with the specified detail message.
	 * @param input
	 */
	public InvalidCommandException(String input) {
		super("Exception-01: Invalid command: "+ input +"\n"
				+ "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100\n");
	}
}

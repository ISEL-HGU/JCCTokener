package edu.handong.csee.java.hw5.exceptions;
/**
 * This is class of InvalidCommandException
 */
public class InvalidCommandException extends Exception{
	/**
	 * This is the part that executes exception with 0 parameter.
	 */
	public InvalidCommandException() {
		super("Exception-01: Invalid command: \n"
				+ "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}
	/**
	 * This is the part that executes exception with 2 parameter.
	 */
	public InvalidCommandException(String engineName, int n){
		super("Exception-01: Invalid command: "+engineName+"\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}
	/**
	 * This is the part that executes exception with 1 parameter.
	 */
	public InvalidCommandException(String message) {
		super(message);
	}
}
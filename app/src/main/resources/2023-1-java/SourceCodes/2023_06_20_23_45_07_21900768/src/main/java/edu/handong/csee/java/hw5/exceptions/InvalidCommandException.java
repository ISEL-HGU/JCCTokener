package edu.handong.csee.java.hw5.exceptions;

/**
 * InvalidCommandException is Exception - 01. 
 * For using this program, you have to write engine names and input values. 
 * it's for checking that if you don't write engine name correctly or anything.
 * if this Exception is working, the guide texts will be print.
 */
public class InvalidCommandException extends Exception {
	/**
	 * this is Exception without parameter.
	 */
	public InvalidCommandException ()
	{
		super("Exception-01: Invalid command: \nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}
	/**
	 * this is Exception with parameter. 
	 */
	public InvalidCommandException (String message)
	{
		super("Exception-01: Invalid command: "+message + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}

}

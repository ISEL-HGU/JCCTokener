package edu.handong.csee.java.hw5.exceptions;

/**
 * This class represents an exception that is thrown when an invalid command is entered.
 */
public class InvalidCommandException extends Exception{
	
	/**
	 * Default constructor of InvalidCommandException.
	 */
	public InvalidCommandException(){
		super("Exception-01: Invalid command: \nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}
	
	/**
	 * Constructor of InvalidCommandException that informs about the exception caused by an invalid command entered.
	 * 
	 * @param engineName name of the entered command
	 */
	public InvalidCommandException(String engineName){
		super("Exception-01: Invalid command: " + engineName + "\n" + "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}
}
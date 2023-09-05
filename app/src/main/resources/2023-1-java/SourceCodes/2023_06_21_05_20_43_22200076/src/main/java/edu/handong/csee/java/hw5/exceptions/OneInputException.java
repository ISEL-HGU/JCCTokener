package edu.handong.csee.java.hw5.exceptions;

/**
 * This class represents an exception that is thrown when there are more than two arguments required for executing a command.
 */
public class OneInputException extends Exception{
	
	/**
	 * Constructor of OneInputException that informs about the exception caused by receiving more than one input argument.
	 * 
	 * @param engineName name of the entered command
	 */
	public OneInputException(String engineName) {
		super("Exception-04: You need one input value for " + engineName + ".");
	}
}
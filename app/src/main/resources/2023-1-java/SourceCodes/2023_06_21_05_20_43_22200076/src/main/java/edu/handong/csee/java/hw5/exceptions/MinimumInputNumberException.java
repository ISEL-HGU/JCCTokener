package edu.handong.csee.java.hw5.exceptions;

/**
 * This class represents an exception that is thrown when the minimum required arguments for executing a command are not provided.
 */
public class MinimumInputNumberException extends Exception{
	
	/**
	 * Constructor of MinimumInputNumberException, which informs about the exception caused by the minimum required number of arguments not being entered for executing a command.
	 * 
	 * @param engineName name of the entered command
	 * @param number minimum number of arguments required for executing a command
	 */
	public MinimumInputNumberException(String engineName, char number) {
		super("Exception-02: You need at least " + number + " input values for " + engineName + ".");
	}
}
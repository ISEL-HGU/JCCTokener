package edu.handong.csee.java.hw5.exceptions;

/**
 * Class for exception about One Input Exception
 *
 */
public class OneInputException extends Exception{
	/**
	 * method that print message
	 * @param message
	 */
	public OneInputException(String message){
		super("Exception-04: You need one input value for "+ message +".");
	}
	
}

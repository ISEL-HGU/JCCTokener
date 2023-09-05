package edu.handong.csee.java.hw5.exceptions;

/**
 * Class for exception about Minimum Input Number Exception
 *
 */
public class MinimumInputNumberException extends Exception{
	
	/**
	 * method that print message
	 * @param message
	 * @param n
	 */
	public MinimumInputNumberException(String message, int n) {
		// TODO Auto-generated method stub
		super("Exception-02: You need at least " + n + " input values for "+ message + ".");
	}
	
}

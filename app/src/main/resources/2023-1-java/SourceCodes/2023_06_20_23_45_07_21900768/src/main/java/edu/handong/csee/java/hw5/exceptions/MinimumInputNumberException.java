package edu.handong.csee.java.hw5.exceptions;

/**
 * MinimumInputNumberException is Exception - 02. 
 * Some engines have to get more than two values. it's for checking that if input values are more 2 or not.
 */
public class MinimumInputNumberException extends Exception{

	/**
	 * this is Exception without parameter.
	 */
	public MinimumInputNumberException ()
	{
		super("Exception-02");
	}
	/**
	 * this is Exception with parameter. 
	 */
	public MinimumInputNumberException (String message)
	{
		super("Exception-02: You need at least 2 input values for "+message +".");
	}
}

package edu.handong.csee.java.hw5.exceptions;

/**
 * OneInputException is Exception - 04. 
 * Some engines have to get a only one value.
 * it's for checking that if input value is one or not.
 */
public class OneInputException extends Exception{
	/**
	 * this is Exception without parameter.
	 */
	public OneInputException ()
	{
		super("Exception-04: You need one input value for .");
	}
	/**
	 * this is Exception with parameter. 
	 */
	public OneInputException (String message)
	{
		super("Exception-04: You need one input value for "+ message + ".");
	}
}

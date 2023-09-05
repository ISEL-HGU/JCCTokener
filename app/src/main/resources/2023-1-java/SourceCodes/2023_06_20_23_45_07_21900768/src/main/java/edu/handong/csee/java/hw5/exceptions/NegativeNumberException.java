package edu.handong.csee.java.hw5.exceptions;

/**
 * NegativeNumberException is Exception - 03. 
 * Every engines have to get positive value without MAX, MIN, it's for checking that if input value is negative or not.
 */
public class NegativeNumberException extends Exception{
	
	/**
	 * this is Exception without parameter.
	 */
	public NegativeNumberException ()
	{
		super("Exception-03");
	}
	/**
	 * this is Exception with parameter. 
	 */
	public NegativeNumberException (String message)
	{
		super("Exception-03: The input value cannot be negative for "+ message + ".");
	}
}

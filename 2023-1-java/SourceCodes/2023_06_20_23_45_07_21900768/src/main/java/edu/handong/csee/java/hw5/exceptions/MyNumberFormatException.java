package edu.handong.csee.java.hw5.exceptions;

/**
 * MyNumberFormatException is Exception - 05. 
 * Every engines have to get number values.
 * it's for checking that if input value is number or String.
 */
public class MyNumberFormatException extends NumberFormatException {
	/**
	 * this is Exception without parameter.
	 */
	public MyNumberFormatException()
	{
		super("Exception-05: The input value should be converted into a number.");
	}
	/**
	 * this is Exception with parameter. 
	 */
	public MyNumberFormatException(String message, String args)
	{
		super("Exception-05: The input value should be converted into a number. ("+ args +" is not a number value for "+ message+".)");
	}
}

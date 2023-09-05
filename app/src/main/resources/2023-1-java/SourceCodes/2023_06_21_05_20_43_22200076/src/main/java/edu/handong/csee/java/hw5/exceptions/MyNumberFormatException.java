package edu.handong.csee.java.hw5.exceptions;

/**
 * This class represents an exception that is thrown when the provided argument cannot be converted to a numeric format.
 */
public class MyNumberFormatException extends NumberFormatException{
	
	/**
	 * Constructor of MyNumberFormatException that informs about the occurrence of an exception when the argument cannot be converted to a number.
	 * 
	 * @param engineName name of the entered command
	 * @param number argument to be converted into a number
	 */
	public MyNumberFormatException(String engineName, String number) {
		super("Exception-05: The input value should be converted into a number. (" + number + " is not a number value for " + engineName + ".)");
	}
}
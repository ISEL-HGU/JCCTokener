package edu.handong.csee.java.hw5.exceptions;
/**
 * This is class of MyNumberFormatException
 */
public class MyNumberFormatException extends NumberFormatException{
	/**
	 * This is the part that executes exception with 2 parameter.
	 */
	public MyNumberFormatException(String engineName, String value){
		super("Exception-05: The input value should be converted into a number. ("+value+" is not a number value for "+engineName+".)");
	}
	/**
	 * This is the part that executes exception with 1 parameter.
	 */
	public MyNumberFormatException(String message) {
		super(message);
	}
}
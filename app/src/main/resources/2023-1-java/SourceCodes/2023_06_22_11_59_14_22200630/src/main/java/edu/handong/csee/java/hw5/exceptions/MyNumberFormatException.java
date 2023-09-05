package edu.handong.csee.java.hw5.exceptions;

/**
 * Class for exception about My Number Format Exception
 *
 */
public class MyNumberFormatException extends NumberFormatException{
	/**
	 * method that print message
	 * @param engineName
	 * @param s
	 */
	public MyNumberFormatException(String message, String s){
		// TODO Auto-generated method stub
		super ("Exception-05: The input value should be converted into a number. ("+ s +" is not a number value for "+ message +".)");
	}

}

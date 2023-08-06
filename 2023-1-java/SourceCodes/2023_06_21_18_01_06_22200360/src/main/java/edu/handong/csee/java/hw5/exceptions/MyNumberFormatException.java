package edu.handong.csee.java.hw5.exceptions;
/**
 * This class is customized exceptions
 * @author seogyeongmi
 *
 */
public class MyNumberFormatException extends NumberFormatException{
	/**
	 * This constructor has not parameters.
	 */
	public MyNumberFormatException() {
		super("Exception-05: The input value should be converted into a number. ");
	}
}

package edu.handong.csee.java.hw5.exceptions;
/**
 * This class is customized exceptions
 * @author seogyeongmi
 *
 */
public class NegativeNumberException extends Exception{
	/**
	 * This constructor has not parameters.
	 */
	public NegativeNumberException() {
		super("edu.handong.csee.java.hw3.exceptions.NegativeNumberException occured!");
	}
	/**
	 * This constructor has parameters.
	 * @param name
	 */
	public NegativeNumberException(String name) {
		super("Exception-03: The input value cannot be negative for " + name + ".");
	}
}

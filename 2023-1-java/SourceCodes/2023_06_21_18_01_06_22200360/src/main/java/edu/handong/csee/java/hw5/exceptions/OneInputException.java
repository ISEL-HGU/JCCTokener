package edu.handong.csee.java.hw5.exceptions;
/**
 * This class is customized exceptions
 * @author seogyeongmi
 *
 */
public class OneInputException extends Exception{
	/**
	 * This constructor has not parameters.
	 */
	public OneInputException() {
		super("edu.handong.csee.java.hw3.exceptions.OneInputException occured!");
	}
	/**
	 * This constructor has parameters.
	 * @param name
	 */
	public OneInputException(String name) {
		super("Exception-04: You need one input value for " + name + ".");
	}
}
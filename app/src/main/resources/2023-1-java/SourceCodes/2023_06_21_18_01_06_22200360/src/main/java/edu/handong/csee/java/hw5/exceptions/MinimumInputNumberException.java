package edu.handong.csee.java.hw5.exceptions;
/**
 * This class is customized exceptions
 * @author seogyeongmi
 *
 */
public class MinimumInputNumberException extends Exception {
	/**
	 * This constructor has not parameters.
	 */
	public MinimumInputNumberException() {
		super("edu.handong.csee.java.hw3.exceptions.MinimumInputNumberException occured!");
	}
	/**
	 * This constructor has parameters.
	 * @param name
	 */
	public MinimumInputNumberException(String name) {
		super("Exception-02: You need at least 2 input values for " + name + ".");
	}
}

package edu.handong.csee.java.hw5.exceptions;
/**
 * Thrown to indicate that the number of input value were insufficient.
 * @author kim hongchan
 *
 */
public class MinimumInputNumberException extends Exception {
	/**
	 * Constructs a MinimumInputNumberException with no detail message.
	 */
	public MinimumInputNumberException() {
		super("Exception-02");
	}
	/**
	 * Constructs a MinimumInputNumberException with the specified detail message.
	 * @param engineName
	 */
	public MinimumInputNumberException(String engineName) {
		super("Exception-02: You need at least 2 input values for "
				+ engineName +".\n");
	}
}

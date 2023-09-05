package edu.handong.csee.java.hw5.exceptions;
/**
 * This class is customized exceptions
 * @author seogyeongmi
 *
 */
public class InvalidCommandException extends Exception {
	/**
	 * This constructor has not parameters.
	 */
	public InvalidCommandException() {
		super("Exception-01: Invalid command: ");
	}
	/**
	 * This constructor has parameters.
	 * @param name
	 */
	public InvalidCommandException(String name) {
		super("Exception-01: Invalid command: " + name);
	}
}

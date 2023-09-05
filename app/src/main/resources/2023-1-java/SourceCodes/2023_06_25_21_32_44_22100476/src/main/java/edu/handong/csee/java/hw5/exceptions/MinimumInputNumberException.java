package edu.handong.csee.java.hw5.exceptions;

/**
 * This exception is thrown when the minimum number of required inputs is not satisfied.
 */

public class MinimumInputNumberException extends Exception{
	/**
     * Constructs a MinimumInputNumberException with a default error message.
     * The error message indicates that the minimum number of input values is not satisfied.
     */
	public MinimumInputNumberException() {
        super("The minimum number of input is not satisfied.");
	}
	/**
     * Constructs a MinimumInputNumberException with a custom error message.
     * The error message includes the provided message and indicates that at least 2 input values are required.
     *
     * @param message Additional message specifying the reason for the exception.
     */
    public MinimumInputNumberException(String message) {
        super("Exception-02: You need at least 2 input values for "+ message + ".");
    }
    

}

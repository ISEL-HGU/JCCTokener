package edu.handong.csee.java.hw5.exceptions;

/**
 * MinimumInputNumberException is thrown when the minimum number of inputs is not met.
 */
public class MinimumInputNumberException extends Exception {
	
	
	/**
	 * Constructor	
	 */
	public MinimumInputNumberException(){
	}
	

	/**
	 * MinimumInputNumberException is make message
	 */
    public MinimumInputNumberException(String message) {
        super(message);
    }
}

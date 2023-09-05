package edu.handong.csee.java.hw5.exceptions;
/**
 * An Exception class that shows the error whit input.
 * shows MinimumInputNumberException
 */
public class MinimumInputNumberException extends Exception {
	/**
	 * Create an error message for MinimumInputNumberException. This method takes an inputValue which is engine name.
	 * 
	 * @param inputValue name of it's engine
	 */
	public MinimumInputNumberException(String inputValue) { 
		super("Exception-02: You need at least 2 input values for "+inputValue+".");    
	} 
}

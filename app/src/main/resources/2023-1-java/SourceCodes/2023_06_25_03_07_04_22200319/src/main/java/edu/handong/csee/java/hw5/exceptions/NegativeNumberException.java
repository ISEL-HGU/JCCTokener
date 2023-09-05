package edu.handong.csee.java.hw5.exceptions;
/**
 * An Exception class that shows the error whit input.
 * shows NegativeNumberException
 */
public class NegativeNumberException extends Exception {
	/**
	 * Create an error message for NegativeNumberException. This method takes an inputValue which is engine name.
	 * 
	 * @param inputValue name of it's engine
	 */
	public NegativeNumberException(String inputValue) { 
		super("Exception-03: The input value cannot be negative for "+inputValue+".");    
	} 
}

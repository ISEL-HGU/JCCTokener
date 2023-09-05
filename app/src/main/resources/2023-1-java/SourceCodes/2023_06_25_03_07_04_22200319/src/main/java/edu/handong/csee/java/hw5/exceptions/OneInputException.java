package edu.handong.csee.java.hw5.exceptions;
/**
 * An Exception class that shows the error whit input.
 * shows OneInputException
 */
public class OneInputException extends Exception {
	/**
	 * Create an error message for OneInputException. This method takes an inputValue which is engine name.
	 * 
	 * @param inputValue name of it's engine
	 */
	public OneInputException(String inputValue) { 
		super("Exception-04: You need one input value for "+inputValue+".");    
	} 

}

package edu.handong.csee.java.hw5.exceptions;
/**
 * An Exception class that shows the error whit input.
 * shows MyNumberFormatException
 */
public class MyNumberFormatException extends NumberFormatException {
	/**
	 * Create an error message for MyNumberFormatException. This method takes an inputValue which is engine name.
	 * 
	 * @param inputValue name of it's engine
	 * @param args the first word of input
	 */
	public MyNumberFormatException(String inputValue,String args) { 
		super("Exception-05: The input value should be converted into a number. ("+args+" is not a number value for "+inputValue+".)");    
	} 
}

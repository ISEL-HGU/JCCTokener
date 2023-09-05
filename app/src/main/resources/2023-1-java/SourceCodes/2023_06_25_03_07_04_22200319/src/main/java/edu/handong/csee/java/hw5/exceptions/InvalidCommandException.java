package edu.handong.csee.java.hw5.exceptions;
/**
 * An Exception class that shows the error whit input.
 * shows InvalidCommandException
 */
public class InvalidCommandException extends Exception {
	/**
	 * Create an error message for InvalidCommandException. This method works when the args.length is 0
	 * 
	 * 
	 */
	public InvalidCommandException() { 
        super("Exception-01: Invalid command: \n" +
                "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }
	/**
	 * Create an error message for InvalidCommandException. This method takes an inputValue which is engine name.
	 * 
	 * @param inputValue name of it's engine
	 */
	public InvalidCommandException(String inputValue) { 
	        super("Exception-01: Invalid command: " + inputValue + "\n" +
	                "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	    }
	} 


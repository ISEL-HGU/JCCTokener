package edu.handong.csee.java.hw4.exceptions;

public class MyNumberFormatException extends NumberFormatException{
	
	public MyNumberFormatException() {
		
	}
	
	public String getMessage(String engineName) {
		
        return ("Exception-05: The input value should be converted into a number.");
	}
	
	public MyNumberFormatException(String message) {
		super(message);
	}
	public MyNumberFormatException(String upperCase, String string) {
		// TODO Auto-generated constructor stub
		System.out.println("Exception-05: The input value should be converted into a number." +
				"(" + string + " is not a number value for " + upperCase + ".)");
		return;
	}
	public String getMessage(String upperCase, String string) {
		//
	
        return ("Exception-05: The input value should be converted into a number." +
				"(" + string + " is not a number value for " + upperCase + ".)");
	}
}
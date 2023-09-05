package edu.handong.csee.java.hw4.exceptions;

public class NegativeNumberException extends Exception{
	
	
	public String getMessage(String engineName) {
		
        return ("Exception-03: The input value cannot be negative for " + engineName.toUpperCase() +".");
	}

}

package edu.handong.csee.java.hw4.exceptions;

public class OneInputException extends Exception{
	
	
	public String getMessage(String engineName) {
		
        return ("Exception-04: You need one input value for " + engineName.toUpperCase() +".");
	}

}
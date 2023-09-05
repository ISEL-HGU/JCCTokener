package edu.handong.csee.java.hw4.exceptions;

public class MinimumInputNumberException extends Exception{
	
	public String getMessage(String engineName) {
		
        return ("Exception-02: You need at least 2 input values for " + engineName.toUpperCase() +".");
	}
	

}

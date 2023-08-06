package edu.handong.csee.java.hw4.exceptions;

public class InvalidCommandException extends Exception {
	
	public InvalidCommandException() {
		// TODO Auto-generated constructor stub
	}
	public InvalidCommandException(String engineName) {
		// TODO Auto-generated constructor stub
		System.out.println("Exception-01: Invalid command: "+ engineName + "\n"
				+ "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}
	public String getMessage(String engineName) {
		//
	
        return ("Exception-01: Invalid command: "+ engineName + "\n"
				+ "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}
	public String getMessage() {
		//
	
        return ("Exception-01: Invalid command: " + "\n"
				+ "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
	}

}

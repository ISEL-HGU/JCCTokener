package edu.handong.csee.java.hw5.exceptions;
/**
 * This exception is thrown when an invalid command is encountered.
 * It indicates that the command provided does not match any valid computing engine options.
 * The exception message provides guidance on how to use the correct command format.
 */
public class InvalidCommandException extends Exception{
	/**
     * Constructs an InvalidCommandException with a default error message.
     * The error message provides information on the correct command format.
     */
	public InvalidCommandException() {
        super("Exception-01: Invalid command: " + "\n" +"Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }
	  /**
     * Constructs an InvalidCommandException with a custom error message.
     * The error message includes the provided message and provides information on the correct command format.
     *
     * @param message Additional message specifying the reason for the invalid command.
     */
    public InvalidCommandException(String message) {

        super("Exception-01: Invalid command: " + message + "\n" + "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    
    }
}

package edu.handong.csee.java.hw5.exceptions;
/**
 * The InvalidCommandException is a custom exception class that represents an invalid command.
 * It is thrown when an invalid command is encountered during the execution of a program.
 */
public class InvalidCommandException extends Exception{
    /**
     * Constructs an InvalidCommandException with a default error message.
     */
    public InvalidCommandException() {
        super("Exception-01: Invalid command: ");
    }
    /**
     * Constructs an InvalidCommandException with a specified error message.
     * 
     * @param message the error message describing the invalid command
     */
    public InvalidCommandException(String message) {
        super("Exception-01: Invalid command: " + message);
    }
}

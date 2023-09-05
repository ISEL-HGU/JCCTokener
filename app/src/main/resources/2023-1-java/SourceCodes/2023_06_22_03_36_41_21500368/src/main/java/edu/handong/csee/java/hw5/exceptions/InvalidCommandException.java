package edu.handong.csee.java.hw5.exceptions;

/**
 * The InvalidCommandException class represents an exception that is thrown when a command is invalid.
 * This class is triggered when a computing engine option is incorrectly entered.
 * This class supports both standard exception messages and custom messages.
 */
public class InvalidCommandException extends Exception {

    /**
     * Default constructor. Uses a standard exception message.
     */
    public InvalidCommandException() {
        super("Exception-01: Invalid command: " + System.lineSeparator()
                + "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }

    /**
     * Overloaded constructor. Uses a custom message for the exception.
     *
     * @param message the custom message for this exception
     */
    public InvalidCommandException(String message) {
        super("Exception-01: Invalid command: " + message + System.lineSeparator()
                + "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }
}

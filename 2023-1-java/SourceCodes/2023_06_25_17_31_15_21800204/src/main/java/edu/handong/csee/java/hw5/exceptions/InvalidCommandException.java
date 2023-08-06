package edu.handong.csee.java.hw5.exceptions;

/**
 * Exception thrown when an invalid command is entered.
 */
public class InvalidCommandException extends Exception {

    /**
     * Default constructor. Initializes exception with a general message.
     */
    public InvalidCommandException() {
        super("Invalid command: \nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }

    /**
     * Constructor. Initializes exception with a specific message.
     * @param args The arguments entered by the user.
     */
    public InvalidCommandException(String[] args) {
        super("Invalid command: " + args[0].toUpperCase() + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }
}

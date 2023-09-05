package edu.handong.csee.java.hw5.exceptions;

/**
 * Exception thrown when only one input value is required.
 */
public class OneInputException extends Exception {

    /**
     * Default constructor. Initializes exception with no message.
     */
    public OneInputException() {
        super();
    }

    /**
     * Constructor. Initializes exception with a specific message.
     * @param engine The engine being used.
     */
    public OneInputException(String engine) {
        super("You need one input value for " + engine + ".");
    }
}


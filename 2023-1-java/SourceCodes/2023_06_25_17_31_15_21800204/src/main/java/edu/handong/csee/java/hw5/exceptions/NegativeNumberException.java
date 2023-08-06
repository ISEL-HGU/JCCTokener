package edu.handong.csee.java.hw5.exceptions;

/**
 * Exception thrown when the input number is negative.
 */
public class NegativeNumberException extends Exception {

    /**
     * Default constructor. Initializes exception with a general message.
     */
    public NegativeNumberException() {
        super("Invalid input. Please enter a positive number.");
    }

    /**
     * Constructor. Initializes exception with a specific message.
     * @param engine The engine being used.
     */
    public NegativeNumberException(String engine) {
        super("The input value cannot be negative for " + engine + ".");
    }
}


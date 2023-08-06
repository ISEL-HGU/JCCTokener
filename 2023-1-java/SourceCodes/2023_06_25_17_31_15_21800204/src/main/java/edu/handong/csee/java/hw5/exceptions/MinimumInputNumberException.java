package edu.handong.csee.java.hw5.exceptions;

/**
 * Exception thrown when there are not enough input numbers.
 */
public class MinimumInputNumberException extends Exception {

    /**
     * Default constructor. Initializes exception with a general message.
     */
    public MinimumInputNumberException() {
        super("Not enough input numbers. Please enter more numbers.");
    }

    /**
     * Constructor. Initializes exception with a specific message.
     * @param minimum The minimum number of input values required.
     * @param engine The engine being used.
     */
    public MinimumInputNumberException(int minimum, String engine) {
        super("You need at least " + minimum + " input values for " + engine + ".");
    }
}

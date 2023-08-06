package edu.handong.csee.java.hw5.clioptions.exceptions;

/**
 * The NegativeNumberException class is an exception that is thrown when an input value is negative.
 * It extends the Exception class.
 */
public class NegativeNumberException extends Exception {

    /**
     * Constructs a NegativeNumberException with the engine name.
     * @param engineName the name of the engine
     */
    public NegativeNumberException(String engineName) {
        super("Exception-03: The input value cannot be negative for " + engineName.toUpperCase() + ".");
    }
}

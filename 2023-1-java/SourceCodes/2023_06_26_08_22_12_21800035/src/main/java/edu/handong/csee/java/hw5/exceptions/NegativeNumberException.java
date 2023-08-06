package edu.handong.csee.java.hw5.exceptions;

/**
 * An exception that is thrown when the input value for the engine is negative.
 */
public class NegativeNumberException extends Exception {

    /**
     * This exception indicates that the input value cannot be negative for the
     * specified engine.
     */
    public NegativeNumberException(String engineName) {
        super("Exception-03: The input value cannot be negative for " + engineName + ".");
    }
}

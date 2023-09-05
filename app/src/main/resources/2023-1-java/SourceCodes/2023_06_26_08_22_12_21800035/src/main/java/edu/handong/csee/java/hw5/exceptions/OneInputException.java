package edu.handong.csee.java.hw5.exceptions;

/**
 * An exception that is thrown when the number of input values for the engine is
 * not exactly one.
 */
public class OneInputException extends Exception {

    /**
     * This exception indicates that exactly one input value is required for the
     * specified engine.
     */
    public OneInputException(String engineName) {
        super("Exception-04: You need one input value for " + engineName + ".");
    }
}

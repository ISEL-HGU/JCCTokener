package edu.handong.csee.java.hw5.exceptions;

/**
 * An exception that is thrown when the number of input values for the engine is
 * less than the required minimum.
 * 
 */
public class MinimumInputNumberException extends Exception {

    /**
     * This exception indicates that at least 2 input values are needed for the
     * specified engine.
     */

    public MinimumInputNumberException(String engineName) {
        super("Exception-02: You need at least 2 input values for " + engineName + ".");
    }
}

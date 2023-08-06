package edu.handong.csee.java.hw5.exceptions;

/**
 * The MinimumInputNumberException class represents an exception that is thrown when the number of inputs is less than the minimum required.
 * This exception is triggered when the input to a specific computing engine is less than its minimum requirement.
 */
public class MinimumInputNumberException extends Exception{

    /**
     * Constructor that takes the name of the engine and the minimum number of required inputs.
     * It generates an exception message that indicates the engine name and the minimum number of inputs required.
     *
     * @param engineName the name of the engine that requires a minimum number of inputs
     * @param TheNumberOfMinimumRequired the minimum number of inputs required for the engine
     */
    public MinimumInputNumberException(String engineName, int TheNumberOfMinimumRequired) {
        super("Exception-02: You need at least " + TheNumberOfMinimumRequired + " input values for " + engineName.toUpperCase() + ".");
    }
}
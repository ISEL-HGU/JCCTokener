package edu.handong.csee.java.hw5.exceptions;

/**
 * The NegativeNumberException class extends the Exception class.
 * This exception is thrown when a method encounters a negative number argument where it is not permitted.
 */
public class NegativeNumberException extends Exception{
    /**
     * Constructor for NegativeNumberException.
     * Constructs a NegativeNumberException with the specified engine name.
     *
     * @param engineName the name of the engine that encountered a negative number
     */
    public NegativeNumberException(String engineName){
        super("Exception-03: The input value cannot be negative for " + engineName.toUpperCase() + ".");
    }
}

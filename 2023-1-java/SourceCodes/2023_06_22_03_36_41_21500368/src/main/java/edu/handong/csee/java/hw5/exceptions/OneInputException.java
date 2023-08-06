package edu.handong.csee.java.hw5.exceptions;

/**
 * The OneInputException class extends the Exception class.
 * This exception is thrown when a method requires exactly one input but does not receive it.
 */
public class OneInputException extends Exception{
    /**
     * Constructor for OneInputException.
     * Constructs a OneInputException with the specified engine name.
     *
     * @param engineName the name of the engine that required exactly one input
     */
    public OneInputException(String engineName){
        super("Exception-04: You need one input value for " + engineName + ".");
    }
}
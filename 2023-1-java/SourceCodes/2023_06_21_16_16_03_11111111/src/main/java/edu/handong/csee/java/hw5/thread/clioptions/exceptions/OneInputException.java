package edu.handong.csee.java.hw5.thread.clioptions.exceptions;

/**
 * The OneInputException class is an exception that is thrown when the required number of inputs is not provided.
 * It extends the Exception class.
 */
public class OneInputException extends Exception {

    /**
     * Constructs a OneInputException with the engine name.
     * @param engineName the name of the engine
     */
    public OneInputException(String engineName) {
        super("Exception-04: You need one input value for " + engineName.toUpperCase() + ".");
    }
}

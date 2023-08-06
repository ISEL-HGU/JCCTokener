package edu.handong.csee.java.hw5.clioptions.exceptions;

/**
 * The MyNumberFormatException class is an exception that is thrown when an input value cannot convert into a number.
 * It extends the NumberFormatException class.
 */
public class MyNumberFormatException extends NumberFormatException {

    /**
     * Constructs a MyNumberFormatException with the engine name and input value.
     * @param engineName the name of the engine
     * @param input the input value that could not be converted into a number
     */
    public MyNumberFormatException(String engineName, String input) {
        super("Exception-05: The input value should be converted into a number. (" + input + " is not a number value for " + engineName + ".)");
    }
}

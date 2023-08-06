package edu.handong.csee.java.hw5.exceptions;

/**
 * Custom exception class for handling number format errors.
 */
public class MyNumberFormatException extends NumberFormatException {
    /**
     * Constructs a with the specified engine name
     * and input.
     *
     * engineName the name of the engine for which the input value is not a number
     * the input value that is not a number
     */

    public MyNumberFormatException(String engineName, String input) {
        super("Exception-05: The input value should be converted into a number. (" + input
                + " is not a number value for " + engineName + ".)");
    }
}

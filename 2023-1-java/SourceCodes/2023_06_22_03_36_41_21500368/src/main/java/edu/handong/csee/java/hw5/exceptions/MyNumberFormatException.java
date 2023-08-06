package edu.handong.csee.java.hw5.exceptions;

/**
 * The MyNumberFormatException class extends the NumberFormatException class.
 * This exception is thrown when a method encounters an argument of a type that it cannot convert to a number.
 */
public class MyNumberFormatException extends NumberFormatException{
    /**
     * Constructor for MyNumberFormatException.
     * Constructs a MyNumberFormatException with the specified engine name and the string that could not be parsed into a number.
     *
     * @param engineName the name of the engine that encountered a parsing error
     * @param message the string that could not be parsed into a number
     */
    public MyNumberFormatException(String engineName, String message) {
        super("Exception-05: The input value should be converted into a number. (" + message + " is not a number value for " + engineName + ".)");
    }
}
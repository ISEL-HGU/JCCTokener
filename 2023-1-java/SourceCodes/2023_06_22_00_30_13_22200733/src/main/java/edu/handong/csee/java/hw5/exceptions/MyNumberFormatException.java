package edu.handong.csee.java.hw5.exceptions;
/**
 * The MyNumberFormatException is a custom exception class that represents a number format exception.
 * It is thrown when a value cannot be converted into a number.
 */
public class MyNumberFormatException extends NumberFormatException {
    /**
     * Constructs a MyNumberFormatException with a default error message.
     */
    public MyNumberFormatException() {
        super("Exception-05");
    }

    /**
     * Constructs a MyNumberFormatException with a specified error message.
     * 
     * @param message the additional information describing the value that couldn't be converted into a number
     */
    public MyNumberFormatException(String message1, String message2) {
        super("Exception-05: The input value should be converted into a number. (" + message1 + " is not a number value for " + message2 + ".)");
    }
}

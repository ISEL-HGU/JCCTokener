package edu.handong.csee.java.hw5.exceptions;

/**
 * MyNumberFormatException class represents an exception that is thrown when the input value cannot be converted into a number.
 */
public class MyNumberFormatException extends NumberFormatException {
    /**
     * Constructs a MyNumberFormatException with the specified detail message.
     * 
     * @param message The detail message of the exception.
     */
    public MyNumberFormatException(String[] args) {
        super("Exception-05: The input value should be converted into a number. (" + args[1] + " is not a number value)\r\n");
    }
}

package edu.handong.csee.java.hw5.exceptions;

/**
 * Exception thrown when the input is not a valid number.
 */
public class MyNumberFormatException extends NumberFormatException {

    /**
     * Default constructor. Initializes exception with a general message.
     */
    public MyNumberFormatException() {
        super("Invalid input. Please enter a valid number.");
    }

    /**
     * Constructor. Initializes exception with a specific message.
     * @param engine The engine being used.
     * @param errorInput The invalid input value.
     */
    public MyNumberFormatException(String engine, String errorInput) {
        super("The input value should be converted into a number. (" + errorInput + " is not a number value for " + engine + ".)");
    }
}

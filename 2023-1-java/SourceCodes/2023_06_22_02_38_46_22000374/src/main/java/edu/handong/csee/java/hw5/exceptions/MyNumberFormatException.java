package edu.handong.csee.java.hw5.exceptions;

/**
 * MyNumberFormatException.java
 */
public class MyNumberFormatException extends NumberFormatException {
    /**
     * MyNumberFormatException / no param
     */
    public MyNumberFormatException() {
        super();
    }

    /**
     * MyNumberFormatException - getMessage
     * 
     * @param errMsg
     */
    public MyNumberFormatException(String errMsg, String name) {
        super("Exception-05: The input value should be converted into a number. (" + errMsg
                + " is not a number value for " + name + ".)");
    }
}

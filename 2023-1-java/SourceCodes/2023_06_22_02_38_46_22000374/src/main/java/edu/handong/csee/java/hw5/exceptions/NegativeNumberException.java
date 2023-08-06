package edu.handong.csee.java.hw5.exceptions;

/**
 * NegativeNumberException.java
 */
public class NegativeNumberException extends Exception {
    /**
     * NegativeNumberException / no param
     */
    public NegativeNumberException() {
        super();
    }

    /**
     * NegativeNumberException - getMessage
     * 
     * @param errMsg
     */
    public NegativeNumberException(String errMsg) {
        super("Exception-03: The input value cannot be negative for " + errMsg + ".");
    }
}

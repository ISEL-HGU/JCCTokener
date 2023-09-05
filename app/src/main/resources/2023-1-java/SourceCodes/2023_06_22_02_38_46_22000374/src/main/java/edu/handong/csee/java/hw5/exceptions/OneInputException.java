package edu.handong.csee.java.hw5.exceptions;

/**
 * OneInputException.java
 */
public class OneInputException extends Exception {
    /**
     * OneInputException / no param
     */
    public OneInputException() {
        super();
    }

    /**
     * OneInputException - getMessage
     * 
     * @param errMsg
     */
    public OneInputException(String errMsg) {
        super("Exception-04: You need one input value for " + errMsg + ".");
    }
}

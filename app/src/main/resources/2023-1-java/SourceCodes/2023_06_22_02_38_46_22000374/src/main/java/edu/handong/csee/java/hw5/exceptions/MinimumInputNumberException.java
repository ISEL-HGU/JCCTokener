package edu.handong.csee.java.hw5.exceptions;

/**
 * MinimumInputNumberException.java
 */
public class MinimumInputNumberException extends Exception {
    /**
     * MinimumInputNumberException / no param
     */
    public MinimumInputNumberException() {
        super();
    }

    /**
     * MinimumInputNumberException - getMessage
     * 
     * @param errMsg
     */
    public MinimumInputNumberException(String errMsg) {
        super("Exception-02: You need at least 2 input values for " + errMsg + ".");
    }

}

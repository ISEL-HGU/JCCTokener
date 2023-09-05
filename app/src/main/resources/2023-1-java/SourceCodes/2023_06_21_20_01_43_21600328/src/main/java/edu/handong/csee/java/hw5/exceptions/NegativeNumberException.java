package edu.handong.csee.java.hw5.exceptions;
/**
 * This code shows error message and closes the program when wrong input is given
 */
@SuppressWarnings("serial")
public class NegativeNumberException extends Exception {
	/**
     * This code gives error for negative numbers as inputs
     * @param engineName that requires input validation
     */
    private String engineName;
    /**
     * Puts the engineName into NegativeNumberException
     * @param engineName
     */
    public NegativeNumberException(String engineName) {
    	super("Exception-03: The input value cannot be negative for " + engineName.toUpperCase() + ".");
        this.engineName = engineName;
    }
    /**
     * Use getter setter to make it encapsulated
     * @return
     */
    public String getEngineName() {
        return engineName;
    }
    /**
     * Use getter setter to make it encapsulated
     * @return
     */
    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }
    /**
     * execute getMessage if error
     */
    public String getMessage() {
        return "Exception-03: The input value cannot be negative for " + engineName.toUpperCase() + ".";
    }
}

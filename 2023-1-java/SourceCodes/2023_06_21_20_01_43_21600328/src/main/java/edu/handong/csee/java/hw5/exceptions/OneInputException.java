package edu.handong.csee.java.hw5.exceptions;
/**
 * This code shows error message and closes the program when wrong input is given
 */
@SuppressWarnings("serial")
public class OneInputException extends Exception {
	/**
     * This code gives error for not giving minimum number of inputs
     * @param engineName for printout
     */
    private String engineName;
    /**
     * Puts the engineName into OneInputException
     * @param engineName
     */
    public OneInputException(String engineName) {
    	super("Exception-04: You need one input value for " + engineName.toUpperCase() + ".");
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
        return "Exception-04: You need one input value for " + engineName.toUpperCase() + ".";
    }
}

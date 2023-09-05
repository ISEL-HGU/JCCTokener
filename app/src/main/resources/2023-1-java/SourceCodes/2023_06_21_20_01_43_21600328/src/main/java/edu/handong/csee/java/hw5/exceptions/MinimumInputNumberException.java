package edu.handong.csee.java.hw5.exceptions;
/**
 * This code shows error message and closes the program when wrong input is given
 */
@SuppressWarnings("serial")
public class MinimumInputNumberException extends Exception {
	/**
     * This code gives error for not giving minimum number of inputs
     * @param numOfRequiredInputs that requires input validation
     * @param engineName for printout
     */
    private int numOfRequiredInputs;
    private String engineName;
    /**
     * Puts the inputs and engineName into MinimumInputNumberException
     * @param numOfRequiredInputs
     * @param engineName
     */
    public MinimumInputNumberException(int numOfRequiredInputs, String engineName) {
    	super("Exception-02: You need at least " + numOfRequiredInputs + " input values for " + engineName.toUpperCase() + ".");
        this.numOfRequiredInputs = numOfRequiredInputs;
        this.engineName = engineName;
    }
    /**
     * Use getter setter to make it encapsulated
     * @return
     */
    public int getnumOfRequiredInputs() {
        return numOfRequiredInputs;
    }
    /**
     * Use getter setter to make it encapsulated
     * @return
     */
    public void setnumOfRequiredInputs(int numOfRequiredInputs) {
        this.numOfRequiredInputs = numOfRequiredInputs;
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
        return "Exception-02: You need at least " + numOfRequiredInputs + " input values for " + engineName.toUpperCase() + ".";
    }
    	
}
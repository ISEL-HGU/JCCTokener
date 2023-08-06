package edu.handong.csee.java.hw5.exceptions;
/**
 * This code shows error message and closes the program when wrong input is given
 */
@SuppressWarnings("serial")
public class MyNumberFormatException extends NumberFormatException {
	/**
     * This code gives error for wrong inputs
     * @param argument for reading input
     * @param engineName for printout
     */
    private String argument;
    private String engineName;
    /**
     * Puts the argument and engineName into MyNumberFormatException
     * @param argument
     * @param engineName
     */
    public MyNumberFormatException(String argument, String engineName) {
    	super("Exception-05: The input value should be converted into a number. (" + argument + " is not a number value for " + engineName.toUpperCase() + ".)");
        this.argument = argument;
        this.engineName = engineName;
    }
    /**
     * Use getter setter to make it encapsulated
     * @return
     */
    public String getArgument() {
        return argument;
    }
    /**
     * Use getter setter to make it encapsulated
     * @return
     */
    public void setArgument(String argument) {
        this.argument = argument;
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
        return "Exception-05: The input value should be converted into a number. (" + argument + " is not a number value for " + engineName.toUpperCase() + ".)";
    }
}
package edu.handong.csee.java.hw5.exceptions;

/**
 * This code shows an error message and closes the program when an invalid command is given
 */
@SuppressWarnings("serial")
public class InvalidCommandException extends Exception {
	/**
     * make variable engineName 
     */
	private String engineName;
    /**
     * Constructs a new InvalidCommandException with the specified engineName.
     * @param engineName the invalid engineName causing the exception
     */
    public InvalidCommandException(String engineName) {
        super("Exception-01: Invalid command" + (engineName != null ? ": " + engineName : "") + "\n" + "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }

    /**
     * Retrieves the engineName associated with the exception.
     *
     * @return the engineName
     */
    public String getEngineName() {
        return engineName;
    }

    /**
     * Sets the engineName associated with the exception.
     *
     * @param engineName the engineName to set
     */
    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    /**
     * Retrieves the error message associated with the exception.
     *
     * @return the error message
     */
    public String getMessage() {
        return super.getMessage();
    }
}
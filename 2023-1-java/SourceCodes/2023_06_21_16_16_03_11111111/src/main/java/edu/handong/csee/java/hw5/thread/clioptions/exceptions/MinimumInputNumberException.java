package edu.handong.csee.java.hw5.thread.clioptions.exceptions;
/**
*
* This exception is thrown when the input number of a command is not sufficient.
*/

public class MinimumInputNumberException extends Exception {

	/**
	 * This is the constructor for the MinimumInputNumberException class which takes two parameters.
	 * @param engineName String of the name of engine.
	 * @param numOfMinInputs The minimum number of inputs required for the engine.
	 */
	public MinimumInputNumberException (String engineName, int numOfMinInputs ) {
	    super("Exception-02: You need at least " + numOfMinInputs + " input values for " + engineName.toUpperCase() + ".");
	}

}

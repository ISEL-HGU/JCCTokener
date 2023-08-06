package edu.handong.csee.java.hw5.exceptions;
/**
 * This is class of MinimumInputNumberExcpetion
 */
public class MinimumInputNumberException extends Exception{
	/**
	 * This is the part that executes exception with 2 parameter.
	 */
	public MinimumInputNumberException(String engineName, int n){
        super ("Exception-02: You need at least 2 input values for "+engineName+".");
	}
	/**
	 * This is the part that executes exception with 1 parameter.
	 */
	public MinimumInputNumberException(String message){
        super (message);
	}
	
}
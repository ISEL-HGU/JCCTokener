package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * An interface that represents a computable object with methods for setting input, performing computation, and retrieving result.
 */
public interface Computable {
    
	/**
     * Sets the input for the computation.
     *
     * @param args the input arguments for the computation
     * @throws MinimumInputNumberException if the minimum number of inputs is not met
     * @throws MyNumberFormatException if an input cannot be parsed into a valid number
     * @throws NegativeNumberException if an input is a negative number
     * @throws OneInputException if the program is expecting one input but multiple inputs are provided
     */
	public void setInput(String[] args) throws MinimumInputNumberException, MyNumberFormatException, NegativeNumberException, OneInputException ;
	 
	/**
     * Performs the computation based on the input.
     */
	public void compute() ;
	 
	/**
     * Returns the result of the computation.
     *
     * @return the result of the computation
     */
	public double getResult();
}
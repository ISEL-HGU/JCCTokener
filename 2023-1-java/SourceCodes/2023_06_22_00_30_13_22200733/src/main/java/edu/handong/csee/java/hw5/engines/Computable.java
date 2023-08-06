package edu.handong.csee.java.hw5.engines;
/**
*An interface for a computable engine that takes input arguments, performs computation, and returns a result.
*/

public interface Computable {
	/**
	*Sets the input arguments for the computation.
	*@param args An array of input arguments
	*/
	public void setInput(String[] args);
	/**
	*Performs the computation using the input arguments.
	*/
	public void compute();
	/**
	*Returns the result of the computation.
	*@return The result of the computation as a double value
	*/
	public double getResult();
}

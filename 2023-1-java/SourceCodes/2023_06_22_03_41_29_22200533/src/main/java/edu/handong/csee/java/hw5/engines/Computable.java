package edu.handong.csee.java.hw5.engines;

/**
 * Skelaton code for each engine.
 * Interface for each engine.
 *
 */
public interface Computable {
	
	/**
	 * The method of obtaining each engine name and the input number to be calculated from the main.
	 * Before handing over each input to the field, make sure that the number of inputs and the contents of the input are appropriate.
	 * Pass the input to each engine field.
	 */
	public void setInput(String[] args);
	
	/**
	 * Methods that implement appropriate calculations for each engine.
	 * Save the calculated value as a result.
	 */
	public void compute();
	
	/**
	 * A method that returns a calculated value and causes another class to use the calculated result value.
	 */
	public double getResult();  
	
	
	
	
}

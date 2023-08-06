package edu.handong.csee.java.hw5.engines;

/**
 * The Computable interface defines a set of methods for computing various
 * mathematical operations.
 */
public interface Computable {
    /**
     * Sets the input values for the computation from the given string array.
     * 
     * @param args The input arguments as a string array.
     */
    public void setInput(String[] args);

    /**
     * Performs the computation based on the input values.
     */
    public void compute();

    /**
     * Returns the result of the computation.
     * 
     * @return The result as a double.
     */
    public double getResult();
}

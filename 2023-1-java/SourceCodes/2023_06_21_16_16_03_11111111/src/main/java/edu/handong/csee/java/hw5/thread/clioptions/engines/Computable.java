package edu.handong.csee.java.hw5.thread.clioptions.engines;

/**
 * An interface that defines methods for computing and retrieving results of computations.
 */
public interface Computable {
    
    /**
     * Sets the input values for a computation.
     *
     * @param input an array of strings representing the input values.
     */
    public void setInput(String[] input);
    
    /**
     * Performs a computation.
     */
    public void compute();
    
    /**
     * Returns the result of a computation.
     *
     * @return the result of the computation as a double.
     */
    public double getResult();
}

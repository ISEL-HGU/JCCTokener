package edu.handong.csee.java.hw4;

/**
 * Computable interface represents a computable operation.
 */
public interface Computable {

    /**
     * Sets the input for the computable operation.
     * 
     * @param args The input arguments as an array of strings.
     */
    public void setInput(String[] args);

    /**
     * Performs the computable operation.
     */
    public void compute();

    /**
     * Returns the result of the computable operation.
     * 
     * @return The result of the computation as a double value.
     */
    public double getResult();

}


package edu.handong.csee.java.hw5.engines;

/**
 * This is the interface for calculation.
 */
public interface Computable {
    /**
     * It is a method that sets the value to be calculated.
     */
    public void setInput(String[] args);
    /**
     * This is the actual calculation method.
     */
    public void compute();

    /**
     * This is the method of obtaining the result value.
     */
    public double getResult();
}

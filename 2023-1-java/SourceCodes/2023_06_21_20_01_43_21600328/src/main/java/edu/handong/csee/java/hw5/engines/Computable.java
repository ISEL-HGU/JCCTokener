package edu.handong.csee.java.hw5.engines;

/**
 * This code is for reading inputs, and computing the numbers inside, and returning results
 */
public interface Computable {
    /**
     * reading the inputs
     * @param args the engineName and numbers
     */
    public void setInput(String[] args); 
    /**
     * computing the calculation
     */
    public void compute();
    /**
     * printing results
     * @return the calculated value
     */
    public double getResult();
}

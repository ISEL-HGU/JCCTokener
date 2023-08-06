package edu.handong.csee.java.hw5.engines;

/**
 * This interface assists in calculations.
 */
public interface Computable {

    /**
     * Converts the input string into the required data type for calculations.
     * 
     * @param input the input string
     * @throws Exception 
     */
    public void setInput(String[] input) throws Exception;

    /**
     * Performs the calculation of numbers according to the calculation method.
     */
    public void compute();

    /**
     * Returns the calculated result value.
     * 
     * @return the result of the calculation
     */
    public double getResult();
}
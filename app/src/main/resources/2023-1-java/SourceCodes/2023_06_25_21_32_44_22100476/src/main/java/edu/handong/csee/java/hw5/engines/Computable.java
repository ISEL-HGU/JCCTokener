package edu.handong.csee.java.hw5.engines;

/**

The Computable interface provides the structure for all calculator engines to follow.
It requires the implementation of methods that will set input values, compute the result,
and return the result as a double value.
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
*/
public interface Computable {
    /**
 * Set the input values for the calculator engine.
 * @param args An array of strings representing the input values.
 */
    public void setInput(String[] args);

    /**
 * Compute the result of the calculator engine.
 */
    public void compute();
/**
 * Get the computed result as a double value.
 * @return The computed result as a double value.
 */
    public double getResult();
    
}

package edu.handong.csee.java.hw5.engines;

/**
 * This interface provides method declaration of commonly used methods.
 */
public interface Computable {
    /**
     * This method sets the argument input to the private state variables.
     * @param arg The input from the terminal written by the user.
     */
    void setInput(String[] arg);
    /**
     * This method does the calculation for each engine classes
     */
    void compute();
    /**
     * This method returns the value calculated through compute method.
     * @return The value that has been calculated through compute method.
     */
    double getResult();
}

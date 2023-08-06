package edu.handong.csee.java.hw5.engines;

public interface Computable {
    /**
    * Set the input values from args.
    */
    public void setInput(String[] args);

    /**
    * Compute the result.
    */
    public void compute();

    /**
    * Get the result.
    * @return the result of computation.
    */
    public double getResult();
}

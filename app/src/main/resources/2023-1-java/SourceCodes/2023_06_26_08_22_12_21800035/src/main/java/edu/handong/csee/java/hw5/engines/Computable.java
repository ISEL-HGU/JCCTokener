package edu.handong.csee.java.hw5.engines;

/**
 * The Computable interface provides methods for setting input, computing a
 * result, and returning the result.
 */
public interface Computable {

    void setInput(String[] args);

    void compute();

    double getResult();
}

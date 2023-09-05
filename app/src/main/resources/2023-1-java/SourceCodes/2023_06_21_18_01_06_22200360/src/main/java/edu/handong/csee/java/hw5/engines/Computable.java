package edu.handong.csee.java.hw5.engines;

/**
 * This interface is This is the interface underlying all engines.
 */
public interface Computable {
    /**
     * This method is setting inputs.
     */
    public void setInput(String[] args);
    /**
     * This method is compute something by some inputs.
     */
    public void compute();
    /**
     * This method gives result.
     */
    public double getResult();
}

package edu.handong.csee.java.hw5.engines;
/**
 * interface for engines
 */
public interface Computable {
    /**
     * method that sets input
     * @param s
     */
    public void setInput(String[] s);
    /**
     * method that computes result
     */
    public void compute();
    /**
     * method that return result
     * @return double
     */
    public double getResult();
}

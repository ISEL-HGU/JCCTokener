package edu.handong.csee.java.hw5.engines;

/**
 * CubeVolEngine.java
 * Computable is Interface.
 */

public interface Computable {
    /**
     * This is inputs of Calculator class.
     * 
     * @param args Type : String[]
     */
    public void setInput(String[] args);

    /**
     * This method is computing calculation.
     */
    public void compute();

    /**
     * This method return calculated result
     * 
     * @return result
     */
    public double getResult();

}

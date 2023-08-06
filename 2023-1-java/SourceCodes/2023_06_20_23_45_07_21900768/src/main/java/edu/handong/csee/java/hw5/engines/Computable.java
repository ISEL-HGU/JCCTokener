package edu.handong.csee.java.hw5.engines;

/**
 * It's an interface of calculator.
 * Every engine classes will implement this interface. 
 */
public interface Computable {
   /**
	 * Every Engines are going to use this for setting the input values.
	 */
   public void setInput(String[] input);
   /**
	 * Every Engines are going to use this for computing.
	 */
   public void compute();
    /**
	 * Every Engines are going to use this for getting result.
	 */
   public double getResult();
}

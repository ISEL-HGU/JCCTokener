/**
 * The `Computable` interface defines the methods that all engine classes must implement to perform their computations.
 * 
 * This interface has three methods: `setInput()`, `compute()`, and `getResult()`. All engine classes that implement 
 * this interface must provide implementations for these methods.
 */
package edu.handong.csee.java.hw5.engines;

public interface Computable {
   /**
    * Sets the input values for the engine.
    * 
    * @param args the array of input values
    */
   public void setInput(String[] args);
   
   /**
    * Performs the computation for the engine.
    */
   public void compute();
   
   /**
    * Returns the result of the computation.
    * 
    * @return the result of the computation
    */
   public double getResult(); 
}


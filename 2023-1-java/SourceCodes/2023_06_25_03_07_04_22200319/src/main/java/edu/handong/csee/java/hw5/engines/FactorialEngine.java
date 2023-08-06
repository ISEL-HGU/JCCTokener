package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * An engine class that calculates the factorial of a given input.
 * Makes factorial
 */
public class FactorialEngine implements Computable {
    private static final String engineName ="FACTORIAL";
    private int n;
    private double result;
        /**
     * Returns the value of n
     * 
     * @return the new value of n
     */
    public int getN() {
        return n;
    }
    
    /**
     * Set the value of n
     * @param n the new value of n
     */
    public void setN(int n) {
        this.n = n;
    }
    
    /**
     * Get the value of result
     * @return the value of result
     */
    public double getResult() {
        return result;
    }
    
    /**
     * Set the value of result
     * @param result the new value of result
     */
    public void setResult(double result) {
        this.result = result;
    }
   /**
    * Sets the input values for the engine.
    * 
    * @param args the array of input values
    */
   public void setInput(String[] args){
    try {
    	if (Double.parseDouble(args[1]) < 0) 
            throw new NegativeNumberException(engineName);
    	if ((args.length)-1>1) 
            throw new OneInputException(engineName);
    	n = Integer.parseInt(args[1]); // Convert the input to an integer
    }catch(NegativeNumberException e){
   	 System.out.println (e.getMessage ());
   	System.exit(0);
 	
   }catch(OneInputException e){
  	 System.out.println (e.getMessage ());
  	System.exit(0);
	
  }
    
   }
   
   /**
    * Performs the computation for the engine.
    */
    public void compute() {
        result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
    }
    

}


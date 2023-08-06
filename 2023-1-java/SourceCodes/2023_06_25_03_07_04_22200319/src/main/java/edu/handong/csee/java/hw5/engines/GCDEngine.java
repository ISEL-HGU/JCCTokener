package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

public class GCDEngine implements Computable {
    private static final String engineName = "GCD";
    private int a[];
    private int result;
    /**
     * Returns the value of a
     * 
     * @return the new calue of a
     */
    public int[] getN() {
        return a;
    }
    
    /**
     * Set the value of a
     * @param a the new value of a
     */
    public void setN(int[] a) {
        this.a = a;
    }
    
/**
     * Set the value of result
     * @param result the new value of result
     */
    public void setResult(int result) {
        this.result = result;
    }
    
    /**
     * Sets the input values for the engine.
     * 
     * @param args the array of input values
     */
    public void setInput(String[] args) {
            try {
            	a = new int[args.length];
            	for(int i=1; i<args.length; i++) {
    	    	if (Double.parseDouble(args[1]) < 0) 
    	            throw new NegativeNumberException(engineName);
    	    	if ((args.length)-1<2) 
    	    		throw new MinimumInputNumberException(engineName);
            	a[i-1] = Integer.parseInt(args[i]);
            	}
            }catch(NegativeNumberException e){
           	 System.out.println (e.getMessage ());
           	System.exit(0);
         	
            }catch(MinimumInputNumberException e){
           	 System.out.println (e.getMessage ());
           	System.exit(0);
           }
    }
   
    /**
     * Performs the computation for the engine. 
     * Get gcd from a[]
     */
    public void compute() {
        
        result = a[0];
        
        for (int i = 1; i < a.length-1; i++) {
            result = gcd(result, a[i]);
        }
    }
    
    /**
     * Returns the result of the computation.
     * 
     * @return the result of the computation
     */
    public double getResult() {
        return result;
    }
    
    /**
     * Computes the greatest common divisor of two numbers using Euclid's algorithm.
     * 
     * @param a the first number
     * @param b the second number
     * @return the greatest common divisor of a and b
     */
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}

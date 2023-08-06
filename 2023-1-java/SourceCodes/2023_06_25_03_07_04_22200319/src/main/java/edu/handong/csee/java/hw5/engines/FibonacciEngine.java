package edu.handong.csee.java.hw5.engines;


import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

public class FibonacciEngine implements Computable {
    private static final String engineName = "FIBONACCI";
    private int n;
    private double result;
    /**
     * Returns the value of n
     * 
     * @return the new calue of n
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
    public void setInput(String[] args) {
    	  try {
    	    	if (Double.parseDouble(args[1]) < 0) 
    	            throw new NegativeNumberException(engineName);
    	    	if ((args.length)-1>1) 
    	    		throw new OneInputException(engineName);
    	    	n = Integer.parseInt(args[1]);// Convert the input to an integer
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
     * Calculate fibonaccinumber
     */
    public void compute() {
        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            double[] fib = new double[n+1];
            fib[0] = 0;
            fib[1] = 1;
            
            for (int i = 2; i <= n; i++) {
                fib[i] = fib[i-1] + fib[i-2];
            }
            
            result = fib[n];
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
}


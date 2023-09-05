package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * The `SQRTEngine` class implements the `Computable` interface and performs 
 * the computation for the square root of a given input value.
 */
public class SQRTEngine implements Computable {
    private static final String engineName ="SQRT";
    private double input;
    private double result;
    /**
     * Returns the value of input
     * 
     * @return the new calue of input
     */
    public double getInput() {
        return input;
    }
    
    /**
     * Set the value of input
     * @param input the new value of input
     */
    public void setInput(double input) {
        this.input = input;
    }
    
/**
     * Set the value of result
     * @param result the new value of result
     */
    public void setResult(double result) {
        this.result = result;
    }
    
    /**
     * Sets the input value for the engine.
     * 
     * @param args the array of input values (only the first value is used)
     */
    public void setInput(String[] args) {
    	try {
	    	if (Double.parseDouble(args[1]) < 0) 
	            throw new NegativeNumberException(engineName);
	    	if ((args.length)-1>1) 
	    		throw new OneInputException(engineName);
    input = Double.parseDouble(args[1]); // Parse the radius from the input
	  }catch(NegativeNumberException e){
	    	 System.out.println (e.getMessage ());
	    	 System.exit(0);
	    }catch(OneInputException e){
	   	 System.out.println (e.getMessage ());
	   	System.exit(0);
	   }
    }

    /**
     * Computes the square root of the input value.
     * Calculate SQRT using Math.sqrt()
     */
    public void compute() {
        result = Math.sqrt(input);
    }

    /**
     * Returns the result of the computation.
     * 
     * @return the square root of the input value
     */
    public double getResult() {
        return result;
    }
}


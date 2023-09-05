package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that provides a service for calculating the factory.
 *
 */
public class FactorialEngine implements Computable {
    private static final String engineName ="FACTORIAL";
    private int n;
    private double result;
    
    /**
	 * The method of obtaining the input number to be calculated.
	 * Before handing over each input to field n, make sure that the number of inputs is 1 and that it is not negative.
     * Hand over the input value to field n.
	 */
    public void setInput(String[] args) {
    	
    	try {            
			if (args.length != 1)
				throw new OneInputException(engineName);       
		}
		catch(OneInputException e) {            
			System.out.println(e.getMessage()); 
			System.exit(0);
		} 
    	
    	
    	try {
    	    n = Integer.parseInt(args[0]);
    	    
    	    
    	} catch (NumberFormatException e) {
    		try {
    			throw new MyNumberFormatException(args[0], engineName);
    		}
    		catch (MyNumberFormatException e1) {
        		
        		System.out.println(e1.getMessage()); 
    			System.exit(0);
        		
        	}
    	    
    	}
    	
    	try {            
        		if(Integer.parseInt(args[0]) < 0) {
        			throw new NegativeNumberException(engineName);
        		}
		}
		catch(NegativeNumberException e) {            
			System.out.println(e.getMessage()); 
			System.exit(0);
		} 
    	

    }
    
    /**
     * Method for calculating the factory.
     * Hand over the result value.
     */
	public void compute() {
		int result = 1;
		for (int i = 1; i <= n; i++) {
            result *= i;
        }
		
		this.result = result;
		
	}

	/**
	 * A method that returns the calculated result value so that it can be used by other classes or methods.
	 */
	public double getResult(){
		return result;
	}
	
	
	/**
	 * The getter method that returns engine name for use by other methods.
	 */
	public static String getEnginename() {
		return engineName;
	}

	/**
	 * A setter method that allows results to be obtained from other methods.
	 */
	public void setResult(double result) {
		this.result = result;
	}
	
	/**
	 * The getter method that returns input for use by other methods.
	 */
	public int getN() {
		return n;
	}

	/**
	 * A setter method that allows input to be obtained from other methods.
	 */
	public void setN(int n) {
		this.n = n;
	}
	
	
    
}

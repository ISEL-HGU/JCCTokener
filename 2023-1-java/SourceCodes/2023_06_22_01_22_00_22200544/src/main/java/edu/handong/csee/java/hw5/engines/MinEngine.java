package edu.handong.csee.java.hw5.engines;

//import java.util.Arrays;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that provides a service for calculating the minimum value among several numbers.
 *
 */
public class MinEngine implements Computable {
    private static final String engineName ="MIN";
    private int[] input;
    private int result;
    private int num;
    
    /**
     *The getter method that returns num for use by other methods.
     */
    public int getNum() {
		return num;
	}

    /**
     *  A setter method that allows num to be obtained from other methods.
     */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * The method of obtaining the input number to be calculated.
	 * Before handing over each input to the field input, make sure that the number of inputs is two or more.
	 * Each input value is handed over to the field input.
	 */
    public void setInput(String[] args) {
    	
    	try {            
			if (args.length < 2)
				throw new MinimumInputNumberException(engineName);       
		}
		catch(MinimumInputNumberException e) {            
			System.out.println(e.getMessage()); 
			System.exit(0);
		} 
    	try {
    		input = new int[args.length];
        	for(int i= 0; i<args.length; i++) {
        		num = i;
        		input[i] = Integer.parseInt(args[i]);
        	}
    	    
    	    
    	} catch (NumberFormatException e) {
    		try {
    			throw new MyNumberFormatException(args[num], engineName);
    		}
    		catch (MyNumberFormatException e1) {
        		
        		System.out.println(e1.getMessage()); 
    			System.exit(0);
        		
        	}
    	    
    	}
    	
 
    }
	
    /**
     * The method for calculating the minimum value.
     * Hand over the result value.
     */
	public void compute() {

		int min = input[0];
		   for (int i = 1; i < input.length; i++) {
		       if (input[i] < min) {
		           min = input[i];
		       }
		   }
		   result = min;
	}

	/**
	 * A method that returns the calculated result value so that it can be used by other classes or methods.
	 */
	public double getResult(){
		
		return result;
		
	}
	
	/**
	 * A setter method that allows input to be obtained from other methods.
	 */
	public void setInput(int[] input) {
		this.input = input;
	}
	

	/**
	 * The getter method that returns input for use by other methods.
	 */
	public int[] getInput() {
		return input;
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
	public void setResult(int result) {
		this.result = result;
	}
    
}

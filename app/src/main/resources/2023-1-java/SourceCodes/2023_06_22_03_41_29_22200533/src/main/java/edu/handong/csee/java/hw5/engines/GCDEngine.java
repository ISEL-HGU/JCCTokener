package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that provides a service for calculating the maximum common divisor of two numbers.
 *
 */
public class GCDEngine implements Computable {
    private static final String engineName ="GCD";
    private int input[];
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
	 * Before handing over each input to fields a and b, make sure that the number of inputs is 2 and that the input is not negative.
	 * Pass the input values to field a and field b, respectively.
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
    	
    	try {            
    		for(int i=0; i<args.length; i++) {
        		if(Integer.parseInt(args[i]) < 0) {
        			throw new NegativeNumberException(engineName);
            	}
        	}     
		}
		catch(NegativeNumberException e) {            
			System.out.println(e.getMessage()); 
			System.exit(0);
		} 
    	
    	
    	
    	
    }
	
    /**
     * Method for calculating the maximum common divisor.
     * Hand over the result value.
     */
	public void compute() {

		int result = input[0];
        for (int i = 1; i < input.length; i++) {
        	
            result = getGCD(result, input[i]);
        }
        this.result = result;
	}
	
	/**
	 * Method for obtaining the maximum common divisor of two numbers
	 * It is used in the compute() method.
	 */
	public static int getGCD(int num1, int num2) {
        if (num1 % num2 == 0) {
            return num2;
        }
        return getGCD(num2, num1 % num2);
    }

	/**
	 * A method that returns the calculated result value so that it can be used by other classes or methods.
	 */
	public double getResult(){
		return result;
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

	/**
	 * A setter method that allows input to be obtained from other methods.
	 */
	public void setInput(int[] input) {
		this.input = input;
	}
	
	
    
}

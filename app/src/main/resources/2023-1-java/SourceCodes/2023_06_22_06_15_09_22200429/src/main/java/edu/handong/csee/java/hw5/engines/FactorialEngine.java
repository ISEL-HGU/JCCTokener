package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class named FactorialEngine, which inherits from Computable.
 */
public class FactorialEngine implements Computable {

  
    private static final String engineName ="FACTORIAL";

 
    private int n;

   
    private double result;

    /**
     * This method returns the Enginename.
     */
    public static String getEngineName() {
        return engineName;
    }

    /** 
     * This is a method that returns n.
     */
    public int getN() {
        return n;
    }

    /**
     * This is a method to set n.
     */
    public void setN(int n) {
        this.n = n;
    }

    /**	
     * This is the method to set the result and refer to the result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This is how to set the inputs in the factorial,
     * And use try catch, Exception is least input value is 1 and Max num of input is 1
     */
    public void setInput(String[] inputs) {
    	
    	try {
            n = Integer.parseInt(inputs[0]);
        } catch (NumberFormatException e) {
            System.out.println("Exception-05: The input value should be converted into a number. (def is not a number value for FACTORIAL.)");
            System.exit(0); 
        }
    	
    	 try {
             if (inputs.length < 1)
                 throw new MinimumInputNumberException("Exception-02: You need at least 1 input values for FACTORIAL.\r\n"	+ "");
         }catch (MinimumInputNumberException e) {
             System.out.println(e.getMessage());
             System.exit(0);
         }
         
    	  try{
    	    	if(inputs.length >=2)
    	    	throw new OneInputException("Exception-04: You need one input value for FACTORIAL.\r\n"	+ "");
    	    	}
    	    	catch(OneInputException e) {
    	    		System.out.println(e.getMessage());
    	    		System.exit(0);
    	    	}
    	 
        try {
            if(n < 0)
            throw new NegativeNumberException("Exception-03: The input value cannot be negative for FACTORIAL.\r\n" + "");  
             }catch(NegativeNumberException e){
            System.out.println(e.getMessage());
        	System.exit(0);
            }
    }

    
    /**
     * This is the method to calculate the factorial.
     */
    public void compute() {
        result = 1;
        for(int i = 1; i<=n; i++)
        {result *= i;}
    }
    
    /**
     * This method returns the result of a factorial.
     */
    public double getResult() {
        return result;
    }

		
	}

 

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * This is a class of fibonacciEngine, and inherits from class computable.
 */
public class FibonacciEngine implements Computable {

 
    private static final String engineName ="FIBONACCI";


   
    private int n ;

 
    private double result;
    
    /**
     * This method returns the enginName.
     */
    public static String getEngineName() {
        return engineName;
    }

    /**
     * This is a method to get n.
     */
    public int getN() {
        return n;
    }

    /**
     *  This method sets n.
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * This method sets the result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    
    /**
     * This is how to set the input of fibonacci,
     * use try catch, Exception is least of input value is 1 and Max num of input is 1.
     */
    public void setInput(String[] inputs) {
    	    try {
    	        n = Integer.parseInt(inputs[0]);
    	    } catch (NumberFormatException e) {
    	        System.out.println("Exception-05: The input value should be converted into a number. (abc is not a number value for FIBONACCI.)");
    	        System.exit(0); 
    	    }

    	    
    	    try {
    	        if (inputs.length < 1)
    	            throw new MinimumInputNumberException("Exception-02: You need at least 1 input value for FIBONACCI.\r\n" + "");
    	    } catch (MinimumInputNumberException e) {
    	        System.out.println(e.getMessage());
    	        System.exit(0);
    	    }

    	    try {
    	        if (inputs.length >= 2)
    	            throw new OneInputException("Exception-04: You need one input value for FIBONACCI.\r\n" + "");
    	    } catch (OneInputException e) {
    	        System.out.println(e.getMessage());
    	        System.exit(0);
    	    }

    	    try {
    	        if (n < 0)
    	            throw new NegativeNumberException("Exception-03: The input value cannot be negative for FIBONACCI.");
    	    } catch (NegativeNumberException e) {
    	        System.out.println(e.getMessage());
    	        System.exit(0);
    	    }
    	}


    
    /**
     * A method to calculate fibonacci.
     */
    public void compute(){
        if(n == 0){
            result = 0;
        } else if(n == 1){
            result = 1;
        } else {
            double prev = 0;
            double curr = 1;
            for(int i = 2; i <= n; i++){
                double next = prev + curr;
                prev = curr;
                curr = next;
            }
            result = curr;
        }
    }

    /**
     * This method returns the result of fibonacci.
     */
    public double getResult(){
        return result;
    }


		
	}


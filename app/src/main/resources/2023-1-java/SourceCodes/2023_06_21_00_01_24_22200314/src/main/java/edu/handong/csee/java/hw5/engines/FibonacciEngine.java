package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * This class calculate the Fibonacci of the value input received from Calculator.java
 */
public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";
    /**
     * This method returns the Engine Name.
     * @return The Engine Name of the class.
     */
    public static String getEnginename() {
        return engineName;
    }

    private int n;
    private double result;

    /**
     * This method returns the number for Fibonacci calculation.
     * @return The number to be calculated in Fibonacci. 
     */
    public int getN() {
        return n;
    }
    /**
     * This method sets the value n which is the number for Fibonacci calcuation.
     * @param n The number to be set as n.
     */
    public void setN(int n) {
        this.n = n;
    }
    /**
     * This method sets the value of result.
     * @param result The value to be set as result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method sets the n as the arg[0] index. 
     * There are some exceptions: 1. If there are more than 1 received arguments, the program is prints an error and gets terminated. 2. If the value of the arg[0] is negative, the program is prints an error and gets terminated. 
     * @param arg String array from the terminal. This value would be the input value also known as the n.
     */
    public void setInput(String[] arg) {
    	try {
	        if(arg.length != 2){
	        	throw new OneInputException(engineName);
	            //InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName, 1);
	        }
	        
	        if(!MyNumberFormatException.isNumber(arg[1])) {
        		throw new MyNumberFormatException(arg[1], engineName);
        	}
	
	        if(Integer.parseInt(arg[1]) < 0){
	        	throw new NegativeNumberException(engineName);
	            //InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
	        }
	        
	        n = Integer.parseInt(arg[1]);
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e){
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}

    	
    }
    
    /**
     * This method does the calculation for Fibonacci n.
     */
    public void compute() {
    	int n1 = 0;
    	int n2 = 1;
		int n3 = n1 + n2;
        
    	for(int i = 2; i <= n; i++) {
    		n3 =  n1 + n2;
    		n1 = n2;
    		n2 = n3;
    	}
    	result = n3;
    }
    
    /**
     * This method returns the result done from Fibonacci calculation.
     * @return The result done from calculating Fibonacci n. 
     */
    public double getResult() {
    	return result;
    }
}

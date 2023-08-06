package edu.handong.csee.java.hw5.thread.clioptions.engines;

import edu.handong.csee.java.hw5.thread.clioptions.exceptions.*;

/**
 * A FibonacciEngine class that calculates the nth Fibonacci number.
 * It implements the Computable interface
 */
public class FibonacciEngine implements Computable {
    /**
     * A static final String that represents the engine name, which is "FACTORIAL".
     */
    private static final String engineName ="FIBONACCI";
    /**
     * The value of n to calculate the Fibonacci number.
     */
    private int n;
    /**
     * The calculated result of the nth Fibonacci number.
     */
    private double result;
    
    /**
     * Returns the value of n to calculate the Fibonacci number.
     * @return the value of n.
     */
    public int getN() {
        return n;
    }
    /**
     * Sets the value of n to calculate the Fibonacci number.
     * @param n the value of n to set.
     */
    public void setN(int n) {
        this.n = n;
    }
    /**
     * Sets the result of the nth Fibonacci number
     * @param result the result of the nth Fibonacci number.
     */
    public void setResult(double result) {
        this.result = result;
    }
    /**
     * Returns the calculated result of the nth Fibonacci number.
     * @return the calculated result of the nth Fibonacci number.
     */
    public double getResult() {
        return result;
    }
    /**
     * Returns the name of this engine.
     * @return the name of this engine.
     */
    public static String getEngineName() {
        return engineName;
    }
    /**
     * Sets the value of n used to calculate the Fibonacci number.
     * If the length of input is not equal to 1, it prints an error for required inputs
     * If the input value is negative, it prints an error message for negative inputs
     * @param input an array of string input values.
     * @throws OneInputException if the length of input is not equal to 1.
	 * @throws MyNumberFormatException if the input value is not a valid number.
	 * @throws NegativeNumberException if the input value is negative.
     */
    public void setInput(String[] input) {
    	try {
            int len = input.length - 1;
            if(len != 1) {
            	throw new OneInputException(getEngineName());
            }
            try {
                setN(Integer.parseInt(input[1]));
            }catch(NumberFormatException e) {
        		throw new MyNumberFormatException(getEngineName(),input[1]);
        	}

            if( getN() < 0) {
            	throw new NegativeNumberException(getEngineName());
            }
    		
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    

    }
    
    /**
     * Calculates the nth Fibonacci number.
     * @param n the value of n to calculate the Fibonacci number.
     * @return the calculated nth Fibonacci number.
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }
    /**
     * Calculates the nth Fibonacci number and sets the result.
     */
    public void compute() {
        setResult(fib(getN()));
    }
}

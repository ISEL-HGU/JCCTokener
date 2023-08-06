package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A fibonacci calculator.
 */
public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";
    private int n;
    private double result;

    /**
     * Sets a number of fibonacci.
     * 
     * @param n a number of fibonacci
     */
    public void setN(int n){
        this.n = n;
    }

    /**
     * Gets the number of fibonacci.
     * 
     * @return the number of fibonacci
     */
    public int getN(){
        return n;
    }

    /**
     * Sets a result of calculating the fibonacci.
     * 
     * @param result a result of calculating the fibonacci
     */
    public void setResult(double result){
        this.result = result;
    }

    /**
     * Converts the input string into the int type for calculations.
     * 
     * @param input the input string
     */
    public void setInput(String[] input){
    	if(input.length > 1){
        	try {
        		throw new OneInputException(engineName);
        	} catch(OneInputException e){
        		System.out.println(e.getMessage());
        		System.exit(0);
        	}
        }
    	try {
    		n = Integer.parseInt(input[0]);
    	} catch(NumberFormatException e) {
    		try {
    			throw new MyNumberFormatException(engineName, input[0]);
    		} catch(MyNumberFormatException ex){
    			System.out.println(ex.getMessage());
    			System.exit(0);
    		}
    	}
        if(n < 0){
        	try {
        		throw new NegativeNumberException(engineName);
        	} catch(NegativeNumberException e){
        		System.out.println(e.getMessage());
        		System.exit(0);
        	}
        }
    }

    /**
     * Calculates the fibonacci.
     */
    public void compute(){
        if (n <= 1) {
            result = n;
        }
        else{
            int prev1 = 0, prev2 = 1, fib = 0;
            for (int i = 2; i <= n; i++) {
            fib = prev1 + prev2;
            prev1 = prev2;
            prev2 = fib;
            }
        result = fib;
        }

    }

    /**
     * Returns the calculated result value.
     * 
     * @return the result of the calculation
     */
    public double getResult(){
        return result;
    }

}
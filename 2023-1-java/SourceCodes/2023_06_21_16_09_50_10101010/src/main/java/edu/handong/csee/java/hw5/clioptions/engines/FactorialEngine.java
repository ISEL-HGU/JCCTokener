package edu.handong.csee.java.hw5.clioptions.engines;

import edu.handong.csee.java.hw5.clioptions.exceptions.*;
/**
 * This class implements the Computable interface and
 * calculates the factorial of a u
 */
public class FactorialEngine implements Computable {
    /**
     * Returns the name of the engine.
     * @return engineName
     */
    public static String getEnginename() {
        return engineName;
    }
     /**
    * A static final String that represents the engine name, which is "FACTORIAL".
    */
    private static final String engineName ="FACTORIAL";
    private int n;
    private double result;

    /**
     * Returns the value of n.
     * @return n
     */
    public int getN() {
        return n;
    }
    /**
     * Sets the value of n.
     * @param n an integer
     */
    public void setN(int n) {
        this.n = n;
    }
    /**
     * Returns the computed result.
     * @return result
     */
    public double getResult() {
        return result;
    }
    /**
     * Sets the computed result.
     * @param result a double value
     */
    public void setResult(double result) {
        this.result = result;
    }
    /**
     * Sets the input values.
     * If the length of input is not equal to 1, it prints an error for required inputs
     * If the input value is negative, it prints an error message for negative inputs
     * @param input an array of strings
     * @throws OneInputException if the length of input is not equal to 1.
	 * @throws MyNumberFormatException if the input value is not a valid number.
	 * @throws NegativeNumberException if the input value is negative.
     */
    public void setInput(String[] input) {
    	try {
            int len = input.length - 1;
            if(len != 1) {
                throw new OneInputException(getEnginename());
                
            }
            try {
                setN(Integer.parseInt(input[1]));	
            }catch(NumberFormatException e) {
        		throw new MyNumberFormatException(getEnginename(),input[1]);
        	}

            if( getN() < 0) {
                throw new NegativeNumberException(getEnginename());
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
     * Computes the factorial of n and sets the result.
     */
    public void compute() {
        int temp = 1;
        int limit = getN();
        for(int i = 1; i <= limit; i++) {
            temp = temp * i;
        }
        setResult(temp);
    }
    
}

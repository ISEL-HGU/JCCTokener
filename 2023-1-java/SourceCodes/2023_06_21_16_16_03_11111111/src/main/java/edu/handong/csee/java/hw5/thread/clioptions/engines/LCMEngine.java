package edu.handong.csee.java.hw5.thread.clioptions.engines;

import edu.handong.csee.java.hw5.thread.clioptions.exceptions.*;
/**
 * This LCMEngine class implements the computable interface.
 * It calculates the LCM of given integers.
 */
public class LCMEngine implements Computable{
    private static final String engineName ="LCM";
    /**
     * This method returns the engine name as a String.
     * 
     * @return A String representing the engine name
     */
    public static String getEngineName() {
        return engineName;
    }

    /**
     * The Integer array that represents in the inputs
     */
    private int[] a; 
    /**
     * The calculated result of the LCM of the array of integers
     */
    private int result;

    /**
     * This method returns the array of integers that calculate the LCM
     * 
     * @return An array of integers used to calculate the LCM
     */
    public int[] getA() {
        return a;
    }

    /**
     * This method sets the array of integers that calculate the LCM
     * 
     * @param a An array of integers to calculate the LCM
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * This method sets the result of the LCM calculation
     * 
     * @param result An integer representing the LCM 
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * This method sets the input for the LCM calculation.
     * 
     * @param input An array of strings representing the input for the LCM calculation
     * @throws MinimumInputNumberException if the length of input is less than 2.
     * @throws MyNumberFormatException if any input value is not a valid number.
     * @throws NegativeNumberException if any input value is negative.
     */
    public void setInput(String[] input) {
    	try {
            int len = input.length - 1;
            if(len < 2) {
            	throw new MinimumInputNumberException(getEngineName(), 2);
            }
            int[] temp = new int[len];
            for(int i = 0; i < len; i++) {
            	try {
                    temp[i] = Integer.parseInt(input[i + 1]);
                    
                    if(temp[i] < 0) {
                    	throw new NegativeNumberException(getEngineName());
                    }
            	}
            	catch(NumberFormatException e) {
            		throw new MyNumberFormatException(getEngineName(),input[i + 1]);
            	}
            	catch(NegativeNumberException e) {
            		System.out.println(e.getMessage());
            		System.exit(0);
            	}
            }
            this.a = temp;
    	}
    	catch(MinimumInputNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}

    	catch(MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    

    }

    /**
     * This method computes the LCM using the array of integers provided as input.
     */
    public void compute() {
        int fin = getA()[0];
        for (int i = 1; i < getA().length; i++) {
            fin = lcm(fin, getA()[i]);
        }
        setResult(fin);
    }

    /**
     * This method returns the result of the LCM calculation.
     * 
     * @return A double representing the result of the LCM calculation
     */
    public double getResult() {
        return result;
    }

    /**
     * This method computes the GCD of two integer.
     * 
     * @param a An integer representing the first number
     * @param b An integer representing the second number
     * @return An integer representing the greatest common divisor of the input numbers
     */
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }   
    }

    /**
     * This method computes the LCM of two integers using the GCD function
     * 
     * @param a An integer representing the first number
     * @param b An integer representing the second number
     * @return An integer representing the least common multiple of the input numbers
     */
    public int lcm(int a, int b) {
            return (a * b) / gcd(a, b);
    }
}

package edu.handong.csee.java.hw5.clioptions.engines;

import edu.handong.csee.java.hw5.thread.clioptions.exceptions.*;
/**
 * The GCDEngine class implements the Computable interface to 
 * compute the greatest common divisor (GCD) of a set of integers.
 */
public class GCDEngine implements Computable {
    /**
     * Returns the name of this engine
     * @return the name of this engine
     */
    public static String getEngineName() {
        return engineName;
    }
    /**
     * The private static String representing the name of the engine
     */
    private static final String engineName = "GCD";
    /**
     * The Integer array that represents in the inputs
     */
    private int[] a; 
    /**
     * The calculated result of the GCD of the array of integers
     */
    private int result;
    
    /**
     * Returns the array of integers input
     * @return the array of integers input
     */
    public int[] getA() {
        return a;
    }
    
    /**
     * Sets the array of integers input
     * @param a the array of integers to set as input
     */
    public void setA(int[] a) {
        this.a = a;
    }
    
    /**
     * Sets the result of the computation
     * @param result the computed result to set
     */
    public void setResult(int result) {
        this.result = result;
    }
    
    /**
     * Sets the input for computation
     * @param input an array of Strings representing the input values
     * @throws MinimumInputNumberException if the length of input is less than 2.
     * @throws MyNumberFormatException if any input value is not a valid number.
     * @throws NegativeNumberException if any input value is negative.
     */
    public void setInput(String[] input) {
    	try {
            int len = input.length - 1;
            if (len < 2) {
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
            	catch(NegativeNumberException e) {
            		System.out.println(e.getMessage());
            		System.exit(0);
            	}
            	catch(NumberFormatException e) {
            		throw new MyNumberFormatException(getEngineName(),input[i + 1]);
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
     * Computes the GCD of an array of integers
     * @param nums an array of integers to find the GCD for
     * @return the GCD of the input integers
     */
    public int gcd_completer(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result = gcd(result, nums[i]);
        }
        return result;
    }
    
    /**
     * Computes the GCD of two integers a and b
     * @param a the first integer
     * @param b the second integer
     * @return the GCD of a and b
     */
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }   
    }

    /**
     * Computes the GCD of the input integers and sets the result
     */
    public void compute(){
        int[] temp = getA();
        int fin = temp[0];
        for (int i = 1; i < temp.length; i++) {
            fin = gcd(fin, temp[i]);
        }
        setResult(fin);
    }
    
    /**
     * Returns the computed result
     * @return the computed GCD as a double
     */
    public double getResult(){
        return result;
    }
}


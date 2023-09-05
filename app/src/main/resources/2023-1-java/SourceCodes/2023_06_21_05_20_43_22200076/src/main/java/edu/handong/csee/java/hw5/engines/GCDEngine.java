package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A greatest common divisor calculator.
 */
public class GCDEngine implements Computable {
    private static final String engineName ="GCD";
    private int[] a;
    private int result;

    /**
     * Sets numbers to calculate GCD.
     * 
     * @param a numbers to calculate GCD
     */
    public void setA(int[] a){
        this.a = a;
    }

    /**
     * Gets numbers to calculate GCD.
     * 
     * @return numbers to calculate GCD
     */
    public int[] getA(){
        return a;
    }

    /**
     * Sets a result of calculating the GCD.
     * 
     * @param result a value of calculating the GCD
     */
    public void setResult(int result){
        this.result = result;
    }

    /**
     * Converts the input string into the int type for calculations.
     * 
     * @param input the input string
     */
    public void setInput(String[] input){
        a = new int[input.length];
        if(a.length < 2){
        	try {
        		throw new MinimumInputNumberException(engineName, '2');
        	} catch(MinimumInputNumberException e){
        		System.out.println(e.getMessage());
        		System.exit(0);
        	}
        }
        for(int i=0; i<input.length; i++){
        	try {
        		a[i] = Integer.parseInt(input[i]);
        	} catch(NumberFormatException e) {
        		try {
        			throw new MyNumberFormatException(engineName, input[i]);
        		} catch(MyNumberFormatException ex) {
        			System.out.println(ex.getMessage());
        			System.exit(0);
        		}
        	}
            if(a[i] < 0) {
            	try {
            		throw new NegativeNumberException(engineName);
            	} catch(NegativeNumberException e){
            		System.out.println(e.getMessage());
            		System.exit(0);
            	}
            }
        }
    }

    /**
     * Calculates the greatest common divisor.
     */
    public void compute(){
        result = a[0];
        for(int i=1; i<a.length; i++){
            int currentNum = a[i];
            if(result<currentNum){
                int temp = result;
                result = currentNum;
                currentNum = temp;
            }
            while(currentNum!=0){
                int rest = result%currentNum;
                result = currentNum;
                currentNum = rest;
            }
        }
    }

    /**
     * Returns the calculated result value.
     * 
     * @return the result of the calculation
     */
    public double getResult(){
        return (double) result;
    }

}
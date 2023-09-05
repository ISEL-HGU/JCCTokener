package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
 * This class calculate the Greatest Common Divisor of the value inputs received from Calculator.java
 */
public class GCDEngine implements Computable {
    private static final String engineName ="GCD";
    /**
     * This method returns the Engine Name.
     * @return The Engine Name of the class.
     */
    public static String getEnginename() {
        return engineName;
    }

    private int[] a;
    private int result;

    /**
     * This method returns the integer array for GCD calculation.
     * @return The integer array to be calculated in GCD. 
     */
    public int[] getA() {
        return a;
    }
    /**
     * This method sets the integer array that would be used for GCD calculation.
     * @param a The number to be set as a.
     */
    public void setA(int[] a) {
        this.a = a;
    }
    /**
     * This method sets the value of result.
     * @param result The value to be set as result.
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * This method sets the value of integer array a. 
     * There are some exceptions: 1. If there are less than 2 received arguments, the program is prints an error and gets terminated. 2. If the value each of the arg[index] is negative, the program is prints an error and gets terminated. 
     * @param arg String array from the terminal. This value would be the input value also known as the a.
     */
    public void setInput(String[] arg) {
        try {
	    	if(arg.length < 3){
	        	throw new MinimumInputNumberException(engineName);
	        }
	
	        for(int i = 1; i < arg.length; i++){
	        	if(!MyNumberFormatException.isNumber(arg[i])) {
	        		throw new MyNumberFormatException(arg[i], engineName);
	        	}
	        	
	            if(Integer.parseInt(arg[i]) < 0){
	            	throw new NegativeNumberException(engineName);
	            }
	        }

	        a = new int[arg.length - 1];
	        for(int i = 1; i < arg.length; i++) {
	    		a[i - 1] = Integer.parseInt(arg[i]); 
	    	}
        
        }
        catch(MinimumInputNumberException e) {
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
     * This method calculates the Greatest Common Divisor. 
     */
    public void compute() {

        int min = a[0];
        for(int i = 1; i < a.length; i++){
            if(min > a[i]) min = a[i];
        }

        for(int i = 1; i <= min; i++){
            int yn = 1;
            for(int num: a){
                if((num % i == 0));
                else{
                    yn = 0;
                }
            }
            if(yn == 1){
                result = i;
            }
        }


    }
    
    /**
     * This method returns the result done from GCD calculation.
     * @return The result done from calculating GCD. 
     */
    public double getResult() {
    	return result;
    }
    
}

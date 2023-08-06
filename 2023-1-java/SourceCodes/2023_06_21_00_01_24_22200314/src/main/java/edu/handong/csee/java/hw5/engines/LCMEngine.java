package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
 * This class calculate the Least Common Multiple of the value inputs received from Calculator.java
 */
public class LCMEngine implements Computable{
    private static final String engineName ="LCM";
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
     * This method returns the integer array for LCM calculation.
     * @return The integer array to be calculated in LCM. 
     */
    public int[] getA() {
        return a;
    }
    /**
     * This method sets the integer array that would be used for LCM calculation.
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
     * This method calculates the Least Common Multiple. 
     */
    public void compute() {

        int temp = a[0];
        result = 1;

        for(int i = 1; i < a.length; i++){
            int gcd = 1;
            int min;
            if(temp < a[i]) min = temp;
            else min = a[i];


            for(int j = 1; j <= min; j++){
                if( (temp % j == 0) && (a[i] % j == 0) ) gcd = j;
            }
            temp = (temp * a[i]) / gcd;       

        }

        result = temp;
         
        
    }

    /**
     * This method returns the result done from LCM calculation.
     * @return The result done from calculating LCM. 
     */
    public double getResult() {
    	return result;
    }


    
}

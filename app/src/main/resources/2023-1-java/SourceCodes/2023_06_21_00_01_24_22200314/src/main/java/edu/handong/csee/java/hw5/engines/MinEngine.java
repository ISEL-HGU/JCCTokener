package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * This class calculate the Minimum of the value inputs received from Calculator.java
 */
public class MinEngine implements Computable {
    private static final String engineName ="MIN";
    /**
     * This method returns the Engine Name.
     * @return The Engine Name of the class.
     */
    public static String getEnginename() {
        return engineName;
    }

    private double[] inputs;
    private double result;

    /**
     * This method returns the double array for Minimum calculation.
     * @return The double array to be used in calculation of Minimum value. 
     */
    public double[] getInputs() {
        return inputs;
    }
    /**
     * This method sets the double array that would be used for Minimum calculation.
     * @param inputs The double array to be set as the private double array.
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }
    /**
     * This method sets the result value.
     * @param result The value to be set a a result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method sets the value of double array inputs. 
     * There are some exceptions: 1. If there are less than 2 received arguments, the program is prints an error and gets terminated. 2. If the value each of the arg[index] is negative, the program is prints an error and gets terminated. 
     * @param arg String array from the terminal. This value would be the input value also known as the inputs.
     */
    public void setInput(String[] arg) {
    	try {
    		if(arg.length < 3){
	    		throw new MinimumInputNumberException(engineName);
	        }
	
	        inputs = new double[arg.length - 1];
	        for(int i = 1; i < arg.length; i++) {
	        	if(!MyNumberFormatException.isNumber(arg[i])) {
	        		throw new MyNumberFormatException(arg[i], engineName);
	        	}
	    		inputs[i-1] = Double.parseDouble(arg[i]); 
	    	}
	        
    	}
    	catch(MinimumInputNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e){
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    }
    
    /**
     * This method calculates the Minimum Value. 
     */
    public void compute() {
    	result = inputs[0];
    	for(int i = 1 ; i < inputs.length; i++) {
    		if(result > inputs[i]) result = inputs[i];
    	}
    }
    
    /**
     * This method returns the result done from Minimum value calculation.
     * @return The result done from calculating Minimum value. 
     */
    public double getResult() {
    	return result;
    }
}

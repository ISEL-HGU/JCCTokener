package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;


/**

This class implements the Computable interface to calculate the maximum value
from a list of numbers.
It provides methods to set the inputs, compute the result and get the input values
and the result.
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
*/
public class MaxEngine implements Computable {
    private String engineName ="MAX";
    private double[] inputs;
    private double result;


    /**
 * Sets the input values from a string array.
 * 
 * @param args the string array containing the input values
 */
    public void setInput(String[] args){
    	try {   
    		if (args.length < 2) {
	            // handle error case where not enough input values were provided
    			throw new MinimumInputNumberException(engineName);
	        }
	     }catch(Exception e){
	    	 System.out.println(e.getMessage()); // print an error message if the input is invalid
	    	 System.exit(0);
;
	     }
        // set inputs array to be the remaining elements of the args array after the first one
        inputs = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            
            inputs[i] = Double.parseDouble(args[i]);
        }
    }

    
/**
 * Computes the maximum value from the input values.
 */
    public void compute(){
        // if (inputs == null || inputs.length == 0) {
        //     // handle case where inputs are not set or empty
        //     return;
        // }
        double max = inputs[0];
        for (int i = 1; i < inputs.length; i++) {
            if (inputs[i] > max) {
                max = inputs[i];
            }
        }
        result = max;
    }

    
/**
 * Gets the input values.
 * 
 * @return the array of input values
 */
    public double[] getInputs(){
        return inputs;
    }

    /**
 * Gets the computed result.
 * 
 * @return the maximum value from the input values
 */
    public double getResult(){
        return result;
    }
    /**
 * Sets the result value.
 * 
 * @param a the value to set the result to
 */
    public void setResult(double a){
        result = a;
    }

    
/**
 * Sets the engine name.
 * 
 * @param a the name to set the engine to
 */
    public void setEngineName(String a){
        engineName = a;
    }

    
/**
 * Gets the engine name.
 * 
 * @return the engine name
 */
    public String getEngineName(){
        return engineName;
    }
}

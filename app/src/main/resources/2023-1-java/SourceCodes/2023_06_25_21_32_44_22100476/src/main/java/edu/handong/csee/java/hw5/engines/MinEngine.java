package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

// import java.util.Arrays;



/**

This class represents the Minimum Engine for the project.
This class implements the Computable interface and provides implementation for setInput, compute and getResults methods.
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
*/
public class MinEngine implements Computable {
    private String engineName ="MIN";
    private double[] inputs;
    private double result;

    /**
 * This method sets the inputs for the engine
 * 
 * @param args an array of string inputs to be set
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
 * This method computes the minimum of the input values and sets the result
 * 
 */
    public void compute(){
        // if (inputs == null || inputs.length == 0) {
        //     // handle case where inputs are not set or empty
        //     return;
        // }
        double min = inputs[0];
        for (int i = 1; i < inputs.length; i++) {
            if (inputs[i] < min) {
                min = inputs[i];
            }
        }
        result = min;
    }

    
/**
 * This method gets the inputs set for the engine
 * 
 * @return an array of doubles representing the input values
 */
    public double[] getInputs(){
        return inputs;
    }

    
/**
 * This method gets the result computed by the engine
 * 
 * @return a double representing the result computed
 */
    public double getResult(){
        return result;
    }

    /**
 * This method sets the name of the engine
 * 
 * @param a a string representing the engine name to be set
 */
    public void setEngineName(String a){
        engineName = a;
    }

    /**
 * This method sets the result computed by the engine
 * 
 * @param a a double representing the result to be set
 */
    public void setResult(double a){
        result = a;
    }

    /**
 * This method gets the name of the engine
 * 
 * @return a string representing the engine name
 */
    public String getEngineName(){
        return engineName;
    }
}

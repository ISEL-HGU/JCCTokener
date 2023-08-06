package edu.handong.csee.java.hw5.thread.clioptions.engines;

import edu.handong.csee.java.hw5.thread.clioptions.exceptions.*;
/**
 * This MaxEngine class implements the Interface Computable.
 * It finds the max number out a given number of doubles.
 */
public class MaxEngine implements Computable {

    /**
    * A static final String that represents the engine name, which is "MAX".
    */
    private static final String engineName ="MAX";
    /**
     * Returns the name of the engine.
     * 
     * @return A String representing the engine name.
     */
    public static String getEngineName() {
        return engineName;
    }
    /**
     * Array of doubles of a given input
     */
    private double[] inputs;
    /**
     * 
     */
    private double result;

    /**
     * Returns the input array.
     * 
     * @return An array of doubles representing the inputs
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * Sets the result of the computation
     * 
     * @param result A double representing the result of the computation.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Parses the input array of Strings into an array of doubles and sets it to the inputs field.
     * If the number of inputs is less than 2, an error message is printed and the program exits.
     * 
     * @param input An array of Strings representing the inputs.
     * @throws MinimumInputNumberException if the number of inputs is less than 2.
     * @throws MyNumberFormatException if any input value is not a valid number.
     */
    public void setInput(String[] input) {
    	try {
            int len = input.length - 1;
            if(len < 2) {
            	throw new MinimumInputNumberException(getEngineName(), 2);
            }
            double[] temp = new double[len];
            for(int i = 0; i < len; i++) {
            	try {
                    temp[i] = Double.parseDouble(input[i + 1]);
            	}
            	catch(NumberFormatException e) {
            		throw new MyNumberFormatException(getEngineName(),input[i + 1]);
            	}

            }
            this.inputs = temp;
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
     * Computes the maximum value from the inputs and sets it to the result field.
     */
    public void compute(){
        double max = 0;
        int len = getInputs().length;
        for(int i = 0; i < len; i++) {
            if(getInputs()[i] > max) {
                max = getInputs()[i];
            }
        }
        setResult(max);
    }

    /**
     * Returns the result of the computation.
     * 
     * @return A double representing the result of the computation.
     */
    public double getResult(){
        return result;
    }

}

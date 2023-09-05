
package edu.handong.csee.java.hw5.clioptions.engines;

import edu.handong.csee.java.hw5.thread.clioptions.exceptions.*;
/**
 * A MinEngine class that implements the Interface Computable.
 * It computes the minimum value of an array of doubles.
 */
public class MinEngine implements Computable {
    private static final String engineName ="MIN";

    /**
     * Returns the name of the engine.
     * @return the name of the engine
     */
    public static String getEngineName() {
        return engineName;
    }

    private double[] inputs;
    private double result;

    /**
     * Returns the input array.
     * @return the input array
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * Sets the result of the computation.
     * @param result the result of the computation
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Parses the input string array into an array of doubles and sets it as the input.
     * Prints an error message and exits the program if the input array length is less than 2.
     * @param input the input string array
     * @throws MinimumInputNumberException if the input array length is less than 2
     * @throws MyNumberFormatException if any of the input values cannot be parsed as a double
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
     * Computes the minimum value of the input array and sets the result.
     */
    public void compute(){
        double min = getInputs()[0];
        int len = this.getInputs().length;
        for(int i = 0; i < len; i++) {
            if(getInputs()[i] < min) {
                min = this.getInputs()[i];
            }
        }
        setResult(min);
    }

    /**
     * Returns the result of the computation.
     * @return the result of the computation
     */
    public double getResult(){
        return result;
    }   
}

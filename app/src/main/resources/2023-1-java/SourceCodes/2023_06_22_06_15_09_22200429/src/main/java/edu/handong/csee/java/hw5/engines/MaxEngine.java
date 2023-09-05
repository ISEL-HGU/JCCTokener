package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;

/**
 * This class is named MaxEngine and inherits from Computable class.
 */
public class MaxEngine implements Computable {
   
    
    private static final String engineName = "MAX";

  
    private double[] inputs;

    private double result;
    
    /**
     * This method returns the engineName.
     */
    public static String getEngineName() {
        return engineName;
    }

    /**
     * Method to get inputs[].
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * Method to set inputs[].
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This is a method to set the result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method checks the input of MAX. 
     * The reason why inputs.length is less than 3 is because at compile time, MAX needs at least 2 inputs. 
     * If there is less than 2 inputs, it is less than 3.
     */
    public void setInput(String [] inputs) {
        try {
            if (inputs.length < 2) {
                throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for MAX.");
            }
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
        this.inputs = new double[inputs.length];

        for (int i = 0; i < inputs.length ; i++) {
            this.inputs[i] = Double.parseDouble(inputs[i]);
        }
    }
    
    /**
     * This is the method to calculate the maximum value.
     */
    public void compute() {
        result = Double.NEGATIVE_INFINITY;

        for (double d : inputs) {
            if (d > result) {
                result = d;
            }
        }
    } 

    /**
     * This is a method to get the result of the maximum value.
     */
    public double getResult() {
        return result;
    }


}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;

/**
  * This class is named MinEngine, and it inherits from Computable.
  */
public class MinEngine implements Computable {

   
    private static final String engineName = "MIN";
 
    
    private double[] inputs;

    
    private double result;
    
    /**
     * This is a method to get the engineName.
     */
    public static String getEngineName() {
        return engineName;
    }

    /**
     * A method that returns inputs[].
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
     * This method checks for MIN inputs. 
     * The reason why inputs.length is less than 3 is because at compile time, 
     * at least 2 inputs are required along with MIN. 
     * It is less than 3 if there is 1 or less inputs.
     */
    public void setInput(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for MIN.\r\n");
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
     * This is the method to calculate min.
     */
    public void compute() {
        result = Double.POSITIVE_INFINITY;

        for (double d : inputs) {
            if (d < result) {
                result = d;
            }
        }
    } 

    /**
     * A method to get the result.
     */
    public double getResult() {
        return result;
    }

}

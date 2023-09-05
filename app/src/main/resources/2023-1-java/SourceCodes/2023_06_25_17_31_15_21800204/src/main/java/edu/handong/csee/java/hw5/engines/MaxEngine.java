package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * MaxEngine class implements the Computable interface and calculates the maximum value of the input numbers.
 */
public class MaxEngine implements Computable {
    private static final String engineName ="MAX";
    private double[] inputs;
    private double result;

    /**
     * Computes the maximum value of the input numbers.
     */
    public void compute() throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        this.result = inputs[0];
        for(int i = 1; i < inputs.length; i++) {
            if(this.result < inputs[i]){
                this.result = inputs[i];
            }
        }
    }
        
    /**
     * Sets the input values for the MaxEngine.
     * @param args A string array of input values.
     */
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        this.inputs = new double[args.length - 1];
        if(args.length < 3){
            throw new MinimumInputNumberException(2, engineName);
        }
        for (int i = 1; i < args.length; i++) {
                this.inputs[i-1] = Double.parseDouble(args[i]);
            }
    }

    /**
     * Returns the result of the computation.
     * @return The maximum value of the input numbers.
     */
    public double getResult(){
        return this.result;
    }

    /**
     * Returns the array of input values.
     * @return The array of input values.
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * Sets the array of input values.
     * @param inputs The array of input values to be set.
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }
}

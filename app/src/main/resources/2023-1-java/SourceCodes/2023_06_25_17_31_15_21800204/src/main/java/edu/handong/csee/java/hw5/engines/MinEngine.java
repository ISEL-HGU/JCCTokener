package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that implements the Computable interface to calculate the minimum value of a set of input numbers.
 * The input numbers are provided as command line arguments.
 */
public class MinEngine implements Computable {
    private static final String engineName ="MIN";
    private double[] inputs;
    private double result;
    
    /**
     * Calculates the minimum value of the input numbers.
     * The result is stored in the {@code result} field.
     */
    public void compute() throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        this.result = inputs[0];
        for(int i = 1; i < inputs.length; i++) {
            if(this.result > inputs[i]){
                this.result = inputs[i];
            }
        }
    }

    /**
     * Sets the input numbers for this engine.
     * The input numbers are provided as command line arguments.
     * @param args The command line arguments containing the input numbers.
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
     * Gets the minimum value of the input numbers.
     * @return The minimum value of the input numbers.
     */
    public double getResult(){        
        return this.result;
    }

    /**
     * Gets the input numbers.
     * @return An array containing the input numbers.
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * Sets the input numbers.
     * @param inputs An array containing the input numbers.
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }
}

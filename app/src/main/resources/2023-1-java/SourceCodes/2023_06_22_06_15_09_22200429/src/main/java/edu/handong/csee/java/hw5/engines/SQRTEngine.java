package edu.handong.csee.java.hw5.engines;


import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * class named SQRTEngine, which inherits from class computable.
 */
public class SQRTEngine implements Computable {

    private static final String engineName = "SQRT";

    private double input;
    private double result;

    /**
     * This is the method to get the input.
     */
    public double getInput() {
        return input;
    }

    /**
     * This method sets the input.
     */
    public void setInput(double input) {
        this.input = input;
    }

    /**
     * This is the method to set the result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method checks the inputs of the SQRT.
     */
    public void setInput(String[] inputs) {
        try {
            if (inputs.length < 1)
                throw new MinimumInputNumberException("Exception-02: You need at least 1 input value for SQRT.");
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        try {
            if (inputs.length > 1)
                throw new OneInputException("Exception-04: You need only one input value for SQRT.");
        } catch (OneInputException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        input = Double.parseDouble(inputs[0]);
        try {
            if (input < 0)
                throw new NegativeNumberException("Exception-03: The input value cannot be negative for SQRT.");
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * This is a method to calculate the SQRT.
     */
    public void compute() {
        result = Math.sqrt(input);
    }

    /**
     * This is the method to get the result.
     */
    public double getResult() {
        return result;
    }

    /**
     * This is a method to get the Engine name.
     */
    public static String getEngineName() {
        return engineName;
    }
}

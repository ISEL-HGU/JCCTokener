package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 *
 * MaxEngine is a class for finding the maximum value among two or more input
 * values.
 * It implements the Computable interface.
 */
public class MaxEngine implements Computable {
    private static final String engineName = "MAX";
    private double[] inputs; // 입력 값
    private double result; // 계산 결과

    /**
     * This method set the input value using command-line arguments and inputcheck
     * with recognize traits of engine
     */

    public void setInput(String[] inputs) {
        try {
            if (inputs.length < 2) {
                throw new MinimumInputNumberException(engineName);
            }

            this.inputs = new double[inputs.length - 1];
            for (int i = 1; i < inputs.length; i++) {
                this.inputs[i - 1] = Double.parseDouble(inputs[i]);
            }
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException(engineName, inputs[1]);
        }
    }

    /**
     * this methods finds the maximum value among the input values.
     */
    public void compute() {
        // Find the maximum value among the input values.
        double max = inputs[0]; // Set the first value as the standard.
        for (int i = 1; i < inputs.length; i++) {
            if (inputs[i] > max) {
                max = inputs[i];
            }
        }
        result = max;
    }

    /**
     * this method returns the input values used for the maximum value calculation.
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * this method sets the input values for the maximum value calculation.
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This method returns the result of the maximum value calculation.
     */
    public double getResult() {
        return result;
    }

    /**
     * This method sets the result of the maximum value calculation.
     */
    public void setResult(double result) {
        this.result = result;
    }
}
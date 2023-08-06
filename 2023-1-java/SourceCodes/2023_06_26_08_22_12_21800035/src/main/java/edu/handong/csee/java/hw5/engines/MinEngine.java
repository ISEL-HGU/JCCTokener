package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is a concrete implementation of the Computable interface that
 * finds the minimum value among given input values.
 * It implements the Computable interface.
 */
public class MinEngine implements Computable {
    private static final String engineName = "MIN";
    private double[] inputs; // 입력 값
    private double result; // 계산 결과

    /**
     * This method set the input value using command-line arguments and inputcheck
     * with recognize traits of engine
     * `printErrorMessageForTheNumberOfMinimumRequiredInputsAndExit()` method,
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
     * This method finds the minimum value among the input values.
     */
    public void compute() {
        result = inputs[0];
        for (double input : inputs) {
            if (input < result) {
                result = input;
            }
        }
    }

    /**
     * This method returns the input values for the minimum value calculation.
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * This method sets the input values for the minimum value calculation.
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This method returns the result of the minimum value calculation.
     */
    public double getResult() {
        return result;
    }

    /**
     * This method sets the result of minimum value calculation.
     */
    public void setResult(double result) {
        this.result = result;
    }

}
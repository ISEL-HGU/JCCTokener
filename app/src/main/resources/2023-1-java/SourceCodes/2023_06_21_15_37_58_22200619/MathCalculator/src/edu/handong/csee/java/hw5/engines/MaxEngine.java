package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.util.InputChecker;

public class MaxEngine implements Computable {
    private static final String engineName ="MAX";
    private double[] inputs;
    private double result;

    /**
     * Get the name of the engine
     * @return engineName
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * Get the inputs of the engine
     * @return inputs
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * Set the inputs of the engine
     * @param inputs An array of double input values
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Set the result of the engine
     * @param result An integer result to set
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Set the inputs of the engine
     * @param args An array of String input values
     */
    public void setInput(String[] args) {
        if (args.length < 2) {
            InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName, 1);
            System.exit(0);
        }
        for(int i=0; i<args.length; i++){
            try {
                inputs[i] = Double.parseDouble(args[1]);
            }
            catch (NumberFormatException e) {
                System.exit(0);
            }
            if (inputs[i] < 0) {
                InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
                System.exit(0);
            }
        }
    }

    /**
     * Compute the maximum value from the inputs array
     */
    public void compute() {
        double max = inputs[0];
        for (int i = 1; i < inputs.length; i++) {
            if (inputs[i] > max) {
                max = inputs[i];
            }
        }
        result=max;
    }

    /**
     * Get the result of the engine
     * @return result
     */
    public double getResult() {
        return result;
    }
}

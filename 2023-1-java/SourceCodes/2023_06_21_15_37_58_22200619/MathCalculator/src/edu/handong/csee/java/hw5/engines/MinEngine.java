package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.util.InputChecker;

public class MinEngine implements Computable {
    private static final String engineName ="MIN";
    private double[] inputs;
    private double result;

    /**
     * Returns the name of the engine
     * @return name of the engine
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * Gets the inputs
     * @return the inputs
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * Set the inputs
     * @param inputs the inputs
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Set the result
     * @param result the result
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Sets the input for the engine
     * @param args the input arguments
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
     * Computes the minimum value
     */
    public void compute() {
        double min = inputs[0];
        for (int i = 1; i < inputs.length; i++) {
            if (inputs[i] < min) {
                min = inputs[i];
            }
        }
        result = min;
    }

    /**
     * Gets the result
     * @return the result
     */
    public double getResult() {
        return result;
    }
}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.util.InputChecker;

public class SQRTEngine implements Computable {

    private static final String engineName ="SQRT";
    private double input;
    private double result;


    /**
     * Returns the name of the engine
     * @return engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * Returns the input value
     * @return input value
     */
    public double getInput() {
        return input;
    }

    /**
     * Sets the input value
     * @param input input value
     */
    public void setInput(double input) {
        this.input = input;
    }

    /**
     * Sets the result value
     * @param result result value
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Sets the input value from the command line arguments
     * @param args command line arguments
     */
    public void setInput(String[] args) {
        if (args.length != 2) {
            InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName, 1);
            System.exit(0);
        }
        try {
            input = Double.parseDouble(args[1]);
        }
        catch (NumberFormatException e) {
            System.exit(0);
        }
        if (input < 0) {
            InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
            System.exit(0);
        }
    }


    /**
     * Computes the square root of the input value
     */
    public void compute() {
        result = Math.sqrt(input);
    }

    /**
     * Returns the computed result
     * @return computed result
     */
    public double getResult() {
        return result;
    }

}

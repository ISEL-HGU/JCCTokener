package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * MaxEngine.java
 * Computing a maximum number for the given multiple numbers.
 */
public class MaxEngine implements Computable {
    private static final String engineName = "MAX";
    private double[] inputs;
    private double result = 0;

    /**
     * engineName is MAX
     * 
     * @param engineName MAX
     */
    public void setEngineName(String engineName) {
    }

    /**
     * This method return engineName.
     * 
     * @return engineName.
     */
    public String getEngineName() {
        return engineName;
    }

    /**
     * Set inputs value.
     * 
     * @param inputs double[]inputs
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This method return inputs.
     * 
     * @return inputs.
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * Set result value.
     * 
     * @param result double result
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method return result.
     * 
     * @return result.
     */
    public double getResult() {
        return result;
    }

    /**
     * This method is for check input - Double or String
     * 
     * @param String ars
     * @return true, false
     */
    public static boolean check(String args) {
        try {
            Double.parseDouble(args);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Set the inputs value through the inputs args.
     * # of inputs: >= two numbers
     *
     * @param args Type : String[]
     */
    public void setInput(String[] args) {
        try {
            if (!check(args[0]))
                throw new MyNumberFormatException(args[1], engineName);
            if (args.length < 2) {
                throw new MinimumInputNumberException(engineName);
            }
            inputs = new double[args.length + 1];
            for (int i = 1; i < args.length; i++) {
                inputs[i - 1] = Integer.parseInt(args[i]);
            }

        } catch (MinimumInputNumberException e2) {
            System.out.println(e2.getMessage());
            System.exit(0);
        } catch (MyNumberFormatException e5) {
            System.out.println(e5.getMessage());
            System.exit(0);
        }
    }

    /**
     * Compute result through inputs
     * result is a maximum number for the given multiple numbers.
     */
    public void compute() {
        result = inputs[0];
        for (int i = 1; i < inputs.length; i++) {
            if (inputs[i] > result)
                result = inputs[i];
        }

    }
}

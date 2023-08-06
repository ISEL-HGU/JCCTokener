/**

This class represents an implementation of the Computable interface,
which calculates the maximum value from a given set of numbers.
*/
package edu.handong.csee.java.hw5.engines;

/**
 * import exceptions 
 */
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * max
 */
public class MaxEngine implements Computable {
/**
 * The name of the engine.
 */
    private static final String engineName = "MAX";
    /**
 * getter enginename
 */
public static String getEnginename() {
    return engineName;
}

/**
 * The input values.
 */
    private double inputs[];

/**
 * Returns the input values.
 */
    public double[] getInputs() {
        return inputs;
    }


/**
 * Sets the input values.
 */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }
/**
 * The result of the computation.
 */
    private double result;
    /**
     * getter result
     */
    public double getResult1() {
        return result;
    }

    /**
     * set result
     */
    public void setResult1(double result) {
        this.result = result;
    }

/**
Sets the input value for the computation.
*/
    public void setInput(String[] args) throws MyNumberFormatException, MinimumInputNumberException{
        //System.out.println(Arrays.toString(args));
        if (args.length < 2) {
            throw new MinimumInputNumberException(engineName);
        }
        inputs = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            try {
                inputs[i] = Double.parseDouble(args[i]);
            } catch (NumberFormatException e) {
                throw new MyNumberFormatException(args[i], engineName);
            }
        }
    }
    
/**
 * Computes the maximum value from the input values.
 */
    public void compute() {
        result = inputs[0];
        for (int i = 0; i < inputs.length; i++) {
            if (result < inputs[i]) {
                result = inputs[i];
            }
        }
    }
/**
 * Returns the result of the computation.
 */
    public double getResult() {
        return result;
    }
   
}

/**

This class represents an implementation of the Computable interface,
which calculates the minimum value from a given set of numbers.
*/
package edu.handong.csee.java.hw5.engines;
/**
 * import arrays 
 */
import java.util.Arrays;
/**
 * import exceptions 
 */
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * min
 * 
 */
public class MinEngine implements Computable {
/**
 * The name of the engine.
 */
    private static final String engineName = "MIN";
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
 * The result of the computation.
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
     * Setss the result values.
     */
    public void setResult1(double result) {
        this.result = result;
    }
/**
Sets the input value for the computation.
*/
    public void setInput(String[] args) throws MyNumberFormatException, MinimumInputNumberException {
        if(args.length < 2) {
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
 * Computes the minimum value from the input values.
 */
    public void compute() {
        Arrays.sort(inputs);
        /**
         * result
         */
        result = inputs[0];
    }
/**
 * Returns the result of the computation.
 */
    public double getResult() {
        return result;
    }
   
}

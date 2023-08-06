/**

A class representing an engine to compute the LCM of multiple integers.
Implements the Computable interface.
*/
package edu.handong.csee.java.hw5.engines;
/**
 * import exceptions 
 */
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * class lcm
 */
public class LCMEngine implements Computable{
/**
 * The name of the engine
 */
    private static final String engineName = "LCM";
    /**
 * getter enginename
 */
public static String getEnginename() {
    return engineName;
}
/**
 * An array of integers to compute the LCM
 */
    private int inputs[];

    /**
     * get inputs
     */
    public int[] getInputs() {
        return inputs;
    }

    /**
     * set inputs
     */
    public void setInputs(int[] inputs) {
        this.inputs = inputs;
    }
/**
 * The result of the computation
 */
    private int result;
    /**
     * getter result
     */
    public int getResult1() {
        return result;
    }

    /**
     * Sets the result value.
     */
    public void setResult1(int result) {
        this.result = result;
    }

/**
*Sets the input value for the computation.args[0].equalsIgnoreCase(engineName)
*/
    public void setInput(String[] args) throws MyNumberFormatException, NegativeNumberException, MinimumInputNumberException{
        if (args.length >= 2) {
            inputs = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                try {
                    inputs[i] = Integer.parseInt(args[i]);
                } catch (NumberFormatException e) {
                    throw new MyNumberFormatException(args[i], engineName);
                }
                if (inputs[i] < 0) {
                    throw new NegativeNumberException(engineName);
                }
            }
        } 
        
        else {
            throw new MinimumInputNumberException(engineName);
        }
    }
/**
 * Computes the LCM of the input integers
 */
    public void compute(){
        int max = inputs[0];
        int min = inputs[1];
        if (min > max) {
            int temp = max;
            max = min;
            min = temp;
        }
        for (int i = 2; i < inputs.length; i++) {
            if (inputs[i] < min) {
                int temp = min;
                min = inputs[i];
                inputs[i] = temp;
            }
            if (inputs[i] > max) {
                max = inputs[i];
            }
        }
        result = max;
        while (true) {
            boolean isLCM = true;
            for (int i = 0; i < inputs.length; i++) {
                if (result % inputs[i] != 0) {
                    isLCM = false;
                    break;
                }
            }
            if (isLCM) {
                break;
            }
            result += max;
        }
    }
/**
 * get result
 */
    public double getResult(){
        return result;
    }
   
}

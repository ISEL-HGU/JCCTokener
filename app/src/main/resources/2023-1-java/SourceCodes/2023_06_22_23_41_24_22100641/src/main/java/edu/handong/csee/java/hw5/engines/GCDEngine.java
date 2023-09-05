/**

This class represents a GCD engine which implements the Computable interface.
It takes two input arguments from the user and computes the greatest common divisor between them.
The results can be obtained using the getResult() method.
*/

package edu.handong.csee.java.hw5.engines;
/**
 * import exceptions 
 */
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * gcd
 */
public class GCDEngine implements Computable {
/**
 * A static constant string to represent the name of the GCD engine.
 */
    private static final String engineName = "GCD";
    /**
 * getter enginename
 */
public static String getEnginename() {
    return engineName;
}
/**
 * An integer array to store the inputs taken from the user.
 */
    private int[] inputs;
/**
 * An integer variable to store the result of the computation.
 */
    private int result;
   
    /**
     * getter result
     */
    public int getResult1() {
        return result;
    }
    /**
     * set result
     */
    public void setResult1(int result) {
        this.result = result;
    }


    /**
     * get innputs
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
Sets the input value for the computation.
*/
    public void setInput(String[] args) throws MyNumberFormatException, NegativeNumberException, MinimumInputNumberException {
        if (args.length >= 2) {
            try {
                inputs = new int[2];
                inputs[0] = Integer.parseInt(args[0]);
                inputs[1] = Integer.parseInt(args[1]);
                if (inputs[0] < 0 || inputs[1] < 0) {
                    throw new NegativeNumberException(engineName);
                }
            } catch (NumberFormatException e) {
                throw new MyNumberFormatException(args[0], engineName);
            }
        } else {
            throw new MinimumInputNumberException(engineName);
        }
    }

    
/**
 * This method computes the greatest common divisor of the input arguments using the Euclidean algorithm.
 */    
    public void compute() {
        int a = inputs[0];
        int b = inputs[1];
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        result = a;
    }
/**
 * This method returns the result of the computation.
 * 
 */ 
    public double getResult() {
        return result;
    }
   
}

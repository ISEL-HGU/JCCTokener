/**

A class that represents a square root engine for computing the square root of a given input value.
*/
package edu.handong.csee.java.hw5.engines;
/**
 * import exceptions 
 */
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * sqrt
 */
public class SQRTEngine implements Computable {
    /** 
     * The name of the engine. 
    */
    private static final String engineName = "SQRT";
    /**
 * getter enginename
 */
    public static String getEnginename() {
        return engineName;
    }

    /** The input value for computing the square root. */
    private double input;
/**
 * Returns the input value of the square root engine.
 */
    public double getInput() {
        return input;
    }

/**
 * Sets the input value of the square root engine.
 */
    public void setInput(double input) {
        this.input = input;
    }

    /** The computed result of the square root engine. */
    private double result;
    /**
     * getter result
     */
    public double getResult1() {
        return result;
    }
    /**
     * Sets the result value.
     */
    public void setResult1(double result) {
        this.result = result;
    }

    
/**
Sets the input value for the computation.
*/
    public void setInput(String[] args) throws MyNumberFormatException, NegativeNumberException, OneInputException{
        if (args.length != 1) {
            throw new OneInputException(engineName);
        }
        
    
        try {
            input = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException(args[0], engineName);
        }
    
        if (input < 0) {
            throw new NegativeNumberException(engineName);
        }
    }
    
/**
 * Computes the square root of the input value for the square root engine.
 */
    public void compute() {
        result = Math.sqrt(input);
    }
/**
 * Returns the computed result of the square root engine.
 */
    public double getResult() {
        return result;
    }
}    


/**

A class representing the engine for computing Fibonacci number up to a given index.
This class implements the Computable interface.
*/
package edu.handong.csee.java.hw5.engines;
/**
 * import exceptions 
 */
import edu.handong.csee.java.hw5.exceptions.*;


/**
 * This class represents a FibonacciEngine that implements Computable.
 * It computes the nth Fibonacci number given the input n.
 */
public class FibonacciEngine implements Computable {

     /**
     * The name of the engine.
     */
    private static final String engineName ="FIBONACCI";

/**
 * getter enginename
 */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * The input n.
     */
    private int n;
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
     * Sets the result of the computation.
     * 
     */
    public void setResult1(double result) {
        this.result = result;
    }
    /**
     * Gets the input n.
     * 
     */
    public int getN() {
        return n;
    }
    /**
     * Sets the input n.
     * 
     */
    public void setN(int n) {
        this.n = n;
    }
/**
Sets the input value for the computation.
*/
public void setInput(String[] args) throws MyNumberFormatException, NegativeNumberException, OneInputException {
    if (args.length != 1) {
        throw new OneInputException(engineName);
    }
    try {
        n = Integer.parseInt(args[0]);
        if (n < 0) {
            throw new NegativeNumberException(engineName);
        }
    } catch (NumberFormatException e) {
        throw new MyNumberFormatException(args[0], engineName);
    }
}


/**
 * Computes the Fibonacci number up to the given index using an iterative approach.
 */
    public void compute() {
        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            double prev1 = 0;
            double prev2 = 1;
            for (int i = 2; i <= n; i++) {
                result = prev1 + prev2;
                prev1 = prev2;
                prev2 = result;
            }
        }
    }

   /**
 * Returns the computed Fibonacci number.
 * 
 */ 
    public double getResult() {
        return result;
    }
   
}    
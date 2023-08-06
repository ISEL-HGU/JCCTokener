/**
 * engines
 */
package edu.handong.csee.java.hw5.engines;
/**
 * import exceptions 
 */
import edu.handong.csee.java.hw5.exceptions.*;
/**
A class that computes the factorial of a given integer.
*/
public class FactorialEngine implements Computable {
    /**
     *engineName
     */
    private static final String engineName = "FACTORIAL";
    /**
     * getter enginename
     */
    public static String getEnginename() {
        return engineName;
    }
    /**
     * n
     */
    private int n;
    /**
Returns the input value of the computation.
*/
    public int getN() {
        return n;
    }
/**
Sets the input value of the computation.
*/
    public void setN(int n) {
        this.n = n;
    }
    /**
     *result
     */
    private double result;

    /**
     * getter result
     */
    public double getResult1() {
        return result;
    }
/**
Sets the computed result of the factorial.
*/
    public void setResult1(double result) {
        this.result = result;
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
Computes the factorial of the input integer.
*/
    public void compute() {
        result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
    }
/**
Returns the computed result of the factorial.
*/
    public double getResult() {
        return result;
    }
   
}

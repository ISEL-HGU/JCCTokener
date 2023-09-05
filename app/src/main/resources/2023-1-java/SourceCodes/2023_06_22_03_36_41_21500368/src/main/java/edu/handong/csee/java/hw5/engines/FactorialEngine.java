package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * The FactorialEngine class calculates the factorial of the given input value.
 */
public class FactorialEngine implements Computable {
    /**
     * The name of the FactorialEngine.
     */
    private static final String engineName = "FACTORIAL";

    /**
     * The input value n.
     */
    private int n;

    /**
     * The calculated factorial result.
     */
    private double result;
    
    /**
     * Returns the engine name.
     * 
     * @return The engine name as a string.
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * Returns the input value n.
     * 
     * @return The input value n as an int.
     */
    public int getN() {
        return n;
    }

    /**
     * Sets the input value n.
     * 
     * @param n The input value n as an int.
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Sets the result of the factorial calculation.
     * 
     * @param result The calculated factorial as a double.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Sets the input value and computes the factorial.
     * 
     * @param args The input arguments as a string array.
     */
    public void setInput(String[] args) {
        try {
            if (args.length > 1) {
                throw new OneInputException(engineName);
            } else {
                try {
                    n = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    throw new MyNumberFormatException(engineName, args[0]);
                }
                if (n < 0) {
                    throw new NegativeNumberException(engineName);
                }
                compute();
            }
        } catch (OneInputException | NegativeNumberException | MyNumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Computes the factorial of the input value n.
     */
    public void compute() {
        result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
    }

    /**
     * Returns the result of the factorial calculation.
     * 
     * @return The calculated factorial as a double.
     */
    public double getResult() {
        return result;
    }
}

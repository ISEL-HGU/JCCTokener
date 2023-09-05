package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * The FibonacciEngine class calculates the nth Fibonacci number of the given input value.
 */
public class FibonacciEngine implements Computable {
    /**
     * The name of the FibonacciEngine.
     */
    private static final String engineName = "FIBONACCI";

    /**
     * The input value n.
     */
    private int n;

    /**
     * The calculated nth Fibonacci number.
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
     * Sets the result of the Fibonacci calculation.
     * 
     * @param result The calculated nth Fibonacci number as a double.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Sets the input value and computes the nth Fibonacci number.
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
     * Computes the nth Fibonacci number.
     */
    public void compute() {
        if (n <= 1) {
            result = n;
        } else {
            double fib1 = 0, fib2 = 1;
            for (int i = 2; i <= n; i++) {
                double nextFib = fib1 + fib2;
                fib1 = fib2;
                fib2 = nextFib;
            }
            result = fib2;
        }
    }

    /**
     * Returns the result of the Fibonacci calculation.
     * 
     * @return The calculated nth Fibonacci number as a double.
     */
    public double getResult() {
        return result;
    }
}

package edu.handong.csee.java.hw4;

import edu.handong.csee.java.hw4.exceptions.*;

/**
 * FibonacciEngine class represents an engine for calculating the Fibonacci sequence.
 */
public class FibonacciEngine implements Computable {
    private static final String engineName = "FIBONACCI";
    int inputs[] = new int[1];
    double result;
    int count = 0;

    /**
     * Sets the input value for calculating the Fibonacci sequence.
     * 
     * @param args The input arguments as an array of strings. The first argument is the number for Fibonacci calculation.
     */
    @Override
    public void setInput(String[] args) {
    	try {
        inputs[0] = Integer.parseInt(args[1]);
        count++;
    	}catch (NumberFormatException e) {
    	    throw new MyNumberFormatException(args);
    	}
    }

    /**
     * Computes the Fibonacci sequence for the given number.
     * 
     * @throws OneInputException       If there is not exactly one input value provided.
     * @throws NegativeNumberException If the input value is negative.
     */
    @Override
    public void compute() {
        try {
            if (inputs.length != 1) {
                throw new OneInputException("Fibonacci.");
            } else if (inputs[0] < 0) {
                throw new NegativeNumberException("Fibonacci.");
            } else {
                result = fibonacci(inputs[0]);
            }
        } catch (OneInputException e) {
            System.out.println(e.getMessage());
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the result of the Fibonacci computation.
     * 
     * @return The Fibonacci sequence value as a double.
     */
    @Override
    public double getResult() {
        return result;
    }

    /**
     * Calculates the Fibonacci sequence for a given number.
     * 
     * @param n The number for Fibonacci calculation.
     * @return The Fibonacci sequence value as an int.
     */
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}


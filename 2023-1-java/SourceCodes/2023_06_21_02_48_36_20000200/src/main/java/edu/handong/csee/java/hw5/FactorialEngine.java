package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * FactorialEngine class represents an engine for calculating the factorial of a number.
 */
public class FactorialEngine implements Computable {
    private static final String engineName = "FACTORIAL";
    int[] inputs = new int[1];
    double result;
    int count = 0;

    /**
     * Sets the input value for calculating the factorial.
     * 
     * @param args The input arguments as an array of strings. The first argument is the number for factorial calculation.
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
     * Computes the factorial of the number.
     * 
     * @throws OneInputException       If there is not exactly one input value provided.
     * @throws NegativeNumberException If the input value is negative.
     */
    @Override
    public void compute() {
        try {
            if (inputs.length != 1) {
                throw new OneInputException("Factorial.");
            }
            if (inputs[0] < 0) {
                throw new NegativeNumberException("Factorial.");
            } else {
                result = factorial(inputs[0]);
            }
        } catch (OneInputException e) {
            System.out.println(e.getMessage());
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the result of the factorial computation.
     * 
     * @return The factorial of the number as a double value.
     */
    @Override
    public double getResult() {
        return result;
    }

    /**
     * Calculates the factorial of a non-negative integer.
     * 
     * @param n The number for factorial calculation.
     * @return The factorial of the number as an value.
     * @throws IllegalArgumentException If the input number is negative.
     */
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid input. n must be a non-negative integer.");
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

package edu.handong.csee.java.hw4;

import edu.handong.csee.java.hw4.exceptions.*;

/**
 * GCDEngine class represents an engine for calculating the greatest common divisor (GCD).
 */
public class GCDEngine implements Computable {
    private static final String engineName = "GCD";
    int inputs[];
    int result;
    int count = 0;
    

    /**
     * Sets the input values for calculating the greatest common divisor (GCD).
     * 
     * @param args The input arguments as an array of strings. The first argument is the number of inputs, followed by the input values.
     */
    @Override
    public void setInput(String[] args) {
    	try {
        inputs = new int[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            inputs[i] = Integer.parseInt(args[i + 1]);
            count++;
        }
    	}catch (NumberFormatException e) {
    	    throw new MyNumberFormatException(args);
    	}
    }

    /**
     * Computes the greatest common divisor (GCD) for the given input values.
     * 
     * @throws MinimumInputNumberException If there are fewer than 2 input values provided.
     * @throws NegativeNumberException    If any of the input values are negative.
     */
    @Override
    public void compute() {
        try {
            if (inputs.length < 2) {
                throw new MinimumInputNumberException("GCD.");
            }
            if (inputs[0] < 0) {
                throw new NegativeNumberException("GCD.");
            } else {
                result = gcd(inputs);
            }
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calculates the greatest common divisor (GCD) for the given input values.
     * 
     * @param numbers The input values as an array of integers.
     * @return The greatest common divisor as an integer.
     */
    public static int gcd(int[] numbers) {
        int result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = gcd(result, numbers[i]);
        }
        return result;
    }

    /**
     * Calculates the greatest common divisor (GCD) of two numbers using the Euclidean algorithm.
     * 
     * @param a The first number.
     * @param b The second number.
     * @return The greatest common divisor as an integer.
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * Returns the result of the greatest common divisor (GCD) computation.
     * 
     * @return The greatest common divisor as a double.
     */
    @Override
    public double getResult() {
        return result;
    }
}


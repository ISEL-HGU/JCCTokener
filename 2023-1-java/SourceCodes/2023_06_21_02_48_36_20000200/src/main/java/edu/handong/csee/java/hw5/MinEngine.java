package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * MinEngine class represents an engine for finding the minimum value among the inputs.
 */
public class MinEngine implements Computable {
    private static final String engineName = "MIN";
    double result;
    double inputs[];
    int count = 0;

    /**
     * Sets the input values for finding the minimum value.
     * 
     * @param args The input arguments as an array of strings. The first argument is the number of inputs, followed by the input values.
     */
    @Override
    public void setInput(String[] args) {
    	boolean isFirstElementNotDouble;
    	try {
        inputs = new double[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            inputs[i] = Double.parseDouble(args[i + 1]);
            count++;
            isFirstElementNotDouble = false;
        }
    	} catch (NumberFormatException e) {
    	    isFirstElementNotDouble = true;
    	    throw new MyNumberFormatException(args);
    	}
    }

    /**
     * Finds the minimum value among the input values.
     * 
     * @throws MinimumInputNumberException If there are fewer than 2 input values provided.
     * @throws NegativeNumberException    If any of the input values are negative.
     */
    @Override
    public void compute() {
        try {
            if (inputs.length < 2) {
                throw new MinimumInputNumberException("MIN.");
            }
            if (inputs[0] < 0) {
                throw new NegativeNumberException("MIN.");
            } else {
                double min = inputs[0];
                for (int i = 1; i <= count; i++) {
                    if (min > inputs[i]) {
                        min = inputs[i];
                    }
                }
                result = min;
            }
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the result of finding the minimum value.
     * 
     * @return The minimum value as a double.
     */
    @Override
    public double getResult() {
        return result;
    }
}

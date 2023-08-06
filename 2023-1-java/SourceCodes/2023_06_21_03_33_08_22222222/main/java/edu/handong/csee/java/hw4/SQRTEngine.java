package edu.handong.csee.java.hw4;

import edu.handong.csee.java.hw4.exceptions.*;

/**
 * SQRTEngine class represents an engine for calculating the square root of a number.
 */
public class SQRTEngine implements Computable {
    private static final String engineName = "SQRT";
    double inputs[];
    double result;
    int count = 0;

    /**
     * Sets the input value for calculating the square root.
     * 
     * @param args The input arguments as an array of strings. The first argument is the number to calculate the square root of.
     */
    @Override
    public void setInput(String[] args) {
    	boolean isFirstElementNotDouble;
    	try {
        inputs[1] = Double.parseDouble(args[1]);
        count++;
    	} catch (NumberFormatException e) {
    	    isFirstElementNotDouble = true;
    	    throw new MyNumberFormatException(args);
    	}
    }

    /**
     * Calculates the square root of the input number.
     * 
     * @throws OneInputException    If there is not exactly one input value provided.
     * @throws NegativeNumberException If the input value is negative.
     */
    @Override
    public void compute() {
        try {
            if (inputs.length != 1) {
                throw new OneInputException("SQRT.");
            }
            if (inputs[0] < 0) {
                throw new NegativeNumberException("SQRT.");
            } else {
                result = Math.sqrt(inputs[0]);
            }
        } catch (OneInputException e) {
            System.out.println(e.getMessage());
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the result of calculating the square root.
     * 
     * @return The square root of the input number as a double.
     */
    @Override
    public double getResult() {
        return result;
    }
}

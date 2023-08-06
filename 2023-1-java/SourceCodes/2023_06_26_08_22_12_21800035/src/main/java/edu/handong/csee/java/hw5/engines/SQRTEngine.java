package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class implements the Computable interface and provides the square root
 * computation.
 */
public class SQRTEngine implements Computable {
    private static final String engineName = "SQRT";
    private double input;
    private double result;

    /**
     * This method sets the input value for square root computation.
     */
    public void setInput(String[] args) {
        try {
            if (args.length != 1) {
                throw new OneInputException(engineName);
            }

            double inputValue = Double.parseDouble(args[0]);

            if (inputValue < 0) {
                throw new NegativeNumberException(engineName);
            }

            input = inputValue;
        } catch (OneInputException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException(engineName, args[0]);
        }
    }

    /**
     * This method performs the square root computation.
     */
    public void compute() {
        result = Math.sqrt(input);
    }

    /**
     * This method returns the result of the square root computation.
     * 
     * 
     */
    public double getResult() {
        return result;
    }

    /**
     * This method sets the result of the square root computation.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method returns the input value for square root computation.
     * 
     */
    public double getInput() {
        return input;
    }

    /**
     * This method sets the input value for square root computation.
     * 
     */

    public void setInput(double input) {
        this.input = input;
    }
}

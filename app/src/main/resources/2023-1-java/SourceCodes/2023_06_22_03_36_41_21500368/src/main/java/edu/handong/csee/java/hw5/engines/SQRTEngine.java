package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;;

/**
 * The SQRTEngine class is a calculation engine for finding the square root of a given input.
 */
public class SQRTEngine implements Computable {
    /**
     * The name of the SQRTEngine.
     */
    private static final String engineName = "SQRT";

    /**
     * The input value for the square root calculation.
     */
    private double input;

    /**
     * The result of the square root calculation.
     */
    private double result;


    /**
     * Returns the name of the SQRTEngine.
     * @return The engine name.
     */
    public static String getEnginename() {
        return engineName;
    }
    
    /**
     * Returns the input value.
     * @return The input value.
     */
    public double getInput() {
        return input;
    }

    /**
     * Sets the input value.
     * @param input The input value.
     */
    public void setInput(double input) {
        this.input = input;
    }

    /**
     * Sets the result value.
     * @param result The result value.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Sets the input value from the given arguments and calls the compute method.
     * @param args The arguments passed from the command line.
     */
    public void setInput(String[] args){
        try {
            if (args.length > 1) {
                throw new OneInputException(engineName);
            } else if (Integer.parseInt(args[0]) < 0) {
                throw new NegativeNumberException(engineName);
            } else {
                input = Double.parseDouble(args[0]);
                compute();
            }
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
     * Computes the square root of the input value and stores it in the result variable.
     */
    public void compute(){
        result = Math.sqrt(input);
    }

    /**
     * Returns the result of the square root computation.
     * @return The result of the computation.
     */
    public double getResult() {
        return result;
    }
}
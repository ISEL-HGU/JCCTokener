package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.util.InputChecker;

public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";
    private int n;
    private double result;

    /**
     * @return the engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * @return the value of n
     */
    public double getN() {
        return n;
    }

    /**
     * sets the value of n
     * @param n the number to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * sets the value of result
     * @param result the result to set
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * sets the value of n based on user input
     * @param args the array of user input
     */
    public void setInput(String[] args) {
        if (args.length != 1) {
            InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName, 1);
            System.exit(0);
        }
        try {
            n = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            System.exit(0);
        }
        if (n < 0) {
            InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
            System.exit(0);
        }
    }

    /**
     * computes the fibonacci value of n
     */
    public void compute() {
       result = fibonacci(n);
    }

    /**
     * @return the computed fibonacci value
     */
    public double getResult() {
        return result;
    }

    /**
     * computes the n-th fibonacci value
     * @param number the value of n
     * @return the n-th fibonacci value
     */
    public static int fibonacci(int number) {
        if (number <= 1) {
            return number;
        }
        return fibonacci(number - 1) + fibonacci(number - 2);
    }
}

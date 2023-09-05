package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.util.InputChecker;

public class FactorialEngine implements Computable {
    private static final String engineName ="FACTORIAL";
    private int n;
    private double result;

    /**
     * @return factorail engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * @return n
     */
    public double getN() {
        return n;
    }

    /**
     * Set n to the given integer
     *
     * @param n integer to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Set result to the given double
     *
     * @param result double to set
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Parse input arguments and set n accordingly
     *
     * @param args input arguments to parse
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
     * Compute the factorial of n
     */
    public void compute() {
        int a = 1;
        for (int i = 1; i <= n; i++) {
            a = a * i;
        }
        result = a;
    }

    /**
     * @return result of the computation
     */
    public double getResult() {
        return result;
    }
}

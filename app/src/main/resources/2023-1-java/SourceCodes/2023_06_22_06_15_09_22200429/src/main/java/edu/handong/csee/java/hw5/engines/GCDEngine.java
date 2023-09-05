package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This is a class of GCDEngine, and inherits from Computable.
 */
public class GCDEngine implements Computable {

    private static final String engineName = "GCD";
    private int[] a;
    private double result;

    /**
     * This method returns the engine name.
     */
    public static String getEngineName() {
        return engineName;
    }

    /**
     * This is a method to get a[].
     */
    public int[] getA() {
        return a;
    }

    /**
     * Method to set an int[] a.
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * This is the method to set the result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This is a method to get the result of a gcd.
     */
    public double getResult() {
        return result;
    }

    /**
     * This method checks the inputs of the GCD.
     * The reason why inputs.length is lower than 3 is because at compile time,
     * at least 2 inputs are needed for the GCD.
     */
    public void setInput(String[] inputs) {
        try {
            if (inputs.length < 2)
                throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for GCD.\r\n" + "");
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        this.a = new int[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            String input = inputs[i].trim();
            try {
                this.a[i] = Integer.parseInt(input);
                if (this.a[i] < 0)
                    throw new NegativeNumberException("Exception-03: The input value cannot be negative for GCD.");
            } catch (NumberFormatException e) {
                System.exit(0);
            } catch (NegativeNumberException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }

    /**
     * This is a method to calculate the GCD.
     */
    public void compute() {
        result = a[0];
        for (int i = 1; i < a.length; i++) {
            int x = (int) result;
            int y = a[i];

            while (y != 0) {
                int temp = x % y;
                x = y;
                y = temp;
            }

            result = x;
        }
    }
}
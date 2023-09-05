package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * The GCDEngine class finds the greatest common divisor (GCD) of the given input values.
 */
public class GCDEngine implements Computable {
    /**
     * The name of the GCDEngine.
     */
    private static final String engineName = "GCD";

    /**
     * The first input value.
     */
    private int a;

    /**
     * The second input value.
     */
    private int b;

    /**
     * The calculated GCD value.
     */
    private int result;
    
    /**
     * Returns the engine name.
     * 
     * @return The engine name as a string.
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * Returns the first input value.
     * 
     * @return The first input value as an int.
     */
    public int getA() {
        return a;
    }

    /**
     * Sets the first input value.
     * 
     * @param a The first input value as an int.
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * Returns the second input value.
     * 
     * @return The second input value as an int.
     */
    public int getB() {
        return b;
    }

    /**
     * Sets the second input value.
     * 
     * @param b The second input value as an int.
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * Sets the result of the GCD calculation.
     * 
     * @param result The calculated GCD value as an int.
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Sets the input values and computes the GCD.
     * 
     * @param args The input arguments as a string array.
     */
    public void setInput(String[] args) {
        try {
            if (args.length < 2) {
                throw new MinimumInputNumberException(engineName, 2);
            } else {
                a = Integer.parseInt(args[0]);
                if (a < 0) {
                    throw new NegativeNumberException(engineName);
                }
                result = a;
    
                for (int i = 0; i < args.length; i++) {
                    try {
                        b = Integer.parseInt(args[i]);
                        if (b < 0) {
                            throw new NegativeNumberException(engineName);
                        }
                        compute();
                        a = result;
                    } catch (NumberFormatException e) {
                        throw new MyNumberFormatException(engineName, args[i]);
                    }
                }
            }
        } catch (MinimumInputNumberException | NegativeNumberException | MyNumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Computes the GCD of the input values.
     */
    public void compute() {
        while (b != 0) {
            result = b;
            b = a % b;
            a = result;
        }
    }

    /**
     * Returns the result of the GCD calculation.
     * 
     * @return The calculated GCD value as a double.
     */
    public double getResult() {
        return result;
    }
}
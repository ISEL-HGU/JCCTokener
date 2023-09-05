package edu.handong.csee.java.hw5.engines;

import java.math.*;

import edu.handong.csee.java.hw5.exceptions.*;

/*
*A class representing a GCD (Greatest Common Divisor) engine that implements the Computable interface.
*This engine calculates the greatest common divisor of the given integers.
*/
public class GCDEngine implements Computable {
    private static final String engineName = "GCD";
    private int[] a;
    private int result;

    /**
     * This method set the input value using command-line arguments and inputcheck
     * with recognize traits of engine
     * `printErrorMessageForTheNumberOfMinimumRequiredInputsAndExit()` method,
     * If it is less than 0, an error message will be printed using the
     * `InputChecker` class's `printErrorMessageForNegativeInputsAndExit()` method
     * and the program will exit.
     */
    public void setInput(String[] args) {
        try {
            if (args.length < 2) {
                throw new MinimumInputNumberException(engineName);

            }

            a = new int[args.length];
            for (int i = 0; i < a.length; i++) {
                a[i] = Integer.parseInt(args[i]);
                if (a[i] < 0) {
                    throw new NegativeNumberException(engineName);
                }
            }

        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException(engineName, args[1]);
        }
    }

    /**
     * this method Calculates the greatest common divisor of the integers stored in
     * the a array.
     */
    public void compute() {
        result = a[0];

        for (int i = 1; i < a.length; i++) {
            result = BigInteger.valueOf(result).gcd(BigInteger.valueOf(a[i])).intValue();
        }
    }

    /**
     * Sets the a array to the given array of integers.
     * 
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * This method gets the array of integers stored in this engine.
     * 
     */
    public int[] getA() {
        return a;
    }

    /**
     * This method set the result of the computation to the given value.
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * this method returns the result of the computation.
     * 
     */
    public double getResult() {
        return (double) result;
    }
}
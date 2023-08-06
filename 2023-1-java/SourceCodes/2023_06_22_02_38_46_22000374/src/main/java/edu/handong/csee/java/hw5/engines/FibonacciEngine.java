package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * FibonacciEngine.java
 * Computing a fibonacci number for the given integer.
 */

public class FibonacciEngine implements Computable {
    private static final String engineName = "FIBONACCI";
    private int n;
    private double result = 0;

    /**
     * engineName is FIBONACCI.
     * 
     * @param engineName Type : String
     */
    public void setEngineName(String engineName) {
    }

    /**
     * This method return engineName.
     * 
     * @return engineName
     */
    public String getEngineName() {
        return engineName;
    }

    /**
     * Set n value.
     * 
     * @param n Type : int
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * 
     * This method return n value
     * 
     * @return n
     */
    public int getN() {
        return n;
    }

    /**
     * Set result value.
     * 
     * @param result Type : double
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method return result.
     * 
     * @return result
     */
    public double getResult() {
        return result;
    }

    /**
     * This method is for check input - Double or String
     * 
     * @param String ars
     * @return true, false
     */
    public static boolean check(String args) {
        try {
            Double.parseDouble(args);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Set the n value through the input args.
     *
     * # of inputs: one integer value
     * The input value must not be a negative value.
     *
     * @param args Type : String[]
     */
    public void setInput(String[] args) {
        try {
            if (!check(args[0]))
                throw new MyNumberFormatException(args[0], engineName);
            if (args.length == 0)
                throw new MinimumInputNumberException(engineName);
            if (args.length > 1)
                throw new OneInputException(engineName);
            n = Integer.parseInt(args[0]);
            if (n < 0)
                throw new NegativeNumberException(engineName);

        } catch (MinimumInputNumberException e2) {
            System.out.println(e2.getMessage());
            System.exit(0);
        } catch (OneInputException e4) {
            System.out.println(e4.getMessage());
            System.exit(0);
        } catch (NegativeNumberException e3) {
            System.out.println(e3.getMessage());
            System.exit(0);
        } catch (MyNumberFormatException e5) {
            System.out.println(e5.getMessage());
            System.exit(0);
        }
    }

    /**
     * Compute result through n
     * result is the nth term of Fibonacci.
     */
    public void compute() {
        result = fibo(n);
    }

    /**
     * This method is computing the nth term of Fibonacci.
     * It works in a recursive way.
     * 
     * @param num Type : int
     * @return num
     */
    public int fibo(int num) {
        if (num == 0 || num == 1)
            return num;
        return fibo(num - 1) + fibo(num - 2);

    }
}

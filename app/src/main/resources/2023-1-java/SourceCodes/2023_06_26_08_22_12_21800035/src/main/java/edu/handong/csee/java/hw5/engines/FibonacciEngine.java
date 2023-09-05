package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * The FibonacciEngine class implements the Computable interface and calculates
 * the nth Fibonacci number for a given integer input.
 * This class contains methods to set and get the input value, to calculate the
 * nth Fibonacci number of the input value, and to get the result of the
 * calculation.
 */
public class FibonacciEngine implements Computable {
    private static final String engineName = "FIBONACCI";
    private int n; // 입력 값
    private double result; // 계산 결과

    /**
     * This method set the input value using command-line arguments and inputcheck
     * with recognize traits of engine
     * `printErrorMessageForTheNumberOfRequiredInputsAndExit()` method,
     * If it is less than 0, an error message will be printed using the
     * and the program will exit.
     */
    public void setInput(String[] args) {
        try {
            if (args.length != 1) {
                throw new OneInputException(engineName);
            }

            int inputValue = Integer.parseInt(args[0]);

            if (inputValue < 0) {
                throw new NegativeNumberException(engineName);
            }

            n = inputValue;
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
     * This method calculate the nth Fibonacci number of the input value.
     */
    public void compute() {
        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            int prev = 0;
            int curr = 1;
            for (int i = 2; i <= n; i++) {
                int next = prev + curr;
                prev = curr;
                curr = next;
            }
            result = curr;
        }
    }

    /**
     * This method get the input value to calculate the nth Fibonacci number.
     */
    public int getN() {
        return n;
    }

    /**
     * This method set the input value to calculate the nth Fibonacci number.
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * This method set the calculation result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method get the calculation result.
     */
    public double getResult() {
        return result;
    }
}

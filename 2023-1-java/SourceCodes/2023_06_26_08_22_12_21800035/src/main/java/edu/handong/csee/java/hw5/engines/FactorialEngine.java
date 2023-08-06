package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * The FactorialEngine class implements the Computable interface and calculates
 * the factorial of a given integer input.
 */
public class FactorialEngine implements Computable {
    private static final String engineName = "FACTORIAL";
    private int n; // 입력 값
    private double result; // 계산 결과

    /**
     * This method set the input value using command-line arguments and inputcheck
     * with recognize traits of engine
     * If the length of `args` is not 2, an error message will be printed using the
     *
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
     * This method Calculate the factorial of the input value.
     */
    public void compute() {
        if (n == 0) {
            result = 1;
        } else {
            int fact = 1;
            for (int i = 1; i <= n; i++) {
                fact *= i;
            }
            result = fact;
        }
    }

    /**
     * This method get the input value to calculate the factorial.
     */
    public int getN() {
        return n;
    }

    /**
     * This method set the input value to calculate the factorial.
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * this method get the calculation result.
     */
    public double getResult() {
        return result;
    }

    /**
     * This method set the calculation result.
     */
    public void setResult(double result) {
        this.result = result;
    }

}

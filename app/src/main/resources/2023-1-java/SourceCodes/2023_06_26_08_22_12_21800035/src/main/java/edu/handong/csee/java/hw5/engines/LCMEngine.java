package edu.handong.csee.java.hw5.engines;

import java.math.BigInteger;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * LCMEngine class calculates the Least Common Multiple (LCM) of two or more
 * input values.
 * This class implements the Computable interface.
 */
public class LCMEngine implements Computable {
    private static final String engineName = "LCM";
    private int[] a;
    private int result;

    /**
     * This method sets the input values using command-line arguments and throws
     * exceptions
     * if the number of inputs is less than 2 or if any input is less than 0.
     */
    public void setInput(String[] args) {
        try {
            if (args.length < 2) {
                throw new MinimumInputNumberException(engineName);

            }

            a = new int[args.length]; // 수정:
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
     * This method calculates the Least Common Multiple (LCM) of the input values.
     */
    public void compute() {
        if (a.length == 0) {
            result = 0;
            return;
        }

        result = Math.abs(a[0]);

        for (int i = 1; i < a.length; i++) {
            BigInteger bi1 = BigInteger.valueOf(result);
            BigInteger bi2 = BigInteger.valueOf(Math.abs(a[i])); // 절댓값 사용
            result = bi1.multiply(bi2).divide(bi1.gcd(bi2)).intValue();
        }
    }

    /**
     * This method sets the input values for the LCM calculation.
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * This method returns the input values used for the LCM calculation.
     */
    public int[] getA() {
        return a;
    }

    /**
     * This method sets the result of the LCM calculation.
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * This method returns the result of the LCM calculation.
     */
    public double getResult() {
        return (double) result;
    }
}

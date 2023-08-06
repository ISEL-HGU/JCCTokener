package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.util.InputChecker;

public class LCMEngine implements Computable{
    private static final String engineName ="LCM";
    private int[] a;
    private int result;

    /**
     * Get the name of the engine
     * @return engineName
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * Get the value of a
     * @return a
     */
    public int[] getA() {
        return a;
    }

    /**
     * Set the value of a
     * @param a
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * Set the value of result
     * @param result
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * Set the input array and print an error message if there are not enough inputs
     * @param args
     */
    public void setInput(String[] args) {
        if (args.length < 2) {
            InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName, 1);
            System.exit(0);
        }
        for(int i=0; i<args.length; i++){
            try {
                a[i] = Integer.parseInt(args[1]);
            }
            catch (NumberFormatException e) {
                System.exit(0);
            }
            if (a[i] < 0) {
                InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
                System.exit(0);
            }
        }
    }

    /**
     * Compute the LCM of the numbers in array a
     */
    public void compute() {
       result = lcm(a);
    }

    /**
     * Compute the LCM of 2 numbers
     * @param a
     * @param b
     * @return LCM of a and b
     */
    public static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    /**
     * Compute the LCM of multiple numbers
     * @param numbers
     * @return LCM of numbers
     */
    public static int lcm(int[] numbers) {
        int n = numbers[0];
        for(int i = 1; i < numbers.length; i++) {
            n = lcm(n, numbers[i]);
        }
        return n;
    }

    /**
     * Compute the GCD of 2 numbers
     * @param a
     * @param b
     * @return GCD of a and b
     */
    public static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Get the result
     * @return result
     */
    public double getResult() {
        return result;
    }
}

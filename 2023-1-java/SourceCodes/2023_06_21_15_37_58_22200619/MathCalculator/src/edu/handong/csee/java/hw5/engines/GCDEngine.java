package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.util.InputChecker;

public class GCDEngine implements Computable {
    private static final String engineName ="GCD";
    private int[] a;
    private int result;

    /**
     * @return the engine name
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * @return the a
     */
    public int[] getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * @param result the result to set
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * @param args the input arguments
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
     * compute gcd
     */
    public void compute() {
       result = gcd(a);
    }

    /**
     * @param numbers
     * @return gcd
     */
    public static int gcd(int[] numbers) {
        int k= numbers[0];
        for(int i = 1; i < numbers.length; i++) {
            k = gcd2(k,numbers[i]);
        }
        return k;
    }

    /**
     * @param j
     * @param w
     * @return gcd2
     */
    public static int gcd2(int j, int w) {
        while (j > 0) {
            int temp = w;
            w = j % w;
            j = temp;
        }
        return j;
    }

    /**
     * @return the result
     */
    public double getResult() {
        return result;
    }
}

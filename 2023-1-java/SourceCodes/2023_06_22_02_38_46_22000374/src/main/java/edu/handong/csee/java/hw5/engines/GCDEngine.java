package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * GCDEngine.java
 * Computing the greatest common divisor
 */
public class GCDEngine implements Computable {
    private static final String engineName = "GCD";
    private int[] a;
    private int result;

    /**
     * engineName is GCD
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
     * Set a value.
     * 
     * @param a Type : int[]
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * 
     * This method return a.
     * 
     * @return a value.
     */
    public int[] getA() {
        return a;
    }

    /**
     * Set result value.
     * 
     * @param result Type : int
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * This method return result.
     * 
     * @return result.
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
     * Set the a value through inputs args.
     * # of inputs: >= two integer numbers
     * The input value must not be a negative value.
     *
     * @param args Type : String[]
     */
    public void setInput(String[] args) {

        try {
            if (!check(args[0]))
                throw new MyNumberFormatException(args[0], engineName);
            if (args.length <= 1) {
                throw new MinimumInputNumberException(engineName);
            }
            a = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                a[i] = Integer.parseInt(args[i]);
                if (a[i] < 0)
                    throw new NegativeNumberException(engineName);
            }

        } catch (MinimumInputNumberException e2) {
            System.out.println(e2.getMessage());
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
     * Compute result through gcd method.
     * result it the greatest common divisor of all inputs.
     */
    public void compute() {
        int gcd = gcd(a[0], a[1]);
        for (int i = 2; i < a.length; i++) {
            gcd = gcd(gcd, a[i]);
        }
        result = gcd;
    }

    /**
     * This method return the greatest common divisor of two numbers.
     * 
     * @param s1 Type : int
     * @param s2 Type : int
     * @return temp.
     */
    public int gcd(int s1, int s2) {
        int temp = 0;
        while (s2 != 0) {
            int remain = s1 % s2;
            s1 = s2;
            s2 = remain;
        }
        temp = s1;
        return temp;
    }

}

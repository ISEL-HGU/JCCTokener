package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * LCMEngine.java
 * Computing the least common multiple
 */
public class LCMEngine implements Computable {
    private static final String engineName = "LCM";
    private int[] a;
    private int result;

    /**
     * engineName is LCM
     * 
     * @param engineName LCM
     */
    public void setEngineName(String engineName) {
    }

    /**
     * This method return engineName.
     * 
     * @return engineName.
     */
    public String getEngineName() {
        return engineName;
    }

    /**
     * Set a value.
     * 
     * @param a int[]a
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
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
                throw new MyNumberFormatException(args[1], engineName);
            if (args.length < 2) {
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
     * Compute result through lcm method.
     * result it the least common multiple of all inputs.
     */
    public void compute() {
        int lcm = lcm(a[0], a[1]);
        for (int i = 2; i < a.length; i++) {
            lcm = lcm(lcm, a[i]);
        }
        result = lcm;
    }

    /**
     * This method return the least common multiple of two numbers.
     * 
     * @param a Type : int
     * @param b Type : int
     * @return result
     */
    public int lcm(int a, int b) {
        for (int i = a * b; i >= 1; i--) {
            if (i % a == 0 && i % b == 0)
                result = i;
        }
        return result;
    }

}

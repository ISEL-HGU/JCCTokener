package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.Computable;

/**
 * LCMEngine class calculates the Least Common Multiple (LCM) of multiple numbers.
 */
public class LCMEngine implements Computable {
    private static final String engineName = "LCM";
    int inputs[];
    int result;
    int count = 0;

    /**
     * Sets the input values for the LCM calculation.
     * 
     * @param args The array of input values.
     */
    @Override
    public void setInput(String[] args) {
    	try {
        for (int i = 0; args[i] != null; i++) {
        	
            inputs[i] = Integer.parseInt(args[i + 1]);
            count++;
        }
    	}catch (NumberFormatException e) {
    	    throw new MyNumberFormatException(args);
    	}
    }

    /**
     * Computes the LCM of the input values.
     */
    @Override
    public void compute() {
        try {
            if (inputs.length >= 2) {
                throw new MinimumInputNumberException("LCM.");
            }
            if (inputs[0] < 0) {
                throw new NegativeNumberException("LCM.");
            } else {
                result = lcmMultiple(inputs);
            }
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calculates the LCM of multiple numbers.
     * 
     * @param nums The array of numbers.
     * @return The LCM of the numbers.
     */
    public static int lcmMultiple(int[] nums) {
        int lcm = nums[0];
        for (int i = 1; i < nums.length; i++) {
            lcm = lcm(lcm, nums[i]);
        }
        return lcm;
    }

    /**
     * Calculates the LCM of two numbers.
     * 
     * @param a The first number.
     * @param b The second number.
     * @return The LCM of the two numbers.
     */
    public static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    /**
     * Calculates the Greatest Common Divisor (GCD) of two numbers.
     * 
     * @param a The first number.
     * @param b The second number.
     * @return The GCD of the two numbers.
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    /**
     * Returns the computed result, which is the LCM.
     * 
     * @return The LCM result.
     */
    @Override
    public double getResult() {
        return result;
    }
    public class TypeChecker {
        public static String checkType(Object[] args, int i) {
            if (args[i] instanceof String) {
                return "string";
            } else if (args[i] instanceof Double || args[i] instanceof Integer || args[i] instanceof Float) {
                return "int";
            } else {
                return "unknown";
            }
        }
    }

}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * the class contains fields,methods that implements Computable interface for calculating greatest common divisor.
 * @author kim hongchan
 *
 */
public class GCDEngine implements Computable {
	/**
	 * String value that means engine name initialized as "GCD"
	 */
    private static final String engineName ="GCD";
    /**
	 * Integer array value that is a[] for calculating greatest common divisor.
	 */
    private int[] a;
    /**
	 * Integer value that is result for calculated greatest common divisor.
	 */
    private int result;
    /**
     * Set a a[] from String type parameter args[]. there should be more than 3 parmeter args[] and each value should be positive.
     * @param args
     */
	public void setInput(String[] args) throws MinimumInputNumberException,NegativeNumberException{
		if(args.length < 2) throw new MinimumInputNumberException(engineName);
		this.a = new int[args.length];
		for(int i = 0; i < args.length; i++) {
			this.a[i] = Integer.parseInt(args[i]);
			if(this.a[i] < 0) throw new NegativeNumberException(engineName);
		}
	}
	/**
	 * Compute greatest common divisor.
	 */
	public void compute() {
		int gcd = getGCD(this.a[0], this.a[1]);
        int lcm = (this.a[0] * this.a[1]) / gcd;
        for (int i = 2; i < this.a.length; i++) {
            gcd = getGCD(lcm, this.a[i]);
            lcm = (lcm * this.a[i]) / gcd;
        }
        this.result = gcd;
	}
	/**
	 * Recursion function. If the remainder between two input values ​is 0, it returns the second input value, 
	 * otherwise it returns the gcd function with the first value and the remainder.
	 * @param num1
	 * @param num2
	 * @return if the remainder with num1 and num2 is 0, return num2. or not return getGCD(num2, num1 % num2)
	 */
	private int getGCD(int num1, int num2) {
		if (num1 % num2 == 0) return num2;
        return getGCD(num2, num1 % num2);
    }
	/**Return result of a double value.
	 * @return result
	 */
	public double getResult() {
		return this.result;
	}
	/**
	 * Return a[]
	 * @return a
	 */
	public int[] getA() {
		return a;
	}
	/**
	 * Set a
	 * @param a
	 */
	public void setA(int[] a) {
		this.a = a;
	}
	/**
	 * Return engineName
	 * @return engineName
	 */
	public static String getEnginename() {
		return engineName;
	}
	/**
	 * Set result
	 * @param result
	 */
	public void setResult(int result) {
		this.result = result;
	}
    
}

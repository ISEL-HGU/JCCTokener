package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * he class contains fields,methods that implements Computable interface for calculating fibonacci.
 * @author kim hongchan
 *
 */
public class FibonacciEngine implements Computable {
	/**
	 * String value that means engine name initialized as "FIBONACCI".
	 */
    private static final String engineName ="FIBONACCI";
    /**
	 * Integer value that is n for calculating fibonacci.
	 */
    private int n;
    /**
	 * Integer value that is result for calculated fibonacci.
	 */
    private double result;
    /**
     * Set a n from String type parameters args[]. there should be two parameters args[] and each value should be positive.
     * @param args
     */
	public void setInput(String[] args) throws OneInputException,NegativeNumberException {
		if(args.length != 1) throw new OneInputException(engineName);
    	if(Integer.parseInt(args[0]) < 0) throw new NegativeNumberException(engineName);
    	this.n = Integer.parseInt(args[0]);
	}
	/**
	 * Compute fibonacci.
	 */
	public void compute() {
		this.result = FibonacciCompute(this.n);
	}
	/**
	 * Recursive function for fibonachi. return integer value that is sum of previous two fibonacci output
	 * @param int n
	 * @return FibonacciCompute(n-2)+FibonacciCompute(n-1)
	 */
	private int FibonacciCompute(int n) {
		if(n <= 1) return n;
		else return FibonacciCompute(n-2)+FibonacciCompute(n-1);
	}
	/**Return result of a double value.
	 * @return result
	 */
	public double getResult() {
		return this.result;
	}
	/**
	 * Return n
	 * @return n
	 */
	public int getN() {
		return n;
	}
	/**
	 * Set n
	 * @param n
	 */
	public void setN(int n) {
		this.n = n;
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
	public void setResult(double result) {
		this.result = result;
	}
	
}

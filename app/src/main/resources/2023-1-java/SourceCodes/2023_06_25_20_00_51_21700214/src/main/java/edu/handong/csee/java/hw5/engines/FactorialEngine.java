package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * the class contains fields,methods that implements Computable interface for calculating factorial.
 * @author kim hongchan
 *
 */
public class FactorialEngine implements Computable {
	/**
	 * String value that means engine name initialized as "FACTORIAL".
	 */
    private static final String engineName ="FACTORIAL";
	/**
	 * Integer value that is n for calculating factorial.
	 */
    private int n;
	/**
	 * Integer value that is result for calculated factorial.
	 */
    private double result;
    /**
     * Set a result from String type parameters args[]. there should be two parmeters args[] and each value should be positive.
     * @param args
     */
    public void setInput(String[] args) throws OneInputException,NegativeNumberException {
    	if(args.length != 1) throw new OneInputException(engineName); 
    	if(Integer.parseInt(args[0]) < 0) throw new NegativeNumberException(engineName);
    	this.n = Integer.parseInt(args[0]);
    }
	/**
	 * Compute factorial.
	 */
    public void compute() {
    	this.result = 1;
    	for(int i = 1; i <= n; i++) {
    		this.result *= i;
    	}
    }
	/**
	 * Return volume of a double value.
	 * @return volume
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

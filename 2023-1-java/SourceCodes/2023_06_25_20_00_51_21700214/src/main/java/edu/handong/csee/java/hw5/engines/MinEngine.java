package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;
import java.util.Arrays;


/**
 * the class contains fields,methods that implements Computable interface for calculating min value among inputs.
 * @author kim hongchan
 *
 */
public class MinEngine implements Computable {
	/**
	 * String value that means engine name
	 */
    private static final String engineName ="MIN";
    /**
  	 * Integer array value that is input[] for calculating min value.
  	 */
    private double[] inputs;
    /**
  	 * Integer value that is result for calculating min value among inputs.
  	 */
    private double result;
    /**
     * Set a input[] from String type parameters args[]. there should be more than 3 parmeters args[].
     * @param args
     */
	public void setInput(String[] args)throws MinimumInputNumberException {
		if(args.length < 2) throw new MinimumInputNumberException(engineName);
		this.inputs = new double[args.length];
		for(int i = 0; i < args.length; i++) inputs[i] = Double.parseDouble(args[i]);
	}
	/**
	 * Compute volume. Arrays.sort() was used.
	 */
	public void compute() {
		double[] tempList = this.inputs.clone();
		Arrays.sort(tempList);
		this.result = tempList[0];
	}
	/**Return result of a double value.
	 * @return result
	 */
	public double getResult() {
		return this.result;
	}
	/**
	 * Return inputs
	 * @return inputs
	 */
	public double[] getInputs() {
		return inputs;
	}
	/**
	 * Set inputs
	 * @param inputs
	 */
	public void setInputs(double[] inputs) {
		this.inputs = inputs;
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

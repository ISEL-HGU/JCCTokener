package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;
import java.util.Arrays;


/**
 * the class contains fields,methods that implements Computable interface for calculating max value among inputs.
 * @author kim hongchan
 *
 */
public class MaxEngine implements Computable {
	/**
	 * String value that means engine name initialized as "MAX"
	 */
    private static final String engineName ="MAX";
    /**
  	 * Integer array value that is input[] for calculating max value.
  	 */
    private double[] inputs;
    /**
	 * Integer value that is result for calculated greatest max value among inputs.
	 */
    private double result;
    /**
     * Set a inputs[] from String type parameter args[]. there should be more than 3 parmeter args[].
     * @param args
     */
	public void setInput(String[] args) throws MinimumInputNumberException {
		if(args.length < 2) throw new MinimumInputNumberException(engineName);
		this.inputs = new double[args.length];
		for(int i = 0; i < args.length; i++) this.inputs[i] = Double.parseDouble(args[i]);
	}
	/**
	 * Compute volume. Arrays.sort() was used.
	 */
	public void compute() {
		double[] tempList = this.inputs.clone();
		Arrays.sort(tempList);
		this.result = tempList[tempList.length-1];
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

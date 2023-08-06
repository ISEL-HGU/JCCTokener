package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This class is one of Engine class for computing the square root of a number.
 */
public class SQRTEngine implements Computable {
	/**
	 * Every engines have its own name to help user distinguish it with other engines
	 * This is a field about engine Name called SQRT
	 */
    private static final String engineName ="SQRT";
    /**
	 * This is a field about input number
	 */
    private double input;
    /**
	 * This is a field about result value
	 */
    private double result;
	
	/**
	 * Method SetInput gets arguments and determines if the program can or cannot perform normally.
	 * If the arguments are not suitable, print error message from InputChecker
	 * Such as the number of required inputs, or negative number.
	 */
    public void setInput(String[] engineName) {
    	input = Integer.parseInt(engineName[0]);
    }
	/**
	 * The values are computed in this method.
	 */
    public void compute() {
    	result = Math.sqrt(input);
    }
	/**
	 * the result of computation is returned in this method.
	 */
    public double getResult(){
    	return result;
    }
	/**
	 * this is a getter of Input
	 */
	public double getInput() {
		return input;
	}
	/**
	 * this is a setter of Input
	 */
	public void setInput(double input) {
		this.input = input;
	}
	/**
	 * this is a getter of Engine name
	 */
	public static String getEnginename() {
		return engineName;
	}
	/**
	 * this is a setter of result
	 */
	public void setResult(double result) {
		this.result = result;
	}
}

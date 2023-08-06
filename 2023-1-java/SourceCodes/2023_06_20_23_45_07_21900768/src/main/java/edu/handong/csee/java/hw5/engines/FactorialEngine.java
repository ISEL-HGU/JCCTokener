package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is one of Engine class for computing the Factorial number for the given number.
 */
public class FactorialEngine implements Computable {
	/**
	 * Every engines have its own name to help user distinguish it with other engines
	 * This is a field about engine Name called Factorial
	 */

    private static final String engineName ="FACTORIAL";
    /** This is a field about n which is input number */
    private int n;
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

    	n = Integer.parseInt(engineName[0]);

    }
	/**
	 * The values are computed in this method.
	 */
    public void compute() {
    	result = 1;
    	if(n ==0)
    		result = 1;
    	for(int i =1; i<=n; i++)
    		result *= (i);
    }
	/**
	 * the result of computation is returned in this method.
	 */
    public double getResult(){
    	return result;
    }
	/**
	 * this is a getter of n
	 */
	public int getN() {
		return n;
	}
	/**
	 * this is a setter of n
	 */
	public void setN(int n) {
		this.n = n;
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

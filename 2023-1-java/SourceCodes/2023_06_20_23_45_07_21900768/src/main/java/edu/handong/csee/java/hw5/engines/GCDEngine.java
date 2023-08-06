package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This class is one of Engine class for computing the greates common divisor(GCD) for the given two numbers.
 */
public class GCDEngine implements Computable {
	/**
	 * Every engines have its own name to help user distinguish it with other engines
	 * This is a field about engine Name called GCD(Greatest Common Divisor)
	 */
    private static final String engineName ="GCD";
    /**
	 * This is a field about input numbers
	 */
	private int[] input;
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
    	input = new int[engineName.length];
    	
    	for(int i=1; i<engineName.length; i++) {
    		input[i] = Integer.parseInt(engineName[i]);
    	}
    }
	/**
	 * The values are computed in this method.
	 */
    public void compute() {
    	int a, b;
    		if(input[0]>=input[1]) {
    			a = input[0];
    			b = input[1];
    			}
    		else {
    			a = input[1];
    			b = input[0];
    		}
    		while (b != 0) {
				int temp = b;
				b = a % b;
				a = temp;
			}
    		result = a;
 
    		if(input.length >=3){
    		for(int i = 2; i<input.length; i++) {
    			if(result > input[i]) {
    				a = (int)result;
    				b = input[i];
    			}
    			else {
    				a = input[i];
    				b = (int)result;
    			}
    		while (b != 0) {
    			int temp = b;
    			b = a % b;
    			a = temp;
    		}
    		result = a;
        }
    	}
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
	public int[] getInput() {
		return input;
	}
	/**
	 * this is a setter of Input
	 */
	public void setInput(int[] input) {
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


package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This class is one of Engine class for computing the least common multiple(LCM) for the given two numbers.
 */
public class LCMEngine implements Computable{
	/**
	 * Every engines have its own name to help user distinguish it with other engines
	 * This is a field about engine Name called LCM(Least Common Multiple)
	 */
    private static final String engineName ="LCM";
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
    	
    	for(int i=0; i<engineName.length; i++) {
    		input[i] = Integer.parseInt(engineName[i]);
    	}
    }
	/**
	 * The values are computed in this method.
	 * Using GCDEngine instance.
	 */
    public void compute() {
    	result = 1;
    	GCDEngine gcd = new GCDEngine();
    	String gcdArg[]= new String[3];
    	
    	int a = input[0];
    	int b = input[1];
    	gcdArg[0] = "GCD";
    		gcdArg[1] = Integer.toString(input[0]);
    		gcdArg[2] = Integer.toString(input[1]);
    	
    	gcd.setInput(gcdArg);
    	gcd.compute();
    	
    	result = (a*b)/gcd.getResult();
    	
    	if(input.length >=3){
    		for(int i=2; i<input.length; i++) {
    			gcdArg[1] = Integer.toString((int)result);
        		gcdArg[2] = Integer.toString(input[i]);
        		
        		gcd.setInput(gcdArg);
            	gcd.compute();
            	
            	result = (result * input[i])/gcd.getResult();
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


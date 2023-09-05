package edu.handong.csee.java.hw5.engines;

import java.util.Arrays;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is one of Engine class for computing the minimum number for the given multiple numbers.
 */
public class MinEngine implements Computable {
	/**
	 * Every engines have its own name to help user distinguish it with other engines
	 * This is a field about engine Name called MIN
	 */
    private static final String engineName ="MIN";
    /**
	 * This is a field about input numbers
	 */
    private double[] inputs;
    /**
	 * This is a field about result value
	 */
    private double result;
	
     /**
	 * Method SetInput gets arguments and determines if the program can or cannot perform normally.
	 * If the arguments are less then 3(engine name + two numbers), print error message from InputChecker.
	 */
    public void setInput(String[] engineName) {
    	
    	inputs = new double[engineName.length];
    	
    	for(int i=0 ; i<engineName.length; i++) {
    		inputs[i]=Double.parseDouble(engineName[i]);
    	}
    }
    /**
	 * The values are computed in this method.
	 */
    public void compute() {
    	result = inputs[0];
        for (int i = 1; i < inputs.length; i++) {
            if (inputs[i] < result) {
            	result = inputs[i];
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
    public double[] getInputs() {
    	return inputs;
    }
	/**
	 * this is a setter of Input
	 */
    public void setInputs(double[] inputs) {
    	this.inputs = inputs;
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

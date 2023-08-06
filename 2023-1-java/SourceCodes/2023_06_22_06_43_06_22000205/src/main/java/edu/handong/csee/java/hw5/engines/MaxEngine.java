package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * This is a class that finds the maximum value among the input values. 
 * engineName, inputs to store the input double, and result which means the result of finding the maximum value are declared as fields.
 */
public class MaxEngine implements Computable {
	private static final String engineName ="MAX";
    private double[] inputs;
    private double result;    

    /**
     * This is a method that checks for input values and sets them.
     * This must have at least two inputs, and code is implemented to terminate the program if this condition is not met.  
     * Exceptions are implemented for entering less than two numbers, and for entering a non-number.
     */
    public void setInput(String[] inputNum) throws MinimumInputNumberException, MyNumberFormatException{
        if(inputNum.length < 2){
        	throw new MinimumInputNumberException("You need at least 2 input values for MAX.");
        }

        inputs = new double[inputNum.length];
        for (int i=0; i<inputNum.length; i++){
            try {
                inputs[i] = Double.parseDouble(inputNum[i]);
            } catch (NumberFormatException e) {
                throw new MyNumberFormatException("The input value should be converted into a number. (" + inputNum[i] + " is not a number value for MAX.)");
            }
        }
    }

    /**
     * This method finds the maximum value using the input values. 
     */
    public void compute(){
        result = inputs[0];
        for(int i=0; i<inputs.length; i++){
            if (inputs[i]>result){
                result = inputs[i];
            }
        }
    }

    /**
     * This method sets the result a value.
     */
    public void setResult(double res){
        result = res;
    }

    /**
     * This method returns the value calculated by the compute method as a double type.
     * It is declared public, so it can be accessed from the Calculator class. 
     */
    public double getResult(){
        return result;
    }

    /**
     * This method returns the engineName value.
     */
    public static final String getEngineName(){
        return engineName;
    }

    /**
     * This method sets the inputs value.
     */
    public void setInputs(double[] inp){
        inputs = inp;
    }

    /**
     * This method returns the inputs value.
     */
    public double[] getInputs(){
        return inputs;
    }
}
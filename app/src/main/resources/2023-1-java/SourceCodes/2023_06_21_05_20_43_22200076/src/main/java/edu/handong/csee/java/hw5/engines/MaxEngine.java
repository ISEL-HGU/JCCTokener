package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A calculator that finds the maximum value among two or more numbers.
 */
public class MaxEngine implements Computable {
    private static final String engineName ="MAX";
    private double[] inputs;
    private double result;

    /**
     * Sets numbers to calculate max number.
     * 
     * @param inputs numbers to calculate max number
     */
    public void setInputs(double[] inputs){
        this.inputs = inputs;
    }

    /**
     * Gets numbers to calculate max number.
     * 
     * @return numbers to calculate max number
     */
    public double[] getInputs(){
        return inputs;
    }

    /**
     * Sets a result of calculating the max number.
     * 
     * @param result a value of calculating the max number
     */
    public void setResult(double result){
        this.result = result;
    }

    /**
     * Converts the input string into the double type for calculations.
     * 
     * @param input the input string
     */
    public void setInput(String[] input) throws Exception{
        inputs = new double[input.length];
        if(inputs.length < 2){
        	try {
        		throw new MinimumInputNumberException(engineName, '2');
        	} catch(MinimumInputNumberException e){
        		System.out.println(e.getMessage());
        		throw new Exception();
        		//System.exit(0);
        	}
        }
        for(int i=0; i<input.length; i++){
        	try {
        		inputs[i] = Double.parseDouble(input[i]);
        	} catch(NumberFormatException e) {
        		try {
        			throw new MyNumberFormatException(engineName, input[i]);
        		} catch(MyNumberFormatException ex){
        			System.out.println(ex.getMessage());
        			throw new Exception();
        			//System.exit(0);
        		}
        	}
        }
    }

    /**
     * Calculates the largest value among the numbers.
     */
    public void compute(){
        result = inputs[0];
        for(int i=1; i<inputs.length; i++){
            if(result<inputs[i]){
                result = inputs[i];
            }
        }
    }

    /**
     * Returns the calculated result value.
     * 
     * @return the result of the calculation
     */
    public double getResult(){
        return result;
    }

}
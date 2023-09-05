package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A square root calculator.
 */
public class SQRTEngine implements Computable {
    private static final String engineName ="SQRT";
    private double input;
    private double result;

    /**
     * Sets a number to calculate Sqrt.
     * 
     * @param input a number to calculate Sqrt
     */
    public void setInput(double input){
        this.input = input;
    }

    /**
     * Gets the number to calculate Sqrt.
     * 
     * @return the number to calculate Sqrt
     */
    public double getInput(){
        return input;
    }


    /**
     * Sets a result of calculating the Sqrt.
     * 
     * @param result a value of calculating the Sqrt
     */
    public void setResult(double result){
        this.result = result;
    }

    /**
     * Converts the input string into the double type for calculations.
     * 
     * @param input the input string
     * @throws Exception 
     */
    public void setInput(String[] input) throws Exception {
        if(input.length > 1){
        	try {
        		throw new OneInputException(engineName);
        	} catch(OneInputException e){
        		System.out.println(e.getMessage());
        		throw new Exception();
        		//System.exit(0);
        	}
        }
        try {
        	this.input = Double.parseDouble(input[0]);
    	} catch(NumberFormatException e) {
    		try {
    			throw new MyNumberFormatException(engineName, input[0]);
    		} catch(MyNumberFormatException ex){
    			System.out.println(ex.getMessage());
    			throw new Exception();
    			//System.exit(0);
    		}
    	}
        if(this.input < 0){
        	try {
        		throw new NegativeNumberException(engineName);
        	} catch(NegativeNumberException e){
        		System.out.println(e.getMessage());
        		throw new Exception();
        		//System.exit(0);
        	}
        }
    }

    /**
     * Calculates the square root.
     */
    public void compute(){
        result = Math.sqrt(input);
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
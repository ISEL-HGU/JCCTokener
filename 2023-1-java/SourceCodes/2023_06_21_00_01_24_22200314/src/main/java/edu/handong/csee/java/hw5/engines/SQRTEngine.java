package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;


/**
 * This class calculate the Square Root of the value input received from Calculator.java
 */
public class SQRTEngine implements Computable {
	
	
    private static final String engineName ="SQRT";
    /**
     * This method returns the Engine Name.
     * @return The Engine Name of the class.
     */
    public static String getEnginename() {
        return engineName;
    }

    private double input;
    private double result;

    /**
     * This method returns the number of input used for Square Root calculation.
     * @return The value of input. 
     */
    public double getInput() {
        return input;
    }
    /**
     * This method sets the value input which is the number for Square Root calcuation.
     * @param input The number to be set as input.
     */
    public void setInput(double input) {
        this.input = input;
    }
    /**
     * This method sets the value result.
     * @param result The number to be set as result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method sets the input as the arg[0] index. 
     * There are some exceptions: 1. If there are more than 1 received arguments, the program is prints an error and gets terminated. 2. If the value of the arg[0] is negative, the program is prints an error and gets terminated. 
     * @param arg String array from the terminal. This value would be the input value also known as the input.
     */
    public void setInput(String[] arg) {
    	try {
	        if(arg.length != 2){
	        	throw new OneInputException(engineName);
	            //InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName, 1);
	        }
	
	        if(!MyNumberFormatException.isNumber(arg[1])) {
        		throw new MyNumberFormatException(arg[1], engineName);
        	}
	        
	        if(Integer.parseInt(arg[1]) < 0){
	        	throw new NegativeNumberException(engineName);
	            //InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
	        }
	        
	    	input = Double.parseDouble(arg[1]);
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e){
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    }

    /**
     * This method does the calculation for Square Root.
     */
    public void compute() {
    	result = Math.sqrt(input);
    }
    
    /**
     * This method returns the result done from Square Root calculation.
     * @return The result done from calculating Square Root. 
     */
    public double getResult() {
    	return result;
    }
}

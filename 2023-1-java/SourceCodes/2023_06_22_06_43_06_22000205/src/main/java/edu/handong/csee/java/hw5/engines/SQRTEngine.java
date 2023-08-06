package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * This class calculates the square root value of the input value.
 * engineName, input variable, and result which means the result of calculating the square root, are declared as fields. 
 */
public class SQRTEngine implements Computable {
	private static final String engineName ="SQRT";
    private double input;
    private double result;

   
    /**
     * This is a method that checks the input value and sets input. 
     * It exits the program if it receives anything other than the engine name and the input value,
     * The following exceptions are implemented
     * if more than one number is entered, if the input is not a number, and if a negative number is entered.
     */
    public void setInput(String[] inputNum) throws OneInputException, NegativeNumberException, MyNumberFormatException{
    	if (inputNum.length != 1 && inputNum.length != 0) {
            throw new OneInputException("You need one input value for SQRT.");
        }

        if (inputNum.length == 1) {
            try {
                input = Double.parseDouble(inputNum[0]);
            } catch (NumberFormatException e) {
                throw new MyNumberFormatException(
                        "The input value should be converted into a number. (" + inputNum[0] + " is not a number value for SQRT.)");
            }

            if (input < 0) {
                throw new NegativeNumberException("The input value cannot be negative for SQRT.");
            }
        }        
        
    }

    /**
     * This method calculates the square root of input given the input value.
     * The square root is calculated using the sqrt method of the Math class.  
     */
    public void compute(){
        result = Math.sqrt(input);
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
     * This method sets the input value.
     */
    public void setInput(double inp){
        input = inp;
    }

    /**
     * This method returns the input value.
     */
    public double getInput(){
        return input;
    }
}
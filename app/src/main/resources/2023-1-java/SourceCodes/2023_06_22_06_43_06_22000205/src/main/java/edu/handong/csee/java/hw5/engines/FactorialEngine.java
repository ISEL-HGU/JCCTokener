package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * This is a class that calculates the factorial value using the input int value.
 * engineName, n which means a variable representing the input integer, and result which means the result of calculating the factorial value are declared as fields.
 */
public class FactorialEngine implements Computable {
	private static final String engineName ="FACTORIAL";
    private int n;
    private double result;
    
    /**
     * This is a method that checks the input value and sets the int value.
     * It terminates the program if it receives anything other than the engine name and the int value as input, 
     * The following exceptions are implemented 
     * if more than one number is entered, if the input is not a number, and if a negative number is entered.
     */
    public void setInput(String[] inputNum) throws OneInputException, NegativeNumberException, MyNumberFormatException{
        
        if(inputNum.length !=1){
        	throw new OneInputException("You need one input value for FACTORIAL.");
        }
        
        try {
            n = Integer.parseInt(inputNum[0]);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException("The input value should be converted into a number. (" + inputNum[0] + " is not a number value for FACTORIAL.)");
        }

        if(n < 0){
        	throw new NegativeNumberException("The input value cannot be negative for FACTORIAL.");
        }
    }

    /**
     * This method calculates the factorial value using the input int value.  
     */
    public void compute(){
        result = 1;
        for (int i=n; i>0; i--){
            result *= i;
        }
    }

    /**
     * This method sets the result value.
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
     * This method sets the n value.
     */
    public void setN(int val){
        n = val;
    }

    /**
     * This method returns the n value.
     */
    public int getN(){
        return n;
    }
	
}
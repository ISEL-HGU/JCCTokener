package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * This is a class that calculates the fibonacci value using the input integer value.
 * engineName, n which means a variable representing the input integer, and result which means the result of calculating the fibonacci value are declared as fields. 
 */
public class FibonacciEngine implements Computable {
	private static final String engineName ="FIBONACCI";
    private int n;
    private double result;

    /**
     * This is a method that checks the input value and sets an int value.
     * It terminates the program if it receives anything other than the engine name and the int value as input,
     * The following exceptions are implemented 
     * if more than one number is entered, if the input is not a number, and if a negative number is entered.
     */
    public void setInput(String[] inputNum) throws OneInputException, NegativeNumberException, MyNumberFormatException{

        if(inputNum.length !=1){
        	throw new OneInputException("You need one input value for FIBONACCI.");
        }

        try {
            n = Integer.parseInt(inputNum[0]);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException("The input value should be converted into a number. (" + inputNum[0] + " is not a number value for FIBONACCI.)");
        }

        if(n < 0){
        	throw new NegativeNumberException("The input value cannot be negative for FIBONACCI.");
        }
    }

    /**
     * This method calculates the fibonacci value using the input int value. 
     */
    public void compute(){
        int num1 = 0;
        int num2 = 1;

        if(n == 1 || n==2){
            result = 1;
        }

        for(int i=2; i<=n; i++){
            result = num1+num2;
            num1 = num2;
            num2 = (int)result;
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
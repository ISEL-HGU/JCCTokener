package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
 * This class calculates the LCD value using the input int values.
 * engineName, integers a and b to assign the first two values of the input values, result to calculate the LCD value, and inputs to store the new number of inputs are declared as fields.
 * The following exceptions are implemented
 * if more than one number is entered, if the input is not a number, and if a negative number is entered.
 */
public class LCMEngine implements Computable {
	private static final String engineName ="LCM";

    private int a;
    private int b;
    private int result;
    private int[] inputs;

    /**
     * This is the method that checks for the input values and sets the a and b values.
     * This need to have at least two inputs, and if this condition is not met, the program terminates,
     * Code is implemented to exit the program if the input value is negative. 
     */
    public void setInput(String[] inputNum)throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException{

        if(inputNum.length < 2){
        	throw new MinimumInputNumberException("You need at least 2 input values for LCM.");
        }

        inputs = new int[inputNum.length];
        for(int i=0; i<inputNum.length; i++) {
        	try {
                inputs[i] = Integer.parseInt(inputNum[i]);
                if(inputs[i] < 0){
                	throw new NegativeNumberException("The input value cannot be negative for LCM.");
                }
            } catch (NumberFormatException e) {
                throw new MyNumberFormatException("The input value should be converted into a number. (" + inputNum[i] + " is not a number value for LCM.)");
            }
        }

        a = inputs[0];
        b = inputs[1];
    }
    
    /**
     * This is a method to calculate the LCD using the a and b values. 
     * This method uses the maximum common factor to find the minimum common multiple.
     */
    public void compute(){
       int gcd = computeGCD(a, b);
       result = (a*b)/gcd;
       for(int i=2; i<inputs.length; i++){
        result = (result*inputs[i])/computeGCD(result,inputs[i]);
       }
    }

    /**
     * The GCD value is required to calculate the LCD, and this method calculates the value.
     */
    public int computeGCD(int a, int b){
        if (b==0){
            return a;
        }
        else{
            return computeGCD(b, a%b);
        }
    }

    /**
     * This method sets the result value.
     */
    public void setResult(int res){
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
     * This method sets the int a value.
     */
    public void setA(int val){
        a = val;
    }

    /**
     * This method returns the int a value.
     */
    public int getA(){
        return a;
    }

    /**
     * This method sets the int b value.
     */
    public void setB(int val){
        b = val;
    }

    /**
     * This method returns the int b value.
     */
    public int getB(){
        return b;
    }

    /**
     * This method sets the inputs value.
     */
    public void setInputs(int[] inp){
        inputs = inp;
    }

    /**
     * This method returns the inputs value.
     */
    public int[] getInputs(){
        return inputs;
    }
}
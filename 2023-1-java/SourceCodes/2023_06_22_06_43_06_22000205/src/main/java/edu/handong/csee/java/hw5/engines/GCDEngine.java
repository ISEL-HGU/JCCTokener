package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
 * This is a Class that calculates the GCD value using the input integer values.
 * engineName, integers a and b to assign the last two values of the input values, and result which means the result of calculating the GCD value are declared as fields. 
 */
public class GCDEngine implements Computable {
	private static final String engineName ="GCD";
    private int a;
    private int b;
    private int result;

    /**
     * This is a method that checks the input values and sets the values of a and b.
     * This must have at least two input values, and will exit the program if this condition is not met,
     * The following exceptions are implemented
     * if more than one number is entered, if the input is not a number, and if a negative number is entered.
     */
    public void setInput(String[] inputNum) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException{

        if(inputNum.length <2){
        	throw new MinimumInputNumberException("You need at least 2 input values for GCD.");
        }

        int[] nums = new int[inputNum.length];
        for (int i=0; i<inputNum.length; i++){
            try {
            	nums[i] = Integer.parseInt(inputNum[i]);
                if(nums[i] < 0){
                	throw new NegativeNumberException("The input value cannot be negative for GCD.");
                }
            } catch (NumberFormatException e) {
                throw new MyNumberFormatException("The input value should be converted into a number. (" + inputNum[i] + " is not a number value for GCD.)");
            }
        }

        a = nums[nums.length-2];
        b = nums[nums.length-1];

        for(int i=nums.length-2; i>=0; i--){
            int gcd = computeGCD(a, nums[i]);
            a = gcd;
            b = nums[i+1];
        }
    }

    /**
     * This calls a method that calculates the GCD using the values a and b, and assigns the value to result. 
     */
    public void compute(){
        result = computeGCD(a, b);
    }

    /** 
     * This method calculates the GCD using the values passed in as parameters.
     */
    public int computeGCD(int a, int b){
        if (b==0){
            return a;
        }
        return computeGCD(b, a%b);
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
}
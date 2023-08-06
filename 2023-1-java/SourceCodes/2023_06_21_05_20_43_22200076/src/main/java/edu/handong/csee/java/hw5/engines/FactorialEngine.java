package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A factorial calculator.
 */
public class FactorialEngine implements Computable {
    private static final String engineName ="FACTORIAL";
    private int n;
    private double result;
    
    /**
     * Sets a number of factorial.
     * 
     * @param n a number of factorial
     */
    public void setN(int n){
        this.n = n;
    }

    /**
     * Gets the number of factorial.
     * 
     * @return the number of factorial
     */
    public int getN(){
        return n;
    }

    /**
     * Sets a result of calculating the factorial.
     * 
     * @param result a value of calculating the factorial
     */
    public void setResult(double result){
        this.result = result;
    }

    /**
     * Converts the input string into the int type for calculations.
     * 
     * @param input the input string
     */
    public void setInput(String[] input){
        if(input.length > 1){
        	try {
        		throw new OneInputException(engineName);
        	} catch(OneInputException e){
        		System.out.println(e.getMessage());
        		System.exit(0);
        	}
        }
       	try {
           	n = Integer.parseInt(input[0]);        	
        } catch(NumberFormatException e) {
        	e.printStackTrace();
       		try {
       			throw new MyNumberFormatException(engineName, input[0]);
       		} catch(MyNumberFormatException ex){
       			System.out.println(ex.getMessage());
       			System.exit(0);
       		}
        }
        if(n < 0){
        	try {
        		throw new NegativeNumberException(engineName);
        	} catch(NegativeNumberException e){
        		System.out.println(e.getMessage());
        		System.exit(0);
        	}
        }
    }

    /**
     * Calculates the factorial.
     */
    public void compute(){
        result = 1;
        for(int i=n; i>1; i--) {
            result *= i;
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
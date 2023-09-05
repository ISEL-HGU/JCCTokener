package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;


/**
 * A class that represents a SQRT engine.
 * This implements the Computable interface.
 */
public class SQRTEngine implements Computable {
    private static final String engineName ="SQRT";
    private double input ;
    private double result ; 

    /**
     * Sets the input of SQRT engine
     * @param num 
     */
    public void setInput(double num){
        this.input = num ; 
    }
    
    /**
     * Sets the result of SQRT engine
     * @param num 
     */
    public void setResult(double num){
        this.result = num ; 
    }
    
    /**
     * Sets the input for the computation.
     * @param args the input arguments for the computation
     * @throws OneInputException if the program is expecting one input but multiple inputs are provided
     * @throws NegativeNumberException if the input number is negative
     * @throws MyNumberFormatException if the input cannot be parsed into a valid number
     */
    public void setInput(String[] args) throws OneInputException, NegativeNumberException, MyNumberFormatException {
    	if(args.length > 1)
             throw new OneInputException(engineName) ;
    	 
    	double number ; 
        try {
        	number = Double.parseDouble(args[0]) ;
        } catch(NumberFormatException e) {
        	throw new MyNumberFormatException(engineName, args[0]);
        }
        if(number < 0)
         	throw new NegativeNumberException(engineName) ;
         
        this.input = number ;
    }
    
    /**
     * Returns the input of SQRT engines
     *
     * @return input
     */
    public double getinput(){
        return this.input ;
    }
    
    /**
     * Computes the sqrt of input based on the input.
     */
    public void compute(){
        this.result = Math.sqrt(input) ;
    }

    /**
     * Returns the result
     *
     * @return result
     */
    public double getResult(){
        return this.result ;
    }
}

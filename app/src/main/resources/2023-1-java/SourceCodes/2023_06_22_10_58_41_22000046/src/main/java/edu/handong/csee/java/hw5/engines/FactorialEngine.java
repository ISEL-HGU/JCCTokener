package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that represents a factorial engine.
 * This implements the Computable interface.
 */
public class FactorialEngine implements Computable {
    private static final String engineName ="FACTORIAL";
    private int n ; 
    private double result ; 

    /**
     * Sets n 
     * @param n 
     */
    public void setN(int num){
        this.n = num ;
    }
    
    /**
     * Sets the result 
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
    public void setInput(String[] args) throws OneInputException, NegativeNumberException, MyNumberFormatException{
        if(args.length > 1)
        	throw new OneInputException(engineName) ;
        
        int number ;
        
        try {number = Integer.parseInt(args[0]) ;}
        catch(NumberFormatException e) {throw new MyNumberFormatException(engineName, args[0]) ;}
       
        
        if(number < 0)
        	throw new NegativeNumberException(engineName) ;
        
        this.n = Integer.parseInt(args[0]) ;
    }
    
    /**
     * Returns n
     *
     * @return n
     */
    public double getN(){
        return this.n ; 
    }

    /**
     * Computes the n factorial based on the input.
     */
    public void compute() {

        double result = 1.0 ;
        
        for(int i = 1 ; i <= this.n ; i++){
            result = result * i ;  
        }

        this.result = result ; 
    }

    /**
     * Returns the result 'n factorial'
     *
     * @return result 
     */
    public double getResult(){
        return this.result ;
    }
    
}

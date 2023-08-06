package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that represents a fibonacci engine.
 * This implements the Computable interface.
 */
public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";
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
        try{ number = Integer.parseInt(args[0]) ;}
        catch(NumberFormatException e) {throw new MyNumberFormatException(engineName, args[0]) ;}
        if(number < 0)
        	throw new NegativeNumberException(engineName) ;
        
        this.n = number ;
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
     * Computes the fibonacci based on the input.
     */
    public void compute() {  

        	
        double[] numArray ; 
        numArray = new double[this.n] ;

        if(this.n < 2){
            this.result = 1 ;
        } else{
            numArray[0] = 1;
            numArray[1] = 1;

            for(int i = 2 ; i < n ; i++){
                numArray[i] = numArray[i-2] + numArray[i-1] ;
            }

            this.result = numArray[n-1] ;
        }
    }
    
    /**
     * Returns result
     *
     * @return result 
     */
    public double getResult(){
        return this.result ;
    }
}

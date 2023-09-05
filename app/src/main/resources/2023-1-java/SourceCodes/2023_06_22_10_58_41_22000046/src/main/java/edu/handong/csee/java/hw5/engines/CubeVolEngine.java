package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.* ;

/**
 * A class that represents a cube volume calculator engine.
 * This implements the Computable interface.
 */
public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";
    private double sideLength ; 
    private double volume ;

    /**
     * Sets the length of the cube's side.
     * @param num the length of the cube's side
     */
    public void setSideLength(double num){
        this.sideLength = num ;
    }

    /**
     * Sets the volume of the cube.
     * @param num the volume of the cube
     */
    public void setVolume(double num){
        this.volume = num ;
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
        try {number = Double.parseDouble(args[0]) ;} 
        catch(NumberFormatException e) {throw new MyNumberFormatException(engineName, args[1]); }
        
        
        if(number < 0.0)
        	throw new NegativeNumberException(engineName) ;
        
        this.sideLength = number ;
    }

    	
    /**
     * Returns the sideLength of the cube.
     *
     * @return the sideLength of the cube
     */
    public double getSideLength(){
        return this.sideLength ;
    }

    /**
     * Computes the volume of the cube based on the input.
     */
    public void compute() 
    { 
        double result = this.sideLength * this.sideLength * this.sideLength ;
        this.volume = result ;  
    }
    
    /**
     * Returns the volume of the cube.
     *
     * @return the volume of the cube
     */
    public double getResult(){
        return this.volume ;
    }
    
}

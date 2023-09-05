package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that represents a sphere volume engine.
 * This implements the Computable interface.
 */
public class SphereVolEngine implements Computable {
    private static final String engineName ="SPHEREVOL";
    private double radius ; 
    private double result ; 
    
    /**
     * Sets the radius of sphere
     * @param num 
     */
    public void setRadius(double num){
        this.radius = num ; 
    }
    
    /**
     * Sets the volume of sphere
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

    	 double number ; 
         try {
         	number = Double.parseDouble(args[0]) ;
         } catch(NumberFormatException e) {
         	throw new MyNumberFormatException(engineName, args[0]);
         }
         
         if(number < 0)
         	throw new NegativeNumberException(engineName) ;
         
        this.radius = number ;
    }
    
    /**
     * Returns the radius of sphere
     *
     * @return radius 
     */
    public double getRadius(){
        return this.radius ;
    }


    /**
     * Computes the volume of sphere based on the input.
     */
    public void compute(){
      
        this.result = ((double)4 / (double)3) * Math.PI * Math.pow(this.radius, 3) ; // 4/3도 type casting 
    }

    /**
     * Returns the volume of sphere
     *
     * @return result
     */
    public double getResult(){
        return this.result ;
    }

}

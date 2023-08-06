package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that represents a MIN (finding the smallest number among inputs) engine.
 * This implements the Computable interface.
 */
public class MinEngine implements Computable {
    private static final String engineName ="MIN";
    private double[] input ;
    private double result ; 
    
    /**
     * Sets the array of input
     * @param double[] arr 
     */
    public void setA(double[] arr){
        this.input = arr ;
    }
    
    /**
     * Sets the result 
     * @param num 
     */
    public void setResult(int num){
        this.result = num ; 
    }

    /**
     * Sets the input for the computation.
     * @param args the input arguments for the computation
     * @throws MinimumInputNumberException if the program is expecting more than one input but only one input is provided
     * @throws MyNumberFormatException if the input cannot be parsed into a valid number
     */
    public void setInput(String[] args) throws MinimumInputNumberException, MyNumberFormatException {
    	
    	if(args.length < 2)
    		throw new MinimumInputNumberException(engineName) ;
    	
        this.input = new double[args.length] ;
        
        for (int i = 0; i < args.length; i++) {
            double value ;
            try {
            	value = Double.parseDouble(args[i]); // i+1번째 값을 double 타입으로 변경}
            } catch (NumberFormatException e) {
            	throw new MyNumberFormatException(engineName, args[i]) ;
            }
            this.input[i] = value; // input 배열의 i번째에 값을 할당
        }
    }
    
    /**
     * Returns array of input
     *
     * @return double[] input
     */
    public double[] getInput(){
        return this.input ;
    }
    
    /**
     * Computes the MIN based on the input.
     */
    public void compute(){

        double min = this.input[0] ;

        for(double num : this.input){
            if(num < this.result)
                min = num ;
        }

        this.result = min ; 
    }
    
    /**
     * Returns the result MAX among inputs
     *
     * @return results 
     */
    public double getResult(){
        return this.result ;
    }
    
}
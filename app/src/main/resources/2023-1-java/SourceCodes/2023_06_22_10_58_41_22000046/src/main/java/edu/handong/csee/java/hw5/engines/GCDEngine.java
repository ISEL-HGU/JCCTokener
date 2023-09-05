package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that represents a GCD(Greatest Common Divisor) engine.
 * This implements the Computable interface.
 */
public class GCDEngine implements Computable {
    private static final String engineName ="GCD";
    private int[] a ; 
    private int result ; 
    
    /**
     * Sets the array of input
     * @param int[] arr 
     */
    public void setA(int[] arr){
        this.a = arr ;
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
     * @throws NegativeNumberException if the input number is negative
     * @throws MyNumberFormatException if the input cannot be parsed into a valid number
     */
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException{
        
    	if(args.length < 2)
    		throw new MinimumInputNumberException(engineName) ;
    	
        this.a = new int[args.length] ;
        
        for (int i = 0; i < args.length ; i++) {
            int value ;
            try{value = Integer.parseInt(args[i]); }
            catch(NumberFormatException e) {throw new MyNumberFormatException(engineName, args[i]) ;}
            if(value < 0)
            	throw new NegativeNumberException(engineName) ;
            
            this.a[i] = value; // input 배열의 i번째에 값을 할당
        }
    }
    
    /**
     * Returns array of input
     *
     * @return int[] a
     */
    public int[] getA(){
        return this.a ;
    }
    
    /**
     * Computes the GCD based on the input.
     */
    public void compute(){
        
        int gcd = a[0];

        for (int i = 1; i < a.length; i++) {
            int b = a[i];
            while (b > 0) {
                int temp = b;
                b = gcd % b;
                gcd = temp;
            }
        }

        this.result = gcd ; 
    }

    /**
     * Returns the result GCD among inputs
     *
     * @return results 
     */
    public double getResult(){
        return this.result ;
    }
    
}

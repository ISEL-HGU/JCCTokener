package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that computes the nth Fibonacci number, where n is a non-negative integer.
 */
public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";
    private int n;
    private double result;

    /**
     * Computes the nth Fibonacci number and sets the result.
     * The value of n is specified by calling the setInput method.
     */
    public void compute() throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        int n1=0,n2=1,n3=0,i;    
        if(this.n == 0){
            this.result = n1;
        }
        else if(this.n == 1){
            this.result = n2;
        }
        else{
            for(i=2;i<this.n+1;++i) 
            {    
            n3=n1+n2;     
            n1=n2;    
            n2=n3;    
            }
            this.result = (double)n3;
        }
    }

    /**
     * Sets the input value for computing the nth Fibonacci number.
     * The input value must be a non-negative integer.
     * @param args the input arguments, where args[1] is the input value
     */
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        try{
            if(Integer.parseInt(args[1]) < 0){
            throw new NegativeNumberException(engineName);
            }
            else if(args.length > 2){
                throw new OneInputException(engineName);       
            }
            else{ 
                
                this.n = Integer.parseInt(args[1]);
            }
        }catch (NumberFormatException e) {
            throw new MyNumberFormatException(engineName, args[1]);
        }
    }

    /**
     * Returns the result of computing the nth Fibonacci number.
     * @return the computed result
     */
    public double getResult(){
        return this.result;
    }
    
    /**
     * Returns the value of n that was used to compute the Fibonacci number.
     * @return the value of n
     */
    public int getN() {
        return this.n;
    }
    
    /**
     * Sets the value of n for computing the Fibonacci number.
     * @param args the input arguments, where args[1] is the value of n
     */
    public void setN(String[] args) {
        this.n = Integer.parseInt(args[1]);
    }
    
    /**
     * Sets the result of computing the nth Fibonacci number.
     * @param result the computed result to set
     */
    public void setResult(double result) {
        this.result = result;
    }
}

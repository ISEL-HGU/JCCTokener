package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This code calculates the Fibonacci of a number
 */
public class FibonacciEngine implements Computable {
    /** 
     * sets the engine name as FIBONACCI
     */
    private static final String engineName = "FIBONACCI";
    /** 
     * n for the calculating FIBONACCI
     */
    private int n;
    /** 
     * returning result 
     */
    private double result;
    
    /** 
     * Read the argument and see if there is error 
     */
    public void setInput(String[] args) {
        try {
            int SubFirstArg = args.length - 1;
            if (SubFirstArg != 0) {
                throw new OneInputException(engineName);
            } else {
                n = Integer.parseInt(args[0]);
                if (n < 0) {
                    throw new NegativeNumberException(engineName);
                }
            }
        } catch (OneInputException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    /**
     * computing the result by recursive adding
     */
    public void compute(){
        if(n == 0){
            result = 0;
            return;
        }
        if(n == 1){
            result = 1;
            return;
        }
        double[] fib = new double[n+1];
        fib[0] = 0;
        fib[1] = 1;
        for(int i = 2; i <= n; i++){
            fib[i] = fib[i-1] + fib[i-2];
        }
        result = fib[n];
    }
    /**
     * Set the input array.
     * 
     * @param input array containing the input numbers
     */
    public void setInputArray(int input) {
        this.n = input;
    }

    /**
     * Get the input array.
     * 
     * @return input array containing the input numbers
     */
    public int getInputArray() {
        return n;
    }
    /**
     * returning the result for printing
     */
    public double getResult(){
        return result;
    }
}
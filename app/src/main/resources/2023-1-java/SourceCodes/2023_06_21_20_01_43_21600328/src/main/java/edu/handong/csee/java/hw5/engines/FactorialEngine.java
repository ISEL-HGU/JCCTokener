package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This code calculates the Factorial of a number
 */
public class FactorialEngine implements Computable {
    /** 
     * sets the engine name as FACTORRIAL
     */
    private static final String engineName ="FACTORIAL";
    /** 
     * n for the calculating FACTORIAL
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
     * computing the result by multiplying until n
     */
    public void compute(){
        result = 1;
        for(int i = 1; i <= n; i++){
            result *= i;
        }
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

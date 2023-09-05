package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;
import java.lang.String;

/**
A class that computes the Least Common Multiple (LCM) of integers.
It implements the Computable interface.
*/
public class LCMEngine implements Computable{
    private static final String engineName ="LCM";
    private int[] a;
    private int result;

    /**
     * Computes the LCM of given integers.
     */
    public void compute() throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        if (this.a.length == 1) {
            this.result = this.a[0];
            return;
        }

        int lcm = this.a[0];
        for (int i = 1; i < this.a.length; i++) {
            lcm = lcm(lcm, this.a[i]);
        }
        this.result = lcm;
    }
    /**
     * Computes the lcm of two integers.
     * @param a an integer
     * @param b an integer
     * @return the lcm of a and b
     */
    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
    /**
     * Computes the GCD of two integers.
     * @param a an integer
     * @param b an integer
     * @return the GCD of a and b
     */
    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
    
    /**
     * Sets the input values for the engine.
     * @param args An array of strings that contains the inputs.
     */
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        this.a = new int[args.length - 1];
        if(args.length < 3){
            throw new MinimumInputNumberException(2, engineName);
        }
        for (int i = 1; i < args.length; i++) {
            try {
                this.a[i-1] = Integer.parseInt(args[i]);
                if(this.a[i-1] < 0){
                    throw new NegativeNumberException(engineName);
                }
            } catch (NumberFormatException e) {
                throw new MyNumberFormatException(engineName, args[i]);
            }
        }
    }

     /**
     * Returns the computed result.
     * @return the GCD of the input values
     */
    public double getResult(){
        return this.result;
    }

    /**
     * Returns the input values.
     * @return an array of input values
     */
    public int[] getA(){
        return this.a;
    }

    /**
     * Sets the input values.
     * @param a an array of input values
     */
    public void setA(int[] a){
        this.a = a;
    }

    /**
     * Returns the computed result.
     * @return the GCD of the input values
     */
    public int getResultValue(){
        return this.result;
    }

    /**
     * Sets the computed result.
     * @param result the computed result
     */
    public void setResultValue(int result){
        this.result = result;
    }
}

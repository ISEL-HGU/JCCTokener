package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that computes the greatest common divisor (GCD) of given integers.
 */
public class GCDEngine implements Computable {
    private static final String engineName ="GCD";
    private int[] a;
    private int result;

    /**
     * Computes the GCD of the given integers.
     */
    public void compute() throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        if (this.a.length == 1) {
            this.result = this.a[0];
            return;
        }

        int gcd = this.a[0];
        for (int i = 1; i < this.a.length; i++) {
            gcd = gcd(gcd, this.a[i]);
        }
        this.result = gcd;
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
     * Sets the input values to compute the GCD.
     * @param args an array of input values
     */
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        this.a = new int[args.length - 1];
        if(args.length < 3){
            throw new MinimumInputNumberException(2, engineName);
        }
        for (int i = 1; i < args.length; i++) {
            if(Integer.parseInt(args[i]) < 0){
                throw new NegativeNumberException(engineName);
            }
            else{ 
                this.a[i-1] = Integer.parseInt(args[i]);
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

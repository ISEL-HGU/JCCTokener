package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
* A class that implements the Computable interface to compute the factorial of a number.
*/
public class FactorialEngine implements Computable {
    private static final String engineName = "FACTORIAL";
    private int n;
    private double result;

    /**
    * A class that implements the Computable interface to compute the factorial of a number.
    */
    public void compute() throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        int i, fact = 1;
        for (i = 1; i <= this.n; i++) {
            fact = fact * i;
        }
        this.result = fact;
    }
    
    /**
     * Sets the input value for the computation.
     * @param args an array of input arguments for the computation.
     */
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        try{
             if (Integer.parseInt(args[1]) < 0) {
                throw new NegativeNumberException(engineName);
            } else if (args.length > 2) {
                throw new OneInputException(engineName);       
            } else {
                this.n = Integer.parseInt(args[1]);

            }
        }catch (NumberFormatException e) {
            throw new MyNumberFormatException(engineName, args[1]);
        }
    }

    /**
     * Returns the result of the computation.
     *
     * @return the result of the computation.
     */
    public double getResult() {
        return this.result;
    }

    /**
     * Returns the value of the input number.
     *
     * @return the input number for the computation.
     */
    public int getN() {
        return this.n;
    }

    /**
     * Sets the value of the input number.
     * @param n the input number for the computation.
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Sets the value of the computation result.
     *
     * @param result the result of the computation.
     */
    public void setResult(double result) {
        this.result = result;
    }
}

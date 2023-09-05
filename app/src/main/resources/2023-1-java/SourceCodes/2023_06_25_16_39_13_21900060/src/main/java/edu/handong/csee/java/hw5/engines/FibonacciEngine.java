package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
* A class that computes the nth number in the Fibonacci sequence.
* Implements the Computable interface.
*/
public class FibonacciEngine implements Computable {
    private static final String engineName = "FIBONACCI";
    private int n;
    private double result;

    /**
     * Getter for the enginen name.
     * @return String of engine name
     */
    public String getEngineName(){
        return engineName;
    }
    
    /**
     * Getter for n
     * @return the the integer type of n stored in private field
     */
    public int getN() {
        return this.n;
    }

    /**
     * Setter for n
     * @param n the number for calculate fibonacci
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Setter for result
     * @param result 
     */
    public void setResult(double result) {
        this.result = result;
    }



    /**
    * Checks the input arguments for correctness.
    * @param args an array of String arguments
     * @throws OneInputException 
     * @throws NegativeNumberException 
    * @throws IllegalArgumentException if there are more than 2 arguments or the input value is negative.
    */
    @Override
    public void checkInput(String[] args) throws OneInputException, NegativeNumberException, MyNumberFormatException{
    	String inputString = args[1];
        if (args.length > 2) throw new OneInputException("You need one input value for "+engineName+".");
        
        try {
        	int inputNumber = Integer.parseInt(inputString);
        	if (inputNumber < 0) throw new NegativeNumberException("The input value cannot be negative for "+engineName+".");        	
        }catch(NumberFormatException nfe) {
        	throw new MyNumberFormatException("The input value should be converted into a number. ("+ inputString + " is not a number value for "+engineName+".)");
        }
    }

    /**
    * Sets the input value from the command line arguments.
    * @param args an array of String arguments
    */
    @Override
    public void setInput(String[] args){
        n = Integer.parseInt(args[1]);
    }

    /**
    * Computes the nth number in the Fibonacci sequence.
    * The result is stored in the instance variable 'result'.
    */
    @Override
    public void compute(){
        int[] fibonacci = new int[n+1];

        if (n == 1) result = 1.0;
        else {
            fibonacci[0] = 0; // The first Fibonacci number is always 0
            fibonacci[1] = 1; // The second Fibonacci number is always 1
    
            for (int i = 2; i <= n; i++) {
                fibonacci[i] = fibonacci[i-1] + fibonacci[i-2]; // Compute the next Fibonacci number
            }
            result = (double)fibonacci[n];
        }
    }

    /**
    * Returns the result of the computation.
    * @return the nth Fibonacci number as a double.
    */
    @Override
    public double getResult(){
        return result;
    }
}

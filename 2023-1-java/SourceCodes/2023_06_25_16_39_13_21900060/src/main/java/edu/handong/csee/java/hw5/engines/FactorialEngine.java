package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;


/**
* A class that computes the factorial of a given integer.
* Implements the Computable interface.
*/
public class FactorialEngine implements Computable {
    private static final String engineName = "FACTORIAL";
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
     * @return the integer type of n
     */
    public int getN() {
        return n;
    }

    /**
     * Setter for n
     * @param n
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Setter for result which gets double type of parameter
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
    * Computes the factorial of the input integer.
    * The result is stored in the instance variable 'result'.
    */
    @Override
    public void compute(){
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        result = (double)(factorial);
    }

    /**
    * Returns the result of the computation.
    * @return the factorial of the input integer as a double.
    */
    @Override
    public double getResult(){
        return result;
    }
}


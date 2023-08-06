package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
/**
* GCDEngine is a class that implements the Computable interface to calculate the greatest common divisor (GCD) of multiple positive integers.
*/
public class GCDEngine implements Computable {
    private static final String engineName ="GCD";
    private int a[];
    private int result;

    /**
     * Getter for the enginen name.
     * @return String of engine name
     */
    public String getEngineName(){
        return engineName;
    }
    /**
     * Getter for the array of a which stores the number for calculation of GCD
     * @return the integer type of number array
     */
	public int[] getA() {
		return a;
	}

    /**
     * Setter for the array of a which stores the number for calculation of GCD
     * @param the interger type of number array
     */
	public void setA(int a[]) {
		this.a = a;
	}

    /**
     * Getter for the calculation of result
     * @param result
     */
	public void setResult(int result) {
		this.result = result;
	}


    /**
     * Checks if the input is valid.
     * 
     * @param args an array of strings representing the input arguments.
     * @throws MinimumInputNumberException, NegativeNumberException 
     * 
     * @throws IllegalArgumentException if the input is invalid.
     */
    @Override
    public void checkInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException{
    	String inputString = null;
    	if (args.length < 3) throw new MinimumInputNumberException("You need at least 2 input values for " + engineName + ".");
    	try {
    		for(int i=1; i< args.length; i++){
			inputString = args[i];
			int inputNumber = Integer.parseInt(inputString);    		
			if(inputNumber < 0) throw new NegativeNumberException("The input value cannot be negative for "+engineName+".");
    		}    	      	  
    	}catch(NumberFormatException nfe) {
    		throw new MyNumberFormatException("The input value should be converted into a number. ("+ inputString + " is not a number value for "+engineName+".)");
    	}
    }

    /**
     * Sets the input for the computation.
     * 
     * @param args an array of strings representing the input arguments.
     */
    @Override
    public void setInput(String[] args){
        this.a = new int[args.length - 1];
        for(int i=0; i < args.length-1; i++){
            this.a[i] = Integer.parseInt(args[i+1]);
        }
    } 

    /**
     * Calculates the GCD of the input values using the Euclidean algorithm.
     */
    @Override
    public void compute(){
        int gcd = a[0];
        for (int i = 1; i < a.length; i++) {
            gcd = gcd(gcd, a[i]);
        }

        result = gcd;
    }

    // private: Helper method to calculate the GCD of two numbers using Euclid's algorithm.
    private static int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a%b);
    }

    
    /**
     * Returns the result of the computation.
     * 
     * @return the GCD of the input values.
     */
    @Override
    public double getResult(){
        return (double)result;
    }
}

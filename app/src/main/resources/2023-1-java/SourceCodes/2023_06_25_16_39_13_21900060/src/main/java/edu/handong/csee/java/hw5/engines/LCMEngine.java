package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
* The LCMEngine class implements the Computable interface and provides a way to compute the LCM of multiple input numbers.
*
* It uses Euclid's algorithm to compute the GCD of two numbers, and then calculates the LCM using the formula a*b/GCD(a,b).
*/
public class LCMEngine implements Computable{
    private static final String engineName ="LCM";
    private int a[];
    private int result;

    /**
     * Getter for the engine name.
     * @return String of engine name
     */
    public String getEngineName(){
      return engineName;
    }

    /**
     * Getter for the array which stores the number for calculation of LCM
     * @return the integer type of number array
     */    
    public int[] getA() {
      return a;
    }
    
    /**
     * Setter for the array of a which stores the number for calculation of LCM
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
    * This method checks the validity of the input arguments.
    * If the number of input arguments is less than 3, it prints an error message and exits the program.
    * If any input number is negative, it prints an error message and exits the program.
    * @param args an array of input arguments
     * @throws MinimumInputNumberException 
     * @throws NegativeNumberException 
    */
    @Override
    public void checkInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException{
    	String inputString = null;
    	if (args.length < 3) throw new MinimumInputNumberException("You need at least 2 input values for "+engineName+".");
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
    * This method sets the input values into the instance variable a.
    * @param args an array of input arguments
    */
    @Override
    public void setInput(String[] args){
      this.a = new int[args.length - 1];
        for(int i=0; i < args.length-1; i++){
            this.a[i] = Integer.parseInt(args[i+1]);
        }
    }

    /**
    * This method computes the LCM of the input values using Euclid's algorithm for GCD.
    * It first computes the GCD of the first two input values, and then computes the LCM using the formula a*b/GCD(a,b).
    * It iterates over the remaining input values and computes the LCM for each value.
    * The final LCM value is stored in the instance variable result.
    */
    @Override
    public void compute(){
      int lcm = a[0];
      for (int i = 1; i < a.length; i++) {
        lcm = lcm(lcm, a[i]);
      }
      result = lcm;
    }

    // private: Helper method to calculate the LCM of two numbers using Euclid's algorithm
    private static int lcm(int a, int b) {
      return (a * b) / gcd(a, b);
    }

    // private: Helper method to calculate the GCD of two numbers using Euclid's algorithm
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
    * This method returns the computed LCM as a double value.
    * @return the computed LCM as a double value
    */
    @Override
    public double getResult(){
      return (double)result;
    }
}

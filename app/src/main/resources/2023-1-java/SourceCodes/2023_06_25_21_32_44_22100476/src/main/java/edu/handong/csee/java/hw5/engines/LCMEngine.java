package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**

This class is a concrete implementation of the Computable interface.
It computes the least common multiple (LCM) of integer inputs provided by the user.
It takes the input values as command-line arguments and checks if the number of input values is at least 2.
If the number of input values is less than 2, the program prints an error message and terminates.
If the input values are negative, the program prints an error message and terminates.
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
*/
public class LCMEngine implements Computable{
    private String engineName ="LCM";
    private int[] a ;
    private double result;

    /**
 * Sets the input values for this LCMEngine object.
 * It takes a String array containing the input values as command-line arguments.
 * If the number of input values is less than 2, it prints an error message and terminates.
 * If the input values are negative, it prints an error message and terminates.
 *
 * @param args A String array containing the input values as command-line arguments.
 */
    public void setInput(String[] args){
    	try {   
    		
    		if (args.length < 2) {
	            // handle error case where not enough input values were provided
    			throw new MinimumInputNumberException(engineName);
    		}
	     }catch(MinimumInputNumberException e){
	    	 System.out.println(e.getMessage()); // print an error message if the input is invalid
	    	 System.exit(0);
	    	 return;
	     }
        a = new int[args.length];
        for(int i = 0; i < args.length; i++){
        	try {
                int inputValue = Integer.parseInt(args[i]);

                if (inputValue < 0) {
                    
                	throw new NegativeNumberException(engineName);
                    // handle error case where input value is negative
                }
                else {
                    a[i] = inputValue;

                }
            }catch(MyNumberFormatException e){
                System.out.println("Exception-05: The input value should be converted into an integer.");
   	    	 System.exit(0);
   	    	 return;
            } 
        	catch(NegativeNumberException e){
                System.out.println(e.getMessage());
   	    	 System.exit(0);
   	    	 return;
            } 
        	catch(Exception e){
            	System.out.println(e.getMessage()); // print an error message if the input is invalid
   	    	 System.exit(0);
   	    	 return;
        	}
            

        }
        
    }
    
/**
 * Computes the greatest common divisor (GCD) of two integer inputs.
 * It uses the Euclidean algorithm to compute the GCD.
 *
 * @param a An integer input.
 * @param b An integer input.
 * @return The GCD of a and b.
 */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
/**
 * 
 * @return
 */
    public int[] getA(){
        return a;
    }


    /**
 * Computes the least common multiple (LCM) of the input values.
 * It computes the LCM by calling the gcd() method and using the formula LCM(a, b) = (a * b) / gcd(a, b).
 * The result is stored in the result field.
 */
    public void compute(){
        
        int lcm = a[0];
        for (int i = 1; i < a.length; i++) {
            lcm = (lcm * a[i]) / gcd(lcm, a[i]);
        }
        result = lcm;

    }

    /**
 * Sets the result of the computation.
 *
 * @param a The result of the computation.
 */
    public void setResult(double a){
        result = a;
    }

    
/**
 * Gets the result of the computation.
 *
 * @return The result of the computation.
 */
    public double getResult(){
        return result;
    }

    /**
 * Sets the name of the engine.
 *
 * @param a The name of the engine.
 */
    public void setEngineName(String a){
        engineName = a;
    }

    /**
 * Gets the name of the engine.
 *
 * @return The name of the engine.
 */
    public String getEngineName(){
        return engineName;
    }
}

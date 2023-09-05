package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**

This class is an implementation of the Computable interface that calculates the Greatest Common Divisor (GCD)
of a set of input values using Euclid's algorithm.
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
*/
public class GCDEngine implements Computable {
    private String engineName ="GCD";
    private int[] a;
    private double result;


    /**
 * This method receives the user input values as string array, validates the inputs and sets them for the computation.
 * If the number of inputs are less than two, it will display an error message and exit.
 * If the input value is negative, it will display an error message and exit.
 * @param args the input values as string array
 */
    public void setInput(String[] args){
    	try {   
    		if (args.length < 2) {
	            // handle error case where not enough input values were provided
    			throw new MinimumInputNumberException(engineName);
	        }
	     }catch(Exception e){
	    	 System.out.println(e.getMessage()); // print an error message if the input is invalid
	    	 System.exit(0);
;
	     }
        a = new int[args.length];

        for(int i = 0; i < args.length; i++){
            try {
                if (Integer.parseInt(args[0]) < 0) {
                    
                	throw new NegativeNumberException(engineName);
                    // handle error case where input value is negative
                }
                else {
                    a[i] = Integer.parseInt(args[i]);

                }
            }catch(NegativeNumberException e){
                System.out.println(e.getMessage());
   	    	 System.exit(0);
   	    	 return;
            } 
            catch(Exception e){
            	System.out.println(e.getMessage()); // print an error message if the input is invalid
   	    	 System.exit(0);
            }
           

        }

    }

    
/**
 * This method returns the input values as an integer array.
 * @return an integer array of input values
 */
    public int[] getA(){
        return a;
    }


    /**
 * This method computes the GCD of the input values by calling the gcd() method.
 */
    public void compute(){
        result = (double)a[0];

        // find the GCD of all input values using Euclid's algorithm
        for (int i=1; i<a.length; i++) {
            result = gcd(result, (double)a[i]);
        }
    }

    /**
 * This is a private helper method that takes two double values as parameters and recursively computes the GCD
 * using Euclid's algorithm until it returns the final GCD as a double.
 * @param a a double value
 * @param b a double value
 * @return the GCD of the two double values as a double
 */
    private double gcd(double a, double b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    /**
 * This method returns the computed GCD value.
 * @return the GCD value as a double
 */
    public double getResult(){
        return result;
    }
    /**
 * This method sets the computed result as the input parameter.
 * @param a the computed result as a double value
 */
    public void setResult(double a){
        result = a;
    }

    /**
 * This method sets the engine name as the input parameter.
 * @param a the engine name as a String value
 */
    public void setEngineName(String a){
        engineName = a;
    }

    /**
 * This method returns the engine name as a String value.
 * @return the engine name as a String value
 */

    public String getEngineName(){
        return engineName;
    }

}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**

This class represents a Fibonacci engine that implements the Computable interface.
It computes the nth number in the Fibonacci sequence where the 0th number is 0, the 1st number is 1,
and each subsequent number is the sum of the two preceding ones.
The engine takes an integer n as an input, and returns the result of the computation as a double.
If n is negative, the engine will print an error message and exit.
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
*/
public class FibonacciEngine implements Computable {
    private String engineName ="FIBONACCI";
    private int n;
    private double result;

    /**
 * This method sets the input value for the engine.
 * If the number of input arguments is not equal to 2, an error message is printed and the program exits.
 * If the input value is negative, an error message is printed and the program exits.
 * 
 * @param args An array of input arguments. args[1] contains the input value for the engine.
 */
    public void setInput(String[] args){
    	try {
        	if (args.length != 1) {
                
                // handle error case where no input value was providedX
        		throw new OneInputException(engineName);
            }
        	if(!args[0].matches("[-+]?\\d*\\.?\\d+")) {
        		throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + args[1] + " is not a number value for FIBONACCI.)");
            	
        	}
            if (Integer.parseInt(args[0]) < 0) {
            	throw new NegativeNumberException(engineName);
                // handle error case where input value is negative
            }
        }catch(NegativeNumberException e){
            System.out.println(e.getMessage());
	    	 System.exit(0);
	    	 return;
        }  catch(Exception e){
        	System.out.println(e.getMessage()); // print an error message if the input is invalid
	    	 System.exit(0);
;
        }

        n = Integer.parseInt(args[0]);
    }

    /**
 * This method computes the nth number in the Fibonacci sequence.
 * If n is 0, the result is 0.
 * If n is 1, the result is 1.
 * Otherwise, the result is the sum of the two preceding numbers in the sequence.
 */
    public void compute() {
        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            double fibNMinus2 = 0;
            double fibNMinus1 = 1;
            for (int i = 2; i <= n; i++) {
                result = fibNMinus1 + fibNMinus2;
                fibNMinus2 = fibNMinus1;
                fibNMinus1 = result;
            }
        }
    }
    
    /**
 * This method returns the value of n used as input for the engine.
 * 
 * @return An integer representing the value of n.
 */
    public int getN(){
        return n;
    }

    /**
 * This method sets the result of the computation for the engine.
 * 
 * @param a A double representing the result of the computation.
 */
    public void setResult(double a){
        result = a;
    }

    
/**
 * This method returns the result of the computation for the engine.
 * 
 * @return A double representing the result of the computation.
 */
    public double getResult() {
        return result;
    }

    
/**
 * This method sets the name of the engine.
 * 
    * @param a A string representing the name of the engine.
    */
    public void setEngineName(String a){
        engineName = a;
    }
    
    
/**
 * This method returns the name of the engine.
 * 
 * * @param a A string representing the enginename.
 */
    public String getEngineName(){
        return engineName;
    }
}

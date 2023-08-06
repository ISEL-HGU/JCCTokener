package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**

A class to represent a FactorialEngine that calculates the factorial of a given integer input.
This class implements the Computable interface.
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
*/

public class FactorialEngine implements Computable {
    private String engineName ="FACTORIAL";
    private int n;
    private double result;

    /**
 * Sets the input for the factorial computation.
 * This method takes an array of strings and extracts the second value from it, which is an integer input.
 * If the input is less than zero, an error message will be printed and the program will exit.
 * @param args an array of strings that contains the input value as the second element.
 */
    public void setInput(String[] args){
    	try {
        	if (args.length != 1) {
                
                // handle error case where no input value was providedX
        		throw new OneInputException(engineName);
            }
        	if(!args[0].matches("[-+]?\\d*\\.?\\d+")) {
        		throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + args[1] + " is not a number value for FACTORIAL.)");
            	
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
 * Computes the factorial of the input value.
 * This method iteratively computes the factorial of the input value and stores the result in a class field.
 */
    public void compute(){
        result = 1;
        for (double i = 2; i <= n; i++) {
            result *= i;
        }
    }
    /**
 * Gets the input value used for the factorial computation.
 * @return the input value as an integer.
 */
    public int getN(){
        return n;
    }

    /**
 * Sets the result of the factorial computation.
 * @param a the result of the factorial computation.
 */
    public void setResult(double a){
        result = a;
    }
    /**
 * Gets the result of the factorial computation.
 * @return the result of the factorial computation.
 */
    public double getResult(){
        return result;
    }

    /**
 * Sets the name of the engine.
 * @param a the name of the engine.
 */
    public void setEngineName(String a){
        engineName = a;
    }

    /**
 * Gets the name of the engine.
 * @return the name of the engine.
 */
    public String getEngineName(){
        return engineName;
    }
}

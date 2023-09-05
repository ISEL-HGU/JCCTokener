package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**

This class represents an engine that computes the square root of a given number.
It implements the Computable interface and defines the necessary methods required by the interface, namely setInput(), compute(), getInputs(), getResult(), setResult(), setEngineName() and getEngineName().
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
 */
public class SQRTEngine implements Computable {
    private String engineName ="SQRT";
    private double input;
    private double result;

    /**
 * Sets the input value for the engine. The input value is provided as a string array and is parsed and stored as a double value in the input variable.
 * If the number of input values is not 2, an error message is printed and the program exits.
 * If the input value is negative, an error message is printed and the program exits.
 * @param args a string array containing the input value provided to the engine
 */
    public void setInput(String[] args){
    	try {
        	if (args.length != 1) {
                
                // handle error case where no input value was providedX
        		throw new OneInputException(engineName);
        	}
        	if(!args[0].matches("[-+]?\\d*\\.?\\d+")) {
        		throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + args[1] + " is not a number value for SQRT.)");
            	
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

        input = Double.parseDouble(args[0]);
    }

    /**
 * Computes the square root of the input value and stores the result in the result variable.
 */
    public void compute(){
        result = Math.sqrt(input);
    }
    
/**
 * Sets the result value of the computation.
 * @param a the value to be set as the result of the computation
 */
    public void setResult(double a){
        result = a;
    }

/**
 * Returns the input value of the computation.
 * @return the input value of the computation
 */
    public double getInput(){
        return input;
    }


/**
 * Returns the result value of the computation.
 * @return the result value of the computation
 */
    public double getResult(){
        return result;
    }

    
/**
 * Sets the name of the engine.
 * @param a the name to be set as the name of the engine
 */
    public void setEngineName(String a){
        engineName = a;
    }

/**
 * Returns the name of the engine.
 * @return the name of the engine
 */
    public String getEngineName(){
        return engineName;
    }
}

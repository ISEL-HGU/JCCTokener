
package edu.handong.csee.java.hw5.thread.clioptions.engines;

import edu.handong.csee.java.hw5.thread.clioptions.exceptions.*;

/**
*
* The public SQRTEngine class implements the Computable interface.
* It computes the square root of a number.
*/

public class SQRTEngine implements Computable {
/**
 * Returns the engine name as a string.
 * @return the engine name
 */
public static String getEngineName() {
    return engineName;
}

private static final String engineName ="SQRT";
private double input;
private double result;

/**
 * Returns the input value.
 * @return the input value
 */
public double getInput() {
    return input;
}

/**
 * Sets the input value as a double.
 * If number of inputs is not 1 then return required inputs error and exit
 * If input is negative then return negative input error and exit
 * @param input an array of strings containing the input value
 * @throws OneInputException if the input array length is not 1
 * @throws MyNumberFormatException if the input value cannot be parsed as a double
 * @throws NegativeNumberException if the input is negative
 */
public void setInput(String[] input){
	try {
	    int len = input.length - 1;
	    try {
		    this.input = Double.parseDouble(input[1]);
	    }
        catch(NumberFormatException e) {
    		throw new MyNumberFormatException(getEngineName(),input[1]);
    	}

	    if(getInput() < 0) {
	        throw new NegativeNumberException(getEngineName());
	    }
	    if ( len != 1) {
	    	throw new OneInputException(getEngineName());
	    }
	    
	}
	catch(OneInputException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
	catch(NegativeNumberException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
	catch(MyNumberFormatException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}

}

/**
 * Sets the result value as a double.
 * @param result the result value to set
 */
public void setResult(double result) {
    this.result = result;
}

/**
 * Computes the square root of the input value and sets the result value.
 */
public void compute(){
    setResult(Math.sqrt(getInput()));
}

/**
 * Returns the result value.
 * @return the result value
 */
public double getResult(){
    return result;
}

}

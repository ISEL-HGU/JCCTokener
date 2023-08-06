package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import java.lang.Math;

/**
*This class implements the Computable interface to calculate the square root of a given input value.
*The class receives input value as an array of String, checks the validity of input value, and
*calculates the square root of the input value using the Math.sqrt() method.
*The result is then stored in a private field 'result'.
*/

public class SQRTEngine implements Computable {
    private static final String engineName ="SQRT";
/**
*Returns the name of the engine.
*@return the name of the engine as a String.
*/
    public static String getEnginename() {
        return engineName;
    }
    private double input; // input value
private double result; // result after calculation

/**
 * Get the input value
 * @return input value
 */
public double getInput() {
    return this.input;
}

/**
 * Set the input value
 * @param input input value to be set
 */
public void setInput(double input) {
    this.input = input;
}

/**
 * Set the result value
 * @param result result value to be set
 */
public void setResult(double result) {
    this.result = result;
}

/**
 * Set the input value by parsing the input array of Strings
 * If the number of arguments is not 2, print an error message and exit the program.
 * If the input value is negative, print an error message and exit the program.
 * @param args array of Strings containing input value
 */
public void setInput(String[] args) {
    try {
		if(args.length != 1) {
			throw new OneInputException(engineName);
		}

		try {
			input = Double.parseDouble(args[0]);
		} catch(NumberFormatException e) {
			throw new MyNumberFormatException(args[0], engineName);
		}

		if(input < 0) {
			throw new NegativeNumberException(engineName);
		}
	} catch(OneInputException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	} catch(NegativeNumberException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	} catch(MyNumberFormatException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
}

/**
 * Calculate the square root of the input value and store the result in the 'result' field.
 */
public void compute() {
    result = Math.sqrt(input);
}

/**
 * Get the result of calculation
 * @return result of calculation
 */
public double getResult() {
    return result;
}


}

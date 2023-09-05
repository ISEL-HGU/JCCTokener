package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
*FactorialEngine class implements Computable interface
*Computes the factorial of a given number and returns the result
*/
public class FactorialEngine implements Computable {
    private static final String engineName ="FACTORIAL";
/**
 * Returns the name of the engine
 * @return the engine name
 */
public static String getEnginename() {
    return engineName;
}

// instance variables
private int n;
private double result;

/**
 * Returns the input value of n
 * @return the value of n
 */
public int getN() {
    return this.n;
}

/**
 * Sets the input value of n
 * @param n the input value of n
 */
public void setN(int n) {
    this.n = n;
}

/**
 * Sets the result of the computation
 * @param result the result of the computation
 */
public void setResult(double result) {
    this.result = result;
}

/**
 * Sets the input for the computation
 * @param args the input arguments
 */
public void setInput(String[] args) {
    
	try {
		if(args.length != 1) {
			throw new OneInputException(engineName);
		}

		try {
			n = Integer.parseInt(args[0]);
		} catch(NumberFormatException e) {
			throw new MyNumberFormatException(args[0], engineName);
		}

		if(n < 0) {
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
 * Computes the factorial of the given input value n
 */
public void compute() {
	result = 1;
    for (int i = 1; i <= n; i++) {
        result *= i;
    }
}

/**
 * Returns the result of the computation
 * @return the result of the computation
 */
public double getResult() {
	return result;
}    
    
}

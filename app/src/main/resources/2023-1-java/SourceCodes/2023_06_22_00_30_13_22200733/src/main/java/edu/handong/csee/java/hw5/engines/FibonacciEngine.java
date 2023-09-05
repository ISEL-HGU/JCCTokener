package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
*A class representing the Fibonacci engine which implements the Computable interface.
*This engine calculates the Fibonacci number of a given integer input.
*/
public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";
    /**
 * Get the name of this engine.
 * 
 * @return the engine name.
 */
public static String getEnginename() {
    return engineName;
}

private int n;
private double result;

/**
 * Get the integer input.
 * 
 * @return the integer input.
 */
public int getN() {
    return this.n;
}

/**
 * Set the integer input.
 * 
 * @param n the integer input to set.
 */
public void setN(int n) {
    this.n = n;
}

/**
 * Set the result of the computation.
 * 
 * @param result the result of the computation to set.
 */
public void setResult(double result) {
    this.result = result;
}

/**
 * Set the input arguments for the engine.
 * 
 * @param args the input arguments to set.
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
 * Compute the Fibonacci number of the input.
 */
public void compute() {
	if (n == 0) {
        result = 0;
    } else if (n == 1) {
        result = 1;
    } else {
        double prevPrev = 0;
        double prev = 1;
        for (int i = 2; i <= n; i++) {
            result = prevPrev + prev;
            prevPrev = prev;
            prev = result;
        }
    }
}

/**
 * Get the result of the computation.
 * 
 * @return the result of the computation.
 */
public double getResult() {
	return result;
}
}

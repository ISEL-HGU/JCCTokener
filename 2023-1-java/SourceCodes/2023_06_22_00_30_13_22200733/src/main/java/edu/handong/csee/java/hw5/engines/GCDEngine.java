package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;


/**
*A class representing a GCD Engine that implements the Computable interface.
*This class is responsible for computing the GCD of a set of positive integers.
*/

public class GCDEngine implements Computable {
    private static final String engineName ="GCD";
    /**
 * This method returns the name of the engine.
 * 
 * @return The name of the engine
 */
    public static String getEnginename() {
        return engineName;
    }

    private int result;
    private int[] a;

    /**
 * Returns the set of a to be processed.
 * 
 * @return The set of a to be processed
 */
public int[] getA() {
    return a;
}

/**
 * Sets the set of a to be processed.
 * 
 * @param a The set of a to be processed
 */
public void setA(int[] a) {
    this.a = a;
}

/**
 * Sets the result of the computation.
 * 
 * @param result The result of the computation
 */
public void setResult(int result) {
    this.result = result;
}

/**
 * Parses the a arguments for the GCD engine.
 * 
 * @param args The a arguments for the GCD engine
 */
public void setInput(String[] args) {

    try {
        if(args.length < 2) {
            throw new MinimumInputNumberException(engineName);
        }

        a = new int[args.length];
        for(int i = 0; i < a.length; i++) {
            try {
                a[i] = Integer.parseInt(args[i]);
            } catch(NumberFormatException e) {
                throw new MyNumberFormatException(args[i], engineName);
            }
            if(a[i] < 0) {
                throw new NegativeNumberException(engineName);
            }
	    }
    } catch(MinimumInputNumberException e) {
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
 * Computes the GCD of the set of input numbers.
 */
public void compute() {
    result = a[0];
    for(int i=1; i<a.length; i++) {
        result = gcd(result, a[i]);
    }
    
}

/**
 * Returns the result of the computation.
 * 
 * @return The result of the computation
 */
public double getResult() {
	return result;
}

/**
 * Computes the GCD of two numbers using the Euclidean algorithm.
 * 
 * @param a The first number
 * @param b The second number
 * @return The GCD of the two numbers
 */
    private int gcd(int a, int b) {
        int x = a;
        int y = b;
        while(y != 0) {
            int r = x % y;
            x = y;
            y = r;
        }
        return x;
    }
    
}

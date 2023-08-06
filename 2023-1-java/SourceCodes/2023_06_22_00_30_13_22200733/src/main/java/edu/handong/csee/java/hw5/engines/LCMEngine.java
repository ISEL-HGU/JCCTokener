package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
*LCMEngine class for finding the least common multiple of given integers.
*Implements the Computable interface.
*/
public class LCMEngine implements Computable{
    private static final String engineName ="LCM";
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
 * Getter method for a field
 * @return integer array a
 */
public int[] getA() {
    return a;
}

/**
 * Setter method for a field
 * @param a integer array a
 */
public void setA(int[] a) {
    this.a = a;
}

/**
 * Setter method for result field
 * @param result integer result
 */
public void setResult(int result) {
    this.result = result;
}

/**
 * Sets a field with given command-line arguments and checks for negative inputs
 * @param args command-line arguments
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
 * Computes the least common multiple of the a
 */
public void compute() {
    result = a[0];
    for(int i=1; i<a.length; i++) {
        result = lcm(result, a[i]);
    }
}

/**
 * Computes the least common multiple of two integers using Euclidean algorithm
 * @param a integer a
 * @param b integer b
 * @return least common multiple of a and b
 */
private int lcm(int a, int b) {
    int x = a;
    int y = b;
    while(y != 0) {
        int r = x % y;
        x = y;
        y = r;
    }
    return (a*b)/x;
}

/**
 * Getter method for result field
 * @return double result
 */
public double getResult() {
    return result;
}   

    
}

/**

A class representing the LCM Engine, implementing the Computable interface
to calculate the LCM of given integer values.
*/
package edu.handong.csee.java.hw5.engines;


import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

public class LCMEngine implements Computable{
private static final String engineName = "LCM";
private int[] a;
private int result;
    /**
     * Returns the value of a
     * 
     * @return the new calue of a
     */
    public int[] getN() {
        return a;
    }
    
    /**
     * Set the value of a
     * @param a the new value of a
     */
    public void setN(int[] a) {
        this.a = a;
    }
    
/**
     * Set the value of result
     * @param result the new value of result
     */
    public void setResult(int result) {
        this.result = result;
    }
    
/**
 * Sets the input values to be processed.
 * @param args an array of strings representing the input values to be processed
 */
public void setInput(String[] args) {
    try {
    	a = new int[args.length];
    	for(int i=1; i<args.length; i++) {
    	if (Double.parseDouble(args[1]) < 0) 
            throw new NegativeNumberException(engineName);
    	if ((args.length)-1<2) 
    		throw new MinimumInputNumberException(engineName);
    	a[i-1] = Integer.parseInt(args[i]);
    	}
    }catch(NegativeNumberException e){
      	 System.out.println (e.getMessage ());
      	System.exit(0);
      	
       }catch(MinimumInputNumberException e){
      	 System.out.println (e.getMessage ());
      	System.exit(0);
    	
      }
}

/**
 * Computes the LCM of the input values.
 */
public void compute() {
    int lcm = a[0];
    for(int i=1; i<a.length-1; i++) {
        int gcd = findGCD(lcm, a[i]);
        lcm = (lcm * a[i]) / gcd;
    }
    result = lcm;
    
}

/**
 * Returns the result of LCM calculation.
 * @return a double value representing the LCM result
 */
public double getResult() {
    return (double)result;
}

/**
 * Calculates the GCD of two integer values.
 * @param a an integer value
 * @param b an integer value
 * @return an integer value representing the GCD of a and b
 */
private int findGCD(int a, int b) {
    if(b == 0) {
        return a;
    }
    else {
        return findGCD(b, a % b);
    }
}
}
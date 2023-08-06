/**

This class is a concrete implementation of the Computable interface, which calculates the volume of a sphere.
The formula for calculating the volume of a sphere is 4/3 * pi * r^3, where r is the radius of the sphere.
This class receives an array of strings as input, where the first element is the radius of the sphere.
*/
package edu.handong.csee.java.hw5.engines;


import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

public class SphereVolEngine implements Computable {
private static final String engineName ="SPHEREVOL"; // The name of the engine
private double radius; // The radius of the sphere
private double result; // The volume of the sphere
/**
     * Returns the value of radius
     * 
     * @return the new calue of radius
     */
    public double getRadius() {
        return radius;
    }
    
    /**
     * Set the value of radius
     * @param radius the new value of radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
    
/**
     * Set the value of result
     * @param result the new value of result
     */
    public void setResult(double result) {
        this.result = result;
    }
/**
 * Sets the input values for the engine.
 * 
 * @param args The array of input values. The first element is the radius of the sphere.
 */
public void setInput(String[] args) {
    
	  try {
	    	if (Double.parseDouble(args[1]) < 0) 
	            throw new NegativeNumberException(engineName);
	    	if ((args.length)-1>1) 
	    		throw new OneInputException(engineName);
    radius = Double.parseDouble(args[1]); // Parse the radius from the input
	  }catch(NegativeNumberException e){
	    	 System.out.println (e.getMessage ());
	    	 System.exit(0);
	    }catch(OneInputException e){
	   	 System.out.println (e.getMessage ());
	   	System.exit(0);
	   }
}

/**
 * Performs the computation for the engine.
 * Calculate sphereVolume
 */
public void compute() {
    result = 4.0 / 3.0 * Math.PI * Math.pow(radius, 3); // Calculate the volume of the sphere using the formula
}

/**
 * Returns the result of the computation.
 * 
 * @return The volume of the sphere.
 */
public double getResult() {
    return result;
} 
}

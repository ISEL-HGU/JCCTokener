package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * the class contains fields,methods that implements Computable interface for calculating volume of a sphere.
 * @author kim hongchan
 *
 */
public class SphereVolEngine implements Computable {
	/**
	 * String value that means engine name initialized as "SPHEREVOL".
	 */
    private static final String engineName ="SPHEREVOL";
    /**
	 * Double value that is radius for calculating volume of sphere.
	 */
    private double radius;
    /**
   	 * Double value that is result for calculated volume of sphere.
   	 */
    private double result;
    /**
     * Set a radius from String type parameters as args[]. there should be two parmeters args[] and each value should be positive.
     * @param args
     */
	public void setInput(String[] args)throws OneInputException,NegativeNumberException {
    	if(args.length != 1) throw new OneInputException(engineName); 
    	if(Integer.parseInt(args[0]) < 0) throw new NegativeNumberException(engineName);
    	this.radius = Double.parseDouble(args[0]);
	}
	/**
	 * Compute result. Math.pow() was used.
	 */
	public void compute() {
		this.result = (4.0/3.0)*(Math.PI * Math.pow(this.radius,3));
	}
	/**
	 * Return result of a double value.
	 * @return result
	 */
	public double getResult() {
		return this.result;
	}
	/**
	 * Return radius
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}
	/**
	 * Set radius
	 * @param radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	/**
	 * Return engineName
	 * @return engineName
	 */
	public static String getEnginename() {
		return engineName;
	}
	/**
	 * Set result
	 * @param result
	 */
	public void setResult(double result) {
		this.result = result;
	}
	
}

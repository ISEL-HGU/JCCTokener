package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * the class contains fields,methods that implements Computable interface for calculating volume of a cube.
 * @author kim hongchan
 */
public class CubeVolEngine implements Computable{
	/**
	 * String value that means engine name initialized as "CUBEVOL".
	 */
	private static final String engineName ="CUBEVOL";
	/**
	 * Double value that is side length of the cube.
	 */
    private double sideLength;
    /**
     * Double value that is volume of the cube.
     */
    private double volume;
    /**
     * Set a side length from String type parameter args[]. there should be two parameters args[] and each value should be positive.
     * @param args
     */
    public void setInput(String[] args) throws OneInputException,NegativeNumberException {
    	if(args.length != 1) throw new OneInputException(engineName);
    	if(Integer.parseInt(args[0]) < 0) throw new NegativeNumberException(engineName);
    	this.sideLength = Integer.parseInt(args[0]);
    }
	/**
	 * Compute volume. Math.pow() was used.
	 */
	public void compute() {
		this.volume = Math.pow(this.sideLength,3);	
	}
	/**
	 * Return volume of a double value.
	 * @return volume
	 */
	public double getResult() {
		return this.volume;
	}
	/**
 	* Return sideLength
 	* @return sideLength
 	*/
    public double getSideLength() {
		return sideLength;
	}
    /**
     * Set sideLength
     * @param sideLength
     */
	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}
	/**
	 * Return volume
	 * @return volume
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 * Set volume
	 * @param volume
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}
	/**
	 * Return engineName
	 * @return engineName
	 */
	public static String getEnginename() {
		return engineName;
	}
}

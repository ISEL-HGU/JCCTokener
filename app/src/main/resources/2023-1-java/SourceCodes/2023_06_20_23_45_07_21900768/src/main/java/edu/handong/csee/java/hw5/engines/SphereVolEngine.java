package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This class is one of Engine class for computing the sphere volume.
 */
public class SphereVolEngine implements Computable {
	/**
	 * Every engines have its own name to help user distinguish it with other engines
	 * This is a field about engine Name called SHPEREVOL
	 */
    private static final String engineName ="SHPEREVOL";
    /**
	 * This is a field about radius
	 */
    private double radius;
    /**
	 * This is a field about result value
	 */
    private double result;
  
    /**
	 * Method SetInput gets arguments and determines if the program can or cannot perform normally.
	 * If the arguments are not suitable, print error message from InputChecker
	 * Such as the number of required inputs, or negative number.
	 */
    public void setInput(String[] engineName) {
    	radius = Integer.parseInt(engineName[0]);
    }
	/**
	 * The values are computed in this method.
	 * The fomula of sphere volume is [4/3 * PI * radius^3].
	 */
    public void compute() {
    	result = (4.0/3.0) * Math.PI * Math.pow(radius, 3);
    }
	/**
	 * the result of computation is returned in this method.
	 */
    public double getResult(){
    	return result;
    }
	/**
	 * this is a getter of radius
	 */
	public double getRadius() {
		return radius;
	}
	/**
	 * this is a setter of radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	/**
	 * this is a getter of Engine name
	 */
	public static String getEnginename() {
		return engineName;
	}
	/**
	 * this is a setter of result
	 */
	public void setResult(double result) {
		this.result = result;
	}
}

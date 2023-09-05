package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
/**
 * Class for calculating sphere volume
 */
public class SphereVolEngine implements Computable {
    private static final String engineName ="SPHEREVOL";
    private double radius;
    private double result;
    /**
     * method that computes result
     */
    @Override
    public void compute() {
        result = (4.0/3.0)*Math.PI*Math.pow(radius,3);
    }
    
    /** 
     * @return double
     * method that gets result
     */
    @Override
    public double getResult() {
        return result;
    }
    
    /** 
     * @param s
     * method that sets input
     */
    @Override
    public void setInput(String[] s) {
    	try {
	        if(s.length > 2)
	            throw new OneInputException(engineName);
	        try {
	        this.radius = Double.parseDouble(s[1]);
	        }catch(Exception e) {
	        	throw new MyNumberFormatException(engineName, s[1]);
	        }
	        if(radius < 0)
	            throw new NegativeNumberException(engineName);   
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    }
    
    /** 
     * @return String
     * method that gets Enginename
     */
    public static String getEnginename() {
        return engineName;
    }
    
    /** 
     * @return double
     * method that gets radius
     */
    public double getRadius() {
        return radius;
    }
    
    /** 
     * @param radius
     * method that sets radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
    
    /** 
     * @param result
     * method that sets result
     */
    public void setResult(double result) {
        this.result = result;
    }
    
}

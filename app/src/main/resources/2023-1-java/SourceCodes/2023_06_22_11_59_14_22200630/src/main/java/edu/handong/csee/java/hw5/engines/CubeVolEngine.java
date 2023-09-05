package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
/**
 * Class for calculating cube volume
 */
public class CubeVolEngine implements Computable {
    
    private static final String engineName ="CUBEVOL";
    private double sideLength;
    private double volume;

    /**
     * method that computes result
     */
    @Override
    public void compute() {
        this.volume= Math.pow(sideLength, 3);
    }
    
    
    /** 
     * @return double
     * method that gets result
     */
    @Override
    public double getResult() {
        return volume;
    }
    
    /** 
     * @param s
     * method that sets input
     */
    @Override
    public void setInput(String[] s) {
    	try {
    		try {
    			this.sideLength = Double.parseDouble(s[1]);
    		}catch(Exception e) {
    			throw new MyNumberFormatException(engineName,s[1]);
    		}
    		if(this.sideLength<0)
    			throw new NegativeNumberException(engineName);
    		if(s.length > 2)
    			throw new OneInputException(engineName);
    	} catch (Exception e) {
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
     * method that gets side lenght
     */
    public double getSideLength() {
        return sideLength;
    }
    
    /** 
     * @return double
     * method that gets volume
     */
    public double getVolume() {
        return volume;
    }
    
    /** 
     * @param sideLength
     * method that sets side length
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }
    
    /** 
     * @param volume
     * method that sets volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }
    
}

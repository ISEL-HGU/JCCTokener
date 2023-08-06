package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
/**
 * Class for calculating square root
 */
public class SQRTEngine implements Computable {
    private static final String engineName ="SQRT";

    private double input;
    private double result;
    /**
     * method that computes result
     */
    @Override
    public void compute() {
        result = Math.sqrt(input);
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
	        this.input =  Double.parseDouble(s[1]);   
	        }catch(Exception e) {
	        	throw new MyNumberFormatException(engineName, s[1]);
	        }
	        if(input < 0)
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
     * method that gets input
     */
    public double getInput() {
        return input;
    }
    
    /** 
     * @param input
     * method that sets input
     */
    public void setInput(double input) {
        this.input = input;
    }
    
    /** 
     * @param result
     * method that sets result
     */
    public void setResult(double result) {
        this.result = result;
    }
    
}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
/**
 * Class for calculating factorial
 */
public class FactorialEngine implements Computable {
    private static final String engineName ="FACTORIAL";
    private int n;
    private double result;

    /**
     * method that computes result
     */
    @Override
    public void compute() {
        result = 1;
        for(int i = 2; i <= n; i++){
            result = result * i;
        }
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
    		try {
    			this.n = Integer.parseInt(s[1]);
    		}catch(Exception e) {
    			throw new MyNumberFormatException(engineName,s[1]);
    		}
    		if(s.length > 2)
    			throw new OneInputException(engineName);
    		if(n<0)
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
     * @return int
     * method that gets n
     */
    public int getN() {
        return n;
    }
    
    /** 
     * @param n
     * method that sets n
     */
    public void setN(int n) {
        this.n = n;
    }
    
    /** 
     * @param result
     * method that sets result
     */
    public void setResult(double result) {
        this.result = result;
    }
    
}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;
/**
 * Class for calculating fibonacci
 */
public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";
    private int n;
    private double result=1;

    /**
     * method that computes result
     */
    @Override
    public void compute() {
        int n1 = 0, n2 = 1;
        for(int i = 2; i < n+1; ++i){
            result = n1 + n2;
            n1 = n2;
            n2 = (int)result;
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
        	if(s.length > 2)
        		throw new OneInputException(engineName);
        	try {
        		this.n = Integer.parseInt(s[1]);
        	}catch(Exception e) {
        		throw new MyNumberFormatException(engineName,s[1]);
        	}
        	if(n < 0)
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

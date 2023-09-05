package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
/**
 * Class for calculating gcd
 */
public class GCDEngine implements Computable {
    private static final String engineName ="GCD";
    private int[] inputs;
    private int result;

    /**
     * method that computes result
     */
    @Override
    public void compute() {
        result = gcd(inputs[0],inputs[1]);
        if(inputs.length-1 > 2) {
            for(int i = 2; i < inputs.length-1; i++){
               result = gcd(result, inputs[i]);
            }
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
        int count = 0;
        inputs = new int[s.length];
        for(int i=0; i<s.length-1; i++)
        try {
        inputs[i] = Integer.parseInt(s[1+i]);
        }catch(Exception e) {
        	throw new MyNumberFormatException(engineName, s[1]);
        }
        try {
        	for(int i=0; i<inputs.length-1; i++)
        		if(inputs[i] < 0)
        			count++;
        	if(count >= 1)
        		throw new NegativeNumberException(engineName);
        	if(inputs.length < 3)
        		throw new MinimumInputNumberException(engineName,2);
        
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        	System.exit(0);
        }
    }
    
    /** 
     * @param a
     * @param b
     * @return int
     * method that find gcd
     */
    public static int gcd(int a,int b){
        int res = 0;
        while (b > 0){
           int temp = b;
           b = a % b;
           a = temp;
           res = a;
        }
        return res;
     }
    
    /** 
     * @return String
     * method that gets engineName
     */
    public static String getEnginename() {
        return engineName;
    }   
    
    /** 
     * @return int[]
     * method that gets inputs
     */
    public int[] getInputs() {
        return inputs;
    }
    
    /** 
     * @param inputs
     * method that sets inputs
     */
    public void setInputs(int[] inputs) {
        this.inputs = inputs;
    }
    
    /** 
     * @param result
     * method that sets result
     */
    public void setResult(int result) {
        this.result = result;
    }
    
}

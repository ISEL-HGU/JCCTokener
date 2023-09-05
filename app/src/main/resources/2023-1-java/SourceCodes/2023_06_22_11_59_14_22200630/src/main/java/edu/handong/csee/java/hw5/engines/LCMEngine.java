package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
 * Class for calculating lcm
 */
public class LCMEngine implements Computable{
    private static final String engineName ="LCM";
    private int[] inputs;
    private int result;

    /**
     * method that computes result
     */
    @Override
    public void compute() {
        int gcd = inputs[0];
        result = inputs[0];
        for(int i=1; i<inputs.length-1; i++){
            gcd = findGCD(inputs[i], result);
            result = (result*inputs[i])/gcd;
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
        for(int i=0; i<s.length-1; i++){
            inputs[i] = Integer.parseInt(s[1+i]);
        }
        try {
        	for(int i=0; i<inputs.length-1; i++){
        		if(inputs[i] < 0)
        			count++;
        	}
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
     * method that find GCD
     */
    public static int findGCD(int a, int b){
        if(b == 0)
          return a;
        return findGCD(b, a%b);
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
     * method that sets results
     */
    public void setResult(int result) {
        this.result = result;
    }
    
}

package edu.handong.csee.java.hw5.engines;

import java.util.Arrays;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * Class for calculating min
 */
public class MinEngine implements Computable {
    private static final String engineName ="MIN";
    private double[] inputs;
    private double result;

    /**
     * method that computes result
     */
    @Override
    public void compute() {
        result = Arrays.stream(inputs).min().getAsDouble();        
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
        inputs = new double[s.length];
        for(int i=0;i<s.length-1;i++) {
        	try {
        		inputs[i] = Double.parseDouble(s[1+i]);
        	}catch(Exception e) {
        		throw new MyNumberFormatException(engineName, s[1]);
        	}
        }
        try {
        	if(inputs.length < 3)
        		throw new MinimumInputNumberException(engineName,2);
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
     * @return double[]
     * method that gets inputs
     */
    public double[] getInputs() {
        return inputs;
    }
    
    /** 
     * @param inputs
     * method that sets inputs
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }
    
    /** 
     * @param result
     * method that sets result
     */
    public void setResult(double result) {
        this.result = result;
    }
    
}

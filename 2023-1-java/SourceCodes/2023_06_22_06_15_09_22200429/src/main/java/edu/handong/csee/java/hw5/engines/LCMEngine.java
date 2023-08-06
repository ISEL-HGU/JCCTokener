package edu.handong.csee.java.hw5.engines;
import java.lang.System;


import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
 * This is a class of lcmengine, and inherits from class computable.
 */
public class LCMEngine implements Computable {

    
    private static final String engineName ="LCM";

    
    private int[] a;

 
    private int result;

    /**
     * This method returns the name of the enginename.
     */
    public static String getEngineName() {
        return engineName;
    }
  
    /**
     * This is a method to get a[].
     */
    public int[] getA() {
        return a;
    }

    /**
     * This is a method to set a[].
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * This is the method to set the result.
     */
    public void setResult(int result) {
        this.result = result;
    }

    
    
    /**
     * This method sets the input value of the LCM. 
     * The reason why inputs.length is low than 3 is because at compile time, 
     * LCM and at least 2 inputs are required. 
     * If there is less than 1 input, it is low than 3.
     */
    public void setInput(String[] inputs) {
    	   a = new int[inputs.length];
           for (int i = 0; i < a.length; i++) {
               a[i] = Integer.parseInt(inputs[i]);
           }
        try {
            if (inputs.length < 2)
                throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for LCM.");
        }catch(MinimumInputNumberException e){
            System.out.println(e.getMessage());
            System.exit(0); 
        }

        for (int i = 0; i < a.length; i++) {
            try {
                if (a[i] < 0)
                    throw new NegativeNumberException("Exception-03: The input value cannot be negative for LCM.");
            } catch (NegativeNumberException e) {
                System.out.println(e.getMessage());
                System.exit(0); 
        }}
    }
    
    
    /**
     * This is the method to calculate the LCM.
     */
    public void compute() {
        int lcm = a[0];
        for (int i = 0; i < a.length; i++) {
            lcm = (lcm * a[i]) / gcd(lcm, a[i]);
        }
        result = lcm;
    }
    
    /**
     * This is a method to calculate gcd. 
     */
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * This is a method to get the result.
     */
    public double getResult() {
        return result;
    }



}

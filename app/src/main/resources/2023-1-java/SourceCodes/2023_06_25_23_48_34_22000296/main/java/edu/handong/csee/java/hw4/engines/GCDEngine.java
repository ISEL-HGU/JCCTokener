package edu.handong.csee.java.hw4.engines;

import edu.handong.csee.java.hw4.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw4.engines.Computable;
import edu.handong.csee.java.hw4.exceptions.*;

public class GCDEngine implements Computable {
    private static final String engineName ="GCD";

    int[] a;
    double result;

    public void setInput(String[] gmax){
        a = new int[gmax.length - 1];
        for(int i=1;i<gmax.length;i++){
        	
            
            try {
            	a[i-1]=Integer.parseInt(gmax[i]);
                //System.out.println("" + number);
            } catch (NumberFormatException e) {
                //System.out.println("");
            	System.out.print(new MyNumberFormatException().getMessage(engineName));
            	System.out.print("(" + gmax[i]);
            	System.out.println(" is not a number value for " + engineName.toUpperCase() +".)");
            	System.exit(0);
            }
            try {
            	if(a[i-1]<0)
            		throw new NegativeNumberException();
            }catch(NegativeNumberException e){
        	System.out.println(e.getMessage(engineName));
            System.exit(0);
        	
        	}
        }
        try {
        	if (a.length<2)
        		throw new MinimumInputNumberException();
        }catch(MinimumInputNumberException e) {
        	System.out.println(e.getMessage(engineName));
            System.exit(0);
        }
    }

    public void compute() {
        result = findGCDArray(a);
    }

    // Find the GCD of an array of integers using the Euclidean algorithm
    private int findGCDArray(int[] a) {
        int gcd = a[0];
        for (int i = 1; i < a.length; i++) {
            gcd = findGCD(gcd, a[i]);
            if (gcd == 1) {
                break;
            }
        }
        return gcd;
    }

    // Find the GCD of two integers
    private int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return findGCD(b, a % b);
        }
    }

    

    public double getResult(){
        return result;
    }
    
}
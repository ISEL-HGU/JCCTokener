package edu.handong.csee.java.hw4.engines;

import edu.handong.csee.java.hw4.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw4.engines.Computable;
import edu.handong.csee.java.hw4.exceptions.*;

public class LCMEngine implements Computable{
    private static final String engineName ="LCM";
    int i;
    int[] a;
    double result;

    public void setInput(String[] lmin){


        a = new int[lmin.length - 1];
        for(i=1;i<lmin.length;i++){
            try {
            	a[i-1]=Integer.parseInt(lmin[i]);
            	
            }catch(NumberFormatException e) {
            	System.out.print(new MyNumberFormatException().getMessage(engineName));
            	System.out.print("(" + lmin[i]);
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

    public void compute(){
        int lcm = getMax(a);
        boolean found = false;


        while (!found) {
            found = true;
            for (int num : a) {
                if (lcm % num != 0) {
                    found = false;
                    break;
                }
            }
            
            if (found) {
                result = lcm;
                break;
            }
            lcm++;
        }

    }

    public double getResult(){
        return result;
    }

    public static int getMax(int[] numbers) {
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    
}
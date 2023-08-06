package edu.handong.csee.java.hw4.engines;

import edu.handong.csee.java.hw4.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw4.engines.Computable;
import edu.handong.csee.java.hw4.exceptions.*;

public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";

    int[] n;
    int i=0;
    double result;

    public void setInput(String[] fib){
        n = new int[fib.length - 1];
        for(i=1;i<fib.length;i++){
        	
            
            try {
            	n[i-1]=Integer.parseInt(fib[i]);
                //System.out.println("" + number);
            } catch (NumberFormatException e) {
                //System.out.println("");
            	System.out.print(new MyNumberFormatException().getMessage(engineName));
            	System.out.print("(" + fib[i]);
            	System.out.println(" is not a number value for " + engineName.toUpperCase() +".)");
            	System.exit(0);
            }
            
            try {
            	if(n[i-1]<0)
            		throw new NegativeNumberException();
            }catch(NegativeNumberException e){
        	System.out.println(e.getMessage(engineName));
            System.exit(0);
        	
        	}
        }
        try {
        	if (n.length>1)
        		throw new OneInputException();
        }catch(OneInputException e){
        	System.out.println(e.getMessage(engineName));
            System.exit(0);
        	
        	}
    }
 
    public void compute(){
        result = n[0] + n[0]-1;
    }

    public double getResult(){
        return result;
    }


    
}

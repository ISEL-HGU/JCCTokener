package edu.handong.csee.java.hw4.engines;

import edu.handong.csee.java.hw4.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw4.engines.Computable;
import edu.handong.csee.java.hw4.exceptions.*;

public class FactorialEngine implements Computable {
    private static final String engineName ="FACTORIAL";

    int[] n;
    
    int i=0;
    double result;

    public void setInput(String[] fac){
        n = new int[fac.length - 1];
        for(i=1;i<fac.length;i++){
        	try {
        		n[i-1]=Integer.parseInt(fac[i]);
        	}catch (NumberFormatException e) {
        		System.out.print(new MyNumberFormatException().getMessage(engineName));
            	System.out.print("(" + fac[i]);
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
        
        if(n[0]==0){
            result = 1;
            }
            else if(n[0]>0){
                int c=1;
            for(int j =n[0];j>=1;j--){
                c = c*j;
            }
            result = c;
            }
        }

    public double getResult(){
        return result;
    }

    
    
}
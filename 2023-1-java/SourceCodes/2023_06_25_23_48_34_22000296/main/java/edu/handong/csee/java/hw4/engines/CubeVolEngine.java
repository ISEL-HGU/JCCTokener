package edu.handong.csee.java.hw4.engines;

import edu.handong.csee.java.hw4.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw4.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw4.exceptions.OneInputException;
import edu.handong.csee.java.hw4.engines.Computable;
import edu.handong.csee.java.hw4.exceptions.*;

public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";

    private int i;
    private double sideLength;
    private double volume;
    private double result;
    private int[] a;

    public void setInput(String[] cuv){
    	
    	
        a = new int[cuv.length - 1];
        for(i=1;i<cuv.length;i++){
            
            try {
            	a[i-1]=Integer.parseInt(cuv[i]);
            	
                //System.out.println("" + number);
            } catch (NumberFormatException e) {
                //System.out.println("");
            	System.out.print(new MyNumberFormatException().getMessage(engineName));
            	System.out.print("(" + cuv[i]);
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
            	
         try{
            if(a.length>1)
            	throw new OneInputException();
            
            }catch(OneInputException e) {
            	System.out.println(e.getMessage(engineName));
            	System.exit(0);
            }
         
         
         
        }
    }

    public void compute(){
        sideLength = a[0];
        volume = sideLength*sideLength*sideLength;
        result = volume;
    }

    public double getResult(){
        return result;
    }
    
}
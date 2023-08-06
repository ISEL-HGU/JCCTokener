package edu.handong.csee.java.hw4.engines;

import edu.handong.csee.java.hw4.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw4.exceptions.*;

public class SphereVolEngine implements Computable {
    private static final String engineName ="SPHEREVOL";

    double radius;
    int[] a;
    double result;
    double volume;

    public void setInput(String[] spv){
        a = new int[spv.length - 1];
        for(int i=1;i<spv.length;i++){
        	
        	try {
            	a[i-1]=Integer.parseInt(spv[i]);
                //System.out.println("" + number);
            } catch (NumberFormatException e) {
                //System.out.println("");
            	System.out.print(new MyNumberFormatException().getMessage(engineName));
            	System.out.print("(" + spv[i]);
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
            if (a.length>1)
            	throw new OneInputException();
            	
            }catch(OneInputException e){
            System.out.println(e.getMessage(engineName));
            System.exit(0);
        }
    }
    // 4/3*PI*radius^3
    public void compute(){
        radius = a[0];
        volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);;
        result = volume;
    }

    public double getResult(){
        return result;
    }
    
}

package edu.handong.csee.java.hw4.engines;

import edu.handong.csee.java.hw4.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw4.exceptions.*;

public class SQRTEngine implements Computable {
    private static final String engineName ="SQRT";

    double input;
    double result;
    int[] n;
    

    public void setInput(String[] sqrt){
        n = new int[sqrt.length - 1];
        for(int i=1;i<sqrt.length;i++){
        	
            
            try {
            	n[i-1]=Integer.parseInt(sqrt[i]);
                //System.out.println("" + number);
            } catch (NumberFormatException e) {
                //System.out.println("");
            	System.out.print(new MyNumberFormatException().getMessage(engineName));
            	System.out.print("(" + sqrt[i]);
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
    //root
    public void compute(){
        input=n[0];
        result = Math.sqrt(input);
        }

    public double getResult(){
        return result;
    }



}

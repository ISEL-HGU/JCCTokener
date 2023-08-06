package edu.handong.csee.java.hw4.engines;

import edu.handong.csee.java.hw4.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw4.engines.Computable;
import edu.handong.csee.java.hw4.exceptions.*;

public class MaxEngine implements Computable {
    private static final String engineName ="MAX";

    double[] inputs;
    double result;

    public void setInput(String[] max){

        inputs = new double[max.length - 1];
        
        for(int i=1;i<max.length;i++){
            
            try {
            	inputs[i-1]=Integer.parseInt(max[i]);
                //System.out.println("" + number);
            } catch (NumberFormatException e) {
                //System.out.println("");
            	System.out.print(new MyNumberFormatException().getMessage(engineName));
            	System.out.print("(" + max[i]);
            	System.out.println(" is not a number value for " + engineName.toUpperCase() +".)");
            	System.exit(0);
            }
            
        }
        try {
        	if (inputs.length<2)
        		throw new NegativeNumberException();
        }catch(NegativeNumberException e) {
        	System.out.println(e.getMessage(engineName));
            System.exit(0);
        }

    }
   
    public void compute(){
        double max=inputs[0];
        for(int j=0; j<inputs.length;j++){
            if(inputs[j]>max) max= inputs[j];
        }
        result = max;
    }

    public double getResult(){
        return result;
    }
    
}

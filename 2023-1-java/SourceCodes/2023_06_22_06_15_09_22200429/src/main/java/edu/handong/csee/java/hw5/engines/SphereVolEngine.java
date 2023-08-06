package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * This class is named SpherVolEngine and inherits from class computable.
 */
public class SphereVolEngine implements Computable {

    
    private static final String engineName ="SPHEREVOL";

   
    private double result;

   
    private double radius;

    /**
     * This is a method to get the radius.
     */
    public static String getEngineName() {
        return engineName;
    }

    /**
     * This method gets the redius.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * This method sets the redius.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * result to set the result.
     */
    public void setResult(double result) {
        this.result = result;
    }



    
    /**
     * This is how to check the input of SHVEREVOL. 
     * The reason why inputs.length is high than 2 is because at compile time and 1 input is required
     * because we need one input
     */
    public void setInput(String[] inputs){  
    	 try{
             if (inputs.length >= 2)
                 throw new OneInputException("Exception-04: You need one input value for SPHEREVOL.\r\n" + "");
         }catch (OneInputException e) {
             System.out.println(e.getMessage());
             System.exit(0);}
        
       
        radius = Double.parseDouble(inputs[0]);
      
	        try{
	        if(radius < 0)
	        throw new NegativeNumberException("Exception-03: The input value cannot be negative for SPHEREVOL.");  
	           }catch(NegativeNumberException e){
	                System.out.println(e.getMessage());
	            	System.exit(0);
	                }
        
    }
    
    /**
     * This is how to calculate SHVEREVOL.
     */
    public void compute(){
        final double pi = 3.141592653589793;
        result = (4.0 / 3.0) * pi * Math.pow(radius, 3);
    }

    /**
     * This is a method that returns a result.
     */
    public double getResult(){
        return result;
    }


}

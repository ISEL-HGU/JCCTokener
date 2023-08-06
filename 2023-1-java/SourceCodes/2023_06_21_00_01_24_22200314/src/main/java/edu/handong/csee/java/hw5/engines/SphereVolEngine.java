package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * This class calculate the Sphere Volume of the value input received from Calculator.java
 */
public class SphereVolEngine implements Computable {
    private static final String engineName ="SPHEREVOL";
    /**
     * This method returns the Engine Name.
     * @return The Engine Name of the class.
     */
    public static String getEnginename() {
        return engineName;
    }

    private double radius;
    private double result;

    /**
     * This method returns the number of Radius used for Sphere Volume calculation.
     * @return The value of radius. 
     */
    public double getRadius() {
        return radius;
    }
    /**
     * This method sets the value radius which is the number for Sphere Volume calcuation.
     * @param radius The number to be set as radius.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
    /**
     * This method sets the value result.
     * @param result The number to be set as result.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method sets the radius as the arg[0] index. 
     * There are some exceptions: 1. If there are more than 1 received arguments, the program is prints an error and gets terminated. 2. If the value of the arg[0] is negative, the program is prints an error and gets terminated. 
     * @param arg String array from the terminal. This value would be the input value also known as the radius.
     */
    public void setInput(String[] arg) {
    	try {
	        if(arg.length != 2){
	        	throw new OneInputException(engineName);
	            //InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName, 1);
	        }
	        
	        if(!MyNumberFormatException.isNumber(arg[1])) {
        		throw new MyNumberFormatException(arg[1], engineName);
        	}
	        
	
	        if(Integer.parseInt(arg[1]) < 0){
	        	throw new NegativeNumberException(engineName);
	            //InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
	        }
	        
	        radius = Double.parseDouble(arg[1]);
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e){
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}

    	
    }
    
    /**
     * This method does the calculation for Sphere Volume.
     */
    public void compute() {
    	result = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3); 
    }
    
    /**
     * This method returns the result done from Sphere Volume calculation.
     * @return The result done from calculating Sphere Volume. 
     */
    public double getResult() {
    	return result;
    }
}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A sphere volume calculator.
 */
public class SphereVolEngine implements Computable {
    private static final String engineName ="SPHEREVOL";
    private double radius;
    private double result;

    /**
     * Sets a radius.
     * 
     * @param radius a length of radius of the sphere
     */
    public void setRadius(double radius){
        this.radius = radius;
    }

    /**
     * Gets the radius.
     * 
     * @return the length of radius of the sphere
     */
    public double getRadius(){
        return radius;
    }


    /**
     * Sets a sphere volume.
     * 
     * @param result a volume of sphere
     */
    public void setResult(double result){
        this.result = result;
    }

    /**
     * Converts the input string into the double type for calculations.
     * 
     * @param input the input string
     */
    public void setInput(String[] input){
        if(input.length > 1){
        	try {
        		throw new OneInputException(engineName);
        	} catch(OneInputException e){
        		System.out.println(e.getMessage());
        		System.exit(0);
        	}
        }
        try {
        	radius = Double.parseDouble(input[0]);
    	} catch(NumberFormatException e) {
    		try {
    			throw new MyNumberFormatException(engineName, input[0]);
    		} catch(MyNumberFormatException ex){
    			System.out.println(ex.getMessage());
    			System.exit(0);
    		}
    	}
        if(radius < 0){
        	try {
        		throw new NegativeNumberException(engineName);
        	} catch(NegativeNumberException e){
        		System.out.println(e.getMessage());
        		System.exit(0);
        	}
        }
    }

    /**
     * Calculates the volume of a sphere.
     */
    public void compute(){
        result = 4.0/3.0*Math.PI*Math.pow(radius, 3);
    }

    /**
     * Returns the calculated result value.
     * 
     * @return the result of the calculation
     */
    public double getResult(){
        return result;
    }

}
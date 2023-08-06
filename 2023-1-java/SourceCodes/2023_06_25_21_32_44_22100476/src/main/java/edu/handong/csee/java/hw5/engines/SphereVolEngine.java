package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;
/**

A class representing an engine for calculating the volume of a sphere.
This class implements the Computable interface.
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
*/

public class SphereVolEngine implements Computable {
    private String engineName ="SPHEREVOL";
    private double radius;
    private double result;

    /**
 * Sets the input values for the engine.
 * @param args An array of strings containing the input values.
 *             The second value should be the radius of the sphere.
 */
    public void setInput(String[] args){
    	try {
        	if (args.length != 1) {
                
                // handle error case where no input value was providedX
        		throw new OneInputException(engineName);
            }
        	if(!args[0].matches("[-+]?\\d*\\.?\\d+")) {
        		throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + args[1] + " is not a number value for SPHEREVOL.)");
            	
        	}
            if (Integer.parseInt(args[0]) < 0) {
                
            	throw new NegativeNumberException(engineName);
                // handle error case where input value is negative
            }
        } catch(NegativeNumberException e){
            System.out.println(e.getMessage());
	    	 System.exit(0);
	    	 return;
        } catch(Exception e){
        	System.out.println(e.getMessage()); // print an error message if the input is invalid
	    	 System.exit(0);
;
        }

        radius = Double.parseDouble(args[0]);

    }
/**
 * Computes the volume of a sphere using the radius input value.
 */
    public void compute() {
        result = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

/**
 * Gets the radius value used for computation.
 * @return The radius value.
 */
    public double getRadius(){
        return radius;
    }

/**
 * Sets the result of the computation.
 * @param a The value to set as the result.
 */
    public void setResult(double a){
        result = a;
    }
/**
 * Gets the result of the computation.
 * @return The computed result.
 */
    public double getResult(){
        return result;
    }
/**
 * Sets the name of the engine.
 * @param a The name to set for the engine.
 */
    public void setEngineName(String a){
        engineName = a;
    }

/**
 * Gets the name of the engine.
 * @return The name of the engine.
 */
    public String getEngineName(){
        return engineName;
    }
}

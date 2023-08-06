/**

A class representing a sphere volume calculation engine.
*/
package edu.handong.csee.java.hw5.engines;
/**
 * import exceptions 
 */
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * spherevolume
 */
public class SphereVolEngine implements Computable {
/**
 * The name of the engine.
 */
    private static final String engineName ="SPHEREVOL";
    /**
 * getter enginename
 */

public static String getEnginename() {
    return engineName;
}

/**
 * The input value.
 */
    private double radius;
/**
 * Returns the radius of the sphere.
 */
    public double getRadius() {
        return radius;
    }
/**
 * Sets the radius of the sphere.
 */
    public void setRadius(double radius) {
        this.radius = radius;
    }


    /**
     * result
     */

    private double result;
    /**
     * getter result
     */
    public double getResult1() {
        return result;
    }
/**
 * Sets the result of the computation.
 */
    public void setResult1(double result) {
        this.result = result;
    }
/**
*Sets the input value for the computation.
*/
    public void setInput(String[] args) throws MyNumberFormatException, NegativeNumberException, OneInputException{
        if(args.length == 1) {
            try {
                radius = Double.parseDouble(args[0]);
                if (radius < 0) {
                    throw new NegativeNumberException(engineName);
                }
            } catch (NumberFormatException e) {
                throw new MyNumberFormatException(args[0], engineName);
            }
        } else if (args.length == 0) {
            throw new OneInputException(engineName);
        } else {
            throw new OneInputException(engineName);
        }
    }
 /**
 * Computes the volume of the sphere based on the input radius.
 */   
    public void compute() {
        result = (4.0/3.0) * Math.PI * Math.pow(radius, 3);
    }
/**
 * Returns the result of the computation.
 */  
    public double getResult() {
        return result;
    }

   
}    

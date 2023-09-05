package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;


/**
A class that computes the volume of a sphere given its radius as input.
*/
public class SphereVolEngine implements Computable {
    private static final String engineName ="SPHEREVOL";
    private double radius;
    private double result;

    /**
     * Computes the volume of a sphere using the given radius.
     */
    public void compute() throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        this.result = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Sets the input for the engine.
     * @param args An array of strings containing the radius value as the second element.
     */
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        if(Integer.parseInt(args[1]) < 0){
            throw new NegativeNumberException(engineName);
        }
        else if(args.length > 2){
            throw new OneInputException(engineName);       
        }
        else{ 
            this.radius = Double.parseDouble(args[1]);
        }
    }

    /**
     * Returns the computed result.
     * @return The computed volume of the sphere.
     */
    public double getResult(){
        return this.result;
    }

    /**
     * Returns the radius value used for computation.
     * @return The radius value.
     */
    public double getRadius(){
        return this.radius;
    }
       
    /**
     * Sets the radius value to be used for computation.
     * @param radius The radius value.
     */
    public void setRadius(double radius){
        this.radius = radius;
    }
    
    /**
     * Sets the computed result.
     * @param result The computed volume of the sphere.
     */
    public void setResult(double result){
        this.result = result;
    }
}

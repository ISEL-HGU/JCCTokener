package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
* The SphereVolEngine class represents an engine for computing the volume of a sphere.
* It implements the Computable interface.
*/
public class SphereVolEngine implements Computable {
    private static final String engineName = "SPHEREVOL";
    private double radius;
    private double result;

    /**
     * Getter for the enginen name.
     * @return String of engine name
     */
    public String getEngineName(){
        return engineName;
    }

    /**
     * Getter for the radius
     * @return double type of radius
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Setter for the radius
     * @param radius calculate for result
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Setter for the result of calculation of sphere volume
     * @param result result of the calculation
     */
    public void setResult(double result) {
        this.result = result;
    }

    
    
    /**
    * Checks that the number of inputs is correct and that the radius is not negative.
     * @throws OneInputException 
     * @throws NegativeNumberException 
    */
    @Override
    public void checkInput(String[] args) throws OneInputException, NegativeNumberException{
        if (args.length > 2) throw new OneInputException("You need one input value for "+engineName+".");
        if (Integer.parseInt(args[1]) < 0) throw new NegativeNumberException("The input value cannot be negative for "+engineName+".");
    }

    /**
    * Sets the radius of the sphere.
    */
    @Override
    public void setInput(String[] args){
        radius = Integer.parseInt(args[1]);
    }

    /**
    * Computes the volume of the sphere using the formula V = 4/3 * pi * r^3.
    */
    @Override
    public void compute(){
        result = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
    * Returns the computed result.
    */
    @Override
    public double getResult(){
        return result;
    }
}

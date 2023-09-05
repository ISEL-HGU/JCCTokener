package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
 * This class calculates the volume of a sphere using the input radius.
 * engineName, radius variable which means the radius, and result which is the result of calculating the volume of the sphere are declared as fields. 
 */
public class SphereVolEngine implements Computable {
	private static final String engineName ="SPHEREVOL";
    private double radius;
    private double result;

    /**
     * This is a method that checks the input values and sets radius. 
     * It exits the program if it receives anything other than the engine name and radius as input,
     * The following exceptions are implemented
     * if more than one number is entered, if the input is not a number, and if a negative number is entered.
     */
    public void setInput(String[] inputNum) throws OneInputException, NegativeNumberException, MyNumberFormatException{

        if(inputNum.length !=1){
        	throw new OneInputException("You need one input value for SPHEREVOL.");
        }

        radius = Integer.parseInt(inputNum[0]);
        try {
            radius = Double.parseDouble(inputNum[0]);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException("The input value should be converted into a number. (" + inputNum[1] + " is not a number value for SPHEREVOL.)");
        }

        if(radius < 0){
        	throw new NegativeNumberException("The input value cannot be negative for SPHEREVOL.");
        }
    }

    /**
     * This method calculates the volume of a sphere using the radius input.
     The volume of a * sphere is 4/3*pi*radius^3. 
     */
    public void compute(){
        result = 4.0/3.0*Math.PI*radius*radius*radius;
    }

    /**
     * This method sets the result a value.
     */
    public void setResult(double res){
        result = res;
    }

    /**
     * This method returns the value calculated by the compute method as a double type.
     * It is declared public, so it can be accessed from the Calculator class. 
     */
    public double getResult(){
        return result;
    }

   /**
    * This method returns the engineName value.
    */
    public static final String getEngineName(){
        return engineName;
    }

    /**
     * This method sets the radius value.
     */
    public void setRadius(double rad){
        radius = rad;
    }

    /**
     * This method returns the radius value.
     */
    public double getRadius(){
        return radius;
    }
}
package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
 * This is a class that calculates the volume of a cube using the input sideLength value.
 * engineName, sideLength variable which means the length of one side, and volume which is the result of calculating the volume of the cube are declared as fields. 
 */
public class CubeVolEngine implements Computable {
	private static final String engineName ="CUBEVOL";
    private double sideLength;
    private double volume;


    /**
     * This is a method that checks the input values and sets the sideLength. 
     * It terminates the program if it receives anything other than the engine name and the length of one side as input
     * The following exceptions are implemented 
     * if more than one number is entered, if the input is not a number, and if a negative number is entered.
     */
    public void setInput(String[] inputNum) throws OneInputException, NegativeNumberException, MyNumberFormatException{
        if(inputNum.length !=1){
        	throw new OneInputException("You need one input value for CUBEVOL.");
        }

        try {
        	sideLength = Integer.parseInt(inputNum[0]);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException("The input value should be converted into a number. (" + inputNum[1] + " is not a number value for CUBEVOL.)");
        }

        if(sideLength < 0){
        	throw new NegativeNumberException("The input value cannot be negative for CUBEVOL.");
        }
        
        
    }


    /**
     * This method calculates the volume of the cube using the input sideLength value.
     * The volume of the cube is the (length of one side^3). 
     */
    public void compute(){
        volume = sideLength * sideLength * sideLength;
    }


    /**
     * This is the method that returns the value calculated by the compute method.
     * It is declared public, so it can be accessed from the Calculator class. 
     */
    public double getResult(){
        return volume;
    }
    
    /**
     * This method returns the engineName value.
     */
    public static final String getEngineName(){
        return engineName;
    }

    /**
     * This method sets the sideLength value.
     */
    public void setSideLength(double len){
        sideLength = len;
    }

    /**
     * This method returns the value of sideLength.
     */
    public double getSideLength(){
        return sideLength;
    }

    /**
     * This method sets the sideLength value.
     */
    public void setVolume(double vol){
        volume = vol;
    }

    /**
     * This method returns the value of volume.
     */
    public double getVolume(){
        return volume;
    }
}

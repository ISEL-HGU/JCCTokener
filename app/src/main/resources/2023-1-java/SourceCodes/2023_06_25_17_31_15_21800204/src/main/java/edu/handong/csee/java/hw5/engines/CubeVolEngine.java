package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;
import java.lang.Math;

/**
 * This class calculates the volume of a cube using the input side length.
 */
public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";
    private double sideLength;
    private double volume;
    
    /**
     * Calculates the volume of the cube based on the side length input.
     */
    public void compute() throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        this.volume = Math.pow(this.sideLength, 3);
    }

    /**
     * Sets the input side length of the cube.
     * @param args String array that contains the side length of the cube.
     */  
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException{
        if(Integer.parseInt(args[1]) < 0){
            throw new NegativeNumberException(engineName);
        }
        else if(args.length > 2){
            throw new OneInputException(engineName);       
        }
        else{ 
            this.sideLength = Double.parseDouble(args[1]);
        }
    }

    /**
     * Returns the calculated volume of the cube.
     * @return The calculated volume of the cube.
     */
    public double getResult(){
        return this.volume;
    }

    /**
     * Returns the side length of the cube.
     * @return The side length of the cube.
     */
    public double getsidelength(){
        return this.sideLength;
    }
    
    /**
     * Sets the side length of the cube.
     * @param sideLength The side length of the cube.
     */
    public void setsidelength(double sideLength){
        this.sideLength = sideLength;
    }

    /**
     * Sets the calculated volume of the cube.
     * @param volume The calculated volume of the cube.
     */
    public void setVolume(double volume){
        this.volume = volume;
    }
    
}

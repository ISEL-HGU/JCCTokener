package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A cube volume calculator.
 */
public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";
    private double sideLength;
    private double volume;

    /**
     * Sets a sidelength.
     * 
     * @param sideLength a length of one side of the cube
     */
    public void setSideLength(double sideLength){
        this.sideLength = sideLength;
    }

    /**
     * Gets the sidelength.
     * 
     * @return the length of one side of the cube
     */
    public double getSideLength(){
        return sideLength;
    }

    /**
     * Sets a cube volume.
     * 
     * @param volume a volume of cube
     */
    public void setVolume(double volume){
        this.volume = volume;
    }

    /**
     * Gets the cube volume.
     * 
     * @return the volume of cube
     */
    public double getVolume(){
        return volume;
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
        	sideLength = Double.parseDouble(input[0]);
    	} catch(NumberFormatException e) {
    		try {
    			throw new MyNumberFormatException(engineName, input[0]);
    		} catch(MyNumberFormatException ex){
    			System.out.println(ex.getMessage());
    			System.exit(0);
    		}
    	}
        if(sideLength < 0) {
        	try {
        		throw new NegativeNumberException(engineName);
        	} catch(NegativeNumberException e){
        		System.out.println(e.getMessage());
        		System.exit(0);
        	}
        }
    }

    /**
     * Calculates the volume of a cube.
     */
    public void compute(){
        volume = Math.pow(sideLength,3);
    }

    /**
     * Returns the calculated result value.
     * 
     * @return the result of the calculation
     */
    public double getResult(){
        return volume;
    }

}

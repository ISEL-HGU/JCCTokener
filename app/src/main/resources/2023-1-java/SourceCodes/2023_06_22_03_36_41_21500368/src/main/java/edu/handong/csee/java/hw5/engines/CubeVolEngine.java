package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * The CubeVolEngine class calculates the volume of a cube with the given side length.
 */
public class CubeVolEngine implements Computable {
    /**
     * The name of the CubeVolEngine.
     */
    private static final String engineName = "CUBEVOL";

    /**
     * The length of a side of the cube.
     */
    private double sideLength;

    /**
     * The calculated volume of the cube.
     */
    private double volume;

    
    /**
     * Returns the engine name.
     * 
     * @return The engine name as a string.
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * Returns the side length of the cube.
     * 
     * @return The side length as a double.
     */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * Sets the side length of the cube.
     * 
     * @param sideLength The side length as a double.
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * Returns the volume of the cube.
     * 
     * @return The volume as a double.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Sets the volume of the cube.
     * 
     * @param volume The volume as a double.
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }   

    /**
     * Sets the input value and computes the volume of the cube.
     * 
     * @param args The input arguments as a string array.
     */
    public void setInput(String[] args) {
        try {
            if (args.length > 1) {
                throw new OneInputException(engineName);
            } else {
                try {
                    sideLength = Double.parseDouble(args[0]);
                } catch (NumberFormatException e) {
                    throw new MyNumberFormatException(engineName, args[0]);
                }
                if (sideLength < 0) {
                    throw new NegativeNumberException(engineName);
                }
                compute();
            }
        } catch (OneInputException | NegativeNumberException | MyNumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Computes the volume of the cube.
     */
    public void compute() {
        volume = Math.pow(sideLength, 3);
    }

    /**
     * Returns the result of the volume calculation.
     * 
     * @return The calculated volume as a double.
     */
    public double getResult() {
        return volume;
    }
}

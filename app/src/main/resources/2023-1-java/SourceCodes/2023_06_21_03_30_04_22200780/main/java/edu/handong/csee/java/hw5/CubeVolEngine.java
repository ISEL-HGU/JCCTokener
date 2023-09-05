package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * CubeVolEngine class represents an engine for calculating the volume of a cube.
 */
public class CubeVolEngine implements Computable {
    private static final String engineName = "CUBEVOL";
    int inputs[] = new int[1];
    double result;
    int count = 0;

    /**
     * Sets the input value for calculating the cube volume.
     * 
     * @param args The input arguments as an array of strings. The first argument is the length of the cube.
     */
    @Override
    public void setInput(String[] args) { 
    	try {
        inputs[0] = Integer.parseInt(args[1]);
        count++;
    	}catch (NumberFormatException e) {
    	    throw new MyNumberFormatException(args);
    	}
    }

    /**
     * Computes the volume of the cube.
     * 
     * @throws OneInputException       If there is not exactly one input value provided.
     * @throws NegativeNumberException If the input value is negative.
     */
    @Override
    public void compute() {
        try {
            if (inputs.length != 1) {
                throw new OneInputException("CubeVol.");
            } else if (inputs[0] < 0) {
                throw new NegativeNumberException("CubeVol.");}
            else {
                result = calculateCubeVolume(inputs[0]);
            }
        } catch (OneInputException e) {
            System.out.println(e.getMessage());
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the result of the cube volume computation.
     * 
     * @return The volume of the cube as a double value.
     */
    @Override
    public double getResult() {
        return result;
    }

    /**
     * Calculates the volume of a cube given its length.
     * 
     * @param length The length of the cube.
     * @return The volume of the cube as a double value.
     */
    public static double calculateCubeVolume(int length) {
        double volume = Math.pow(length, 3);
        return volume;
    }
}

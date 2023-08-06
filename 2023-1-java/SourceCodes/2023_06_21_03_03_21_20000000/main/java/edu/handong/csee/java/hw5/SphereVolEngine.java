package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * SphereVolEngine class represents an engine for calculating the volume of a sphere.
 */
public class SphereVolEngine implements Computable {
    private static final String engineName = "SPHEREVOL";
    int inputs[] = new int[1];
    double result;
    int count = 0;

    /**
     * Sets the input value for calculating the volume of a sphere.
     * 
     * @param args The input arguments as an array of strings. The first argument is the radius of the sphere.
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
     * Calculates the volume of a sphere based on the input radius.
     * 
     * @throws OneInputException    If there is not exactly one input value provided.
     * @throws NegativeNumberException If the input value is negative.
     */
    @Override
    public void compute() {
        try {
            if (inputs.length != 1) {
                throw new OneInputException("SphereVol.");
            }
            if (inputs[0] < 0) {
                throw new NegativeNumberException("SphereVol.");
            } else {
                result = calculateSphereVolume(inputs[0]);
            }
        } catch (OneInputException e) {
            System.out.println(e.getMessage());
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the result of calculating the volume of a sphere.
     * 
     * @return The volume of the sphere as a double.
     */
    @Override
    public double getResult() {
        return result;
    }

    /**
     * Calculates the volume of a sphere based on the given radius.
     * 
     * @param radius The radius of the sphere.
     * @return The volume of the sphere as a double.
     */
    public static double calculateSphereVolume(int radius) {
        double volume = (4 / 3.0) * Math.PI * Math.pow(radius, 3);
        return volume;
    }
}

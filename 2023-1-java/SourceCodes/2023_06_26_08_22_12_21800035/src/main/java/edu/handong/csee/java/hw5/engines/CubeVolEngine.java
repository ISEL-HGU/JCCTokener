package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * The CubeVolEngine class is an implementation of the Computable interface for
 * computing the volume of a cube.
 */
public class CubeVolEngine implements Computable {

    private static final String engineName = "CUBEVOL";
    private double sideLength;
    private double volume;

    /**
     * This method returns the length of the side of the cube.
     */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * This method sets the length of the side of the cube. with parameter
     * sideLength.
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * This method returns the computed volume of the cube.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * This method sets the volume of the cube.
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * This method set the input value using command-line arguments and inputcheck
     * with recognize traits of engine. If the length of `args` is not 2, an error
     */
    public void setInput(String[] args) {
        try {
            if (args.length != 1) {
                throw new OneInputException(engineName);
            }

            double inputValue = Double.parseDouble(args[0]);

            if (inputValue < 0) {
                throw new NegativeNumberException(engineName);
            }

            setSideLength(inputValue);
        } catch (OneInputException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException(engineName, args[1]);
        }
    }

    /**
     * This method computes the volume of the cube based on the length of its side
     * using Math.pow().
     */
    public void compute() {
        setVolume(Math.pow(getSideLength(), 3));
    }

    /**
     * This method returns the computed volume of the cube.
     */
    public double getResult() {
        return getVolume();
    }
}

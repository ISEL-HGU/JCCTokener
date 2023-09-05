package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.util.InputChecker;

public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";
    private double sideLength;
    private double volume;

    /**
     * Returns the name of the engine.
     *
     * @return engineName
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * Returns the side length of the cube.
     *
     * @return sideLength
     */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * Sets the side length of the cube.
     *
     * @param sideLength
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * Sets the volume of the cube.
     *
     * @param volume
     */
    public void setResult(double volume) {
        this.volume = volume;
    }

    /**
     * Sets the input for the engine.
     *
     * @param args
     */
    public void setInput(String[] args) {
        if (args.length != 1) {
            InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName, 1);
            System.exit(0);
        }
        try {
            sideLength = Double.parseDouble(args[1]);
        }
        catch (NumberFormatException e) {
            System.exit(0);
        }
        if (sideLength < 0) {
            InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
            System.exit(0);
        }
    }

    /**
     * Computes the volume of the cube.
     */
    public void compute() {
        volume = sideLength * sideLength * sideLength;
    }

    /**
     * Returns the volume of the cube.
     *
     * @return volume
     */
    public double getResult() {
        return volume;
    }
}

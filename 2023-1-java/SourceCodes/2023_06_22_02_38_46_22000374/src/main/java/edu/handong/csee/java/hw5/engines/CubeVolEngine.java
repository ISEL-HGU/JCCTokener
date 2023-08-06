package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * CubeVolEngine.java
 * Computing a cube volume
 */

public class CubeVolEngine implements Computable {
    private static final String engineName = "CUBEVOL";
    private double sideLength;
    private double volume;

    /**
     * engineName is CEUBEVOL
     * 
     * @param engineName Type : String
     */
    public void setEngineName(String engineName) {
    }

    /**
     * This method return engineName.
     * 
     * @return engineName.
     */
    public String getEngineName() {
        return engineName;
    }

    /**
     * Set sideLength value.
     * 
     * @param sideLength Type : double
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * This method return getSideLength value.
     * 
     * @return getSideLength value.
     */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * Set volume value.
     * 
     * @param volume Type : double
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * This method return volume.
     * 
     * @return volume.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * This method is for check input - Double or String
     * 
     * @param String ars
     * @return true, false
     */
    public static boolean check(String args) {
        try {
            Double.parseDouble(args);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Set the sideLength value through the input args.
     * # of inputs: >= two integer numbers
     * The input value must not be a negative value.
     *
     * @param args Type : String[]
     */
    public void setInput(String[] args) {
        try {
            if (!check(args[0]))
                throw new MyNumberFormatException(args[0], engineName);
            if (args.length == 0)
                throw new MinimumInputNumberException(engineName);
            if (args.length > 1)
                throw new OneInputException(engineName);
            sideLength = Integer.parseInt(args[0]);
            if (sideLength < 0)
                throw new NegativeNumberException(engineName);
        } catch (MinimumInputNumberException e2) {
            System.out.println(e2.getMessage());
            System.exit(0);
        } catch (NegativeNumberException e3) {
            System.out.println(e3.getMessage());
            System.exit(0);
        } catch (OneInputException e4) {
            System.out.println(e4.getMessage());
            System.exit(0);
        } catch (MyNumberFormatException e5) {
            System.out.println(e5.getMessage());
            System.exit(0);
        }
    }

    /**
     * Compute CubeVolume through sideLength
     */
    public void compute() {
        volume = sideLength * sideLength * sideLength;
    }

    /**
     * This method return CubeVolume.
     * 
     * @return volume
     */
    public double getResult() {
        return volume;
    }
}
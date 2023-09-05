package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is the CubeVolEngine for doing calculate Cube's volume.
 */
public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";
    private double sidelength;
    private double volume;

    /**
     * This is the getter for engineName.
     * @return
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * This is the getter of sidelength.
     * @return
     */
    public double getSidelength() {
        return sidelength;
    }

    /**
     * This is the setter of sidelength.
     * @param sidelength
     */
    public void setNum(double sidelength) {
        this.sidelength = sidelength;
    }

    /**
     * This is the getter of volume.
     * @return
     */
    public double getVolume() {
        return volume;
    }

    /**
     * This is the setter of volume.
     * @param volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * This is the method of CubeVolEngine to get Input data.
     *
     * @param args
     */
    @Override
    public void setInput(String[] args) throws OneInputException, NegativeNumberException {
        int len = args.length;

        if(len != 1)
            throw new OneInputException("Exception-04: You need one input value for " + engineName + ".");

        if(Double.parseDouble(args[0]) < 0)
            throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName + ".");

//        if(len != 2)
//            InputChecker.printErrorMessageForTheNumberOfRequiredInputsAndExit(engineName, 1);
//        if(Double.parseDouble(args[1]) < 0)
//            InputChecker.printErrorMessageForNegativeInputsAndExit(engineName);

        sidelength = Double.parseDouble(args[0]);
    }

    /**
     * This is the method of CubeVolEngine to compute Cube's Volume.
     */
    @Override
    public void compute() {
        volume = sidelength * sidelength * sidelength;
    }

    /**
     * This method is the return method that value from CubeVolEngine.
     *
     * @return
     */
    @Override
    public double getResult() {
        return volume;
    }
}

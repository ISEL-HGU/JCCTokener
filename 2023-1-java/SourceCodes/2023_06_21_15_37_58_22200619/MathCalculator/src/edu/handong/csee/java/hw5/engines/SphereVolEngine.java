package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.util.InputChecker;

public class SphereVolEngine implements Computable {
    private static final String engineName ="SPHEREVOL";
    private double radius;
    private double result;

    /**
     * The method to get the name of the engine.
     * @return engineName
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * The method to get the value of radius.
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * The method to set the value of radius.
     * @param radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * The method to set the value of result.
     * @param result
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * The method to set the input.
     * @param args
     */
    public void setInput(String[] args) {
        if (args.length != 1) {
            InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName, 1);
            System.exit(0);
        }
        try {
            radius = Double.parseDouble(args[1]);
        }
        catch (NumberFormatException e) {
            System.exit(0);
        }
        if (radius < 0) {
            InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
            System.exit(0);
        }
    }

    /**
     * The method to compute the result.
     */
    public void compute() {
        result = (4.0/3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * The method to get the result.
     * @return result
     */
    public double getResult() {
        return result;
    }
}

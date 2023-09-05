package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * MinEngine.java
 * Computing a sphere volume
 */
public class SphereVolEngine implements Computable {
    private static final String engineName = "SPHEREVOL";
    private double radius;
    private double result;

    /**
     * engineName is SPHEREVOL
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
     * Set radius value.
     * 
     * @param radius Type : double
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * This method return radius
     * 
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set result value.
     * 
     * @param result Type : double
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method return result.
     * 
     * @return result.
     */
    public double getResult() {
        return result;
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
     * Set the result value through radius; the inputs args.
     * # of inputs: one number (radius).
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
            radius = Integer.parseInt(args[0]);
            if (radius < 0)
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
     * Compute result through radius
     * result is a sphere volume
     */
    public void compute() {
        result = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }
}

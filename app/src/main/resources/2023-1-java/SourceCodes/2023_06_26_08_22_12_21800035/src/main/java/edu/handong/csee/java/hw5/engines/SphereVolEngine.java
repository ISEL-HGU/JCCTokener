package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is a compute engine that calculates the volume of a sphere with a
 * given radius.
 * This class implements the Computable interface and defines its methods.
 */
public class SphereVolEngine implements Computable {
    private static final String engineName = "SPHEREVOL";
    private double radius;
    private double volume;

    /**
     * This method sets the input values for the computation of the sphere volume.
     * If the number of inputs is not 1 or less then 0, it prints an error message
     * and terminates the program.
     */
    public void setInput(String[] args) {
        try {
            if (args.length != 1) {
                throw new OneInputException(engineName);
            }
            radius = Double.parseDouble(args[0]);

            if (radius < 0) {
                throw new NegativeNumberException(engineName);
            }
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
     * This method computes the volume of the sphere based on the input radius.
     */
    public void compute() {
        // 부피 계산
        volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * This method sets the volume of the sphere.
     */
    public void setResult(double result) {
        this.volume = result;
    }

    /**
     * This method returns the volume of the sphere.
     */
    public double getResult() {
        return volume;
    }

    /**
     * This method returns the radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * This method sets the radius of the sphere.
     */
    public void setRadius(double radius) {

        this.radius = radius;
    }
}
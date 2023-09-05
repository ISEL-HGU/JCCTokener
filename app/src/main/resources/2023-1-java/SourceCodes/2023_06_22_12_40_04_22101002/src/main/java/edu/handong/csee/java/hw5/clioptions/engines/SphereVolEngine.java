package edu.handong.csee.java.hw5.clioptions.engines;

import edu.handong.csee.java.hw5.thread.clioptions.exceptions.*;
/**
 * The SphereVolEngine class implements the Computable interface to
 * compute the volume of a sphere given its radius.
 */
public class SphereVolEngine implements Computable {
    /**
     * Returns the name of this engine
     * @return the name of this engine
     */
    public static String getEngineName() {
        return engineName;
    }

    /**
     * The private static String representing the name of the engine
     */
    private static final String engineName ="SPHEREVOL";
    /**
     * Private double representing the radius of a sphere
     */
    private double radius;
    /**
     * A private double representing the result which is the volume of the sphere
     */
    private double result;

    /**
     * Returns the radius of the sphere
     * @return the radius of the sphere
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the sphere
     * @param radius the radius to set for the sphere
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Sets the result of the computation
     * @param result the computed result to set
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Sets the input for computation
     * @param input an array of Strings representing the input values
     * @throws OneInputException if the input array length is not 1
     * @throws MyNumberFormatException if the input value cannot be parsed as a double
     * @throws NegativeNumberException if the radius is negative
     */
    public void setInput(String[] input) {
    	try {
            int len = input.length - 1;
            if(len != 1) {
                throw new OneInputException(getEngineName());
            }
            try {
                setRadius(Double.parseDouble(input[1]));
            }
            catch(NumberFormatException e) {
        		throw new MyNumberFormatException(getEngineName(),input[1]);
        	}

            if(radius < 0) {
                throw new NegativeNumberException(getEngineName());
            }
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    

    }

    /**
     * Computes the volume of the sphere with the given radius and sets the result
     */
    public void compute() {
        double volume = 0;
        volume = (Math.PI * ( 4.0 / 3.0) * (getRadius() * getRadius() * getRadius()));
        setResult(volume);
    }

    /**
     * Returns the computed result
     * @return the computed volume of the sphere as a double
     */
    public double getResult() {
        return result;
    }
}

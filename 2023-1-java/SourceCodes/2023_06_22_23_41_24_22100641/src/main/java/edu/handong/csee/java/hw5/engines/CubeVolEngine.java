/**

This class is an engine for computing the volume of a cube with a given side length.
The engine implements the Computable interface, which defines three methods: setInput, compute, and getResult.
The input must be a string array with length 2, where the first element is the engine name ("CUBEVOL") and the second element is a positive number representing the side length of the cube.
The engine calculates the volume of the cube using the formula volume = sideLength^3 and returns the result as a double.
*/
package edu.handong.csee.java.hw5.engines;

/**
 * import exceptions 
 */
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * public class cubevolume
 */
public class CubeVolEngine implements Computable {
/**
 *engine Name
 */
private static final String engineName = "CUBEVOL";

/**
 * getter enginename
 */
public static String getEnginename() {
    return engineName;
}

/**
 * sideLength
 */
private double sideLength;

/**
 * getter sidelength
 */
public double getSideLength() {
    return sideLength;
}

/**
 * setter sidelength
 */
public void setSideLength(double sideLength) {
    this.sideLength = sideLength;
}

/**
 *volume
 */
private double volume;






/**
 * getter volume
 */
public double getVolume() {
    return volume;
}

/**
 * setter volume
 */
public void setVolume(double volume) {
    this.volume = volume;
}

/**
Sets the input value for the computation.
*/
public void setInput(String[] args) throws MyNumberFormatException, OneInputException, NegativeNumberException {
    if (args.length == 1) {
        try {
            sideLength = Double.parseDouble(args[0]);
            if (sideLength < 0) {
                throw new NegativeNumberException(engineName);
            }
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException(args[0], engineName);
        }
    } else if (args.length != 1) {
        throw new OneInputException(engineName);
    }
}

 
 



/**
 * Computes the volume of the cube using the formula volume = sideLength^3.
 */
public void compute() {
    volume = Math.pow(sideLength, 3);
}

/**
 * Returns the computed volume of the cube as a double.
 * @return the computed volume of the cube as a double.
 */
public double getResult() {
    return volume; 
}



}
package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import java.lang.Math;

/**
*This class implements the Computable interface to calculate the volume of a sphere.
*The input required for this engine is the radius of the sphere, and the result is the volume of the sphere.
*The formula used to calculate the volume of a sphere is 4/3 * PI * r^3.
*/
public class SphereVolEngine implements Computable {
    private static final String engineName ="SPHEREVOL";
/**
*Returns the name of the engine.
*@return the name of the engine as a String.
*/
public static String getEnginename() {
    return engineName;
}

// Define instance variables for radius and result
private double radius;
private double result;

/**
*Returns the value of the radius set for this SphereVolEngine instance.
*@return the value of the radius
*/
public double getRadius() {
    return this.radius;
}
/**
*Sets the value of the radius of the sphere to be computed.
*@param radius a double value representing the radius of the sphere
*/
public void setRadius(double radius) {
    this.radius = radius;
}
/**
*Sets the result of the computation.
*@param result a double value representing the result of the computation
*/
public void setResult(double result) {
    this.result = result;
}

/**
 * This method takes an array of String inputs as argument and sets the instance variable radius based on the input.
 * It also checks the validity of the input using the InputChecker utility class.
 * If the input is not valid, it prints an error message and exits the program.
 * @param args an array of String inputs
 */
public void setInput(String[] args) {
	try {
		if(args.length != 1) {
			throw new OneInputException(engineName);
		}

		try {
			radius = Double.parseDouble(args[0]);
		} catch(NumberFormatException e) {
			throw new MyNumberFormatException(args[0], engineName);
		}

		if(radius < 0) {
			throw new NegativeNumberException(engineName);
		}
	} catch(OneInputException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	} catch(NegativeNumberException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	} catch(MyNumberFormatException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
}

/**
 * This method calculates the volume of a sphere using the formula 4/3 * PI * r^3.
 */
public void compute() {
    result = 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
}

/**
 * This method returns the computed result of the engine, which is the volume of the sphere.
 * @return the volume of the sphere as a double
 */
public double getResult() {
    return result;
}

}

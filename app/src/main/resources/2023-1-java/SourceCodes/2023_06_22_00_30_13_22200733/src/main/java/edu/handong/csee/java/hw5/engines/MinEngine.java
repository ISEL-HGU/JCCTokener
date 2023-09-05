package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
*This class is a concrete implementation of the Computable interface.
*It provides the functionality to calculate the minimum value among given doubles.
*The computation is done by setting inputs using setInput method,
*then calling compute method which calculates and sets the result value.
*The result can be obtained by calling getResult method.
*/

public class MinEngine implements Computable {
    private static final String engineName ="MIN";
    /**
 * This method returns the name of the engine.
 * 
 * @return The name of the engine
 */
public static String getEnginename() {
    return engineName;
}

private double[] inputs;
private double result;

/**
 * A setter method for the array of input numbers.
 * @param inputs the array of input numbers to set.
 */
public void setInputs(double[] inputs) {
    this.inputs = inputs;
}

/**
 * A setter method for the result value.
 * @param result the result value to set.
 */
public void setResult(double result) {
    this.result = result;
}

/**
 * This method returns the inputs that were set.
 * 
 * @return The inputs that were set
 */
public double[] getInputs() {
    return this.inputs;
}

/**
 * This method sets the inputs for the computation.
 * If the number of inputs is less than 2, it will exit the program.
 * 
 * @param args An array of inputs passed as strings
 */
public void setInput(String[] args) {
	try {
        if(args.length < 2) {
            throw new MinimumInputNumberException(engineName);
        }
        
        inputs = new double[args.length];

        for(int i = 0; i < args.length; i++) {
            try {
                inputs[i] = Double.parseDouble(args[i]);
            } catch(NumberFormatException e) {
                throw new MyNumberFormatException(args[i], engineName);
            }
        }
    } catch(MinimumInputNumberException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	} catch(MyNumberFormatException e) {
        System.out.println(e.getMessage());
        System.exit(0);
    }
}

/**
 * This method computes the minimum value from the set inputs.
 * 
 */
public void compute() {
    result = inputs[0];
    for(int i = 1; i < inputs.length; i++) {
        if(inputs[i] < result) {
            result = inputs[i];
        }
    }
}

/**
 * This method returns the computed result.
 * 
 * @return The computed result
 */
public double getResult() {
    return result;
}

}

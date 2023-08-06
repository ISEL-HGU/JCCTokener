package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
/**
*This class represents an engine that computes the maximum value among given numbers.
*It implements the Computable interface.
*/
public class MaxEngine implements Computable {
    private static final String engineName ="MAX";

    /**
 * A static method that returns the name of the engine.
 * @return the name of the engine.
 */
public static String getEnginename() {
    return engineName;
}

// An array of input numbers.
private double[] inputs;

// The maximum value among input numbers.
private double result;

/**
 * A getter method for the array of input numbers.
 * @return the array of input numbers.
 */
public double[] getInputs() {
    return this.inputs;
}

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
 * A method that sets the input numbers for the computation.
 * It checks the validity of the input values and prints error messages if necessary.
 * @param args the input values passed as arguments from the main method.
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
 * A method that computes the maximum value among input numbers.
 */
public void compute() {
    result = inputs[0]; // Initialize the maximum value as the first input value.
    for(int i = 1; i < inputs.length; i++) {
        if(inputs[i] > result) { // If the current input value is greater than the maximum value.
            result = inputs[i]; // Set the current input value as the maximum value.
        }
    }
}

/**
 * A getter method for the result value.
 * @return the result value.
 */
public double getResult() {
    return result;
}

}

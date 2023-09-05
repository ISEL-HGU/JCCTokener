/**
 * engines
 */
package edu.handong.csee.java.hw5.engines;
/**
 * import exceptions 
 */
import edu.handong.csee.java.hw5.exceptions.*;

/**
This interface represents a computable object that can perform a computation on given inputs and produce a result.
*/
public interface Computable {

    /**
    Sets the input for the computation. The input is represented as an array of Strings.
    */
    public void setInput(String[] args) throws OneInputException, NegativeNumberException, MinimumInputNumberException, InvalidCommandException, MyNumberFormatException;

    /**
    Computes the result based on the given input.
    */
    public void compute();
    /**
    Returns the result of the computation.
    */
    public double getResult();

}

    

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
 
/**
* It specifies the methods that all the engine classes must implement to be able to perform a computation.
*/
public interface Computable {
    /**
    * Sets the input values required for the computation.
    * @param args an array of input values provided as string arguments.
    */
    public void setInput(String[] args);

    /**
    * Computes the result of the operation based on the input values provided.
    */
    public void compute();

    /**
    * Returns the result of the computation.
    * @return the result of the computation as a double value.
    */
    public double getResult();

    /**
    * Checks if the input values provided are valid for the computation.
    * @param args an array of input values provided as string arguments.
     * @throws NegativeNumberException 
     * @throws OneInputException 
     * @throws MinimumInputNumberException 
    */
    public void checkInput(String[] args) throws OneInputException, NegativeNumberException, MinimumInputNumberException;
}

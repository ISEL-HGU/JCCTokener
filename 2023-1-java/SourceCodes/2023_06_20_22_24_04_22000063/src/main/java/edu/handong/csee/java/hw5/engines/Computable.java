package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This is the interface of this Calculator program
 */
public interface Computable {
    /**
     * This is the method of Computable to get Input data
     * @param args
     */
    void setInput(String[] args) throws FileException, MinimumInputNumberException, MyNumberFormatException, NegativeNumberException, OneInputException;

    /**
     * This is the method of Computable to compute some values
     */
    void compute() throws MyNumberFormatException, FileException, NegativeNumberException, MinimumInputNumberException;

    /**
     * This method is the return method
     * @return
     */
    double getResult();
}

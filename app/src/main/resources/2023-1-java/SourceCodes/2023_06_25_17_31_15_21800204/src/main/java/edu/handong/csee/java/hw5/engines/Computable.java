package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;
/**
This is an interface for classes that can perform a computation.
Any class that implements this interface must provide the compute, setInput and getResult methods.
*/
public interface Computable {

    /**
    This method performs the computation.
    */
    public void compute() throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException;
    /**
    This method sets the input values for the computation.
    @param args An array of strings that represent the input values.
    */
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException, MyNumberFormatException, OneInputException;
    /**
    This method returns the result of the computation.
    @return The result of the computation.
    */
    public double getResult();
    }

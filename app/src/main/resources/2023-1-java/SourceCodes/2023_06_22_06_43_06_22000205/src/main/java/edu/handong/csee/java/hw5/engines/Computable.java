package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;


/**
 * This is an interface provides a set of methods for computing math problems. 
 * Classes implementing this interface must provide implementations of the methods defined below.
 */
public interface Computable {
	void setInput(String[] inputNum) throws MinimumInputNumberException, NegativeNumberException, OneInputException, MyNumberFormatException;
    void compute();
    double getResult();
}
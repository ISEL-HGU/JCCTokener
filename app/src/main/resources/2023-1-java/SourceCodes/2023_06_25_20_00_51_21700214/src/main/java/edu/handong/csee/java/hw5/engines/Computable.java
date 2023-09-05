package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;
import java.io.FileNotFoundException;
/**
 *  the interface for calculating Cube volume, Factorial, Fibonacci, Greatest common divisor, Least common multiple, Sphere volume, Square root, Maximum value, Minimum value.
 * @author kim hongchan
 *
 */
public interface Computable {
    void setInput(String[] args) throws OneInputException,InvalidCommandException,MinimumInputNumberException,NegativeNumberException,MyNumberFormatException,InvalidOptionException,FileNotFoundException;
    void compute();
    double getResult();
}

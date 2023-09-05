/**
 * exceptions
 */
package edu.handong.csee.java.hw5.exceptions;
/**
Exception class : cannnot be negative
*/
public class NegativeNumberException extends Exception {
    /**
     * method    
     */
    public NegativeNumberException(String engineName) {
        super("Exception-03: The input value cannot be negative for " + engineName + ".");
    }
}

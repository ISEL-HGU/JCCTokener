/**
 * exceptions
 */
package edu.handong.csee.java.hw5.exceptions;
/**
Exception class : at least 2 input values
*/
public class MinimumInputNumberException extends Exception {
    /**
     * method
     */
    public MinimumInputNumberException(String engineName) {
        super("Exception-02: You need at least 2 input values for " + engineName + ".");
    }
}

/**
 * exceptions
 */
package edu.handong.csee.java.hw5.exceptions;
/**
Exception class : only one input
*/
public class OneInputException extends Exception {
    /**
     * 
     * method
     */
    public OneInputException(String engineName) {
        super("Exception-04: You need one input value for " + engineName + ".");
    }
}

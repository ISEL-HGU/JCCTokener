/**
 * exceptions
 */
package edu.handong.csee.java.hw5.exceptions;
/**
Exception class : valid number
*/
public class MyNumberFormatException extends NumberFormatException {
    /**
     * method
     */
    public MyNumberFormatException(String input, String engineName) {
        super("Exception-05: The input value should be converted into a number. (" + input + " is not a number value for " + engineName + ".)");
    }
}

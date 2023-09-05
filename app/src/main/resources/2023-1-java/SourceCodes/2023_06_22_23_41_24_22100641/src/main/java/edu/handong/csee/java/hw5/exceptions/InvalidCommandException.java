/**
 * exceptions
 */
package edu.handong.csee.java.hw5.exceptions;
/**
Exception class : Invalid command
*/
public class InvalidCommandException extends Exception {
    /**
     * method
     */
    public InvalidCommandException(String command) {
        super("Exception-01: Invalid command: " + command + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }
}

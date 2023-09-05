package edu.handong.csee.java.hw5.util;

public class InputChecker {

    /**
     * Print an error message and exit if there are not enough input values for the specified engine.
     * @param engineName The name of the engine
     * @param numOfRequiredInputs The number of input values required
     */
    public static void printErrorMesssageForTheNumberOfRequiredInputsAndExit(String engineName, int numOfRequiredInputs) {
        System.out.println("You need " + numOfRequiredInputs + " input values for " + engineName.toUpperCase() + ".");
        System.exit(0);
    }

    /**
     * Print an error message and exit if there are not enough minimum input values for the specified engine.
     * @param engineName The name of the engine
     * @param numOfRequiredInputs The minimum number of input values required
     */
    public static void printErrorMesssageForTheNumberOfMinimumRequiredInputsAndExit(String engineName, int numOfRequiredInputs) {
        System.out.println("You need " + numOfRequiredInputs + " input values for " + engineName.toUpperCase() + ".");
        System.exit(0);
    }

    /**
     * Print an error message and exit if the input value is negative for the specified engine.
     * @param engineName The name of the engine
     */
    public static void printErrorMesssageForNegativeInputsAndExit(String engineName) {
        System.out.println("The input value cannot be negative for " + engineName.toUpperCase() + ".");
        System.exit(0);
    }
}

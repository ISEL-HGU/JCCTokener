package edu.handong.csee.java.hw5.clioptions.exceptions;
/**
 * The InvalidCommandException class that extends the extends the Exception class is an exception that is thrown when an invalid command is provided.
 * 
 */
public class InvalidCommandException extends Exception {

    /**
     * InvalidCommandException with a specific input value.
     * @param String input the invalid command input
     */
    public InvalidCommandException(String input) {
        super("Exception-01: Invalid command: " + input + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }

    /**
     * Constructs an InvalidCommandException without a specific input value.
     *  Used when the command input is missing.
     */
    public InvalidCommandException() {
        super("Exception-01: Invalid command: \nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }
}


package edu.handong.csee.java.hw5.exceptions;

public class MinimumInputNumberException extends Exception{
    public MinimumInputNumberException(String engineName, int numOfRequiredInputs) {
        super("You need " + numOfRequiredInputs + " input values for " + engineName.toUpperCase() + ".");
    }
}

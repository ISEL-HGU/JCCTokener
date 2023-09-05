package edu.handong.csee.java.hw5.exceptions;

public class NegativeNumberException extends Exception {
    public NegativeNumberException(String engineName) {
        super("The input value cannot be negative for " + engineName.toUpperCase() + ".");
    }
}

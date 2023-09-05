package edu.handong.csee.java.hw5.exceptions;

public class MyNumberFormatException extends NumberFormatException{
    public MyNumberFormatException(String engineName, String input) {
        super("The input value should be converted into a number. (" + input + " is not a number value for " + engineName.toUpperCase() + "().");
    }
}

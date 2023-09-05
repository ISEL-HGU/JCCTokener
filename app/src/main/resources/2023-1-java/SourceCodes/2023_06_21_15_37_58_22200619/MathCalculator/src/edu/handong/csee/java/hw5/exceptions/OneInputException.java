package edu.handong.csee.java.hw5.exceptions;

public class OneInputException extends Exception{
    public OneInputException(String engineName) {
        super("You need one input value for " + engineName.toUpperCase() + ".");
    }
}

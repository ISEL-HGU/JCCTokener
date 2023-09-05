package edu.handong.csee.java.hw5.exceptions;

public class InvalidCommandException extends Exception{
    public InvalidCommandException() {
        super("Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }
}
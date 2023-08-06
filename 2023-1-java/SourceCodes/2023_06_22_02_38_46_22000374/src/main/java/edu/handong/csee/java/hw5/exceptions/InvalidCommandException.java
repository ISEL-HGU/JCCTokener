package edu.handong.csee.java.hw5.exceptions;
/**
 *  InvalidCommandException.java
 */
public class InvalidCommandException extends Exception{
    /**
    * InvalidCommandException / no param
    */ 
	public InvalidCommandException(){
        super("Exception-01: Invalid command: \nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");

    }
    /**
    * InvalidCommandException - getMessage
    * @param errMsg
    */
	public InvalidCommandException(String errMsg) {
        super("Exception-01: Invalid command: " + errMsg + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    }

}


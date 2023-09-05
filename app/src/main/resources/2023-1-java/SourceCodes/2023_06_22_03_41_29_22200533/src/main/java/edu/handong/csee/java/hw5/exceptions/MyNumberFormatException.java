package edu.handong.csee.java.hw5.exceptions;

/**
 * Class that handles exceptions when input comes in, not numbers.
 */
public class MyNumberFormatException extends NumberFormatException {

	/**
	 * Method to pawn error messages to the parent class.
	 */
    public MyNumberFormatException(String a, String b) {
        super("Exception-05: The input value should be converted into a number. ("+a+" is not a number value for "+b+".)");

    }
   
    

}

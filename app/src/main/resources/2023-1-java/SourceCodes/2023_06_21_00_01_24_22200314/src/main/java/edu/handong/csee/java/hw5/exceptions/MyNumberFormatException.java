package edu.handong.csee.java.hw5.exceptions;

/**
 * This class is a exception class about Number Formatting errors. 
 */
public class MyNumberFormatException extends NumberFormatException {	
	/**
	 * This constructor calls NumberFormatException class' methods.
	 */
	public MyNumberFormatException(){
		super();
	}
	/**
	 * This constructor gives Exception-05 Message to Exception class with the engine name and the string that made the error.
	 * @param errorName The string that made the error
	 * @param engineName The name of the engine
	 */
	public MyNumberFormatException(String errorName, String engineName) {
		super("Exception-05: The input value should be converted into a number. (" + errorName +  " is not a number value for " + engineName + ".)");
	}
	/**
	 * This method tests whether the given String in the parameter is a number or is not a number. It returns a boolean value.
	 * @param number The String that would be tested.
	 * @return If the String is a number, it would return true. False in other cases.
	 */
	public static boolean isNumber(String number) {

		boolean flag = true;
		if (number == null || "".equals(number)) {
			return false;
		}

		int string_size = number.length();
		int index = 0;

		if (number.charAt(0) == 45) {
			index = 1;
		}

		for (int i = index; i < string_size; ++i) {
			if (!(48 <= ((int) number.charAt(i)) && 57 >= ((int) number.charAt(i)))) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * This method tells if the strValue is a float or not.
	 * @param strValue The String that would be tested.
	 * @return Result in boolean
	 */
	public static boolean isFloat(String strValue) {
	    try {
	      Float.parseFloat(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	}
	
}

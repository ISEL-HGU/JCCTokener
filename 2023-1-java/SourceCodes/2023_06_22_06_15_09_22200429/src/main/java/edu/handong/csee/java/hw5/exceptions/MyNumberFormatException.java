package edu.handong.csee.java.hw5.exceptions;



/**
 * InvalidCommandException is say put some engine option
 */
public class MyNumberFormatException extends NumberFormatException {
		
		/**
		 * Constructor
		 */
		public MyNumberFormatException() {
			
		}
		/**
		 * MyNumberFormatException make message
		 */
		public MyNumberFormatException(String message){
			super(message) ; 
		}

}
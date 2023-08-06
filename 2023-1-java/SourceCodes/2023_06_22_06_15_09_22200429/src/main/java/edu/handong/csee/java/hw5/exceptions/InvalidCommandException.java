package edu.handong.csee.java.hw5.exceptions;



/**
 * InvalidCommandException is say put some engine option
 */
public class InvalidCommandException extends Exception {
		
		/**
		 * Constructor
		 */
		public InvalidCommandException() {
		}
		
		/**
		 * InvalidCommnadException that make message
		 */
		public InvalidCommandException(String message){
			super(message) ; 
		}

}	
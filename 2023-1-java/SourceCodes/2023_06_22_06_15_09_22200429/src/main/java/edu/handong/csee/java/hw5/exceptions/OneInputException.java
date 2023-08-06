package edu.handong.csee.java.hw5.exceptions;



/**
 * InvalidCommandException is say put some engine option
 */
public class OneInputException extends Exception {
	
		
	/**
	 * Constructor
	 */
	public OneInputException() {
	}
	
	
		/**
		 * OneINputException make message
		 */
		public OneInputException(String message){
			super(message) ; 	
		}

}
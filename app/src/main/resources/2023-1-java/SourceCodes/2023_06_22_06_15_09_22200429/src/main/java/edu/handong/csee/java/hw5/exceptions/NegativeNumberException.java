package edu.handong.csee.java.hw5.exceptions;



/**
 * InvalidCommandException is say put some engine option
 */
	public class NegativeNumberException extends Exception {
		
		/**
		 * constructor
		 */
		public NegativeNumberException(){
		}
		
		/**
		 * NegativeNumberException make message
		 */
		public NegativeNumberException(String message){
			super(message) ; 
		}

}
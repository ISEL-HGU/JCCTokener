package edu.handong.csee.java.hw5.engines;


import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that provides a service for calculating the volume of a cube
 *
 */
public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";
    private double sideLength;
    private double volme;


	/**
	 * The method of obtaining the input number to be calculated.
	 * Before handing over each input to the field side length, make sure that the input number is not negative, with one input number.
	 * Hand over the input value to the fieldideLength.
	 */
    public void setInput(String[] args) {
    	try {            
			if (args.length != 1)
				throw new OneInputException(engineName);       
		}
		catch(OneInputException e) {            
			System.out.println(e.getMessage()); 
			System.exit(0);
		} 
    	
    	try {
    	    sideLength = Integer.parseInt(args[0]);
    	    
    	    
    	} catch (NumberFormatException e) {
    		try {
    			throw new MyNumberFormatException(args[0], engineName);
    		}
    		catch (MyNumberFormatException e1) {
        		
        		System.out.println(e1.getMessage()); 
    			System.exit(0);
        		
        	}
    	    
    	} 
    	
    	try {            
        		if(Integer.parseInt(args[0]) < 0) {
        			throw new NegativeNumberException(engineName);
        		}
		}
		catch(NegativeNumberException e) {            
			System.out.println(e.getMessage()); 
			System.exit(0);
		}
    	
    	
	
	

    }


	/**
     * Method for calculating the volume of a cube.
     * Hand over the result value.
     */
	public void compute() {
		volme = Math.pow(sideLength, 3);
	}
	
	/**
	 * A method that returns the calculated result value so that it can be used by other classes or methods.
	 */
	public double getResult(){
		return volme;
	}
	
	
	/**
	 * The getter method that returns engine name for use by other methods.
	 */
	public static String getEnginename() {
		return engineName;
	}

	/**
	 * The getter method that returns input for use by other methods.
	 */
	public double getSideLength() {
		return sideLength;
	}


	/**
	 * A setter method that allows results to be obtained from other methods.
	 */
	public void setVolme(double volme) {
		this.volme = volme;
	}
	
	

}

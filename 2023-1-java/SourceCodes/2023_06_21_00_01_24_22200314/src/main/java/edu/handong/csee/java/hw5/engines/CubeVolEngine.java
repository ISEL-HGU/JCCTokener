package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * This class calculate the volume of the value input received from Calculator.java
 */
public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";
    /**
     * This method returns the Engine Name.
     * @return The Engine Name of the class.
     */
    public static String getEnginename() {
        return engineName;
    }

    private double sideLength;
    private double volume;


    /**
     * This method returns the Side Length of the Cube.
     * @return The Side Length of the Cube.
     */
    public double getSideLength() {
        return sideLength;
    }
    /**
     * This method sets the value of Side Length of the cube to the given parameter sideLength.
     * @param sideLength The parameter that has the value to be set as sideLength.
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }
    /**
     * This method returns the Volume of the Cube.
     * @return The Volume of the Cube. 
     */
    public double getVolume() {
        return volume;
    }
    /**
     * This method sets the value of Volume of the cube to the given parameter volume.
     * @param volume The parameter that has the value to be set as volume.
    */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * This method sets the sideLength as the arg[0] index. 
     * There are some exceptions: 1. If there are more than 1 received arguments, the program is prints an error and gets terminated. 2. If the value of the arg[0] is negative, the program is prints an error and gets terminated. 
     * @param arg String array from the terminal. This value would be the input value also known as the sideLength.
     */
    public void setInput(String[] arg) {
    	try {
	        if(arg.length != 2){
	        	throw new OneInputException(engineName);
	            //InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName, 1);
	        }
	
	        if(!MyNumberFormatException.isNumber(arg[1])) {
        		throw new MyNumberFormatException(arg[1], engineName);
        	}
	        
	        if(Integer.parseInt(arg[1]) < 0){
	        	throw new NegativeNumberException(engineName);
	            //InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
	        }
	        
	        sideLength = Double.parseDouble(arg[1]);
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e){
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}


    }

    /**
     * This method calculates and sets the value of volume (sideLength)^3. 
     */
    public void compute() {
    	volume = Math.pow(sideLength,3);
    }
    
    
    
    /**
     * This method returns the Volume of the Cube.
     * @return The value of Volume.
     */
    public double getResult() {
    	return volume;
    }
}


package edu.handong.csee.java.hw5.clioptions.engines;

import edu.handong.csee.java.hw5.clioptions.exceptions.*;
/**
* This class implements the Computable interface and represents a cube volume calculation engine.
* It calculates the volume of a cube based on the length of its side.
*/
public class CubeVolEngine implements Computable {
    /**
    * The private static String that represents the name of the engine
    */
    private static final String engineName ="CUBEVOL";

    /**
    * Returns the name of the engine
    * @return the name of the engine as a String
    */
    public static String getEngineName() {
        return engineName;
    }

    /**
    * The length of the side of the cube represented as a double 
    */
    private double sideLength;

    /**
    * The volume of the cube represented as a double
    */
    private double volume;

    /**
    * Returns the length of the side of the cube
    * @return the length of the side of the cube as a double
    */
    public double getSideLength() {
        return sideLength;
    }

    /**
    * Sets the length of the side of the cube
    * @param sideLength the length of the side of the cube as a double
    */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    /**
    * Returns the volume of the cube
    * @return the volume of the cube as a double
    */
    public double getVolume() {
        return volume;
    }

    /**
    * Sets the volume of the cube
    * @param volume the volume of the cube as a double
    */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * Sets the input values for the calculation
     * 
     * @param input an array of Strings representing the input values
     * @throws OneInputException if the length of input is not equal to 1
     * @throws MyNumberFormatException if the input value is not a valid number
     * @throws NegativeNumberException if the input value is negative
     */
    
    public void setInput(String[] input) {
    	try {
            int len = input.length - 1;
            if(len != 1) {
                throw new OneInputException(getEngineName());
            }
            try {
            	setSideLength(Double.parseDouble(input[1]));
            }catch(NumberFormatException e) {
        		throw new MyNumberFormatException(getEngineName(),input[1]);
        		
        	}
            
            if(getSideLength() < 0) {
                throw new NegativeNumberException(getEngineName());
            }
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}

    
    }

    /**
    * Computes the volume of the cube based on the input values
    */
    public void compute(){
        setVolume(getSideLength() * getSideLength() * getSideLength());
    }

    /**
    * Returns the result of the calculation
    * @return the result of the calculation as a double
    */
    public double getResult(){
        return getVolume();
    }


}

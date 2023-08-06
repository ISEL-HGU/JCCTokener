package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;

/**
* A class that computes the volume of a cube with a given side length.
* Implements the Computable interface.
*/
public class CubeVolEngine implements Computable {
    private static final String engineName = "CUBEVOL";
    private double sideLength;
    private double volume;

    /**
     * Getter for the engin name.
     * @return String of engine name
     */
    public String getEngineName(){
        return engineName;
    }

    /**
     * Getter for sideLength.
     * @return double type of side length
     */
    public double getSideLength() {
        return this.sideLength;
    }

    /**
     * Setter for sideLength which gets double type of parameter
     * @param sideLength
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }
    /**
     * Getter for getVolume
     * @return double type of volume
     */
    public double getVolume() {
        return this.volume;
    }

    /**
     * setter for volume which gets double type of parameter
     * @param volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }
    
    /**
    * Checks the input arguments for correctness.
    * @param args an array of String arguments
     * @throws OneInputException, NegativeNumberException 
    * @throws IllegalArgumentException if there are more than 2 arguments or the input value is negative.
    */
    @Override
    public void checkInput (String[] args) throws OneInputException, NegativeNumberException, MyNumberFormatException{
    	String inputString = args[1];
        if (args.length > 2) throw new OneInputException("You need one input value for "+engineName+".");
        
        try {
        	int inputNumber = Integer.parseInt(inputString);
        	if (inputNumber < 0) throw new NegativeNumberException("The input value cannot be negative for "+ engineName + ".");        	
        }catch(NumberFormatException nfe) {
        	throw new MyNumberFormatException("The input value should be converted into a number. ("+ inputString + " is not a number value for "+engineName+".)");
        }
        
    }

    /**
    * Sets the input value from the command line arguments.
    * @param args an array of String arguments
    */
    @Override
    public void setInput(String[] args){
        this.sideLength = Double.parseDouble(args[1]);
    }

    /**
    * Computes the volume of the cube with the given side length.
    * The result is stored in the instance variable 'volume'.
    */
    @Override
    public void compute(){
        volume = sideLength * sideLength * sideLength;
    }
    
    /**
    * Returns the result of the computation.
    * @return the volume of the cube as a double.
    */
    @Override
    public double getResult(){
        return volume;
    }
}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * The `CubeVolEngine` class implements the `Computable` interface and represents an engine for computing 
 * the volume of a cube given its side length.
 */
public class CubeVolEngine implements Computable {
    private static final String engineName = "CUBEVOL"; // The name of this engine
    private double sideLength; // The side length of the cube
    private double volume; // The volume of the cube
        /**
     * Sets the side length of the cube.
     * 
     * @param sideLength the new side length of the cube
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
        updateVolume();
    }

    /**
     * Returns the side length of the cube.
     * 
     * @return the side length of the cube
     */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * Updates the volume of the cube based on the current side length.
     */
    private void updateVolume() {
        volume = sideLength * sideLength * sideLength;
    }
    /**
     * Sets the input values for the engine.
     * 
     * @param args the array of input values
     */
    public void setInput(String[] args) {
    try {

    	if (Double.parseDouble(args[1]) < 0) 
            throw new NegativeNumberException(engineName);
    	if ((args.length)-1>1) 
            throw new OneInputException(engineName);

        this.sideLength = Double.parseDouble(args[1]);
    }catch(NegativeNumberException e){
    	 System.out.println (e.getMessage ());
    	 System.exit(0);
    }catch(OneInputException e){
   	 System.out.println (e.getMessage ());
   	System.exit(0);
   }
    }

    /**
     * Performs the computation for the engine.
     * Claculate volume
     */
    public void compute() {
        this.volume = Math.pow(this.sideLength, 3);
    }

    /**
     * Returns the result of the computation.
     * 
     * @return the result of the computation
     */
    public double getResult(){
        return this.volume;
    }
}


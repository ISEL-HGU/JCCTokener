package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This code calculates the volume of a cube given its side length.
 */
public class CubeVolEngine implements Computable {
    /** 
     * sets the engine name as CUBEVOL
     */
    private static final String engineName = "CUBEVOL"; 
    /** 
     * sidelength for the calculating volume
     */
    private double sideLength;
    /** 
     * returning volume 
     */
    private double volume; 

    /** 
     * Read the argument and see if there is error 
     */
    public void setInput(String[] args) {
        try {
            int SubFirstArg = args.length - 1;
            if (SubFirstArg != 0) {
                throw new OneInputException(engineName);
            } else {
            	sideLength = Integer.parseInt(args[0]);
                if (sideLength < 0) {
                    throw new NegativeNumberException(engineName);
                }
            }
        } catch (OneInputException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * computing the volume by muliplying sidelength 3 times
     */
    public void compute() {
        
        volume = sideLength * sideLength * sideLength;
    }
    /**
     * Set the input array.
     * 
     * @param input array containing the input numbers
     */
    public void setInputArray(double input) {
        this.sideLength = input;
    }

    /**
     * Get the input array.
     * 
     * @return input array containing the input numbers
     */
    public double getInputArray() {
        return sideLength;
    }
    /**
     * returning the volume for printing
     */
    public double getResult() {
        
        return volume;
    }
}
package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;


/**

*This class implements the Computable interface to calculate the volume of a cube.
*It takes one input argument which is the length of one side of the cube, and returns the volume of the cube.
*If the input is less than 0, an error message will be printed and the program will exit.
*The input argument should be passed as a command line argument.
*/

public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";
    /**
 * This method returns the name of the engine.
 * 
 * @return The name of the engine
 */
    public static String getEnginename() {
        return engineName;
    }
    private double sideLength;
    private double volume;
    
    /**
     * Getter for the length of one side of the cube
     * @return The length of one side of the cube
     */
    public double getSideLength() {
        return sideLength;
    }
    /**
     * Setter for the length of one side of the cube
     * @param sideLength The length of one side of the cube
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }
    /**
     * Getter for the volume of the cube
     * @return The volume of the cube
     */
    public double getVolume() {
        return volume;
    }
    /**
     * Setter for the volume of the cube
     * @param volume The volume of the cube
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }
    /**
     * Parses the input arguments for the engine.
     * If the number of input arguments is not 2, an error message will be printed and the program will exit.
     * The first input argument will be ignored.
     * The second input argument is the length of one side of the cube, and will be stored in the sideLength field.
     * If the length is less than 0, an error message will be printed and the program will exit.
     * @param args The input arguments for the engine
     */
    public void setInput(String[] args) {

        try{
            if(args.length != 1) {
                throw new OneInputException(engineName);
            }

            try {
                sideLength = Double.parseDouble(args[0]);
            } catch(NumberFormatException e) {
                throw new MyNumberFormatException(args[0], engineName);
            }

            if(sideLength < 0) {
                throw new NegativeNumberException(engineName);
            }

        } catch(OneInputException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch(NegativeNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch(MyNumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
    }
    /**
     * Computes the volume of the cube using the length of one side of the cube.
     * The volume will be stored in the volume field.
     */
    public void compute() {
    	volume = sideLength * sideLength * sideLength;
    }
    /**
     * Returns the result of the engine, which is the volume of the cube.
     * @return The volume of the cube
     */
    public double getResult() {
    	return volume;
    }
}
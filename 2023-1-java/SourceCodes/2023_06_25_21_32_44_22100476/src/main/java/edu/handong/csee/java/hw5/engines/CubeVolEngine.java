package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**

This is an implementation of the Computable interface which calculates the volume of a cube given its side length.
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
*/
public class CubeVolEngine implements Computable {
    private String engineName ="CUBEVOL";
    private double sideLength;
    private double volume;

    /**
 * Sets the input for the computation of the cube volume.
 * @param args An array of String containing the side length of the cube.
 * 
 */
    public void setInput(String[] args){
        try {
        	if (args.length != 1) {
                
                // handle error case where no input value was providedX
        		throw new OneInputException(engineName);
            }

        	if(!args[0].matches("[-+]?\\d*\\.?\\d+")) {
        		throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + args[1] + " is not a number value for CUBEVOL.)");
            	
        	}

            if (Integer.parseInt(args[0]) < 0) {
                
            	throw new NegativeNumberException(engineName);
                // handle error case where input value is negative
            }
        } catch(NegativeNumberException e){
            System.out.println(e.getMessage());
	    	 System.exit(0);
	    	 return;
        } 
//        catch(MyNumberFormatException e) {
//        	System.out.println("HH");
//        }
        catch(Exception e){
        	System.out.println(e.getMessage()); // print an error message if the input is invalid
	    	 System.exit(0);
	    	 return;
        }
        sideLength = Double.parseDouble(args[0]);

    }

	/**
 * Calculates the volume of the cube.
 */
    public void compute(){
        volume = Math.pow(sideLength, 3);
    }

    /**

Returns the value of the side length of the cube.
@return The side length of the cube as a double value.
*/
    public double getSideLength(){
        return sideLength;
    }
    /**
 * Returns the result of the computation, which is the volume of the cube.
 * @return A double representing the volume of the cube.
 */
    public double getResult(){
        return volume;
    }
    /**
 * Sets the result of the computation, which is the volume of the cube.
 * @param a A double representing the volume of the cube.
 */
    public void setResult(double a){
        volume = a;
    }
    /**
 * Sets the name of the engine.
 * @param a A String representing the name of the engine.
 */
    public void setEngineName(String a){
        engineName = a;
    }

    /**
 * Returns the name of the engine.
 * @return A String representing the name of the engine.
 */
    public String getEngineName(){
        return engineName;
    }
}

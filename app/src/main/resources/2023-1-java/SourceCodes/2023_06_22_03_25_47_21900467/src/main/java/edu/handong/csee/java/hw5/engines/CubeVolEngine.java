package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * This is a class that calculates the volume of a cube.
 */
public class CubeVolEngine implements Computable {

	private static String engineName ="CUBEVOL";
    private double sideLength;
    private double volume;

    /**
     * It is a method that sets the value to be calculated.
     */
    public void setInput(String[] args){

    	try {
            if(args.length>1){
                throw new OneInputException(getEnginename(), 0);
            }
            if(! args[0].matches("-?\\d+(\\.\\d+)?")) {
            	throw new MyNumberFormatException(getEnginename(), args[0]);
            }
            sideLength = Double.parseDouble(args[0]);
            if(sideLength<0){
            	throw new NegativeNumberException(getEnginename(), 0);
            }
    	}
    	catch (NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    }
    /**
     * This is the actual calculation method.
     */
    public void compute(){
        volume = sideLength*sideLength*sideLength;
    }
    /**
     * This is the method of obtaining the result value.
     */
    public double getResult(){
        return volume;
    }
    /**
     * Return enginame.
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * Return sidelength.
     */
    public double getSideLength() {
        return sideLength;
    }
    /**
     * Set sideLength.
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }
    
    /**
     * Return volume.
     */
    public double getVolume() {
        return volume;
    }
    /**
     * Set volume.
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }
    /**
     * set engineName
     * @param enginename
     */
    public static void setEnginename(String enginename) {
		engineName = enginename;
	}
}

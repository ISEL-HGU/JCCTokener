package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This class is one of Engine class for computing the cube volume.
 */
public class CubeVolEngine implements Computable {
	/**
	 * Every engines have its own name to help user distinguish it with other engines
	 * This is a field about engine Name called CUBEVOL
	 */
    private static final String engineName ="CUBEVOL";
    /**
	 * This is a field about side lenght.
	 */
    private int sideLength;
    /**
	 * This is a field about the volum of cube.
	 */
    private double volume;

	/**
	 * Method SetInput gets arguments and determines if the program can or cannot perform normally.
	 * If the arguments are not suitable, print error message from InputChecker
	 * Such as the number of required inputs, or negative number.
	 */
    public void setInput(String[] engineName) {
    	sideLength = Integer.parseInt(engineName[0]);

    }
	/**
	 * The values are computed in this method.
	 * The fomula of sphere volume is [side lenght ^3]
	 */
    public void compute() {
    	volume = Math.pow(sideLength, 3);
    }
	/**
	 * the result of computation is returned in this method.
	 */
    public double getResult(){
    	return volume;
    }
	/**
	 * this is a getter of Engine name
	 */
	public static String getEnginename() {
		return engineName;
	}
	/**
	 * this is a getter of side Length
	 */
	public int getSideLength() {
		return sideLength;
	}
	/**
	 * this is a setter of side Length
	 */
	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}
	/**
	 * this is a getter of volume
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 * this is a setter of volume
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}
}

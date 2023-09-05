
package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * This is a class to find the volume of a sphere.
 */
public class SphereVolEngine implements Computable {

	private static String engineName ="SPHEREVOL";
    private double radius;
    private double result;
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
            radius = Double.parseDouble(args[0]);
            if(radius<0){
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
        result = Math.pow(radius,3)* 4/3* Math.PI ;
    }
    /**
     * This is the method of obtaining the result value.
     */
    public double getResult(){
        return result;
    }

    /**
     * It returns enginame.
     */
    public static String getEnginename() {
        return engineName;
    }
    /**
     * It returns radius.
     */
    public double getRadius() {
        return radius;
    }
    /**
     * It sets radius.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
    /**
     * It sets result.
     */
    public void setResult(double result) {
        this.result = result;
    }
    /**
     * set enginename
     * @param enginename
     */
    public static void setEnginename(String enginename) {
		engineName = enginename;
	}
}

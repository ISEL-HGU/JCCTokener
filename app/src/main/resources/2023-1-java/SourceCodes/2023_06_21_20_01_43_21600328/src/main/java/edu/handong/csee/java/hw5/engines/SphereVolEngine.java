package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This code calculates the volume of Sphere with n as radius
 */
public class SphereVolEngine implements Computable {
    /** 
     * sets the engine name as SPHEREVOL
     */
    private static final String engineName ="SPHEREVOL";
    /** 
     * radius for the calculating Least Common Multiple
     */
    private double radius;
    /** 
     * returning result 
     */
    private double result;
    
    /** 
     * Read the argument and see if there is error 
     */
    public void setInput(String[] args) {
        try {
            int SubFirstArg = args.length - 1;
            if (SubFirstArg != 0) {
                throw new OneInputException(engineName);
            } else {
            	radius = Integer.parseInt(args[0]);
                if (radius < 0) {
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
     * computing by using the formula
     */
    public void compute(){
        result = (4.0/3)*Math.PI*(radius)*(radius)*(radius);
    }
    /**
     * Set the input array.
     * 
     * @param input array containing the input numbers
     */
    public void setInputArray(double input) {
        this.radius = input;
    }

    /**
     * Get the input array.
     * 
     * @return input array containing the input numbers
     */
    public double getInputArray() {
        return radius;
    }
    /**
     * returning the result for printing
     */
    public double getResult(){
        return result;
    }
}

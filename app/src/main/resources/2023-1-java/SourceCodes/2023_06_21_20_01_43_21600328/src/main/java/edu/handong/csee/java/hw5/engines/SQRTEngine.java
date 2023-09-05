package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This code calculates square root of a number
 */
public class SQRTEngine implements Computable {
    /** 
     * sets the engine name as MIN
     */
    private static final String engineName ="SQRT";
    /** 
     * input for the calculating Least Common Multiple
     */
    private double input;
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
                input = Double.parseDouble(args[0]);
                if (input < 0) {
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
     * computing by using the Math tool sqrt
     */
    public void compute(){
        result = Math.sqrt(input);
    }
    /**
     * Set the input array.
     * 
     * @param input array containing the input numbers
     */
    public void setInputArray(double input) {
        this.input = input;
    }

    /**
     * Get the input array.
     * 
     * @return input array containing the input numbers
     */
    public double getInputArray() {
        return input;
    }
    /**
     * returning the result for printing
     */
    public double getResult(){
        return result;
    }

}

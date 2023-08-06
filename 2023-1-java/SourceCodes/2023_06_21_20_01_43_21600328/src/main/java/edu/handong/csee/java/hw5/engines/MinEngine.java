package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This code calculates the Minimum number within various numbers
 */
public class MinEngine implements Computable {
    /** 
     * sets the engine name as MIN
     */
    private static final String engineName ="MIN";
    /** 
     * inputs for the calculating Least Common Multiple
     */
    private double inputs[];
    /** 
     * returning result 
     */
    private double result;
    
    /** 
     * Read the argument and see if there is error 
     */
    public void setInput(String[] args) {
        try {
            if (args.length < 2) {
                throw new MinimumInputNumberException(2, engineName);
            }
            inputs = new double[args.length];
            for (int i = 0; i < args.length; i++) { // Adjusted loop condition
                inputs[i] = Double.parseDouble(args[i]);
            }
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    /**
     * computing by setting min as first one, then comparing all the numbers
     */
    public void compute(){
        double min = inputs[0];
        for(int i =0; i < inputs.length; i++){
            if(min > inputs[i]){
                min = inputs[i];
            }
        }
        result = min;
    }
    /**
     * Set the input array.
     * 
     * @param input array containing the input numbers
     */
    public void setInputArray(double[] input) {
        this.inputs = input;
    }

    /**
     * Get the input array.
     * 
     * @return input array containing the input numbers
     */
    public double[] getInputArray() {
        return inputs;
    }
    /**
     * returning the result for printing
     */
    public double getResult(){
        return result;
    }
    
}

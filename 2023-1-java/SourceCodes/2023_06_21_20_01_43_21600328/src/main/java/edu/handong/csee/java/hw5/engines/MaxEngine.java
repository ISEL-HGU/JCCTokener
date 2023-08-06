package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This code calculates the Maximum number within various numbers
 */
public class MaxEngine implements Computable {
    /** 
     * sets the engine name as MAX
     */
    private static final String engineName ="MAX";
    /** 
     * inputs for the getting Maximum
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
     * computing by setting max as first one, then comparing all the numbers
     */
    public void compute(){
        double max = inputs[0];
        for(int i = 0; i < inputs.length; i++){
            if(max < inputs[i]){
                max = inputs[i];
            }
        }
        result = max;
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

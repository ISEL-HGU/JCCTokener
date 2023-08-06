package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;

/**
* The MaxEngine class that implements the Computable interface for finding the maximum value from a list of numbers.
*/
public class MaxEngine implements Computable {
    private static final String engineName = "MAX";
    private double[] inputs;
    private double result;

    /**
     * Getter for the enginen name.
     * @return String of engine name
     */
    public String getEngineName(){
        return engineName;
    }
    /**
     * Getter for the double array which stores the number for calculation of maximum
     * @return the double type of number array
     */
    public double[] getInputs() {
        return this.inputs;
    }
    /**
     * Setter for the double array which stores the number for calculation of maximum
     * @param inputs the double type of number array
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }
    /**
     * Setter for the calculation of result
     * @param result
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
    * Checks whether the input has at least 2 values
    * @param args the input values as a String array
     * @throws MinimumInputNumberException 
    */
    @Override
    public void checkInput(String[] args) throws MinimumInputNumberException{
    	
        if (args.length < 3) throw new MinimumInputNumberException("You need at least 2 input values for "+engineName+".");
    }

    /**
    * Parses the input values from String to double and sets them as the instance variable
    * @param args the input values as a String array
    */
    @Override
    public void setInput(String[] args){
        this.inputs = new double[args.length - 1];
        for (int i=0; i<args.length-1; i++){
            this.inputs[i] = Double.parseDouble(args[i+1]);
        }
    }

    /**
    * Finds the maximum value from the input values
    */
    @Override
    public void compute(){
        Double max = inputs[0];
        for(int i=0; i<inputs.length; i++){
            if(max < inputs[i]){
                max = inputs[i];
            }
        }
        result = max;
    }
    
    /**
    * Returns the maximum value found from the input values
    * @return the maximum value as a double
    */
    @Override
    public double getResult(){
        return result;
    }
}

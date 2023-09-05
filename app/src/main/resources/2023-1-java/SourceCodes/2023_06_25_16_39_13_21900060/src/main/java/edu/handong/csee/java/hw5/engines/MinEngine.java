package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;

/**
* The MinEngine class represents a computation engine that calculates the minimum value from a list of input values.
* It implements the Computable interface.
*/
public class MinEngine implements Computable {
    private static final String engineName = "MIN";
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
     * Getter for the double array which stores the number for calculation of minimum
     * @return the double type of number array
     */    
    public double[] getInputs() {
        return this.inputs;
    }
    /**
     * Setter for the double array which stores the number for calculation of minimum
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
    * Checks the input arguments to ensure that the minimum number of required inputs is provided.
    * @param args the array of input arguments.
     * @throws MinimumInputNumberException 
    */
    @Override
    public void checkInput(String[] args) throws MinimumInputNumberException{
        if (args.length < 3) throw new MinimumInputNumberException("You need at least 2 input values for "+engineName+".");
    }

    /**
    * Sets the input values for the computation engine.
    * @param args the array of input arguments.
    */
    @Override
    public void setInput(String[] args){
        this.inputs = new double[args.length - 1];
        for(int i=0; i<args.length-1; i++){
            this.inputs[i] = Double.parseDouble(args[i+1]);
        }
    }

    /**
    * Computes the minimum value from the input values.
    */
    @Override
    public void compute(){
        Double min = inputs[0];
        for(int i = 0; i<inputs.length; i++){
            if(min > inputs[i]){
                min = inputs[i];
            }
        }
        result = min;
    }

    /**
    * Returns the result of the computation.
    * @return the minimum value from the input values.
    */
    @Override
    public double getResult(){
        return result;
    }
}

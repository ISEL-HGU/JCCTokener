package edu.handong.csee.java.hw5.engines;


import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;

/**
 * The `MaxEngine` class is an implementation of the `Computable` interface that calculates the maximum value
 * of an array of input values.
 */
public class MaxEngine implements Computable {

    private static final String engineName = "MAX";
    private double[] inputs;
    private double result;
        /**
     * Returns the value of inputs
     * 
     * @return the new calue of inputs
     */
    public double[] getInputs() {
        return inputs;
    }
    
    /**
     * Set the value of inputs
     * @param inputs the new value of inputs
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }
    
/**
     * Set the value of result
     * @param result the new value of result
     */
    public void setResult(double result) {
        this.result = result;
    }
    

    /**
     * Sets the input values for the engine. This method takes an array of strings as input, converts each string
     * to a double value
     * 
     * @param args the array of input values
     */
    public void setInput(String[] args) {
        try {
        	inputs = new double[args.length];
        	for(int i=1; i<args.length; i++) {
	    	if ((args.length)-1<2) 
	    		throw new MinimumInputNumberException(engineName);
	    	inputs[i-1] = Double.parseDouble(args[i]);
        	}
        }catch(MinimumInputNumberException e){
       	 System.out.println (e.getMessage ());
       	System.exit(0);
        }
     	
       }

    /**
     * Calculates the maximum value in the input array.
     */
    public void compute() {
        double max = inputs[0];
        for(int i=1; i<inputs.length; i++) {
            if(inputs[i] > max) {
                max = inputs[i];
            }
        }
        result = max;
    }

    /**
     * Returns the maximum value calculated by the engine.
     * 
     * @return the maximum value
     */
    public double getResult() {
        return result;
    }
}


package edu.handong.csee.java.hw5.engines;


import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;


/**
 * The MinEngine class implements the Computable interface to perform the computation of finding the minimum value
 * among the input values.
 */
public class MinEngine implements Computable {
    private static final String engineName = "MIN";
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
     * Sets the input values for the engine.
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
     * Performs the computation for the engine.
     * Finds the minimum value among the input values.
     */
    public void compute() {
        double min = Double.MAX_VALUE;
        for (double input : inputs) {
            if (input < min) {
                min = input;
            }
        }
        result = min;
    }

    /**
     * Returns the result of the computation.
     * 
     * @return the minimum value among the input values
     */
    public double getResult() {
        return result;
    }
}

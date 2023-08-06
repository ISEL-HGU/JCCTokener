package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.exceptions.*;
import org.apache.commons.cli.*;

/**
 * This class is computing MaxEngine and implement Computable(interface) 
 */
public class MaxEngine implements Computable {
    private static final String engineName ="MAX";
    private double[] input;
    private double result;
    /**
     * This method is setting input.
     * @param args
     */
    public void setInput(String[] args) {
    	OptionHandler op = new OptionHandler();
    	Options options = op.createOptions();
    	boolean temp = op.parseOptions(options, args);
    	String inputvalues = op.getInputValues();
    	if(inputvalues == null) {
    		op.run(args);
    		System.exit(0);
    	}
    	try {
    		String[] values = inputvalues.split("\\s+");
    		input = new double[values.length];
	    	if(input.length < 2) {
	    		throw new MinimumInputNumberException(engineName);
	        }
	        int i;
	        for(i=0; i<input.length; i++){
	            input[i] = Double.parseDouble(values[i]);
	        }
    	}
    	catch(MinimumInputNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.out.println("(" + inputvalues + " is not a number value for "+ engineName + ".)");
    		System.exit(0);
    	}
    	catch(NumberFormatException e) {
    		throw new MyNumberFormatException();
    	}
    }
    /**
     * This method is compute something by some inputs.
     */
    public void compute() {
        double max = input[0];
        for(int i=1; i<input.length; i++){
            if(input[i] >  max)
                max = input[i];
        }
        this.result = max;
    }
    /**
     * This method return this.input[](array)
     * @return
     */
    public double[] getArr() {
    	return this.input;
    }
    /**
     * This method gives result.
     * @return
     */
    public double getResult() {
        return this.result;
    }
    /**
     * This method is return this.engineName.
     * @return
     */
    public String getEngineName() {
        return MaxEngine.engineName;
    }
}

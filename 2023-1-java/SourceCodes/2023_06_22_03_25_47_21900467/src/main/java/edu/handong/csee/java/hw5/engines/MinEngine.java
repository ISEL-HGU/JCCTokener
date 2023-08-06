

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * Class to find the smallest value in multiple numbers.
 */
public class MinEngine implements Computable {

	private static String engineName ="MIN";
    private double[] inputs;
    private double result;

    /**
     * It is a method that sets the value to be calculated.
     */
    public void setInput(String[] args){
    	try {
            inputs = new double[args.length];
            if (args.length <2){
            	throw new MinimumInputNumberException(getEnginename(), 0);
            }
            for (int i=0; i<args.length; i++){
                if(! args[i].matches("-?\\d+(\\.\\d+)?")) {
                	throw new MyNumberFormatException(getEnginename(), args[i]);
                }
                inputs[i] = Double.parseDouble(args[i]);
            }
    	}
    	catch (MinimumInputNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch (MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);;
    	}
    }
    /**
     * This is the actual calculation method.
     */
    public void compute(){
        double min = inputs[0];
        for(int i =1; i<inputs.length; i++){
            if(min > inputs[i]){
                min = inputs[i];
            }
        }
        result = min;

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
     * It returns inputs.
     */
    public double[] getInputs() {
        return inputs;
    }
    /**
     * It sets inputs.
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }
    /**
     * It sets result.
     */
    public void setResult(double result) {
        this.result = result;
    }
	/**
	 * setEnginename
	 * @param enginename
	 */
    public static void setEnginename(String enginename) {
		engineName = enginename;
	}
}

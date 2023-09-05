package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import java.lang.Math;

import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This class is computing SQRTEngine and implement Computable(interface) 
 */
public class SQRTEngine implements Computable {
    private static final String engineName ="SQRT";
    private double input;
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
    		String[] values = inputvalues.split(" ");
    		if(values.length > 1) {
    			throw new OneInputException(engineName);
    		}
    		if(Double.parseDouble(inputvalues) < 0) {
    			throw new NegativeNumberException(engineName);
    		}
    		// 발생된 예외가 없을 시 실행되는 문장
    		this.input = Double.parseDouble(inputvalues);
    		 
    	}
    	catch(OneInputException e){
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
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
     * Calculates the square root of a given value.
     * @param value The value to calculate the square root of.
     * @return The square root of the given value.
     */
    public double calculateSQRT(double value) {
        return Math.sqrt(value);
    }
    /**
     * This method is compute something by some inputs.
     */
    public void compute() {
        this.result = Math.sqrt(input);
    }
    /**
     * This method return input.
     * @return
     */
    public double getInput() {
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
        return SQRTEngine.engineName;
    }
}

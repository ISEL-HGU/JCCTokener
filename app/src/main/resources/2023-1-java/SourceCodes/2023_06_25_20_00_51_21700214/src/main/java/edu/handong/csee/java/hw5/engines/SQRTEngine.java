package edu.handong.csee.java.hw5.engines;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.handong.csee.java.hw5.fileutil.*;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * the class contains fields,methods that implements Computable interface for calculating square root.
 * @author kim hongchan
 *
 */
public class SQRTEngine implements Computable {
	/**
	 * String value that means engine name initialized as "SQRT"
	 */
    private static final String engineName ="SQRT";
    /**
	 * Double value that is input for calculating square root.
	 */
    private double input;
    /**
   	 * Double value that is result for calculated square root.
   	 */
    private double result;
    /**
     * Set a input from String type parameters as args[]. there should be two parmeters args[] and each value should be positive.
     * @param args
     */
	public void setInput(String[] args)throws OneInputException,NegativeNumberException{
    	if(args.length != 1) throw new OneInputException(engineName); 
    	if(Integer.parseInt(args[0]) < 0) throw new NegativeNumberException(engineName);
    	this.input = Double.parseDouble(args[0]);
	}
	/**
	 * Compute square root of input. Math.sqrt() was used.
	 */
	public void compute() {
		this.result = Math.sqrt(input);
	}
	
	
	public void computeNwriteOnFile(String inputFilePath, String outputFilePath)throws OneInputException,InvalidCommandException,MinimumInputNumberException,NegativeNumberException,MyNumberFormatException,InvalidOptionException,FileNotFoundException {
		FileManager fileManager = new FileManager();
		
		ArrayList<String> inputValues = fileManager.readLinesFromATxtFiles(inputFilePath);
		ArrayList<String> outputValues = new ArrayList<String>();
		
		for(String value : inputValues) {
			String[] arg = {value};		
			this.setInput(arg);
			this.compute();
			outputValues.add(Double.toString(this.getResult()));
		}
		fileManager.writeAtxtFile(outputFilePath, outputValues);
		String[] filePathName = outputFilePath.split(File.separator + File.separator);
		System.out.println("The "+filePathName[filePathName.length-1]+" file has been successfully written.");
	}
	/**
	 * Return result of a double value.
	 * @return result
	 */
	public double getResult() {
		return this.result;
	}
	/**
	 * Return input
	 * @return input
	 */
	public double getInput() {
		return input;
	}
	/**
	 * Set input
	 * @param input
	 */
	public void setInput(double input) {
		this.input = input;
	}
	/**
	 * Return engineName
	 * @return engineName
	 */
	public static String getEnginename() {
		return engineName;
	}
	/**
	 * Set result
	 * @param result
	 */
	public void setResult(double result) {
		this.result = result;
	}
	
}

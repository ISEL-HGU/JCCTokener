package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.engines.*;

import java.util.ArrayList;

import edu.handong.csee.java.hw5.thread.*;

import org.apache.commons.cli.*;
import edu.handong.csee.java.hw5.clioptions.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.fileutil.FileManager;
/**
 * The Calculator class performs calculations using various calculation engines based on the given arguments.
 */
public class Calculator {

	/**
     * The main method creates and runs a Calculator object based on the arguments provided by the user.
     * @param args The arguments provided by the user via the command line.
     */
    public static void main(String[] args){
    	OptionHandler options = new OptionHandler();
        Options cliOptions = options.createOptions();
        
        if (!options.parseOptions(cliOptions, args)) {
            System.exit(0);
        }
        Calculator myCalculator = new Calculator();

        myCalculator.run(options);
    }
    /**
     * The run method selects the appropriate calculation engine based on the user's input, performs the calculation,
     * and either prints the result or writes it to a file depending on the input arguments.
     * @param options An instance of OptionHandler which holds the parsed user input arguments.
     */
    public void run(OptionHandler options){

    	String engineName = options.getTask().toUpperCase();
    	String inputFilePath = options.getDataInputPath();
    	String outputFilePath = options.getDataOutputFilePath();
    	String[] inputValues = options.getInputValues();

        Computable engine =null;

        switch(engineName) {
            case "LCM":
                engine = new LCMEngine();
                break;
            case "GCD":
                engine = new GCDEngine();
                break;
            case "SQRT":
                engine = new SQRTEngine();
                break;
            case "FACTORIAL":
                engine = new FactorialEngine();
                break;
            case "FIBONACCI":
                engine = new FibonacciEngine();
                break;
            case "MAX":
                engine = new MaxEngine();
                break;
            case "MIN":
                engine = new MinEngine();
                break;
            case "CUBEVOL":
                engine = new CubeVolEngine();
                break;
            case "SPHEREVOL":
                engine = new SphereVolEngine();
                break;
            default:
                try{
            	    throw new InvalidCommandException(engineName);
                }
                catch(InvalidCommandException e){
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
        }
        try {
        	if (inputFilePath == null) {
        		engine.setInput(inputValues);
                engine.compute();
                System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
        	}
        	else if (inputFilePath.contains(".csv")){
        		ArrayList<String> inputData = FileManager.readLinesFromATxtFile(inputFilePath);
	            ArrayList<String> outputData = new ArrayList<>();
	            
                for (String line : inputData) {
                    inputValues = line.split(",");
                    String[] results = new String[inputValues.length];
                    for (int i = 0; i < inputValues.length; i++) {
                    	try {
                    		Integer.parseInt(inputValues[i]);
                    		engine.setInput(new String[]{inputValues[i]});
                            engine.compute();
                            results[i] = String.valueOf(engine.getResult());
                    	}
                    	catch (NumberFormatException ex){
                    		results[i] = inputValues[i];
                    	}
                    }
                    outputData.add(String.join(",", results));
                }
                FileManager.writeATxtFile(outputFilePath, outputData);
                System.out.println("The " + outputFilePath + " file has been successfully written.");
            }
        	else {
        	    CSVFileCalculator CSVFileCalculators = new CSVFileCalculator(engineName, inputFilePath, outputFilePath);
        	    Thread thread = new Thread(CSVFileCalculators);
        	    thread.start();
        	    
        	    try {
        	        thread.join();
        	    } catch (InterruptedException e) {
        	        e.printStackTrace();
        	    }
        	}
        } catch (MyNumberFormatException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}


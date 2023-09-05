package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;

import org.apache.commons.cli.Options;
import edu.handong.csee.java.hw5.fileutil.FileManager;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;

/**
 * 
 * This is the class that outputs the result of the computation using the computing engine classes. 
 *
 */
public class Calculator {
	/**
	 * Methods for receiving input values.
	 * A Calculator instance is implemented, as well as the part that handles exception when there is no input value.
	 */
	public static void main(String[] args) {
		Calculator myCalculator = new Calculator();

        try {
            myCalculator.run(args);
        } catch (InvalidCommandException e) {
            System.out.println("Exception-01: " + e.getMessage());
        } catch (MinimumInputNumberException e) {
			System.out.println("Exception-02: " + e.getMessage());
		} catch (NegativeNumberException e) {
			System.out.println("Exception-03: " + e.getMessage());
		} catch (OneInputException e) {
			System.out.println("Exception-04: " + e.getMessage());
		} catch (MyNumberFormatException e) {
			System.out.println("Exception-05: " + e.getMessage());
		}
    }
	
	/**
	 * This method outputs the calculated value based on the input value.
	 * An instance is created according to the engine and outputs the value.
	 */
    public void run(String[] args) throws InvalidCommandException, MinimumInputNumberException, NegativeNumberException, OneInputException, MyNumberFormatException {
    	OptionHandler optionHandler = new OptionHandler();
    	Options options = optionHandler.createOptions();
    	
    	if (optionHandler.parseOptions(options, args)) {
 		   boolean help = optionHandler.getHelpRequested();
 		   String task = optionHandler.getTask();
 		   String dataInputFilePath = optionHandler.getDataInputFilePath();
 		   String dataOutputFilePath = optionHandler.getDataOutputFilePath();
 		   String engineName = task.toUpperCase();
 		   
 		   
 		   String[] inputArgs;
 		   String inputValues = optionHandler.getInputValues();
 		   //System.out.println(inputValues);
 		   
 		   if(inputValues != null) {
 			   inputArgs = inputValues.split("\\s+");
 		   } 
 		   else {
 			   inputArgs = new String[0];
 		   }
 		   

 		   Computable engine =null;
 		   
 		   if (help) {
 			   optionHandler.printHelp(options);
 			   return;
 			   }
 		   
 		   if (dataInputFilePath != null) {
 			   if(isDirectoryPath(dataInputFilePath)) {
 				   //System.out.println("Directory");
 			 	   CSVFileCalculator csvFileCalculator = new CSVFileCalculator();
			 	   csvFileCalculator.setInputDirectory(dataInputFilePath);
			 	   csvFileCalculator.setOutputDirectory(dataOutputFilePath); // Set the output directory
			 	   csvFileCalculator.run(); 
 			   }
 			  
 			   else if(engineName.equals("SQRT")) {
 				  try {
        			  engine = new SQRTEngine();
        			  if(new File(dataInputFilePath).exists()) {
        				  ArrayList<String> lines = FileManager.readLinesFromATxtFile(dataInputFilePath);
                          StringBuilder outputBuilder = new StringBuilder();
                          for (int i = 0; i < lines.size(); i++) {
                        	     String line = lines.get(i);
                        	     try {
                        	         double number = Double.parseDouble(line);
                        	         engine.setInput(new String[]{String.valueOf(number)});
                        	         engine.compute();
                        	         double result = engine.getResult();
                        	         outputBuilder.append(String.valueOf(result));
                         	     } catch (NumberFormatException e) {
                        	         outputBuilder.append(line);
                        	     }
                        	 }
                          FileManager.writeAtxtFile(dataOutputFilePath, new ArrayList<>(Collections.singletonList(outputBuilder.toString())));
        			  }
        			  else {
        				  optionHandler.printHelp(options);
        	 			   return;
        			  }
                      
                     //System.out.println("Output file is created at " + dataOutputFilePath);
                  } catch (IOException e) {
                      //System.out.println("Exception while reading the input file: " + e.getMessage());
                      return;
                  }
 			   }
          }
 		    		  
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
		            throw new InvalidCommandException("Invalid command: " + engineName
		          			+ "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
		      }
 		   
 		   	  engine.setInput(inputArgs);
 		   	  engine.compute();
 		   	  
 		   	
       	
 		   	  if(dataInputFilePath == null && engineName != "SQRT")
 		   		  System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");

    	}
    		
    }
    private boolean isDirectoryPath(String path) {
		File file = new File(path);
		return file.isDirectory();
	}
}

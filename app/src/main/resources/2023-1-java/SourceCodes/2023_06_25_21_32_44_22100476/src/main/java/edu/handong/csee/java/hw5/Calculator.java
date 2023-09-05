package edu.handong.csee.java.hw5;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.fileutil.*;
import edu.handong.csee.java.hw5.clioptions.*;
import edu.handong.csee.java.hw5.thread.*;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


/**

The Calculator class is the main class of the program that runs different types of computations based on the arguments passed through the command line.
 * @author [Author Name]
 * @version [Version Number, e.g. 1.0]
*/
public class Calculator {
	
	
    /**
 * The main method that calls the run method to start the program.
 * @param args The arguments passed through the command line
 */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }

    /**
 * The run method runs the program by checking the validity of the inputs, selecting the corresponding computation engine based on the first argument, and executing the computation.
 * @param args The arguments passed through the command line
 */
    public void run(String[] args) {
        OptionHandler optionHandler = new OptionHandler();
        Options options = OptionHandler.createOptions();

        
        if (OptionHandler.parseOptions(options, args, optionHandler)) {
        	String task = optionHandler.getTask();
            String inputPath = optionHandler.getDataInputFilePath();
            String outputPath = optionHandler.getDataOutputFilePath();
            String inputValues = optionHandler.getInputValues();
            boolean helpRequested = optionHandler.getHelpRequested();
            if (helpRequested) {
                OptionHandler.printHelp(options);
                return;
            }
            if(task == null) {
           	OptionHandler.printHelp(options);
            	// System.out.println("task is null?\n");
                return;
            }
            

            if (task != null) {
                try {
                    Computable engine = null;
        
                    switch (task.toUpperCase()) {
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
                            throw new InvalidCommandException(task.toUpperCase());
                    }
                    if(inputPath == null && outputPath == null) {
                    	if (inputValues != null) {
                        	try {
                        		String[] values = inputValues.split("\\s+");
                        		for (String value : values) {
                        			if(!value.matches("[-+]?\\d*\\.?\\d+")) {
                                		throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + value + " is not a number value for "+task.toUpperCase() +".)");
                                    	
                                	}
                        	    }
                                engine.setInput(values);
                                
                        	}catch(Exception e){
                            	System.out.println(e.getMessage()); // print an error message if the input is invalid
                   	    	 System.exit(0);
                   
                           }
                        	
                           
                        }
                    	else {
                        	OptionHandler.printHelp(options);
                            return;
                        }
                    	engine.compute();
                        System.out.println("The result of " + task.toUpperCase() + " is " + engine.getResult() + ".");
                    }
                    else {
                    	if(inputPath != null && outputPath != null) {
                            if(inputValues != null) {
                                OptionHandler.printHelp(options);
                                return;
                            }
                            if(task.toUpperCase() != "SQRT" && task.toUpperCase() != "MIN" && task.toUpperCase() != "MAX") {
                                OptionHandler.printHelp(options);
                                return;
                            }
                            ArrayList<String> csvData = new ArrayList<>();

                            if (inputPath.toLowerCase().endsWith(".csv")) {
                                ArrayList<String> lines = FileManager.readLinesFromATxtFile(inputPath);
                                String[] a = inputValues.split(",");
                                for (String processedData : a) {
                                    if (optionHandler.getTask().equals("SQRT")) {
                                        double sqrtValue = Math.sqrt(Double.parseDouble(processedData));
                                        if (sqrtValue < 0) {
                                            System.out.println("Exception-03: The input value cannot be negative for SQRT.");
                                            System.exit(0);
                                        }
                                        csvData.add(Double.toString(sqrtValue));
                                    } else {
                                        // Handle other transformation options here
                                    }
                                }
                            

                            }
                            else {
                                ArrayList<String> lines = FileManager.readLinesFromATxtFile(inputPath);
                                for (String line : lines) {
                                    // Apply the transformation based on the -t option
                                    if (optionHandler.getTask().equals("SQRT")) {
                                        double sqrtValue = Math.sqrt(Double.parseDouble(line));
                                        if (sqrtValue < 0) {
                                            System.out.println("Exception-03: The input value cannot be negative for SQRT.");
                                            System.exit(0);
                                        }
                                        csvData.add(Double.toString(sqrtValue));
                                    } else {
                                        optionHandler.printHelp(options);
                                    }
                                }
                            }

                            FileManager.writeAtxtFile(outputPath, csvData);
                                try (FileWriter writer = new FileWriter(outputPath)) {
                                    for (String data : csvData) {
                                        writer.write(data + System.lineSeparator());
                                    }
                                    
                                    System.out.println("The " + outputPath + " file has been successfully written.");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                        }

                    	else {
                    		OptionHandler.printHelp(options);
                            return;
                    	}
                    	
                    }
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage()); // Print an error message if the input is invalid
                    return; // Exit the program if the input is invalid
                }
            }
            
        }
    }

	


}
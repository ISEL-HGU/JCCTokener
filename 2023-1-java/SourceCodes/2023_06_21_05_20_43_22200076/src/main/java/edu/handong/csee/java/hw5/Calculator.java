package edu.handong.csee.java.hw5;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.cli.Options;

import java.io.File;

import edu.handong.csee.java.hw5.thread.CSVFileCalculator;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.fileutil.FileManager;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;

/**
 * This class is for starting the entire program.
 * This class performs the calculation that the user wants.
 */
public class Calculator {

    /**
     * A starting point of Calculator class.
     * 
     * @param args user input value
     */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }

    /**
     * A starting point where the Calculator class actually operates.
     * 
     * @param args user input value
     */
    public void run(String[] args) {
    	OptionHandler optionHandler = new OptionHandler();
    	Options options = optionHandler.createOptions();
    	//char checkFile = 'N';
    	
    	if(optionHandler.parseOptions(options, args)) {
    		String engineName = optionHandler.getTask();
    		engineName = engineName.toUpperCase();
    		
    		if((engineName.equals("SQRT") || engineName.equals("MAX") || engineName.equals("MIN")) && optionHandler.getInputValues()==null) {
    			//File outputFile = new File(optionHandler.getDataOutputFilePath());
    			//String outputFilePath = outputFile.getAbsolutePath();
    			String outputFilePath = optionHandler.getDataOutputFilePath();
    			File file = new File(optionHandler.getDataInputFilePath());
        		ArrayList<String> csvFiles = new ArrayList<>();
    			if(file.isDirectory()) {
    				File[] files = file.listFiles();
    		        if (files != null) {
    		            for (File ffile : files) {
    		                if (ffile.isFile() && ffile.getName().toLowerCase().endsWith(".csv")) {
    		                    csvFiles.add(ffile.getPath());
    		                }
    		            }
    		        }
    		        
    			} else {
    				ArrayList<String> list = new ArrayList<String>();
        			list = FileManager.readLinesFromATxtFile(file.getPath());
        			FileManager.writeAtxtFile(file.getPath(), outputFilePath, list);
        			return;
    				//csvFiles.add(file.getPath());
    				//checkFile = 'Y';
    			}
    			//Thread pool
    			ArrayList<CSVFileCalculator> calculateRunners = new ArrayList<CSVFileCalculator>();
    	        int numOfCoresInMyCPU = Runtime.getRuntime().availableProcessors();
    	        ExecutorService executor = Executors.newFixedThreadPool(numOfCoresInMyCPU);
    	        for(String inputFilePath : csvFiles) {
    	        	Runnable calculator = new CSVFileCalculator(inputFilePath, outputFilePath, engineName);
    	        	calculateRunners.add((CSVFileCalculator)calculator);
    	        }
    	        executor.shutdown();
    	        while (!executor.isTerminated()) {
    	        }
    	        
    	        for(CSVFileCalculator runner : calculateRunners) {
    	        	runner.run();
    	        }
    	        /*if(checkFile == 'Y') {
    	        	 System.out.println("The " + outputFilePath + " file has been successfully written.");
    	        }*/
    	        return;
    		}
    		
    		Computable engine = null;
    		try {
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
            		throw new InvalidCommandException(engineName);
    			}
    		} catch(InvalidCommandException e){
            	System.out.println(e.getMessage());
            	System.exit(0);
    		}
    		String temp = optionHandler.getInputValues(); 
    		String[] values = temp.split(" ");
        	try {
				engine.setInput(values);
			} catch (Exception e) {
			}
            engine.compute();

            System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
    	}
    }
}
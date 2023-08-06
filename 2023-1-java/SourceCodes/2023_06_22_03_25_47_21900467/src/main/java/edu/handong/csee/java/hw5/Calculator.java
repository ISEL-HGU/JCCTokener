package edu.handong.csee.java.hw5;

import org.apache.commons.cli.*;


import edu.handong.csee.java.hw5.clioptions.*;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.fileutil.*;
import edu.handong.csee.java.hw5.thread.*;
import java.util.ArrayList;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * This is a class for a calculator.
 */
public class Calculator {
    /**
     * This is the main part that is actually implemented.
     */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }
    
    /**
     * It's actually the part that works.
     */
    public void run(String[] args) {
    	OptionHandler OpH = new OptionHandler();
    	Options options = OpH.createOptions();
    	if(!OpH.parseOptions(options, args)) {
    		return;
    	}
    	if (OpH.getHelpRequested()) {
    		OpH.printHelp(options);
    		return;
    	}
        if(OpH.getDataInputFilePath() == null || OpH.getDataOutputFilePath() == null) {
        	if (OpH.getTask() == null || OpH.getInputValues() == null) {
            	OpH.setHelpRequested(true);
                OpH.printHelp(options);
                return;
            	}
        }
    	
	    String engineName = (OpH.getTask()).toUpperCase();
	    
    	
        Computable engine =null;
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
	            	throw new InvalidCommandException(engineName, 0);
            }
        }
        catch(InvalidCommandException e) {
        	System.out.println(e.getMessage());
        	System.exit(0);
        }
        if((engineName.equals("SQRT") || engineName.equals("MAX")|| engineName.equals("MIN")) && OpH.getDataInputFilePath() != null && OpH.getDataOutputFilePath() != null &&isDirectory(OpH.getDataInputFilePath())) {
            File directory = new File(OpH.getDataInputFilePath());
            File[] files = directory.listFiles();
            if (files != null) {
                ExecutorService executorService = Executors.newFixedThreadPool(15);

                for (File file : files) {
                    if (file.isFile() && isCSVFile(file)) {
                        executorService.execute(new CSVFileCalculator(file.getPath(), OpH.getDataOutputFilePath(), engineName));
                    }
                }
                executorService.shutdown();
                try {
                    executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return;
        	
        }
        if (engineName.equals("SQRT") && OpH.getDataInputFilePath() != null && OpH.getDataOutputFilePath() != null && isFile(OpH.getDataInputFilePath())) {
        	String inputFilePath = OpH.getDataInputFilePath();
        	String outputFileName = OpH.getDataOutputFilePath();
        	
            try {
                ArrayList<String> lines = FileManager.readLinesFromATxFile(inputFilePath);

                ArrayList<String> updatedLines = new ArrayList<>();
                boolean isFirstLine = true;
                for (String line : lines) {
                    String[] values = line.split(",");
                    String[] newValues = new String[1];
                    for (int i = 0; i < values.length; i++) {
                        try {
                        	if(isFirstLine) {
                        		updatedLines.add(values[i]);
                        		continue;
                        	}
                        	newValues[0] = values[i];
                        	engine.setInput(newValues);
                        	engine.compute();
                        	updatedLines.add(Double.toString(engine.getResult()));
                        } 
                        catch (NumberFormatException e) {
                        }
                    }
                    isFirstLine = false;
                }
                FileManager.writeAtxtFile(outputFileName, updatedLines);

                System.out.println("The " + outputFileName + " file has been successfully written.");
            } 
            catch (Exception e) {
                System.out.println("Error occurred while processing the file.");
            }
        }
        String[] inputs;
        if(OpH.getInputValues() != null) {
        	inputs = OpH.splitInputValues();
        }
        else {
        	inputs = new String[0];
        }
        if (engineName.equals("SQRT") && OpH.getInputValues() == null) {
        	return;
        }
        
        else {
        	engine.setInput(inputs);
        	engine.compute();
        	System.out.println("The result of " + engineName + " is " + engine.getResult() + ".");
        }
    }
    private boolean isDirectory(String path) {
        File file = new File(path);
        return file.isDirectory();
    }
    private boolean isFile(String path) {
    	File file = new File(path);
    	return file.isFile();
    }
    private boolean isCSVFile(File file) {
        String fileName = file.getName();
        int extensionIndex = fileName.lastIndexOf('.');

        if (extensionIndex != -1) {
            String extension = fileName.substring(extensionIndex + 1);
            return extension.equalsIgnoreCase("csv");
        }

        return false;
    }
}

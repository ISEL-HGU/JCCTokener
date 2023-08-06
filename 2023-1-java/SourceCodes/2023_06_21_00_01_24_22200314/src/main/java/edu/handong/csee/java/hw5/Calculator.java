package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.thread.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.io.Files;
import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.fileutil.FileManager;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import java.io.File;
/**
 * This is the main class of Calculator. 
 */
public class Calculator {

	/**
	 * This is the main method where new Calculator instance is made and calls the run method.
	 * @param args The input from the terminal written by the user.
	 */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();
        
        myCalculator.run(args);
        
        
    }

    /**
     * This method is where all the calculation occurs.
     * @param args The input from the terminal written by the user.
     */
    public void run(String[] args) {

    	OptionHandler oh = new OptionHandler();
    	
    	Options options = oh.createOptions();
    	boolean b = oh.parseOptions(options, args);
    	
    	if(b == false) {
    		System.exit(0);
    	}
    	
    	
    	if(oh.getHelpRequested()) {
    		oh.printHelp(options);
    		System.exit(0);
    	}
    	

    	
    	else if(oh.getDataInputFilePath() != null && oh.getDataOutputFilePath() != null && oh.getInputValues() == null && (oh.getTask().toUpperCase().equals("MIN") || oh.getTask().toUpperCase().equals("MAX") || oh.getTask().toUpperCase().equals("SQRT") )&& oh.getHelpRequested() == false) {
    		
    		
    		ArrayList<Thread> threads = new ArrayList<Thread>();
    		File file = new File(oh.getDataInputFilePath());
    		
    		
    		if(!file.exists()) {
    			oh.printHelp(options);
    			System.exit(0);
    		}
    		
    		else if(file.isDirectory()) {
    			CSVFileCalculator CSVCal = null;
    			for(int i = 0; i < file.listFiles().length; i++) {
    				String inputName = oh.getDataInputFilePath() + File.separator + file.listFiles()[i].getName();
    				File input = new File(inputName);
    				if(!Files.getFileExtension(inputName).equals("csv")) {
    					continue;
    				}
    				
    				String outputName = Files.getNameWithoutExtension(file.listFiles()[i].getName())+"-"+oh.getDataOutputFilePath();
    				File output = new File(outputName);
    				
	    			CSVCal = new CSVFileCalculator(input.getPath(), output.getPath() , oh.getTask());
	    			Thread thread = new Thread(CSVCal);
	    			thread.start();
	    			threads.add(thread);
    			}
    			
    			for(int i = 0; i < threads.size(); i++) {
        			try {
        				threads.get(i).join();
    					
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
        		}
    		}
    		else if(file.isFile()){
        		ArrayList<String> list = FileManager.readLinesFromATxtFile(oh.getDataInputFilePath());
        		FileManager.writeATxtFile(oh.getDataOutputFilePath(), list);
    		}

 
    		

    		
    		System.exit(0);
    	}
    	else if(oh.getDataInputFilePath() == null && oh.getDataOutputFilePath() == null && oh.getInputValues() != null && oh.getTask() != null && oh.getHelpRequested() == false) {
    		String[] temp = oh.getInputValues().split("\\s+");

    		String[] newarg = new String[temp.length + 1];
    		
    		newarg[0] = oh.getTask();
    		for(int i = 1; i <= temp.length; i++) {
    			newarg[i] = temp[i-1];
    		}

    		
	    	try {
		        if(newarg.length == 0){
		            throw new InvalidCommandException();
		        }
	    	
		        String engineName = newarg[0].toUpperCase();
		
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
		            	throw new InvalidCommandException(engineName);
		        }
		        
		        engine.setInput(newarg);
		        engine.compute();
	
		        System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
		        
	    	}
	        
	        catch(InvalidCommandException e) {
	    		System.out.println(e.getMessage());
	    		System.exit(0);
	    	}

    	}
    	else {
    		oh.printHelp(options);
    		System.exit(0);
    	}
    }


}
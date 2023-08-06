package edu.handong.csee.java.hw4;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw4.Calculator;
import edu.handong.csee.java.hw4.engines.*;
import edu.handong.csee.java.hw4.exceptions.*;
import edu.handong.csee.java.hw4.engines.FactorialEngine;
import edu.handong.csee.java.hw4.clioptions.*;
import edu.handong.csee.java.hw4.fileutil.FileManager;

public class Calculator {

    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();
        myCalculator.run(args);
        
}

    public void run(String[] args) {
    	OptionHandler optionhandler = new OptionHandler();
    	Options options;
    	
    	options = optionhandler.createOptions();
    	optionhandler.parseOptions(options, args);
    	
    	

    	try {
    		if(args.length==0)throw new InvalidCommandException();
    		
    		String engineName = optionhandler.getTask();
    		engineName = engineName.toUpperCase();

    		Computable engine = null;
    		
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
    		
    		if(optionhandler.getDataInputFilePath() != "") {
    			engine.setInput(FileManager.readLinesFromATxtFile(optionhandler.getDataInputFilePath()).toArray(new String[0]));
    		}else {
    			String[] splited = optionhandler.getInputValues().split("\\s+"); 
    			engine.setInput(splited);
    		}
    		
    		engine.compute();
        
            System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
            
            if(optionhandler.getDataOutputFilePath() != "") {
            	ArrayList<String> arraylist = new ArrayList<String>();
            	arraylist.add(engine.getResult() + "");
            	FileManager.writeAtxtFile(optionhandler.getDataOutputFilePath(),arraylist);
            }
    	}catch(InvalidCommandException e) {
    		optionhandler.printHelp(options);
    	}catch(NumberFormatException e) {
    		optionhandler.printHelp(options);
    	}
                /////////////////////////////////use for repeat
        
        /*try {
        	int number = parseInt(args[0], engineName);
        }catch(MyNumberFormatException e) {
        	System.out.println(e.getMessage());
        	
        }
        */
                
        
        
    /*

	public static int parseInt(String args, String engineName) throws MyNumberFormatException {
		// TODO Auto-generated method stub
		 try {
	            return Integer.parseInt(args);
	        } catch (NumberFormatException e) {
	            throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (aaa is not a number value for " + engineName.toUpperCase() +".");
	        }
		
	}
	*/

    }

}
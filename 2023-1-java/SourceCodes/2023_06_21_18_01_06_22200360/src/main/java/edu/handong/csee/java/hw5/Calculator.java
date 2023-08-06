package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.engines.*;
import org.apache.commons.cli.Options;
import java.util.concurrent.*;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;
/**
 * This class can calculate whole engines. This class have main method.
 */
public class Calculator {

    /**
     * This is main method. 
     * This method actually selects and calculates the engine from the input value entered by the user.
     * @param args
     */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }
    /**
     * This is run method. it will be called by main method.
     * @param args
     */
    public void run(String[] args) {
    	OptionHandler op = new OptionHandler();
	    Options options = op.createOptions();
	    
	    //op.run(args);
    	try {
	        if(args.length == 0){
	        	throw new Exception();
	        }
	        boolean temp = op.parseOptions(options, args);
	        if(!temp)
	        	throw new Exception();
	        String engineName = op.getTask();

	        Computable engine =null;
	        
	        switch(engineName) {
	            case "LCM":
	            	//System.out.println("Here");
	                engine = new LCMEngine();
	                //System.out.println("Here3");
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
	        engine.setInput(args);
	        engine.compute();
	        System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
	        /*
	        if(engineName.equals("SQRT") || engineName.equals("MIN")  || engineName.equals("MAX")) {
	        	int numOfCoresInMyCPU = Runtime.getRuntime().availableProcessors();
	        	ExecutorService executor = Executors.newFixedThreadPool(numOfCoresInMyCPU);
	        	Runnable worker = new CSVFileCalculator(op.getDataInputFilePath(), op.getDataOutputFilePath());
	        	executor.execute(worker);
	        	executor.shutdown();

	        }
	        */
   
    	}
    	catch(InvalidCommandException e) {
    		System.out.println(e.getMessage());
    		System.out.println("Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
    		System.exit(0);
    	}
    	catch(Exception e) {
    		op.printHelp(options);
    		e.getMessage();
    		System.exit(0);
    	}
        
    }


}

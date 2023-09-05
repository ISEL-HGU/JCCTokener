package edu.handong.csee.java.hw5;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.thread.CSVFileCalculator;

import java.io.File;
import java.util.ArrayList;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.fileutil.FileManager;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import edu.handong.csee.java.hw2.util.InputChecker;

/**
 * It is a class that contains the main.
 * It is a class that calls a specific engine and implements it to make appropriate calculations for the engine.
 *
 */
public class Calculator {
	
	
	/**
	 *This is the main method.
	 * It works by invoking the run method.
	 */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }
    
	 /**
	  * It is a method that calls a specific engine to calculate using the input received from the main method.
	  */
    public void run(String[] args) {
    	
    	OptionHandler optionHandler = new OptionHandler();
    	Options options = optionHandler.createOptions();
    	CSVFileCalculator csvFileCalculator = new CSVFileCalculator();
    	//Options options = createOptions();
		
		if(optionHandler.parseOptions(options, args)){
			if (optionHandler.getHelpRequested()){
				optionHandler.printHelp(options);
				
				System.exit(0);
			}
		}
    	
		
		if(optionHandler.getTask() == null) {
        	optionHandler.printHelp(options);  
			System.exit(0);
        }

        String engineName = optionHandler.getTask().toUpperCase();
        
        
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
        }
        catch(InvalidCommandException e) { 
        	
        	//optionHandler.printHelp(options);
			System.out.println(e.getMessage());   
			System.exit(0);
		} 

        
		if (optionHandler.getDatalnputFilePath() != null && (engineName.equals("MAX") || engineName.equals("SQRT") || engineName.equals("MIN"))) {
			 File file = new File(optionHandler.getDatalnputFilePath());
            if (file.isDirectory()) {
            	
                //System.out.println("입력한 경로는 디렉토리입니다.");
            	
            	String DATA_DIRECTORY = optionHandler.getDatalnputFilePath();
            	File dir = new File(DATA_DIRECTORY);

        
            	
            	int numOfCoresInMyCPU = Runtime.getRuntime().availableProcessors();
            	ExecutorService executor = Executors.newFixedThreadPool(numOfCoresInMyCPU);
            	
            	String[] filenames = dir.list();
            	
            	ArrayList<Callable<Object>> calls = new ArrayList<Callable<Object>>();
            	 
            	for (String filename : filenames) {
            		//System.out.println(optionHandler.getDatalnputFilePath()+"/"+filename); 
            		Runnable worker = new CSVFileCalculator(optionHandler.getDatalnputFilePath()+"/"+filename, filename, optionHandler, options, engineName);
            		calls.add(Executors.callable(worker));
            		//calls.sleep(1000);
            	}
            	
            	try {
        			executor.invokeAll(calls); // This line will be terminated after all threads are terminated.
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
            	
        		executor.shutdown();
            	
            
            	System.exit(0);

            	                
            } else if (file.isFile()) {
                //System.out.println("입력한 경로는 파일입니다.");
                // System.out.println(engineName); 
                if( (engineName.equals("MAX") || engineName.equals("SQRT") || engineName.equals("MIN") ) && optionHandler.getInputValues() == null) {
                	//System.out.println("=======>");
                	if(engineName.equals("SQRT") && optionHandler.getDatalnputFilePath() != null) {
            			//System.out.println("=======>");
            			
                    	if(optionHandler.getDataOutputFilePath() == null) {
                    		optionHandler.printHelp(options);
                    	}
                    	if(optionHandler.getInputValues() != null) {
                    		optionHandler.printHelp(options);
                    	}; 
                    	 
                    	ArrayList<ArrayList<String>> inputData = csvFileCalculator.readCSV(optionHandler.getDatalnputFilePath());
                    	ArrayList<ArrayList<String>> outputData = new ArrayList<>();
                  
                    	//int num = 0;
                    	for (ArrayList<String> line : inputData) {
                    		
                    		
                    		ArrayList<String> outline = new ArrayList<>();
            	    		for(String word : line) {
            	    			String[] num = word.split("\\s+");
            	                
            	                try {
            	                	//System.out.println(word);
            	                    int IntegerNum = Integer.parseInt(num[0]);
            	                	//Double DoubleNum = Double.parseDouble(num[0]);
            	                } catch (NumberFormatException e) {
            	                	outline.add(num[0]);
            	                    continue;
            	                	
            	                }

            	                engine.setInput(num);
            	                engine.compute();
            	                outline.add(Double.toString(engine.getResult()));
            	                
            	    		}
                            
                            
            	    		outputData.add(outline);
                            
                            
                        }
                    	
                    	
                    	
                    	
                    	
                    	csvFileCalculator.writeCSV(optionHandler.getDataOutputFilePath(), outputData);
                    	System.exit(0);
                    }
                    
                   
                    
                    if( ( engineName.equals("MIN") || engineName.equals("MAX") ) && optionHandler.getDatalnputFilePath() != null) {
                    	if(optionHandler.getDataOutputFilePath() == null) {
                    		optionHandler.printHelp(options);
                    	}
                    	if(optionHandler.getInputValues() != null) {
                    		optionHandler.printHelp(options);
                    	}
                    	
                    	
                    	ArrayList<ArrayList<String>> inputData = csvFileCalculator.readCSV(optionHandler.getDatalnputFilePath());
                    	ArrayList<ArrayList<String>> outputData = new ArrayList<>();
                    	
                    	
                    	
                    	for (ArrayList<String> line : inputData) {
                    		
                    		ArrayList<String> outline = new ArrayList<>();
               
            	    		for(String word : line) {
            	    			String[] num = word.split("\\s+");
            	                
            	                try {
            	                	//System.out.println(word);
            	                    int IntegerNum = Integer.parseInt(num[0]);
            	                	//Double DoubleNum = Double.parseDouble(num[0]);
            	                } catch (NumberFormatException e) {
            	                	outline.add(num[0]);
            	                    continue;
            	                	
            	                }
            	                outline.add(word);
            	                
            	                
            	    		}
            	    		try {
                                int IntegerNum = Integer.parseInt(outline.get(0));
                            	//Double DoubleNum = Double.parseDouble(num[0]);
                            } catch (NumberFormatException e) {
                            	if(engineName.equals("MIN")) {
                            		outline.add("MIN");
                            	}
                            	else {
                            		outline.add("MAX");
                            	}
                            	
                            	outputData.add(outline);
                                continue;
                            	
                            }
            	    		
            	    		engine.setInput(outline.toArray(new String[outline.size()]));
                            engine.compute();
                            outline.add(Double.toString(engine.getResult()));
                            
                            
            	    		outputData.add(outline); 
                            
                            
                           
                        }
                    	
                    	
                    	
                    	
                    	
                    	csvFileCalculator.writeCSV(optionHandler.getDataOutputFilePath(), outputData);
                    	System.exit(0);
                    }
                	System.exit(0);
                }
                
            }
        }
		
		
 
        
        
        
        
        if(optionHandler.getInputValues() == null) {
        	optionHandler.printHelp(options);
        	System.exit(0);
        }
        

        String[] numbers = optionHandler.getInputValues().split("\\s+"); // 공백을 기준으로 문자열을 분할하여 배열로 저장
   
        engine.setInput(numbers);
        engine.compute();

        System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
        

    }


}
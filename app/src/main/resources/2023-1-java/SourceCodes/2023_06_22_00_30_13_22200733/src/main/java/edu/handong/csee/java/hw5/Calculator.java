package edu.handong.csee.java.hw5;

import org.apache.commons.cli.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.fileutil.FileManager;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;
import java.util.ArrayList;

/**
*Calculator class for running different engines to perform computations.
*It accepts command-line arguments with the name of the engine and the inputs required for that engine.
*The available engines are LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL.
*It creates an instance of the chosen engine and performs the computation by setting the inputs and calling the compute() method.
*Finally, it prints the result of the computation.
*If no arguments are provided, it will print a message asking for the engine name and the required inputs.
*/

public class Calculator {
/**
 * The main method for running the Calculator class
 * @param args command-line arguments with the name of the engine and the inputs required for that engine.
 * If no arguments are provided, it will print a message asking for the engine name and the required inputs.
 */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }
/**
 * The run method for performing the computation using the chosen engine.
 * @param args command-line arguments with the name of the engine and the inputs required for that engine.
 * If no arguments are provided, it will print a message asking for the engine name and the required inputs.
 */
    public void run(String[] args) {

    		OptionHandler optionHandler = new OptionHandler();
    		Options options = optionHandler.createOptions();
    	
    	
    		if(!optionHandler.parseOptions(options, args))
    			System.exit(0);
    		
    		if(optionHandler.getHelpRequested())
    		{
    			optionHandler.printHelp(options);
    			System.exit(0);
    		}
    		
    	
    	
			Computable engine;
			
			String engineName = optionHandler.getTask().toUpperCase();
			
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
		        
		        if(optionHandler.getInputValues().length() != 0)
    			{
		        	String tempString[] = optionHandler.getInputValues().split("\\s+");
		        	
    				engine.setInput(tempString);
        	        engine.compute();

        	        System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
    			}
    			else
    			{
    				if(!(engineName.equals("SQRT") || engineName.equals("MAX") || engineName.equals("MIN"))) {
    					optionHandler.printHelp(options);
    					System.exit(0);
    				}

    				ArrayList<Thread> threadsForFile = new ArrayList<Thread>();
    				
    				String directoryPath = optionHandler.getDataInputFilePath();
    		        File directory = new File(directoryPath);
    		        Path path = Paths.get(directoryPath);
    		        String outPath = optionHandler.getDataOutputFilePath();
    		        
    		        
    		        if (directory.isDirectory()) {
    		        	ArrayList<String> filename = new ArrayList<String>();
    		        
    		        	try {
    		            File[] inputFiles = directory.listFiles();	
    		            if (inputFiles != null) {
    		            	for (File file : inputFiles) {
    		            		if (file.isFile() && file.getName().endsWith(".csv")) {
    		            		         
    		            			filename.add(file.getName());
    		            			
    		            		}
    		            		
    		            	}
    		            }
    		        	} catch(NullPointerException e) {
    		        		System.exit(0);
    		            }
    		        	
    		        	int size = filename.size();
    		        	
    		        	
    		        for(int i = 0; i < size; i++) {
    		        	String inPath = directoryPath+File.separator+filename.get(i);
    		        	int dotIndex = filename.get(i).lastIndexOf(".");
            			String newName = filename.get(i).substring(0,dotIndex)+"-"+outPath;
            			
            			CSVFileCalculator myCsvFileCalculator = new CSVFileCalculator(inPath, newName, engineName);

            			Thread thread = new Thread(myCsvFileCalculator);
	            		thread.start();
	            		threadsForFile.add(thread);
	            		
	            		try {
	            			thread.join();
	            		} catch(InterruptedException e) {
	            			e.printStackTrace();
	            		}
    		        }
    		        }
    		        else if(directory.isFile()){
    		        	ArrayList<String> a = FileManager.readLinesFromATxtFile(optionHandler.getDataInputFilePath());
        				
        				ArrayList<String> pasreArrayList = new ArrayList<>();
        				
        				ArrayList<String> resultArrayList = new ArrayList<>();
        				
        				String tempString = a.get(0);
        				
        				int col = tempString.length()- tempString.replace(",", "").length() + 1;
        				
        				for(String xString : a)
        				{
        					String k[] = xString.split(",");
        					
        					for(String p: k)
        						pasreArrayList.add(p);
        				}
        				
        				
        				for(int i = 0; i < pasreArrayList.size(); i++)
        				{
        					try {
        						Double.parseDouble(pasreArrayList.get(i));
        					}
        					catch (NumberFormatException e) {
    							 continue;
    						}
        					String[] nStrings = new String[1];
        					nStrings[0] = pasreArrayList.get(i);
        					engine.setInput(nStrings);
        					engine.compute();
        					pasreArrayList.set(i, Double.toString(engine.getResult()));
        				}
        				
        				String result = "";
        				for(int i = 0; i < pasreArrayList.size(); i++) {
        					result += pasreArrayList.get(i) + ",";
        					
        					if((i+1) % col == 0)
        					{
        						result = result.substring(0, result.length()-1);
        						resultArrayList.add(result);
        						result = "";
        					}
        				}
        				FileManager.writeToTxtFile(optionHandler.getDataOutputFilePath(), resultArrayList);
    		        	
    				
    				System.out.println("The " + optionHandler.getDataOutputFilePath() + " file has been successfully written.");
    				System.exit(0);
    				
    		        }
    		        else {
    		        	File[] inputFiles = directory.listFiles();	
    		        	if(inputFiles == null) optionHandler.printHelp(options);
    		        	System.exit(0);
    		        }
    		    

		        
		        
		        
    	    } 
			}
			catch(InvalidCommandException e) 
			{
    	        System.out.println(e.getMessage());
    	        System.out.println("Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100 ");
    	        System.exit(0);
    	    }
    			
    	        

    	    
    		}

}
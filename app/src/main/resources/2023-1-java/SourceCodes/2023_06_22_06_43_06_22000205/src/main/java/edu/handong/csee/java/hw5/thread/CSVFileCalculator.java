package edu.handong.csee.java.hw5.thread;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import edu.handong.csee.java.hw5.fileutil.FileManager;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * A class that uses threads to read and write multiple files simultaneously
 */
public class CSVFileCalculator implements Runnable {
   
   private String inputDirectory;
   private String outputDirectory;
   
   /**
    * Methods that work on threaded execution
    */
   public void run() {
      ArrayList<ArrayList<String>> csvData = readCSV(inputDirectory);
        ArrayList<ArrayList<String>> calculatedData = calculate(csvData);
        writeCSV(outputDirectory, calculatedData);
   }
      
   
   /**
    * Methods for calculating on task when multiple files exist
    */
   public ArrayList<ArrayList<String>> calculate(ArrayList<ArrayList<String>> csvDataList) {
      ArrayList<ArrayList<String>> calculatedDataList = new ArrayList<>();
      
      for(ArrayList<String> csvData : csvDataList) {
    	  ArrayList<String> calculatedCsvData = new ArrayList<>();
    	  
    	  for(String value : csvData) {
    		  value = value.toUpperCase();
    		  if(value.equals("SQRT")) {
    			  SQRTEngine sqrtEngine = new SQRTEngine();
    			  try {
    				  sqrtEngine.setInput(new String[] {});
    				  sqrtEngine.compute();
    				  double result = sqrtEngine.getResult();
    				  calculatedCsvData.add(Double.toString(result));
    			  } catch(OneInputException | NegativeNumberException | MyNumberFormatException e) {
    				  calculatedCsvData.add("Error: " + e.getMessage());
    			  }    			  
    		  }
    		  else if(value.equals("MAX")) {
    			  MaxEngine maxEngine = new MaxEngine();
    			  try {
    				  maxEngine.setInput(new String[] {});
    				  maxEngine.compute();
    				  double result = maxEngine.getResult();
    				  calculatedCsvData.add(Double.toString(result));
    			  } catch(MyNumberFormatException | MinimumInputNumberException e) {
    				  calculatedCsvData.add("Error: " + e.getMessage());
    			  }    			  
    		  }
    		  else if(value.equals("MIN")) {
    			  MinEngine minEngine = new MinEngine();
    			  try {
    				  minEngine.setInput(new String[] {});
    				  minEngine.compute();
    				  double result = minEngine.getResult();
    				  calculatedCsvData.add(Double.toString(result));
    			  } catch(MyNumberFormatException | MinimumInputNumberException e) {
    				  calculatedCsvData.add("Error: " + e.getMessage());
    			  }    			  
    		  }else {
    			  calculatedCsvData.add(value);
    		  }
    	  }
    	  calculatedDataList.add(calculatedCsvData);
      }
      return calculatedDataList;
      
   }
   
   /**
    * Reads a csv file from the given file path and returns it as an ArrayList<ArrayList<String>>.
    */
   public ArrayList<ArrayList<String>> readCSV(String filePath){
      ArrayList<ArrayList<String>> csvData = new ArrayList<>();
      
      File folder = new File(filePath);   // Get all files in a file path
      File[] files = folder.listFiles();
      
      if(files != null) {   // create thread pool, size of the thread pool = number of files
         int numOfCoresInMyCPU = Runtime.getRuntime().availableProcessors(); // get my CPU number
         ExecutorService executor = Executors.newFixedThreadPool(numOfCoresInMyCPU);   // create thread pool, executors ready to get thread
         
         for(File file : files) {
        	 if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
        		 executor.execute(()-> {
                     try {
                  	   Reader reader = new FileReader(file);
                  	   CSVParser csvParser = CSVFormat.DEFAULT.parse(reader);
                  	   List<CSVRecord> records = csvParser.getRecords();
                  	   
                  	   ArrayList<String> lines = new ArrayList<>();
                  	   for(CSVRecord record : records) {
                  		   String line = String.join(",", record);
                  		   try {
                			   Double.parseDouble(line);
                			} catch (MyNumberFormatException e) {
                				System.out.println("Exception-05: " + e.getMessage());
	                       	    return;
	                       }
                  		   lines.add(line);
                  		   
                  	   }
                  	   
                  	   synchronized (csvData) {
                  		   csvData.add(lines);
                        }
                     } catch(IOException e) {
                         System.out.println("Error reading file: " + file.getName());
                                 e.printStackTrace();
                     }
                  });
        	 }
    
         }
         executor.shutdown();
         while(!executor.isTerminated()) {
            // keep waiting until executor finish
         }
      }

      
      return csvData;
      
   }
   
   /**
    * Methods to write calculated input data to a new file
    */
   public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
	    //File inputFile = new File(filePath);
	    File inputFile = new File(inputDirectory);
	    //String inputFileName = inputFile.getName();
	    String[] dirParts = inputDirectory.split(Pattern.quote(File.separator));
	    String lastPart = dirParts[dirParts.length -1];
	    //System.out.println(inputFile);
	    	    
	    String outputPath = File.separator + lastPart + "-"+filePath;

	    int numOfCoresInMyCPU = Runtime.getRuntime().availableProcessors(); // get my CPU number
	    ExecutorService executor = Executors.newFixedThreadPool(numOfCoresInMyCPU); // create thread pool, executors ready to get thread

	    for (ArrayList<String> row : csvData) {
	        executor.execute(() -> {
	            try {
	                Writer writer = new FileWriter(outputPath, true); // append to the existing file
	                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

	                synchronized (csvPrinter) {
	                    csvPrinter.printRecord(row);
	                }

	                csvPrinter.close();
	                System.out.println("The " + filePath + " file has been successfully written.");
	            } catch (IOException e) {
	                System.out.println("Error writing to file");
	                e.printStackTrace();
	            }
	        });
	    }

	    executor.shutdown();
	    while (!executor.isTerminated()) {
	        // keep waiting until executor finishes
	    }
   }
   
   // a constructor
   
   /**
    * Methods that return the inputDirectory variable
    */
   public String getInputDirectory() {
      return inputDirectory;
   }
   
   /**
    * Methods to set inputDirectory
    */
   public void setInputDirectory(String inputDirectory) {
      this.inputDirectory = inputDirectory;
   }

   /**
    * Methods to return the outputDirectory variable
    */
   public String getoutputDirectory() {
      return outputDirectory;
   }

   /**
    * Methods to set the outputDirectory
    */
   public void setOutputDirectory(String outputDirectory) {
      this.outputDirectory = outputDirectory;
      
   }


}
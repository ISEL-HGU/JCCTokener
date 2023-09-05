package edu.handong.csee.java.hw5.thread;

import java.util.ArrayList;

import org.apache.commons.cli.Options;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.File;

/**
 * This class is for handling input and output of CSV files.
 * This class is intended to be used as a thread for handling multiple CSV files.
 */
public class CSVFileCalculator implements Runnable{
	private ArrayList<ArrayList<String>> csvData;
	private String inputFilePath;
	private String outputFilePath;
	private String Engine;
	
	/**
	 * Sets values of the csvData to be processed and calculated.
	 * 
	 * @param csvData values of a CSV file to be processed and calculated
	 */
	public void setCsvData(ArrayList<ArrayList<String>> csvData) {
		this.csvData = csvData;
	}
	
	/**
	 * Gets values of the csvData to be processed and calculated.
	 * 
	 * @return values of a CSV data to be processed and calculated
	 */
	public ArrayList<ArrayList<String>> getCsvData(){
		return this.csvData;
	}
	
	/**
	 * Sets input file path to be read.
	 * 
	 * @param inputFilePath path of the file to be read
	 */
	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}
	
	/**
	 * Gets input file path to be read.
	 * 
	 * @return path of the file to be read
	 */
	public String getInputFilePath(){
		return this.inputFilePath;
	}
	
	/**
	 * Sets output file path to be print.
	 * 
	 * @param outputFilePath path of the file to be print
	 */
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}
	
	/**
	 * Gets output file path to be print.
	 * 
	 * @return path of the file to be print
	 */
	public String getOutputFilePath(){
		return this.outputFilePath;
	}
	
	/**
	 * Sets name of engine to be calculate.
	 * 
	 * @param Engine name of the engine to be calculate
	 */
	public void setEngine(String Engine) {
		this.Engine = Engine;
	}
	
	/**
	 * Gets name of engine to be calculate.
	 * 
	 * @return name of the engine to be calculate
	 */
	public String getEngine(){
		return this.Engine;
	}
	
	/**
	 * Constructs a CSVFileCalculator with the specified file path, Engine.
	 * 
	 * @param inputFilePath path of the file to be read
	 * @param outputFilePath path of the file to be write
	 * @param Engine name of the engine to be calculate
	 */
	public CSVFileCalculator(String inputFilePath, String outputFilePath, String Engine) {
		setInputFilePath(inputFilePath);
		setOutputFilePath(outputFilePath);
		setEngine(Engine);
	}
	
	/**
	 * Runs the CSV file calculation process.
	 * This method will be executed when the thread starts.
	 * the thread will stop when error or exception occurs during the calculation
	 */
	public void run() {
		try {
			setCsvData(readCSV(getInputFilePath()));
			doCalculate(getEngine(),getCsvData());
			writeCSV(getOutputFilePath(),getCsvData());
		} catch(Exception e) {
			return;
		}
	}
	
	/**
	 * Calculates the values of a CSV file using a specific engine.
	 * 
	 * @param Engine name of the engine to be used for calculation
	 * @param csvData values of a CSV file to be processed and calculated
	 * @return CSV data after the calculation
	 * @throws Exception if an error occurs during the calculation process
	 */
	public ArrayList<ArrayList<String>> doCalculate(String Engine, ArrayList<ArrayList<String>> csvData) throws Exception{
		if(csvData.size()==0) throw new Exception();
		if(Engine.equals("SQRT")==true) {
			if(csvData.size()==1) {
				try{
					throw new OneInputException(Engine);
				} catch(OneInputException e) {
					System.out.println(e.getMessage());
					throw new Exception();
				}
			}
			SQRTEngine sqrt = new SQRTEngine();
			int countForHeader = 0;
			for(ArrayList<String> csv : csvData) {
				String[] inputArray = new String[1];
				int index = 0;
				for(String s : csv) {
					if(csv.size() > countForHeader) {
						countForHeader ++;
						continue;
					}
					try {
						inputArray[0] = s;
						sqrt.setInput(inputArray);
						sqrt.compute();
						csv.set(index, Double.toString(sqrt.getResult()));
					} catch(Exception e) {
						throw e;
					}
					index++;
				}
			}
		}
		else if(Engine.equals("MAX")==true) {
			MaxEngine max = new MaxEngine();
			char checkHead = 'Y';
			for(ArrayList<String> csv : csvData) {
				if(csv.size()<2 || csvData.size()==1) {
					try{
						throw new MinimumInputNumberException(Engine, '2');
					} catch(MinimumInputNumberException e) {
						System.out.println(e.getMessage());
						throw new Exception();
					}
				}
				String[] inputArray = new String[csv.size()]; //number of columns
				int i=0;
				for(String s : csv) {
					try {
						inputArray[i] = s;
					} catch(NumberFormatException ex) {
					}
					i++;
				}
				if(checkHead=='Y') {
					csv.add("MAX");
					checkHead = 'N';
				}
				else {
					max.setInput(inputArray);
					max.compute();
					csv.add(Integer.toString((int)max.getResult()));
				}
			}
		}
		else if(Engine.equals("MIN")==true) {
			MinEngine min = new MinEngine();
			char checkHead = 'Y';
			for(ArrayList<String> csv : csvData) {
				if(csv.size()<2 || csvData.size()==1) {
					try{
						throw new MinimumInputNumberException(Engine, '2');
					} catch(MinimumInputNumberException e) {
						System.out.println(e.getMessage());
						throw new Exception();
					}
				}
				String[] inputArray = new String[csv.size()]; //number of columns
				int i=0;
				for(String s : csv) {
					try {
						inputArray[i] = s;
					} catch(NumberFormatException ex) {
					}
					i++;
				}
				if(checkHead=='Y') {
					csv.add("MIN");
					checkHead = 'N';
				}
				else {
					min.setInput(inputArray);
					min.compute();
					csv.add(Integer.toString((int)min.getResult()));
				}
			}
		}
		return csvData;
	}
	
	/**
	 * Reads the CSV file at the specified path.
	 * 
	 * @param inputFilePath path of the file to be read
	 * @return The csvData read from the CSV file
	 */
	public ArrayList<ArrayList<String>> readCSV(String inputFilePath){
		OptionHandler optionHandler = new OptionHandler();
    	Options options = optionHandler.createOptions();
    	ArrayList<ArrayList<String>> valuesOfFile = new ArrayList<>();
    	
		try {
        	Reader in = new FileReader(inputFilePath);
        	Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        	for(CSVRecord record : records) {
        		ArrayList<String> valuesOfRows = new ArrayList<>();
        		for(String s : record) {
        			valuesOfRows.add(s);
        		}
        		valuesOfFile.add(valuesOfRows);
        	}
        } catch(IOException e){
    		optionHandler.printHelp(options);
    		System.exit(0);
    	}
		return valuesOfFile;
	}
	
	/**
	 * Print the csvData to CVS file at the specified path.
	 * 
	 * @param outputFilePath path of the file to be write
	 * @param csvData CSV data after the calculation
	 */
	public void writeCSV(String outputFilePath, ArrayList<ArrayList<String>> csvData) {
		OptionHandler optionHandler = new OptionHandler();
    	Options options = optionHandler.createOptions();
    	
    	//make file name
    	File inputFile = new File(getInputFilePath());
		File outputFile = new File(outputFilePath);
		
		String inputFileName = inputFile.getName();
		int inputDotIndex = inputFileName.lastIndexOf(".");
		String inputFileNameWithoutDot = inputFileName.substring(0, inputDotIndex);
		
		String outputFileName = outputFile.getName();
		int outputDotIndex = outputFileName.lastIndexOf(".");
		String outputFileNameWithoutDot = outputFileName.substring(0, outputDotIndex);
		
		String finalOutputFilePath;
		String finalOutputFileName = inputFileNameWithoutDot + "-" + outputFileNameWithoutDot;
		if(outputFile.getParent()==null) {
			finalOutputFilePath = finalOutputFileName + ".csv";
		} else {
			finalOutputFilePath = outputFile.getParent() + File.separator + finalOutputFileName + ".csv";
		}
		
		//print(write) csvData
		ArrayList<String> header1 = csvData.get(0);
		String header = String.join(",", header1);

		try (PrintWriter writer = new PrintWriter(new FileWriter(finalOutputFilePath))) {
		    writer.write(header + "\n"); 
		    
		    try (CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
		        for (int i = 1; i < csvData.size(); i++) {
		            ArrayList<String> csv = csvData.get(i);
		            printer.printRecord(csv);
		        }
		    } catch (IOException e) {
		    	optionHandler.printHelp(options);
	    		System.exit(0);
		    }
		} catch (IOException e) {
			optionHandler.printHelp(options);
    		System.exit(0);
		}
	}
}
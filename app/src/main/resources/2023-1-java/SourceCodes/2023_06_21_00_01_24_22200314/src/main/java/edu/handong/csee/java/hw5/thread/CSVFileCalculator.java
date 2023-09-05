package edu.handong.csee.java.hw5.thread;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.commons.cli.Options;


import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class works as a calculator for sqrt, min, and max with the csv file in the input path.
 * This class implements Threads. If we take a directory as a input path, we use multiple threads and if we take a file as a input path, we just use one thread.
 */
public class CSVFileCalculator implements Runnable {
	private String task, inputFilePath, outputFilePath;
	private boolean exception = false;
	
	/**
	 * This method is a constructor for this class. This sets the initial values needed for run method.
	 * @param inputFilePath The input path given from the CLI (Edited when path is a directory).
	 * @param outputFilePath The output path given from the CLI (Edited when path is a directory).
	 * @param task The task given from the CLI.
	 */
	public CSVFileCalculator(String inputFilePath, String outputFilePath, String task) {
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
		this.task = task;
	}
	
	/**
	 * This method is called when the thread calls start() method. 
	 */
	public void run() {
		ArrayList<ArrayList<String>> list = readCSV(inputFilePath);
		calculate(list, task.toUpperCase());
		if(exception == true) return;
		writeCSV(outputFilePath, list);
	}
	
	/**
	 * This method reads a CSV file and returns into an Arraylist that has elements as ArrayList that has elements as Strings.
	 * @param filePath inputFilePath The input path given from the CLI.
	 * @return The ArrayList created through reading the CSV.
	 */
	public ArrayList<ArrayList<String>> readCSV(String filePath){
		
		OptionHandler oh = new OptionHandler();
		Options options = oh.createOptions();
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
        Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(filePath));
		} catch (IOException e) {
			oh.printHelp(options);
			exception = true;
		}
        CSVParser csvParser = null;
		try {
			csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
		} catch (IOException e) {
			oh.printHelp(options);
			exception = true;
		}
		
		ArrayList<CSVRecord> csvRecords = (ArrayList<CSVRecord>) csvParser.getRecords();
		ArrayList<String> tmp = new ArrayList<String>();
		
		int size = 0;
		for(int i = 0; i < csvRecords.size(); i++) {
			size = csvRecords.get(i).values().length;
			String[] array = new String[size];
			array = csvRecords.get(i).values();
			
			for(int j = 0; j < array.length ; j++) {
				tmp.add(array[j]);
			}
						
			list.add(tmp);
			tmp = new ArrayList<String>();
		}
		
		if(list.size() == 0) exception = true;
		return list;
	}
	
	/**
	 *  This method writes or creates a new csv with the ArrayList created in the readCSV method. 
	 * @param filePath The output path given from the CLI (Edited when path is a directory).
	 * @param csvData The ArrayList created in the readCSV method.
	 */
	public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
		
		OptionHandler oh = new OptionHandler();
		Options options = oh.createOptions();
		
		String header = csvData.get(0).get(0);
		for(int i = 1; i < csvData.get(0).size(); i++) {
			if(i == 1) header+=",";
			if(i == csvData.get(0).size() - 1) header+= csvData.get(0).get(i);
			else header+=csvData.get(0).get(i)+",";
		}
		
		
        try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
			
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
			
			writer.write(header + "\n");

			
			for(int i = 1; i < csvData.size(); i++) {	
				String tmp[] = new String[csvData.get(i).size()];
	
				
				for(int j = 0; j < csvData.get(i).size(); j++) {
					tmp[j] = csvData.get(i).get(j);
					if(j == csvData.get(i).size() - 1) {
						tmp[j] = csvData.get(i).get(j);
						csvPrinter.printRecord(tmp);
					}
				}
			}
			
			csvPrinter.close();
		} catch (IOException e) {
			oh.printHelp(options);
			System.exit(0);
		}
        

	            
			
	}
	

	/**
	 * This method does the calculation with the ArrayList created in the readCSV method. Tasks could be min, max, sqrt
	 * @param csvData The ArrayList created in the readCSV method.
	 * @param engineName Kind of Calculation 
	 */
	public void calculate(ArrayList<ArrayList<String>> csvData, String engineName) {
		if(exception == true) return;
		
		if(engineName.equals("SQRT")) {
			
			SQRTEngine sqrt = new SQRTEngine();
			
			for(int i = 0; i < csvData.size(); i++) {
				try {
					if(csvData.size() == 1) {
						throw new OneInputException(engineName);
					}
					for(int j = 0; j < csvData.get(i).size(); j++) {
						if(i == 0);
						else{
							try {
								
								if(csvData.get(i).get(j).matches("[0-9]+") || MyNumberFormatException.isFloat( csvData.get(i).get(j)) ){
									sqrt.setInput(Double.parseDouble(csvData.get(i).get(j)));
									try {
										if(sqrt.getInput() < 0) throw new NegativeNumberException(engineName); 
									}
									catch(NegativeNumberException e) {
							    		System.out.println(e.getMessage());
							    		exception = true;
							    		break;
							    	}
									sqrt.compute();
									csvData.get(i).set(j, String.valueOf(sqrt.getResult()));
								}
								else {
									throw new MyNumberFormatException(csvData.get(i).get(j), engineName);
								}
							}
							catch(MyNumberFormatException e) {
								System.out.println(e.getMessage());
								exception = true;
					    		break;
							}
						}
						
					}
					if(exception == true) break;
				}
				catch (OneInputException e) {
					System.out.println(e.getMessage());
					exception = true;
		    		break;
				}
			}
			
		}
		
		
		
		
		else if(engineName.equals("MIN") || engineName.equals("MAX")) {
			MinEngine engine1 = null;
			MaxEngine engine2 = null;
			
			if(engineName.equals("MIN")) engine1 = new MinEngine();
			engine2 = new MaxEngine();
			

			ArrayList<double[]> list = new ArrayList<double[]>();
			double[] tmp = new double[csvData.get(0).size()];
			
			for(int i = 0; i < csvData.size(); i++) {
				try {
					if(csvData.size() == 1) {
						throw new MinimumInputNumberException(engineName);
					}
				
					for(int j = 0; j < csvData.get(i).size(); j++) {
						
						if(i == 0);
						
						else{
							try {
						
								if(csvData.get(i).get(j).matches("[0-9]+") || MyNumberFormatException.isFloat( csvData.get(i).get(j)) ){
									if( j  == csvData.get(i).size() - 1) {
										tmp[j] = Double.parseDouble(csvData.get(i).get(j));
										if(tmp.length < 2) throw new MinimumInputNumberException(engineName);
										list.add(tmp);
										tmp = new double[csvData.get(i).size()];
									}
									else tmp[j] = Double.parseDouble(csvData.get(i).get(j));
									
								}
								
								
								else {
									throw new MyNumberFormatException(csvData.get(i).get(j), engineName);
								}
							}
							catch(MyNumberFormatException e) {
								System.out.println(e.getMessage());
								exception = true;
					    		break;
							} catch (MinimumInputNumberException e) {
								System.out.println(e.getMessage());
								exception = true;
					    		break;
							}
						}
						
					}
				}
				catch (MinimumInputNumberException e) {
					System.out.println(e.getMessage());
					exception = true;
		    		break;
				}
				
				
				if(exception == true) break;

				
				
			}
			for(int j = 0; j <= list.size(); j++) {
				if(j == 0) {
					if(engineName.equals("MIN")) csvData.get(j).add("MIN");
					else csvData.get(j).add("MAX");
				}
				else {
					if(engineName.equals("MIN")) { 
						engine1.setInputs(list.get(j-1));
						engine1.compute();
						csvData.get(j).add(String.valueOf(Integer.parseInt(String.valueOf(Math.round(engine1.getResult())))));
					}
					else {
						engine2.setInputs(list.get(j-1));
						engine2.compute();
						csvData.get(j).add(String.valueOf(Integer.parseInt(String.valueOf(Math.round(engine2.getResult())))));
					}
				}
			}
		}		
	}
	

	/**
	 * This method returns the private field task.
	 * @return The private field task.
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * This method returns the private field inputFilePath.
	 * @return The private field inputFilePath.
	 */
	public String getInputFilePath() {
		return inputFilePath;
	}
	
	/**
	 * This method returns the private field inputFilePath.
	 * @return The private field inputFilePath.
	 */
	public String getOutputFilePath() {
		return outputFilePath;
	}
	
	/**
	 * This method returns the private field exception.
	 * @return The private field exception.
	 */
	public boolean getException() {
		return exception;
	}
	
	/**
	 * This method sets the value of the private field task;
	 * @param task The String value that would be set as the private field task.
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	/**
	 * This method sets the value of the private field inputFilePath;
	 * @param task The String value that would be set as the private field inputFilePath.
	 */
	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}
	
	/**
	 * This method sets the value of the private field outputFilePath;
	 * @param task The String value that would be set as the private field outputFilePath.
	 */
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}
	
	/**
	 * This method sets the value of the private field exception;
	 * @param task The boolean value that would be set as the private field exception.
	 */
	public void setException(boolean exception) {
		this.exception = exception;
	}
	
	
}

package edu.handong.csee.java.hw5.thread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import edu.handong.csee.java.hw5.*;

public class CSVFileCalculator implements Runnable {
	private String inputFilePath;
	private String outputFilePath;
	private String taskName;
	private String inputValues;
	@Override
	public void run() {
		List<List<String>> csvData = readCSV(inputFilePath);
		calculate(csvData);
		writeCSV(outputFilePath, csvData);
		try {
	        List<List<String>> csvData1 = readCSV(inputFilePath);
	        List<List<String>> calculatedData = calculate(csvData1);

	        writeCSV(outputFilePath, calculatedData);

	        System.out.println("Thread has finished its task.");
	    } catch (Exception e) {
	        System.err.println("Error occurred in the thread: " + e.getMessage());
	    }
	}
	private List<List<String>> calculate(List<List<String>> csvData) {
		return csvData;
		
	}
	public CSVFileCalculator(String filePath, String taskName, String outputFilePath, String inputValues) {
		this.inputFilePath = inputFilePath;
	    this.outputFilePath = outputFilePath;
	    this.taskName = taskName;
	    this.inputValues = inputValues;
	}
	public List<List<String>> readCSV(String filePath) {
		List<List<String>> csvData = new ArrayList<>();
		return csvData;
	}
	public void writeCSV(String filePath, List<List<String>> csvData) {
	}
	public String getInputFilePath() {
	    return inputFilePath;
	}

	public String getOutputFilePath() {
	    return outputFilePath;
	}

	public String getTaskName() {
	    return taskName;
	}

	public String getInputValues() {
	    return inputValues;
	}
	public void setInputFilePath(String inputFilePath) {
	    this.inputFilePath = inputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
	    this.outputFilePath = outputFilePath;
	}

	public void setTaskName(String taskName) {
	    this.taskName = taskName;
	}

	public void setInputValues(String inputValues) {
	    this.inputValues = inputValues;
	}
	private static List<String> getAllCSVFiles(String inputPath) {
	    List<String> csvFiles = new ArrayList<>();

	    File directory = new File(inputPath);
	    File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

	    if (files != null) {
	        for (File file : files) {
	            if (file.isFile()) {
	                csvFiles.add(file.getAbsolutePath());
	            }
	        }
	    }
	    return csvFiles;
	}

}

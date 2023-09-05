package edu.handong.csee.java.hw5.thread;

import edu.handong.csee.java.hw5.engines.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.io.BufferedWriter;
import java.io.*;
import java.util.*;
import java.nio.file.*;

import org.apache.commons.csv.*;

/**
 * This class calculates and creates new CSV files based on given engine type and directory path.
 */
public class CSVFileCalculator implements Runnable{
	
	/**
     * The name of the computation engine to use (SQRT, MAX, or MIN).
     */
	private String engineName;

	/**
     * The directory path where the input CSV files are located.
     */
	private String directoryPath;

    /**
     * The base name for the output CSV files.
     */
	private String outputName;

    /**
     * List of files to be processed.
     */
    private List<File> fileList;
    

    /**
     * Gets the name of the computation engine.
     *
     * @return the engine name
     */
	public String getEngineName() {
		return engineName;
	}

    /**
     * Sets the name of the computation engine.
     *
     * @param engineName the name of the engine
     */
	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

    /**
     * Gets the directory path of the input CSV files.
     *
     * @return the directory path
     */
	public String getDirectoryPath() {
		return directoryPath;
	}

    /**
     * Sets the directory path of the input CSV files.
     *
     * @param directoryPath the directory path
     */
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

    /**
     * Gets the base name for the output CSV files.
     *
     * @return the output name
     */
	public String getOutputName() {
		return outputName;
	}

    /**
     * Sets the base name for the output CSV files.
     *
     * @param outputName the output name
     */
	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

    /**
     * Gets the list of files to be processed.
     *
     * @return the list of files
     */
	public List<File> getFileList() {
		return fileList;
	}

    /**
     * Sets the list of files to be processed.
     *
     * @param fileList the list of files
     */
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}
    
	/**
     * Reads the CSV file and returns its content as a list of string lists.
     * @param filePath the path to the CSV file.
     * @return the content of the CSV file as a list of string lists.
     */
    public ArrayList<ArrayList<String>> readCSV(String filePath) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                ArrayList<String> singleLine = new ArrayList<>();
                csvRecord.forEach(singleLine::add);
                data.add(singleLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Writes the given CSV data into the specified file path.
     * @param filePath the path to the file where the data should be written.
     * @param csvData the CSV data that should be written to the file.
     */
    public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            for (ArrayList<String> row : csvData) {
                csvPrinter.printRecord(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * Constructor for the CSVFileCalculator.
	 * @param engineName the name of the calculation engine to be used (SQRT, MAX, or MIN).
	 * @param directoryPath the directory where the input files are located.
	 * @param outputName the base name for the output files.
	 */
	public CSVFileCalculator(String engineName, String directoryPath, String outputName) {
		this.engineName = engineName;
        this.directoryPath = directoryPath;
        this.outputName = outputName;
        this.fileList = new ArrayList<>();
    }
	
	/**
	 * Calculates the result for each row in the file using the specified engine
	 * and writes the result in a new CSV file.
	 * @param file the file to calculate the result for.
	 */
	public void calculate(File file) {
		ArrayList<ArrayList<String>> data = readCSV(file.getAbsolutePath());
	    ArrayList<ArrayList<String>> resultData = new ArrayList<>();

	    Computable engine = null;
	    switch(engineName) {
	        case "SQRT":
	            engine = new SQRTEngine();
	            break;
	        case "MAX":
	            engine = new MaxEngine();
	            break;
	        case "MIN":
	            engine = new MinEngine();
	            break;
	    }

	    ArrayList<String> header = data.get(0);
	    header.add(engineName);
	    resultData.add(header);

	    for (int i = 1; i < data.size(); i++) {
	        ArrayList<String> row = data.get(i);
	        ArrayList<String> resultRow = new ArrayList<>();
	        for (String value : row) {
	            if (engine instanceof SQRTEngine) {
	                engine.setInput(new String[] { value });
	                engine.compute();
	                resultRow.add(String.valueOf(engine.getResult()));
	            } else {
	                resultRow.add(value);
	            }
	        }
	        
	        if (!(engine instanceof SQRTEngine)) {
	            engine.setInput(row.toArray(new String[0]));
	            engine.compute();
	            resultRow.add(String.valueOf(engine.getResult()));
	        }
	        resultData.add(resultRow);
	    }

	    String originalName = file.getName();
	    int originalIndex = originalName.lastIndexOf(".");
	    String baseOriginalName;
	    String baseOutputName;
	    String newFileName;

	    if(originalIndex == -1) {
	        baseOriginalName = originalName;
	    } else {
	        baseOriginalName = originalName.substring(0, originalIndex);
	    }

	    int outputIndex = outputName.lastIndexOf(".");
	    if(outputIndex == -1) {
	        baseOutputName = outputName;
	    } else {
	        baseOutputName = outputName.substring(0, outputIndex);
	    }

	    newFileName = baseOriginalName + "-" + baseOutputName;

	    writeCSV(directoryPath + "/" + newFileName + ".csv", resultData);
	}

	/**
	 * Runs the CSVFileCalculator for each file in the specified directory.
	 * Each file is processed in a separate thread.
	 */
	public void run() {
	    try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
	        paths.filter(Files::isRegularFile).forEach(file -> fileList.add(file.toFile()));
	    } catch (IOException e) {

	    }
	    for (File file : fileList) {
	        new Thread(() -> {
	            try {
	                ArrayList<ArrayList<String>> data = readCSV(file.getAbsolutePath());
	                calculate(file);
	            } catch (Exception e) {

	            }
	        }).start();
	    }
	}
}



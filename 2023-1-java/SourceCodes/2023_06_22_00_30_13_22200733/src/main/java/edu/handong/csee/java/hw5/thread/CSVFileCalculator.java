package edu.handong.csee.java.hw5.thread;

import org.apache.commons.csv.*;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * A class that represents a CSV file calculator.
 * It implements the Runnable interface to support multi-threading.
 */
public class CSVFileCalculator implements Runnable {

    private String inputFilePath;
    private String outputFilePath;
    private String task;
    /**
     * Getter for the input file path.
     * @return the input file path
     */
    public String getInputFilePath() {
		return inputFilePath;
	}
    /**
     * Setter for the input file path.
     * @param inputFilePath the input file path to set
     */
	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}
	/**
	 * Getter for the output file path.
	 * @return the output file path
	 */
	public String getOutputFilePath() {
		return outputFilePath;
	}
	/**
	 * Setter for the output file path.
	 * @param outputFilePath the output file path to set
	 */
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}
	/**
	 * Getter for the task.
	 * @return the task
	 */
	public String getTask() {
		return task;
	}
	/**
	 * Setter for the task.
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}

	
	/**
	 * Constructor for the CSVFileCalculator class.
	 * @param inputFilePath the input file path
	 * @param outputFilePath the output file path
	 * @param task the task to perform
	 */
    public CSVFileCalculator(String inputFilePath, String outputFilePath, String task) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.task = task;
    }

    /**
     * Overridden run method from the Runnable interface.
     * It reads the CSV file, performs the calculation, and writes the result to the output file.
     */
    public void run() {
        ArrayList<ArrayList<String>> csvData = readCSV(inputFilePath);
        ArrayList<ArrayList<String>> newCsvData = calculate(csvData, task);
        if(newCsvData != null) {
        	writeCSV(outputFilePath, newCsvData);
        }
        
    }
    /**
     * Reads the CSV file and returns the data as a 2D ArrayList.
     * @param filePath the path of the CSV file to read
     * @return the data read from the CSV file
     */
    public static ArrayList<ArrayList<String>> readCSV(String filePath) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                ArrayList<String> row = new ArrayList<>();

                for (String value : csvRecord) {
                    row.add(value);
                }

                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
    /**
     * Writes the data to a CSV file.
     * @param filePath the path of the output CSV file
     * @param csvData the data to write to the CSV file
     */
    public static void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
        	
        	ArrayList<String> line = csvData.get(0);
        	String header = "";
        	for(int i = 0; i < line.size();i++) {
        		header += line.get(i);
        		if(i < line.size()-1) {
        			header += ",";
        		}
        	}
        	writer.write(header + "\n");
        	
        	for(int i = 1; i < csvData.size(); i++) {
        		ArrayList<String> record = csvData.get(i);
        		csvPrinter.printRecord(record);
        	}
        	

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Performs the specified task on the CSV data and returns the result.
     * @param csvData the input CSV data
     * @param task the task to perform ("SQRT", "MIN", or "MAX")
     * @return the result after performing the task
     */
    public ArrayList<ArrayList<String>> calculate(ArrayList<ArrayList<String>> csvData, String task) {
        ArrayList<Integer> colMin = new ArrayList<>();
        ArrayList<Integer> colMax = new ArrayList<>();

        boolean error = false;

        if (task.equals("SQRT")) {
            try {
                if (csvData.size() == 0) {
                    throw new OneInputException(task);
                }
            } catch (OneInputException e) {
                if (!error) {
                    System.out.println(e.getMessage());
                }
                error = true;
            }
            if (csvData.size() == 1) {
                error = true;
            }
            
            ArrayList<ArrayList<String>> result = new ArrayList<>();  
            
            for (int i = 0; i < csvData.size(); i++) {
                ArrayList<String> newRow = new ArrayList<>();  
                ArrayList<String> row = csvData.get(i);
                
                for (int j = 0; j < row.size(); j++) {
                	if(i != 0) {
                		double num = 0;
                        try {
                            if (!row.get(j).matches("-?\\d+(\\.\\d+)?")) {
                                throw new MyNumberFormatException(row.get(j), task);
                            }
                            num = Double.parseDouble(row.get(j));
                            if (num < 0) {
                                throw new NegativeNumberException(task);
                            }

                        } catch (MyNumberFormatException | NegativeNumberException e) {
                            if (!error) {
                                System.out.println(e.getMessage());
                            }
                            error = true;
                        }
                        double sqrtNum = Math.sqrt(num);
                        newRow.add(String.valueOf(sqrtNum));  
                	}else {
                		newRow.add(row.get(j));
                	}
                }
                
                result.add(newRow);  
            }
            if(!error) {
            	return result;
            }
        } else if (task.equals("MIN") || task.equals("MAX")) {
            try {
                if (csvData.size() == 0) {
                    throw new MinimumInputNumberException(task);
                }
            } catch (MinimumInputNumberException e) {
                if (!error) {
                    System.out.println(e.getMessage());
                }
                error = true;
            }
            for (int i = 0; i < csvData.get(0).size(); i++) {
                colMin.add(Integer.MAX_VALUE);
                colMax.add(Integer.MIN_VALUE);
            }
            if (csvData.size() == 1) {
                error = true;
            }
            for (int i = 1; i < csvData.size(); i++) {
                ArrayList<String> row = csvData.get(i);
                int rowMin = Integer.MAX_VALUE;
                int rowMax = Integer.MIN_VALUE;
                for (int j = 0; j < row.size(); j++) {
                    try {
                        if (row.size() <= 1) {
                            throw new MinimumInputNumberException(task);
                        }
                        if (!row.get(j).matches("-?\\d+(\\.\\d+)?")) {
                            throw new MyNumberFormatException(row.get(j), task);
                        }
                    } catch (MinimumInputNumberException | MyNumberFormatException e) {
                        if (!error) {
                            System.out.println(e.getMessage());
                        }
                        error = true;
                    }
                    if (!error) {
                        double num = Double.parseDouble(row.get(j));
                        int intNum = (int) num;
                        if (intNum < colMin.get(j)) {
                            colMin.set(j, intNum);
                        }
                        if (intNum > colMax.get(j)) {
                            colMax.set(j, intNum);
                        }
                        if (intNum < rowMin) {
                            rowMin = intNum;
                        }
                        if (intNum > rowMax) {
                            rowMax = intNum;
                        }
                    }
                }
                if (task.equals("MIN")) {
                    row.add(String.format("%d", rowMin));
                } else {
                    row.add(String.format("%d", rowMax));
                }
            }

            ArrayList<String> firstRow = csvData.get(0);
            if (task.equals("MIN")) {
                firstRow.add("MIN");
            } else {
                firstRow.add("MAX");
            }
        }

        if (error) {
            return null;
        }

        return csvData;
    }

}
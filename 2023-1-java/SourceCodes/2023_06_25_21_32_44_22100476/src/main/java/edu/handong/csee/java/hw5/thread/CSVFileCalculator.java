package edu.handong.csee.java.hw5.thread;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 * The CSVFileCalculator class is responsible for reading and processing CSV files in a separate thread.
 * It implements the Runnable interface to run the logic in a separate thread.
 * The processed results, such as maximum value, minimum value, and square root sum, can be accessed using getter methods.
 * It also provides methods to read CSV files, write CSV files, and perform calculations on the data.
 * 
 */

public class CSVFileCalculator implements Runnable {
	private ArrayList<ArrayList<String>> csvData;
    private double maxResult;
    private double minResult;
    private double sqrtResult;
    private String filePath;    

    /**
     * Constructs a CSVFileCalculator object with the specified file path.
     * 
     * @param filePath The path of the CSV file to be processed
     */

    public CSVFileCalculator(String filePath) {
        this.filePath = filePath;
        this.csvData = new ArrayList<>();
        this.maxResult = Double.MIN_VALUE;
        this.minResult = Double.MAX_VALUE;
        this.sqrtResult = 0.0;
    }

    /**
     * Reads the CSV file and returns the data as a list of lists.
     * Each inner list represents a row in the CSV file.
     * 
     * @return The data from the CSV file as a list of lists
     */
    public ArrayList<ArrayList<String>> readCSV() {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ArrayList<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value);
                }
                csvData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }

    /**
     * Writes the CSV data to a file at the specified file path.
     * 
     * @param filePath The path of the file to write the CSV data
     * @param csvData The CSV data to be written to the file
     */
    public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (ArrayList<String> row : csvData) {
                StringBuilder sb = new StringBuilder();
                for (String value : row) {
                    sb.append(value).append(",");
                }
                sb.deleteCharAt(sb.length() - 1); // Remove the trailing comma
                writer.write(sb.toString());
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Performs calculations on the CSV data.
     * Calculates the maximum value, minimum value, and square root sum of the numbers in the CSV data.
     */
    public void calculate() {
        for (ArrayList<String> row : csvData) {
            for (String value : row) {
                double num = Double.parseDouble(value);
                maxResult = Math.max(maxResult, num);
                minResult = Math.min(minResult, num);
                sqrtResult += Math.sqrt(num);
            }
        }
    }


    /**
     * Gets the file path of the CSV file.
     * 
     * @return The file path of the CSV file
     */
    public String getFilePath() {
        return filePath;
    }
     /**
     * Sets the file path of the CSV file.
     * 
     * @param filePath The file path of the CSV file
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Gets the CSV data read from the file.
     * 
     * @return The CSV data as a list of lists
     */
    public ArrayList<ArrayList<String>> getCsvData() {
        return csvData;
    }
    
     /**
     * Sets the CSV data to be processed.
     * 
     * @param csvData The CSV data as a list of lists
     */
    public void setCsvData(ArrayList<ArrayList<String>> csvData) {
        this.csvData = csvData;
    }
    /**
     * Gets the maximum value calculated from the CSV data.
     * 
     * @return The maximum value
     */
    public double getMaxResult() {
        return maxResult;
    }
    
    
    /**
     * Sets the maximum value calculated from the CSV data.
     * 
     * @param maxResult The maximum value
     */
    public void setMaxResult(double maxResult) {
        this.maxResult = maxResult;
    }
    /**
     * Gets the minimum value calculated from the CSV data.
     * 
     * @return The minimum value
     */
    public double getMinResult() {
        return minResult;
    }
    /**
     * Sets the minimum value calculated from the CSV data.
     * 
     * @param minResult The minimum value
     */
    public void setMinResult(double minResult) {
        this.minResult = minResult;
    }
    /**
     * Gets the square root sum calculated from the CSV data.
     * 
     * @return The square root sum
     */
    public double getSqrtResult() {
        return sqrtResult;
    }
    
    /**
     * Sets the square root sum calculated from the CSV data.
     * 
     * @param sqrtResult The square root sum
     */
    public void setSqrtResult(double sqrtResult) {
        this.sqrtResult = sqrtResult;
    }

    /**
     * @Override
     */
        public void run() {

            /**
             * Executes the logic to be executed by the thread. It performs calculations on the CSV file data.
             * This method calculates the maximum value, minimum value, and square root sum of the numbers in the CSV file.
             * The calculated results are stored in the respective instance variables.
             * Additionally, the CSV file data is read and stored in the 'csvData' instance variable.
             */

            // Implementation for the logic to be executed by the thread
            calculate();
            readCSV();
            
        }
    }

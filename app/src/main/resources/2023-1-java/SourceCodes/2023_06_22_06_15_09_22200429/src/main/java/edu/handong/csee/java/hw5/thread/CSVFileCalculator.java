package edu.handong.csee.java.hw5.thread;

import org.apache.commons.csv.*;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is csvFileCaculator that calculate csv file.
 */
public class CSVFileCalculator implements Runnable {
	
    private String engineName;
    private String filePath;
    
    /**
     * This method is constructor that init eginename and filepath
     */
    public CSVFileCalculator(String engineName, String filePath) {
        this.engineName = engineName;
        this.filePath = filePath;
    }
    
    /**
     * this method is use csv and write and read.
     */
    public ArrayList<ArrayList<String>> readCSV(String filePath) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        try {
            CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT);

            for (CSVRecord csvRecord : csvParser) {
                ArrayList<String> row = new ArrayList<>();

                for (String value : csvRecord) {
                    row.add(value);
                }

                csvData.add(row);
            }

            csvParser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvData;
    }

    /**
     * This method is write csv
     */
    public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
        try {
            CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), CSVFormat.DEFAULT);

            for (ArrayList<String> row : csvData) {
                csvPrinter.printRecord(row);
            }

            csvPrinter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method is run csv file for write and compute and read.
     */
    public void run() throws InvalidCommandException {
        ArrayList<ArrayList<String>> csvData = readCSV(filePath);

        switch (engineName) {
            case "SQRT":
                calculateSQRT(csvData);
                break;
            case "MAX":
                calculateMAX(csvData);
                break;
            case "MIN":
                calculateMIN(csvData);
                break;
            default:
                throw new InvalidCommandException("Exception-01: Invalid command: " + engineName + "\n"
                        + "Please put a computing engine option such as SQRT, MAX, and MIN. For example, ./app MAX -i input.csv -o output.csv");
        }

        writeCSV(filePath, csvData);
    }

    /**
     * This method is compute SQRT
     */
    public void calculateSQRT(ArrayList<ArrayList<String>> csvData) {
      
    }
    
    /**
     * This method is compute Max
     */
    public void calculateMAX(ArrayList<ArrayList<String>> csvData) {

    }
    
    /**
     * This method is compute min.
     * @param csvData
     */
    public void calculateMIN(ArrayList<ArrayList<String>> csvData) {
   
    }
}

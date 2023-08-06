package edu.handong.csee.java.hw5.thread;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;

import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * class CSVFileCalculator (runnable)
 */
public class CSVFileCalculator implements Runnable {
/**
 * inputfilepath
 */
    private String inputFilePath;
/**
 * set inputfilepath
 * @param inputFilePath
 */
    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }
/**
 * get inputfilepath
 * @return
 */
    public String getInputFilePath() {
        return inputFilePath;
    }




/**
 * outputfilepath
 */
    private String outputFilePath;
/**
 * set outputfilepath
 * @param outputFilePath
 */
    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }
/**
 * get outputfilepath
 * @return
 */
    public String getOutputFilePath() {
        return outputFilePath;
    }

/**
 * engineName
 */
    private String engineName;
    
/**
 * get enginename
 * @return
 */
    public String getEngineName() {
        return engineName;
    }
/**
 * set enginename
 * @param engineName
 */
    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }
/**
 * calculator method
 * @param inputFilePath
 * @param outputFilePath
 * @param engineName
 */
    public CSVFileCalculator(String inputFilePath, String outputFilePath, String engineName) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.engineName = engineName;
    }
/**
 * readCSV method
 * @param filePath
 * @return
 */
    public ArrayList<ArrayList<String>> readCSV(String filePath) {
    ArrayList<ArrayList<String>> csvData = new ArrayList<>();

    File file = new File(filePath);

    if (file.isDirectory()) {
        File[] files = file.listFiles();

        for (File csvFile : files) {
            if (csvFile.isFile() && csvFile.getName().endsWith(".csv")) {
                try (Reader reader = new FileReader(csvFile);
                     CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
                    boolean skipHeader = true; // Flag to skip the header row

                    for (CSVRecord csvRecord : csvParser) {
                        if (skipHeader) {
                            skipHeader = false;
                            continue; // Skip the header row
                        }
                        ArrayList<String> rowData = new ArrayList<>();
                        for (String value : csvRecord) {
                            rowData.add(value);
                        }
                        csvData.add(rowData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    } else if (file.isFile() && file.getName().endsWith(".csv")) {
        try (Reader reader = new FileReader(file);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
               
                ArrayList<String> rowData = new ArrayList<>();
                for (String value : csvRecord) {
                    rowData.add(value);
                }
                csvData.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Invalid file path: " + filePath);
    }

    return csvData;
}


/**
 * writeCSV method
 * @param outputFilePath
 * @param csvData
 */
    public void writeCSV(String outputFilePath, ArrayList<ArrayList<String>> csvData) {
    try (Writer writer = new FileWriter(outputFilePath);
         BufferedWriter bufferedWriter = new BufferedWriter(writer);
         CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT)) {

        // Write the header line
        ArrayList<String> headerRow = csvData.get(0);
        String header = String.join(",", headerRow);
        bufferedWriter.write(header + "\n");

        // Write the data lines
        for (int i = 1; i < csvData.size(); i++) {
            ArrayList<String> rowData = csvData.get(i);
            csvPrinter.printRecord(rowData);
        }

        csvPrinter.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Remove empty lines
    try {
        File outputFile = new File(outputFilePath);
        RandomAccessFile raf = new RandomAccessFile(outputFile, "rw");
        long length = raf.length();
        if (length > 0) {
            raf.setLength(length - 2); // Remove empty lines (reduce the length by the size of CRLF)
        }
        raf.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}



/**
 * run method
 */
public void run() {
    File directory = new File(inputFilePath);

    if (directory.isDirectory()) {
        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".csv")) {
                ArrayList<ArrayList<String>> csvData = readCSV(file.getAbsolutePath());

                calculate(engineName, csvData, file.getName());

                //System.out.println("Calculation completed for file: " + file.getAbsolutePath());
            }
        }
    } else {
        System.out.println("Invalid directory path: " + inputFilePath);
    }
}
/**
 * calculate method
 * @param task
 * @param csvData
 * @param inputFileName
 */

public void calculate(String task, ArrayList<ArrayList<String>> csvData, String inputFileName) {
    // Get the header row and add the corresponding column
            if (csvData.isEmpty() || csvData.size() == 0) {
                if(task.equalsIgnoreCase("MAX")||task.equalsIgnoreCase("MIN"))
                {System.out.println("Exception-02: You need at least 2 input values for " + task.toUpperCase() + ".");}
                else {System.out.println("Exception-04: You need one input value for " + task.toUpperCase() + ".");}
                return;
            }
        ArrayList<String> headerRow = csvData.get(0);

    // Create an instance of the corresponding engine based on the task
    Computable engine = null;

    switch (task.toUpperCase()) {
        case "MAX":
            engine = new MaxEngine();
            break;
        case "MIN":
            engine = new MinEngine();
            break;
        case "SQRT":
            engine = new SQRTEngine();
            break;
        default:
            System.out.println("Exception-01: Invalid command: " + task.toUpperCase() + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
            return;
    }

    // Iterate through the data rows and perform the computation
    if (task.equalsIgnoreCase("SQRT")) {
        for (int i = 1; i < csvData.size(); i++) {
            ArrayList<String> rowData = csvData.get(i);
            int numValues = rowData.size();

            // Iterate through each value in the row and compute square root
            for (int j = 0; j < numValues; j++) {
                String value = rowData.get(j);

                // Handle exceptions for invalid input
                try {
                    engine.setInput(new String[]{value});
                    engine.compute();
                    rowData.set(j, Double.toString(engine.getResult()));
                } catch (NumberFormatException | OneInputException | NegativeNumberException | MinimumInputNumberException | InvalidCommandException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
        }

        // Write the updated data to the CSV file
        String outputFileName = inputFileName.substring(0, inputFileName.lastIndexOf(".")) + "-" + task.toLowerCase() + ".csv";
        String outputFilePath = outputFileName;
        writeCSV(outputFilePath, csvData);
    } else { // MAX, MIN
        if(csvData.size() == 1) {
            return;
        }
            headerRow.add(task.toUpperCase()); 

        for (int i = 1; i < csvData.size(); i++) {
            ArrayList<String> rowData = csvData.get(i);
            double[] inputValues = new double[rowData.size()];

            // Extract the input values from the row
            for (int j = 0; j < rowData.size(); j++) {
                try{inputValues[j] = Double.parseDouble(rowData.get(j));}
                 catch (NumberFormatException e) {
               System.out.println("Exception-05: The input value should be converted into a number. (" + rowData.get(j) + " is not a number value for " + task.toUpperCase() + ".)");
               return;
            }
            }

            // Convert double[] to String[]
            String[] stringInputs = new String[inputValues.length];
            for (int k = 0; k < inputValues.length; k++) {
                stringInputs[k] = Double.toString(inputValues[k]);
            }

            // Set the input values for the engine and handle exceptions
            try {
                engine.setInput(stringInputs);
            } catch (MyNumberFormatException | OneInputException | NegativeNumberException | MinimumInputNumberException | InvalidCommandException e) {
                System.out.println(e.getMessage());
                return;
            }

            // Compute the result
            engine.compute();

            // Get the result
            double result = engine.getResult();

            // Add the result value to the row
            rowData.add(Integer.toString((int) result));

        }

        // Write the updated data to the CSV file
        
        String outputFileName = inputFileName.substring(0, inputFileName.lastIndexOf(".")) + "-" + outputFilePath;
        String outputFilePath = outputFileName;
        writeCSV(outputFilePath, csvData);
    }
}

}  

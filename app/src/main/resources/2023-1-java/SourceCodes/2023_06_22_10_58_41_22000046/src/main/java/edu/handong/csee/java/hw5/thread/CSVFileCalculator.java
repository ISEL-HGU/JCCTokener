package edu.handong.csee.java.hw5.thread;

import java.util.ArrayList;

import edu.handong.csee.java.hw5.engines.Computable;
import edu.handong.csee.java.hw5.engines.MaxEngine;
import edu.handong.csee.java.hw5.engines.MinEngine;
import edu.handong.csee.java.hw5.engines.SQRTEngine;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class represents a calculator that can perform MAX, MIN, SQRT
 * calculations on CSV data.
 * It implements the Runnable interface to support running in a separate thread.
 */
public class CSVFileCalculator implements Runnable {

    private String task;
    private String inputPath;
    private String outputPath;

    /**
     * Constructs a CSVFileCalculator object with the given task name.
     *
     * @param taskName   the name of the task to perform
     * @param inputPath  the path to the input CSV file
     * @param outputPath the path to the output CSV file
     */
    public CSVFileCalculator(String taskName, String inputPath, String outputPath) {
        this.task = taskName;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    /**
     * Sets the fileInputPath.
     *
     * @param inputPath the name of the input path
     */
    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Returns the fileInputPath.
     *
     * @return inputPath
     */
    public String getInputPath() {
        return this.inputPath;
    }

    /**
     * Sets the fileOutputPath.
     *
     * @param outputPath the name of the output path
     */
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * Returns the fileOutputPath.
     *
     * @return outputPath
     */
    public String getOutputPath() {
        return this.outputPath;
    }

    /**
     * Sets the task name for the calculator.
     *
     * @param taskName the name of the task to perform
     */
    public void setTaskName(String taskName) {
        this.task = taskName;
    }

    /**
     * Retrieves the current task name.
     *
     * @return the name of the task
     */
    public String getTaskName() {
        return this.task;
    }

    /**
     * Reads a CSV file and returns its data as a list of rows, each represented by
     * a list of strings.
     *
     * @param filePath the path to the CSV file
     * @return a list of rows from the CSV file
     */
    public ArrayList<ArrayList<String>> readCSV(String filePath) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(filePath);
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);

            for (CSVRecord record : csvParser) {
                ArrayList<String> row = new ArrayList<>();
                for (String value : record) {
                    row.add(value);
                }
                csvData.add(row);
            }

            csvParser.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvData;
    }

    /**
     * Writes the CSV data to a file at the specified path.
     *
     * @param filePath the path to write the CSV file
     * @param csvData  the CSV data to write
     */
    public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {

        File output = new File(filePath);

        try {
            FileWriter fileWriter = new FileWriter(output);
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);

            boolean isHeader = true;
            for (ArrayList<String> row : csvData) {
                ArrayList<String> line = new ArrayList<>(row);

                if (isHeader) {
                    StringBuilder headerBuilder = new StringBuilder();
                    for (String attr : csvData.get(0)) {
                        headerBuilder.append(attr).append(",");
                    }
                    String header = headerBuilder.deleteCharAt(headerBuilder.length() - 1).toString();

                    if (task.equalsIgnoreCase("MAX")) {
                        header = header + ",MAX";
                    } else if (task.equalsIgnoreCase("MIN")) {
                        header = header + ",MIN";
                    }

                    fileWriter.write(header + "\n");

                    isHeader = false;
                } else {
                    if (!this.task.equals("SQRT")) {
                        try {
                            double[] tmp = calculate(row, this.task);
                            int result = ((int) tmp[0]);

                            line.add(String.valueOf(result));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        csvPrinter.printRecord(line);
                    } else {
                        ArrayList<String> sqrtLine = new ArrayList<String>();
                        try {
                            double[] result = calculate(row, this.task);
                            for (double sqrtData : result) {
                                String temp = String.valueOf(sqrtData);
                                sqrtLine.add(temp);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        csvPrinter.printRecord(sqrtLine);
                    }
                }
            }

            csvPrinter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overrides the run() method in the Runnable interface.
     * This method reads the CSV file, performs the calculation, and writes the result to the output CSV file.
     */
    @Override
    public void run() {
        ArrayList<ArrayList<String>> csvData = readCSV(this.inputPath);
        writeCSV(this.outputPath, csvData);
    }

    /**
     * Performs a calculation on the given lines of CSV data based on the task name.
     *
     * @param lines    the lines of CSV data to perform the calculation on
     * @param taskName the name of the task to perform
     * @return the result of the calculation
     * @throws Exception
     */
    public double[] calculate(ArrayList<String> lines, String taskName) {
        setTaskName(taskName);

        int num = lines.size();
        double[] result = new double[num];
        Computable myEngine = null;
        String[] input = lines.toArray(new String[lines.size()]);

        if (task.equalsIgnoreCase("MIN")) { // MIN
            myEngine = new MinEngine();

            try {
                myEngine.setInput(input);
                myEngine.compute();
                result[0] = myEngine.getResult();
            } catch (OneInputException | MinimumInputNumberException | MyNumberFormatException e) {
                e.getMessage();
                System.exit(0);
            } catch (NegativeNumberException e1) {
                // No additional processing required
            }
        } else if (task.equalsIgnoreCase("MAX")) { // MAX
            myEngine = new MaxEngine();

            try {
                myEngine.setInput(input);
                myEngine.compute();
                result[0] = myEngine.getResult();
            } catch (OneInputException | MinimumInputNumberException | MyNumberFormatException e) {
                e.getMessage();
                System.exit(0);
            } catch (NegativeNumberException e) {
                // No additional processing required
            }
        } else if (task.equalsIgnoreCase("SQRT")) { // SQRT
            myEngine = new SQRTEngine();

            try {
                int i = 0;
                for (String number : input) {
                    String[] inputForSqrt = new String[1];
                    inputForSqrt[0] = number;
                    myEngine.setInput(inputForSqrt);
                    myEngine.compute();
                    result[i] = myEngine.getResult();
                    i++;
                }
            } catch (OneInputException | MinimumInputNumberException | MyNumberFormatException | NegativeNumberException e) {
                e.getMessage();
                System.exit(0);
            }
        }

        return result;
    }
}

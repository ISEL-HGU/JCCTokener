package edu.handong.csee.java.hw5.thread;

import org.apache.commons.cli.Options;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
/**
 * CSVFileCalculator.java
 * This class is for reading and writing file.
 */
public class CSVFileCalculator implements Runnable {
    private String filePath;
	 /**
     * Set filePathn value
     * @param filePath Type : String
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    /**
     * 
     * This method return filePath
     * @return filePath
     */
    public String getFilePath() {
        return filePath;
    }
	/**
     * This method return read file ArrayList<ArrayList<String>>
     * @param filePath Type : String
     * @return lines
     */
    public static ArrayList<ArrayList<String>> readCSV(String filePath) {
        ArrayList<ArrayList<String>> lines = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : csvParser) {
                ArrayList<String> splittedLine = new ArrayList<>();
                for (String value : csvRecord) {
                    splittedLine.add(value.trim());
                }
                lines.add(splittedLine);
            }
        } catch (IOException e) {
        	OptionHandler optionshandler = new OptionHandler();
        	Options options = new Options();
        	options = optionshandler.createOptions();
			optionshandler.printHelp(options);
			System.exit(0);
            return lines;
        }

        return lines;
    }
	/**
     * This method return write file ArrayList<ArrayList<String>>
     * @param filePath Type : String,  lines Type : ArrayList<ArrayList<String>>
     */
    public static void writeCSV(String filePath, ArrayList<ArrayList<String>> lines) {
        try (Writer writer = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (List<String> line : lines) {
                csvPrinter.printRecord(line);
            }
        } catch (IOException e) {
        }

        System.out.println("The " + filePath + " file has been successfully written.");
    }
	/**
     * This method is for implementing Runnable
     */
    public void run() {
        // Your implementation for the run() method
    }
}

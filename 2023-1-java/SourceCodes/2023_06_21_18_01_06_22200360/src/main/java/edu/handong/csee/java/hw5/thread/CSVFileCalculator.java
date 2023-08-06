package edu.handong.csee.java.hw5.thread;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.ArrayList;
/**
 The CSVFileCalculator class is responsible for reading and writing CSV files and implementing the logic for processing the data.
 * It implements the Runnable interface, allowing it to be executed as a separate thread.
 * @author seogyeongmi
 *
 */
public class CSVFileCalculator implements Runnable {
    private String inputFilePath;
    private String outputFilePath;

    /**
     * Constructor for CSVFileCalculator.
     *
     * @param inputFilePath  The path to the input CSV file.
     * @param outputFilePath The path to the output CSV file.
     */
    public CSVFileCalculator(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    /**
     * Reads a CSV file and returns the data as ArrayList<ArrayList<String>>.
     *
     * @param filePath The path to the CSV file.
     * @return The CSV data as ArrayList<ArrayList<String>>.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<ArrayList<String>> readCSV(String filePath) throws IOException {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();
        Reader reader = new FileReader(filePath);
        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
        // CSV 데이터를 읽어서 ArrayList<ArrayList<String>> 형태로 변환합니다.
        for (CSVRecord record : parser) {
            ArrayList<String> row = new ArrayList<>();
            for (String value : record) {
                row.add(value);
            }
            csvData.add(row);
        }

        parser.close();
        reader.close();

        return csvData;
    }

    /**
     * Writes the CSV data to a file.
     *
     * @param filePath The path to the output CSV file.
     * @param csvData  The CSV data to be written.
     * @throws IOException If an I/O error occurs while writing the file.
     */
    public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) throws IOException {
        Writer writer = new FileWriter(filePath);
        CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);

        // CSV 데이터를 파일에 쓰기 위해 ArrayList<ArrayList<String>> 형태를 변환
        for (ArrayList<String> row : csvData) {
            printer.printRecord(row);
        }
        printer.close();
        writer.close();
    }

    /**
     * Implements the logic for processing the CSV file.
     */
    @Override
    public void run() {
        try {
            // 입력 파일에서 CSV 데이터를 읽어오기ㅣ
            ArrayList<ArrayList<String>> csvData = readCSV(inputFilePath);

            // TODO: CSV 데이터 처리 로직을 구현
            

            // 처리된 CSV 데이터를 출력 파일에 쓰기
            writeCSV(outputFilePath, csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * This is setter of inputFilePath.
     * @param s
     */
    public void setInputFilePath(String s) {
    	this.inputFilePath = s;
    }
    /**
     * This is getter of inputFilePath.
     * @return
     */
    public String getInputFilePath() {
    	return this.inputFilePath;
    }
    /**
     * This is setter of outputFilePath.
     * @param s
     */
    public void setOutputFilePath(String s) {
    	this.outputFilePath = s;
    }
    /**
     * This is getter of outputFilePath.
     * @return
     */
    public String getOutputFilePath() {
    	return this.outputFilePath;
    }

}



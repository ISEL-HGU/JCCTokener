package edu.handong.csee.java.hw5.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVFileCalculator implements Runnable {

    private String filePath;
    private ArrayList<ArrayList<String>> csvData;

    public CSVFileCalculator(String filePath, double result) {
        this.filePath = filePath;
        this.csvData = new ArrayList<>();
    }

    public ArrayList<ArrayList<String>> readCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<String> row = new ArrayList<>();
                String[] values = line.split(",");
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

    public void writeCSV(ArrayList<ArrayList<String>> csvData) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (ArrayList<String> row : csvData) {
                StringBuilder sb = new StringBuilder();
                for (String value : row) {
                    sb.append(value).append(",");
                }
                sb.deleteCharAt(sb.length() - 1); // Remove the trailing comma
                writer.write(sb.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculate() {
        // Implementation for calculating SQRT, MAX, and MIN tasks
        // You can add your logic here
    }

    @Override
    public void run() {
        readCSV();
        calculate();
        writeCSV(csvData);
    }

    // Getter and setter methods for encapsulation
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<ArrayList<String>> getCsvData() {
        return csvData;
    }

    public void setCsvData(ArrayList<ArrayList<String>> csvData) {
        this.csvData = csvData;
    }
}
package edu.handong.csee.java.hw5.thread;
import org.apache.commons.io.input.BOMInputStream;

import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import org.apache.commons.csv.*;

public class CSVFileCalculator implements Runnable {
    private String inputFilePath;
    private String outputFilePath;
    private String task;

    public CSVFileCalculator(String inputFilePath, String outputFilePath, String task) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.task = task;
    }

    @Override
    public void run() {
        try {
            List<String[]> data = new ArrayList<>();
            Map<String, String> header = new LinkedHashMap<>();

            try (Reader in = new InputStreamReader(new BOMInputStream(new FileInputStream(inputFilePath)))) {
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
                header.putAll(records.iterator().next().toMap());

                for (CSVRecord record : records) {
                    String[] row = new String[record.size()];
                    for (int i = 0; i < record.size(); i++) {
                        row[i] = record.get(i);
                    }
                    data.add(row);
                }
            }
        switch (task.toUpperCase()) {
                case "SQRT":
                for (String[] row : data) {
                    if (row.length != 1) {
                        throw new OneInputException("You need one input value for SQRT.");
                    }
                    for (int i = 0; i < row.length; i++) {
                        try {
                            double num = Double.parseDouble(row[i]);
                            if (num < 0) {
                                throw new NegativeNumberException("SQRT");
                            }
                            row[i] = String.valueOf(Math.sqrt(num));
                        } catch (NumberFormatException e) {
                            throw new MyNumberFormatException("SQRT", row[i]);
                        }
                    }
                }
                break;

                case "MAX":
                    header.put("MAX", "");
                    for (String[] row : data) {
                        String max = "-Infinity";
                        for (String field : row) {
                            try {
                                double num = Double.parseDouble(field);
                                if (Double.parseDouble(max) < num) max = field;
                            } catch (NumberFormatException e) {
                            }
                        }
                        row = Arrays.copyOf(row, row.length + 1);
                        row[row.length - 1] = max;
                        data.set(data.indexOf(row), row);

                    }
                    break;
                case "MIN":
                    header.put("MIN", "");
                    for (String[] row : data) {
                        String min = "Infinity";
                        for (String field : row) {
                            try {
                                double num = Double.parseDouble(field);
                                if (Double.parseDouble(min) > num) min = field;
                            } catch (NumberFormatException e) {
                            }
                        }
                        row = Arrays.copyOf(row, row.length + 1);
                        row[row.length - 1] = min;
                        data.set(data.indexOf(row), row);

                    }
                    break;
            }

            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath));
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header.keySet().toArray(new String[0])))) {
                for (String[] row : data) {
                    csvPrinter.printRecord((Object[]) row);
                }
            }
        } catch (NegativeNumberException e) {
            System.out.println("Exception-03: " + e.getMessage());
            System.exit(0);
        } catch (OneInputException e) {
            System.out.println("Exception-04: " + e.getMessage());
            System.exit(0);
        } catch (MyNumberFormatException e) {
            System.out.println("Exception-05: " + e.getMessage());
            System.exit(0);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
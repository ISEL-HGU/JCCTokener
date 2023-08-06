package edu.handong.csee.java.hw5.thread;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import edu.handong.csee.java.hw5.thread.clioptions.engines.*;
import edu.handong.csee.java.hw5.thread.clioptions.exceptions.InvalidCommandException;

public class CSVFileCalculator implements Runnable {
	    private String ipath;
    	private String opath;
    	private String task;

	public void setTask(String task) {
		this.task = task;
	}
	public CSVFileCalculator(String ipath, String opath, String task) {
		this.ipath = ipath;
	        this.opath = opath;
	        this.task = task;
	}
    @Override
    public void run() {
        try {
            // Handle directory
            File inputFile = new File(ipath);
            if(inputFile.isDirectory()) {
                // ThreadPool creation
                ExecutorService executor = Executors.newFixedThreadPool(10);

                for (File file : inputFile.listFiles()) {
                    // For each file in directory, create new instance and submit to executor
                    CSVFileCalculator worker = new CSVFileCalculator(file.getAbsolutePath(), opath, task);
                    executor.submit(worker);
                }

                executor.shutdown();

                // Wait for all tasks to finish
                while (!executor.isTerminated()) {
                }

                return;
            }

            // Handle single file
            ArrayList<ArrayList<String>> data = readCSV(ipath);
            ArrayList<ArrayList<String>> results = new ArrayList<>();

            for (ArrayList<String> row : data) {
                ArrayList<String> tempResults = row.stream().map(val -> calculate(task, val)).collect(Collectors.toCollection(ArrayList::new));
                if ("MIN".equals(task) || "MAX".equals(task)) {
                    tempResults.add(calculate(task, String.join(",", row)));
                }
                results.add(tempResults);
            }

            String newOpath = inputFile.getName().replace(".csv","") + "-" + new File(opath).getName().replace(".csv","") + ".csv";

            writeCSV(newOpath, results);
        } catch (Exception e) {
            System.out.println("Exception in thread for file: " + ipath);
            e.printStackTrace();
        }
    }

    public String calculate(String task, String value) {
        Computable engine = null;

        switch (task.toUpperCase()) {
            case "SQRT":
                engine = new SQRTEngine();
                break;
            case "MAX":
                engine = new MaxEngine();
                break;
            case "MIN":
                engine = new MinEngine();
                break;
            default:
                throw new IllegalArgumentException("Invalid task: " + task);
        }

        engine.setInput(value.split(","));
        engine.compute();

        return Double.toString(engine.getResult());
    }

    public ArrayList<ArrayList<String>> readCSV(String filePath) throws IOException {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<String> row = new ArrayList<>(Arrays.asList(line.split(",")));
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (ArrayList<String> row : csvData) {
                String line = String.join(",", row);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

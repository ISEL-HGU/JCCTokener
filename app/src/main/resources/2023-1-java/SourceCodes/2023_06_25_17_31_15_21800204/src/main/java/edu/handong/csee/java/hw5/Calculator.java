package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;

import org.apache.commons.cli.Options;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Calculator {

    private OptionHandler optionHandler;

    public Calculator() {
        optionHandler = new OptionHandler();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run(args);
    }

    public void run(String[] args) {
        Options options = optionHandler.createOptions();

        try {
            if(!optionHandler.parseOptions(options, args)) {
                throw new InvalidCommandException(args);
            };

            // Check if a file path was given
            if (optionHandler.getDataInputFilePath() != null) {
                File inputPath = new File(optionHandler.getDataInputFilePath());

                if (!inputPath.exists()) {
                    throw new MinimumInputNumberException();
                }

                File[] inputFiles;
                if(inputPath.isDirectory()) {
                    inputFiles = inputPath.listFiles((dir, name) -> name.endsWith(".csv"));
                } else {
                    inputFiles = new File[]{inputPath};
                }

                ExecutorService executor = Executors.newFixedThreadPool(inputFiles.length);

                for(File inputFile: inputFiles) {
                    Runnable worker = new CSVFileCalculator(
                            inputFile.getPath(),
                            optionHandler.getDataOutputFilePath() + File.separator + inputFile.getName(),
                            optionHandler.getTask()
                    );
                    executor.execute(worker);
                }

                executor.shutdown();
                while (!executor.isTerminated()) {
                    // Wait until all threads are finish
                }
            } else {
                // If no -i argument or a wrong argument is given, print the help screen
                optionHandler.printHelp(options);
                System.exit(0);
            }
        } catch (InvalidCommandException e) {
            System.out.println("Exception-01: " + e.getMessage());
            optionHandler.printHelp(options);
            System.exit(0);
        } catch (MinimumInputNumberException e) {
            System.out.println("Exception-02: " + e.getMessage());
            optionHandler.printHelp(options);
            System.exit(0);
        }
    }

}


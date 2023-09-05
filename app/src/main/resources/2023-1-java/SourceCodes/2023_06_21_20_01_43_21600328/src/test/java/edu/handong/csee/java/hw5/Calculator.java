package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.clioptions.*;
import edu.handong.csee.java.hw5.fileutil.*;
import java.util.ArrayList;
import org.apache.commons.cli.Options;

/**
 * This code is the main function to operate Calculator
 */
public class Calculator {
    /**
     * using the main method to make new OptionHandler, and run OptionHandler
     *
     * @param args getting input of argument of engineName and numbers
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run(args);
    }

    /**
     * Runs the Calculator program with the given command-line arguments.
     */
    public void run(String[] args) {
        OptionHandler optionHandler = new OptionHandler();
        Options options = optionHandler.createOptions();

        if (optionHandler.parseOptions(options, args)) {
            if (optionHandler.getHelpRequested()) {
                optionHandler.printHelp(options);
            } else {
                String engineName = optionHandler.getTask().toUpperCase();

                Computable engine = null;

                switch (engineName) {
                    // Existing code for selecting the engine...

                    case "MAX":
                        engine = new MaxEngine();
                        break;
                    case "MIN":
                        engine = new MinEngine();
                        break;
                    case "CUBEVOL":
                        engine = new CubeVolEngine();
                        break;
                    case "SPHEREVOL":
                        engine = new SphereVolEngine();
                        break;
                    default:
                        optionHandler.printHelp(options);
                        return;
                }

                ArrayList<String> lines = new ArrayList<>();

                if (optionHandler.getDataInputFilePath() != null) {
                    lines = FileManager.readLinesFromATxtFile(new String[]{optionHandler.getDataInputFilePath()});
                } else if (optionHandler.getInputValues() != null) {
                    String[] values = optionHandler.getInputValues().split("\\s+");
                    for (String value : values) {
                        lines.add(value);
                    }
                } else {
                    System.out.println("No input values provided.");
                    optionHandler.printHelp(options);
                    return;
                }

                try {
                    engine.setInput(lines.toArray(new String[0]));
                    engine.compute();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input value: " + e.getMessage());
                    return;
                }

                if (optionHandler.getDataOutputFilePath() != null) {
                    FileManager.writeATxtFile(new String[]{optionHandler.getDataOutputFilePath()},
                            convertResultToString(engine.getResult()));
                    System.out.println("The result has been written to the output file: "
                            + optionHandler.getDataOutputFilePath());
                } else {
                    String resultFileName = "a-result.csv";
                    String resultHex = convertResultToHex(engine.getResult());
                    System.out.println(resultFileName + " : " + resultHex);
                }

                // Create and run the CSVFileCalculator
                if (engineName.equals("MAX") || engineName.equals("MIN")) {
                    double result = engine.getResult(); // Get the result directly
                    CSVFileCalculator csvCalculator = new CSVFileCalculator(optionHandler.getDataOutputFilePath(), result);
                    csvCalculator.run();
                }
            }
        } else {
            optionHandler.printHelp(options);
        }
    }
    private static String convertResultToHex(double result) {
        String hex = Double.toHexString(result);
        hex = hex.replace("0x", "");
        hex = hex.replace(".", "");
        return hex;
    }
    /**
     * Converts the result to an ArrayList<String> with a single element.
     *
     * @param result the double result to convert
     * @return the converted ArrayList<String>
     */
    private static ArrayList<String> convertResultToString(double result) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(String.valueOf(result));
        return lines;
    }
}
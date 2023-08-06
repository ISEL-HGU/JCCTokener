package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.clioptions.*;
import edu.handong.csee.java.hw5.fileutil.*;
import org.apache.commons.cli.*;
import java.util.ArrayList;

/**
 * This is the main calculator class responsible for running the application.
 * It processes command line arguments, handles errors,
 * creates and manages computation engines, and manages input/output operations.
 */
public class Calculator {

    /**
     * This is the main method which makes use of run method.
     * 
     * 
     */
    public static void main(String[] args) {
        Calculator myCalculator = new Calculator();
        myCalculator.run(args);
    }

    /**
     * This method is used to process command line arguments, manage computation
     * engines,
     * handle exceptions, and manage file reading and writing.
     * 
     * @param args This is the command line arguments.
     * @return Nothing.
     */
    public void run(String[] args) {
        OptionHandler optionHandler = new OptionHandler();
        Options options = optionHandler.createOptions();

        if (!optionHandler.parseOptions(options, args)) {
            optionHandler.printHelp(options);
            return;
        }

        String task = optionHandler.getTask();
        String inputValues = optionHandler.getInputValues();
        String dataInputFilePath = optionHandler.getDataInputFilePath();
        String dataOutputFilePath = optionHandler.getDataOutputFilePath();

        if (task == null) {
            optionHandler.printHelp(options);
            return;
        }
        Computable engine = null;

        switch (task.toUpperCase()) {
            case "LCM":
                engine = new LCMEngine();
                break;
            case "GCD":
                engine = new GCDEngine();
                break;
            case "SQRT":
                engine = new SQRTEngine();
                break;
            case "FACTORIAL":
                engine = new FactorialEngine();
                break;
            case "FIBONACCI":
                engine = new FibonacciEngine();
                break;
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
                System.out.println("Exception-01: Invalid command: " + task + "\n" +
                        "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
                return;
        }
        if (inputValues == null || inputValues.isEmpty()) {
            if (dataInputFilePath == null || dataInputFilePath.isEmpty()) {
                optionHandler.printHelp(options);
                return;
            }
        }
        if (dataOutputFilePath != null) {
            FileManager fileManager = new FileManager();

            ArrayList<String[]> outputLines = new ArrayList<>();
            ArrayList<String[]> inputLines = fileManager.readLinesFromATxTFile(dataInputFilePath);

            outputLines.add(inputLines.get(0));

            SQRTEngine sqrtEngine = new SQRTEngine();
            for (int j = 1; j < inputLines.size(); j++) {
                String[] line = inputLines.get(j);
                String[] outputLine = new String[line.length];
                for (int i = 0; i < line.length; i++) {
                    try {
                        sqrtEngine.setInput(new String[] { line[i] });
                        sqrtEngine.compute();
                        outputLine[i] = String.valueOf(sqrtEngine.getResult());
                    } catch (MyNumberFormatException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
                outputLines.add(outputLine);
            }

            fileManager.writeAtxtFile(dataOutputFilePath, outputLines);
        }

        if (inputValues != null && !inputValues.isEmpty()) {
            try {
                String[] inputArgs = inputValues.split("\\s+");
                engine.setInput(inputArgs);
            } catch (MyNumberFormatException e) {
                System.out.println(e.getMessage());
                return;
            }

            engine.compute();
            System.out.println("The result of " + task + " is " + engine.getResult() + ".");
        }
    }
}

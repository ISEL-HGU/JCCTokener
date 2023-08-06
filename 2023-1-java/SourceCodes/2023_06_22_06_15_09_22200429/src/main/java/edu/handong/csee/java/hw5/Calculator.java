package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.fileutil.FileManager;
import org.apache.commons.cli.Options;
import edu.handong.csee.java.hw5.clioptions.*;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import java.util.ArrayList;
/**
 * This class is to get result that for calculator
 */
public class Calculator {
	
	/**
	 * This method is do set args and option.
	 */
    public static void main(String[] args) {
        OptionHandler optionHandler = new OptionHandler();
        Options options = optionHandler.createOptions();

        if (!optionHandler.parseOptions(options, args) || optionHandler.getHelpRequested()) {
            optionHandler.printHelp(options);
            return;
        }

        try {
            Calculator myCalculator = new Calculator();
            myCalculator.run(optionHandler);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        } catch (MyNumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * This method is run Calculator
     */
    public void run(OptionHandler optionHandler) throws InvalidCommandException, MyNumberFormatException {
        String engineName = optionHandler.getTask().toUpperCase();

        Computable engine;

        switch (engineName) {
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
                throw new InvalidCommandException("Exception-01: Invalid command: " + engineName + "\n"
                        + "Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app MAX 12 4 5 45 100");
        }

        if (optionHandler.getDataInputFilePath() != null) {
            ArrayList<String> lines = FileManager.readLinesFromATxtFile(optionHandler.getDataInputFilePath());
            ArrayList<String> processedLines = new ArrayList<>();

            if (!lines.isEmpty()) {
                processedLines.add(lines.get(0));
                lines.remove(0);
            }

            for (String line : lines) {
                String[] inputs = line.split(",");

                if (inputs.length < 2) {
                    throw new InvalidCommandException("Exception-02: " + engineName + " requires at least 2 input values.");
                }

                for (int i = 0; i < inputs.length; i++) {
                    try {
                        double num = Double.parseDouble(inputs[i]);
                        engine.setInput(new String[]{inputs[i]});
                        engine.compute();
                        double result = engine.getResult();
                        inputs[i] = Double.toString(result);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format: " + inputs[i]);
                    }
                }

                processedLines.add(String.join(",", inputs));
            }

            try {
                if (optionHandler.getDataOutputFilePath() != null) {
                    FileManager.writeAtxtFile(optionHandler.getDataOutputFilePath(), processedLines);
                    System.out.println("The result has been successfully written to " + optionHandler.getDataOutputFilePath());
                }
            } catch (Exception e) {
                System.out.println("Exception-03: The output file path does not exist. Please check your CLI argument!");
            }
        } else {
            engine.setInput(optionHandler.getInputValues().split(" "));
            engine.compute();
            double result = engine.getResult();
            System.out.println("The result of " + engineName + " is " + result + ".");
        }
    }
}

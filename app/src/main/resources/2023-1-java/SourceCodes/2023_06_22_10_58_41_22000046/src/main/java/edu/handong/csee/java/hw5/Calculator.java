package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.clioptions.*;
import org.apache.commons.cli.*;

import java.io.*;
import java.util.*;

/**
 * Calculator class represents a calculator that provides various mathematical functions.
 * The calculator can calculate the Fibonacci sequence, factorial, volume of a sphere, volume of a cube,
 * root, maximum value, minimum value, least common multiple, and greatest common divisor.
 */
public class Calculator {

    /**
     * The main method that creates a Calculator object and executes its run method with the provided arguments.
     *
     * @param args the arguments passed to the program
     */
    public static void main(String[] args) {
        Calculator myCalculator = new Calculator();

        try {
            myCalculator.run(args);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("Please put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
            System.exit(0);
        } catch (MinimumInputNumberException | MyNumberFormatException | NegativeNumberException | OneInputException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (Exception e){
            e.getMessage();
            System.exit(0);
        }
    }

    /**
     * Executes the calculator program with the given arguments.
     *
     * @param args the arguments passed to the calculator program
     */
    public void run(String[] args) throws InvalidCommandException, MyNumberFormatException, MinimumInputNumberException, OneInputException, NegativeNumberException {
        OptionHandler myOptionHandler = new OptionHandler();
        Options myOption = myOptionHandler.createOptions();

        if ((myOptionHandler.parseOptions(myOption, args) == true)) {

            String engineName = myOptionHandler.getTask().toUpperCase();
            Computable engine = null;

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
                    throw new InvalidCommandException(engineName);
            }
        
            if (myOptionHandler.getDataInputFilePath() != null) {

                String inputPath = myOptionHandler.getDataInputFilePath();
                String outputPath = myOptionHandler.getDataOutputFilePath();
                File input = new File(inputPath);

                if (input.isDirectory()) {

                    File[] files = input.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

                    if (files != null && files.length > 0) {
                        List<Thread> threads = new ArrayList<>();

                        for (File file : files) {

                            String filename = file.toString().substring(0, file.toString().lastIndexOf("."));
                            String extension = file.toString().substring(file.toString().lastIndexOf("."));
                            String finalOutput = filename + "-" + outputPath.replace(".csv", "") + extension;
                            CSVFileCalculator csvFileCalculator = new CSVFileCalculator(engineName, file.getAbsolutePath(), finalOutput);
                            Thread thread = new Thread(csvFileCalculator);
                            threads.add(thread);
                            thread.start();
                        }

                        for (Thread thread : threads) {
                            try {
                                thread.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (input.isFile()) {
                    CSVFileCalculator csvFileCalculator = new CSVFileCalculator(engineName, inputPath, outputPath);
                    csvFileCalculator.run();
                    System.out.println("The " + outputPath + " file has been successfully written.");
                }
            } else {

                String[] inputs = myOptionHandler.getInputValues().split("\\s+");
                try{
                    engine.setInput(inputs);
                    engine.compute();
                    System.out.println("The result of " + engineName + " is " + engine.getResult() + ".");
                } catch (MinimumInputNumberException | MyNumberFormatException | NegativeNumberException | OneInputException e){
                    throw e ;
                }
    
            }
        } else {
            myOptionHandler.printHelp(myOption);
        }
    }
}
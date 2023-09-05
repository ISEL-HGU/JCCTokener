/**

The Calculator class that contains the main method to run the program.
*/
package edu.handong.csee.java.hw5;
/**
 * optionhandler
 */
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
/**
 * engines
 */
import edu.handong.csee.java.hw5.engines.*;

/**
 * exceptions
 */
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * filemanager
 */
import edu.handong.csee.java.hw5.fileutil.FileManager; // Import FileManager
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;

import java.io.File;
/**
 * arraylist
 */
import java.util.ArrayList; // Import ArrayList
//import edu.handong.csee.java.hw5.thread.*;

/**
 * The main method of the Calculator class that runs the program.
 */
public class Calculator {
     /**
     *
     * main method
     */
    public static void main(String[] args) {
        Calculator myCalculator = new Calculator();
        myCalculator.run(args);
    }
/**
     * The method that runs the computation based on the user's input.
     * 
     * @param args The command-line arguments passed to the program.
     */
public void run(String[] args) {
        OptionHandler optionHandler = new OptionHandler();
        if (!optionHandler.parseOptions(optionHandler.createOptions(), args)) {
            return;
        }

        if (optionHandler.getHelpRequested()) {
            optionHandler.printHelp(optionHandler.createOptions());
            return;
        }
        /**
         * engineName
         */
        String engineName = optionHandler.getTask();
        /**
         * inputValues
         */
        String inputValues = optionHandler.getInputValues();
        /**
         * inputFilePath
         */
        String inputFilePath = optionHandler.getDataInputFilePath();
        /**
         * outputFilePath
         */
        String outputFilePath = optionHandler.getDataOutputFilePath();
        /**
         * engine null
         */
        Computable engine = null;

        switch (engineName.toUpperCase()) {
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
                System.out.println("Exception-01: Invalid command: " + engineName.toUpperCase()+ "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
                return;
        }
        
        try {
            if(inputFilePath!=null)
            {File inputFile = new File(inputFilePath);

            if (engineName.equalsIgnoreCase("SQRT") && !inputFile.isDirectory()) {
                /* */
                
                /**
                 * filemanager
                 */

                FileManager fileManager = new FileManager();
                ArrayList<String> lines = fileManager.readLinesFromATxtFile(inputFilePath);
                ArrayList<String> outputLines = new ArrayList<String>();

                if (lines == null) {
                    return;
                    }

                
                 // 첫 번째 줄은 그대로 출력
    outputLines.add(lines.get(0)+"\n");
    String[] firstLineValues = lines.get(0).split(",");
    int numValues = firstLineValues.length;


    // 나머지 줄들을 엔진을 사용하여 계산하고 출력
    for (int i = 1; i < lines.size(); i++) {
        String line = lines.get(i);
        String[] inputValuesArray = line.split(",");
        int count=0;
            for (String value : inputValuesArray) {

                engine.setInput(new String[]{value});
                engine.compute();
                if(count == numValues-1){
                    outputLines.add(Double.toString(engine.getResult())+"\n");
                    count = 0;
                }
                else {
                    outputLines.add(Double.toString(engine.getResult())+",");
                }
                count++;
        }
    }

                
                if (outputFilePath != null) {
                    fileManager.writeATxtFile(outputFilePath, outputLines);
                    System.out.println("The " + outputFilePath + " file has been successfully written.");
                }
            } else if(inputFile.isDirectory()) {
                //System.out.println("is directory!");
                // Handle directory input
                CSVFileCalculator csvCalculator = new CSVFileCalculator(inputFilePath, outputFilePath, engineName);
                Thread thread = new Thread(csvCalculator);
                thread.start();
                try {
                    thread.join();  // Wait for the CSVFileCalculator thread to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                   }            
                }}
            else if (inputValues != null) {
                try {
                    // Split inputValues using regular expression "\\s+" to handle multiple spaces
                    /**
                     * inputValuesArray
                     */
                    String[] inputValuesArray = inputValues.split("\\s+");
                    engine.setInput(inputValuesArray);
                    engine.compute();
                    System.out.println("The result of " + engineName.toUpperCase() + " is " + engine.getResult() + ".");

                 
             } catch (MyNumberFormatException | OneInputException | NegativeNumberException | MinimumInputNumberException | InvalidCommandException e) {
                 System.out.println(e.getMessage());
                 return;
             }
            } else {
                optionHandler.printHelp(optionHandler.createOptions());
                return;
            }
        } catch (MyNumberFormatException | OneInputException | NegativeNumberException | MinimumInputNumberException | InvalidCommandException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
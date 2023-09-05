package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;
import org.apache.commons.cli.Options;

import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the main class of this program
 */
public class Calculator {
    /**
     * This is the main method
     * @param args
     */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();

        myCalculator.run(args);
    }

    /**
     * This is the run method
     * @param args
     */
    public void run(String[] args) {
        String[] values;
//        CSVFileCalculator csvFileCalculator = new CSVFileCalculator();
//        FilenameFilter filenameFilter = new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith("csv");
//            }
//        };


        int pt = 0;
        OptionHandler options = new OptionHandler();
        Options opts = options.createOptions();

        if(options.parseOptions(opts, args)) { // 이 뒤 if문이 실행 됐다는 것은 command를 잘 받아왔다는 것을 의미
            if(options.isHelpRequested()) { // help 발생
                options.printHelp(opts);
                return; // 종료
            }
            if(options.getDataInputFilePath() != null) {
                File tempFile = new File(options.getDataInputFilePath());
                if(!tempFile.exists()) {
                    options.printHelp(opts);
                    return; // 종료
                }
            }

        } else {
            return;
        }

        String engineName = options.getTask().toUpperCase();


        if(options.getDataInputFilePath() != null) { // option i가 있는 경우
            String file = options.getDataInputFilePath() + " " + options.getDataOutputFilePath();
            values = file.split(" ");
        } else { // option i가 없으면 실행
            values = options.getInputValues().split("\\s+");
            try {
                for(int i = 0; i < values.length; i++) {
                    if(!values[i].matches("[+-]?\\d*(\\.\\d+)?")) {
                        pt = i;
                        throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + values[pt] + " is not a number value for " + engineName + ".)");
                    }
                }
            } catch (MyNumberFormatException e) {
                System.out.print(e.getMessage());
                System.exit(0);
            }

        }




        Computable engine = null;

        try {
            switch(engineName) {
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
                    throw new InvalidCommandException("Exception-01: Invalid command: " + engineName + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
            }
        } catch (InvalidCommandException e) {
            System.out.print(e.getMessage());
            System.exit(0);
        }

        if(options.getDataInputFilePath() != null) { // input path가 존재하는 경우
            String inputFileName = options.getDataInputFilePath();
            File inputFilePath = new File(inputFileName);

            if(inputFilePath.isFile()) { // file인 경우

                try {
                    engine.setInput(values);
                } catch (NegativeNumberException e) {
                    System.out.print(e.getMessage());
                    System.exit(0);
                } catch (OneInputException e) {
                    System.out.print(e.getMessage());
                    System.exit(0);
                } catch (MinimumInputNumberException e) {
                    System.out.print(e.getMessage());
                    System.exit(0);
                } catch (FileException e) {
                    options.printHelp(opts);
                    System.exit(0);
                }

                try {
                    engine.compute();
                } catch (MyNumberFormatException e) {
                    System.out.print(e.getMessage());
                    System.exit(0);
                } catch (FileException e) {
                    options.printHelp(opts);
                    System.exit(0);
                } catch (NegativeNumberException e) {
                    System.out.print(e.getMessage());
                    System.exit(0);
                } catch (MinimumInputNumberException e) {
                    System.out.print(e.getMessage());
                    System.exit(0);
                }

            } else if(inputFilePath.isDirectory()) { // directory인 경우
                File directoryPath = new File(options.getDataInputFilePath());
                File[] fileNameList = directoryPath.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith("csv");
                    }
                });
                int numOfCoresInMyCPU = Runtime.getRuntime().availableProcessors();

                ExecutorService executorService = Executors.newFixedThreadPool(numOfCoresInMyCPU);

                for(int i = 0; i < fileNameList.length; i++) {
                    Runnable worker = new CSVFileCalculator(engineName, fileNameList[i].toString(), options.getDataOutputFilePath());

                    executorService.execute(worker);
                }
                executorService.shutdown();

                while(!executorService.isTerminated()) {

                }

            } else {

            }
        } else { // input path가 존재하지 않는 경우
            try {
                engine.setInput(values);
            } catch (NegativeNumberException e) {
                System.out.print(e.getMessage());
                System.exit(0);
            } catch (OneInputException e) {
                System.out.print(e.getMessage());
                System.exit(0);
            } catch (MinimumInputNumberException e) {
                System.out.print(e.getMessage());
                System.exit(0);
            } catch (FileException e) {
                options.printHelp(opts);
                System.exit(0);
            }

            try {
                engine.compute();
            } catch (MyNumberFormatException e) {
                System.out.print(e.getMessage());
                System.exit(0);
            } catch (FileException e) {
                options.printHelp(opts);
                System.exit(0);
            } catch (NegativeNumberException e) {
                System.out.print(e.getMessage());
                System.exit(0);
            } catch (MinimumInputNumberException e) {
                System.out.print(e.getMessage());
                System.exit(0);
            }
        }

        if(options.getDataInputFilePath() != null) {
            File fp = new File(options.getDataInputFilePath());
            if(fp.isFile()) {
                System.out.println("The " + options.getDataOutputFilePath() + " file has been successfully written.");
            }
        } else {
            System.out.print("The result of " +  engineName + " is " + engine.getResult() + ".");
        }


    }


}
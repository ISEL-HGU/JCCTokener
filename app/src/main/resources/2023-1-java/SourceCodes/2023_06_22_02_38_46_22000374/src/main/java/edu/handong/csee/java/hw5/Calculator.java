package edu.handong.csee.java.hw5;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import java.util.StringJoiner;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.fileutil.*;
import edu.handong.csee.java.hw5.thread.*;

/**
 * Calculator.java
 * Computing a lot of Method
 * 
 * @author : SMY
 */
public class Calculator {
    /**
     * This method is Main Method.
     * args is input
     * 
     * @param args Type : String[] args
     */

    public static void main(String[] args) {
        Calculator myCalculator = new Calculator();
        myCalculator.run(args);
    }

    /**
     * This method is a starting point of Main class.
     * 
     * @param args Type : Strings[]
     */
    public void run(String[] args) {
    	OptionHandler optionshandler = new OptionHandler();
    	Options options = new Options();
    	options = optionshandler.createOptions();
    	String engineName = null;
    	
    	ArrayList<String[]> splittedLines = new ArrayList<>();
		if(optionshandler.parseOptions(options, args)){
			if (optionshandler.getHelpRequested()){
				optionshandler.printHelp(options);
				System.exit(0);
				return;
			}
			if (optionshandler.getDataInputFilePath()!=null){
				CSVFileCalculator csvManager = new CSVFileCalculator();
				ArrayList<ArrayList<String>> lines =csvManager.readCSV(optionshandler.getDataInputFilePath());
				 for (ArrayList<String> value : lines) {
			            String[] splitted = new String[value.size()];
			            for (int i = 0; i < value.size(); i++) {
			                String newValue = value.get(i).replace(",", " ");
			                splitted[i] = newValue;
			            }
			            splittedLines.add(splitted);
			        }
			}
		}     
		if(optionshandler.getTask()==null) return;
		engineName = optionshandler.getTask().toUpperCase();
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
                try {
                    throw new InvalidCommandException(engineName);
                } catch (InvalidCommandException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
        }
        ArrayList<ArrayList<String>> resultList = new ArrayList<>();
        String line = new String();

        if (optionshandler.getDataOutFilePath() != null) {
            if (!engineName.equals("SQRT")&&!engineName.equals("MAX")&&!engineName.equals("MIN")) {
                optionshandler.printHelp(options);
                System.exit(0);
            }
            int count = 0;
            int check = 0;
            for (String[] splitted : splittedLines) {
                ArrayList<String> resultRow = new ArrayList<>();
                for (String value : splitted) {
                    String[] tmp = { value };
                    double input;
                    try {
                        input = Double.parseDouble(tmp[0]);
                        check++;
                    } catch (NumberFormatException e) {
                        resultRow.add(value);
                        count++;
                        continue;
                    }
                    engine.setInput(tmp);
                    engine.compute();
                    String change = String.valueOf(engine.getResult());

                    if (check % count == 0) {
                        resultRow.add(change);
                        continue;
                    }
                    resultRow.add(change);
                }
                resultList.add(resultRow);
            }
            resultList.removeIf(ArrayList::isEmpty);
            CSVFileCalculator.writeCSV(optionshandler.getDataOutFilePath(), resultList);
            System.exit(0);
        }

        
        else if(optionshandler.getInputValues()==null) {

			optionshandler.printHelp(options);
			return;
		}
		else {
	        String[] words = optionshandler.getInputValues().split("\\s+");
	        engine.setInput(words);
	        engine.compute();
	        System.out.println("The result of " + engineName + " is " + engine.getResult() + ".");
		}

    }
    

}
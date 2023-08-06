package edu.handong.csee.java.hw5;

import edu.handong.csee.java.hw5.clioptions.*;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.fileutil.*;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;

import java.util.ArrayList;


/**
 * This class is for starting the entire program. This class generate calculator
 */
public class Calculator {

	/**
	 * This is main method. instance is going to call run() function to run the
	 * program.
	 */
	public static void main(String[] args) {
		// LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL

		Calculator myCalculator = new Calculator();
		myCalculator.run(args);
	}

	/**
	 * This method is a starting point of Calculator class. 
	 * This run method will call various methods which are about options and engines.
	 */
	public void run(String[] args) {

		OptionHandler options = new OptionHandler().createOptions();
		if(args.length == 0) {
			options.printHelp(options);
			return;
		}
		ArrayList<String> filteredArrayList = filteringArrayList(options, args);
		if (filteredArrayList == null)
			return;

		Computable engine = setAndCheckEngine(filteredArrayList, options);
		//Computable engine = setAndCheckEngine(filteredArrayList);
		if (engine == null)
			return;
		String engineName = filteredArrayList.get(0);
		filteredArrayList.remove(0);
		String[] filteredArgs = filteredArrayList.toArray(new String[0]);

		if (options.getInputValues() != null) {
			engine.setInput(filteredArgs);
			engine.compute();
			System.out.println("The result of " + engineName + " is " + engine.getResult() + ".");
		}
	}

	/**
	 * This method is for filtering the arguments with options. 
	 * If user don't follow the right options usage, OptionsHandler class will return false or null, 
	 * and then, this method will print help message.
	 */
	
	public ArrayList<String> filteringArrayList(OptionHandler options, String[] args) {
		ArrayList<String> filteredArrayList = new ArrayList<String>();
		Computable engine =null; 
		//check the parseOptions for the options are matched well.
		if (options.parseOptions(options, args)) {
			if (options.getHelpRequested()) {
				options.printHelp(options);
				return null;
			}
			//check the task option.
			if (options.getTask() != null) {
				filteredArrayList.add(options.getTask().toUpperCase());
			}
			//check the input file option.
			if (options.getDataInputFilePath() != null) {
				//FileManager fileManager = new FileManager();
				CSVFileCalculator csvFileCalculator = new CSVFileCalculator(options);
				//ArrayList<String[]> tmp = fileManager.readLinesFromATxtFile(options.getDataInputFilePath());
				
				//read csv file.
				ArrayList<String> tmpToEngineName = new ArrayList<String>();
				tmpToEngineName.add(0, filteredArrayList.get(0));
				ArrayList<ArrayList<String>> tmp = csvFileCalculator.readCSV(options.getDataInputFilePath());
				if (tmp == null) {
					return null;
				}
				//else if(tmp.get(0).get(0) == "null"){
				//	return null;
				//}
				//adding the values from files to filteredArrayList.
				for (int i = 1; i < tmp.size(); i++)
					for (int j = 0; j < tmp.get(i).size(); j++) {
						filteredArrayList.add(tmp.get(i).get(j));
						//System.out.println(tmp.get(i).get(j));
					}
				//System.out.println("this is main thread");
				engine = setAndCheckEngine(filteredArrayList, options);
				//engine = setAndCheckEngine(filteredArrayList);
				if (engine == null)
					return null;
				
				/*ArrayList<String> tmpToEngineName = new ArrayList<String>();
				tmpToEngineName.add(0, filteredArrayList.get(0));
				tmp.add(0, tmpToEngineName);*/
				
				/*for (ArrayList<String> row : tmp) {
	                for (String value : row) {
	                    System.out.print(value + " ");
	                }
	                System.out.println();
	            }*/
				
				//fileManager.writeATxtFile(options.getDataOutputFilePath(), tmp);
				
				//write csv file.
				 //System.out.println("this is main thread");
				csvFileCalculator.writeCSV(options.getDataInputFilePath(), tmp);
				return null;
			}
			else if (options.getInputValues() != null) {
				String[] tmp = options.getInputValues().split("\\s+");
				for (int i = 1; i < tmp.length + 1; i++) {
					filteredArrayList.add(tmp[i - 1]);
				}
				engine = setAndCheckEngine(filteredArrayList, options);
				//engine = setAndCheckEngine(filteredArrayList);
				if (engine == null)
					return null;
				
			}
			else {
				options.printHelp(options);
				return null;
			}
		} 
		else {
			options.printHelp(options);
			return null;
		}
		return filteredArrayList;
	}
	
	/**
	 * This method is for setting Engines and checking the Exceptions. 
	 * It implements various engines with arguments for calculating various math formulas There are five
	 * Exception method.
	 */
	
	public Computable setAndCheckEngine(ArrayList<String> arrayList, OptionHandler options) {

		String engineName = arrayList.get(0);
		Computable engine = null;
		try {

			int countOfInputNumber = 0;
			int countOfNegativeNumber = 0;
			for (int i = 1; i < arrayList.size(); i++) {
				if (isInteger(arrayList.get(i)) == false) {
					throw new MyNumberFormatException(engineName, (String) arrayList.get(i)); // Exception-05
				}
				countOfInputNumber++;
				if (Integer.parseInt(arrayList.get(i)) < 0)
					countOfNegativeNumber++;
			}
			switch (engineName) {
			case "LCM":
				if (countOfNegativeNumber > 0)
					throw new NegativeNumberException(engineName);
				if (countOfInputNumber < 2)
					throw new MinimumInputNumberException(engineName);
				engine = new LCMEngine();
				break;
			case "GCD":
				if (countOfNegativeNumber > 0)
					throw new NegativeNumberException(engineName);
				if (countOfInputNumber < 2)
					throw new MinimumInputNumberException(engineName);
				engine = new GCDEngine();
				break;
			case "SQRT":
				if (countOfNegativeNumber > 0)
					throw new NegativeNumberException(engineName);
				if ((options.getInputValues() != null && countOfInputNumber != 1) || countOfInputNumber == 0)
					throw new OneInputException(engineName);
				engine = new SQRTEngine();
				break;
			case "FACTORIAL":
				if (countOfNegativeNumber > 0)
					throw new NegativeNumberException(engineName);
				if (countOfInputNumber != 1)
					throw new OneInputException(engineName);
				engine = new FactorialEngine();
				break;
			case "FIBONACCI":
				if (countOfNegativeNumber > 0)
					throw new NegativeNumberException(engineName);
				if (countOfInputNumber != 1)
					throw new OneInputException(engineName);
				engine = new FibonacciEngine();
				break;
			case "MAX":
				if (countOfInputNumber < 2)
					throw new MinimumInputNumberException(engineName);
				engine = new MaxEngine();
				break;
			case "MIN":
				if (countOfInputNumber < 2)
					throw new MinimumInputNumberException(engineName);
				engine = new MinEngine();
				break;
			case "CUBEVOL":
				if (countOfNegativeNumber > 0)
					throw new NegativeNumberException(engineName);
				if (countOfInputNumber != 1)
					throw new OneInputException(engineName);
				engine = new CubeVolEngine();
				break;
			case "SPHEREVOL":
				if (countOfNegativeNumber > 0)
					throw new NegativeNumberException(engineName);
				if (countOfInputNumber != 1)
					throw new OneInputException(engineName);
				engine = new SphereVolEngine();
				break;
			default:
				throw new InvalidCommandException(engineName);
			}
		}
		/**
		 * InvalidCommandException is Exception - 01. For using this program, you have
		 * to write engine names and input values. it's for checking that if you don't
		 * write engine name correctly or anything. if this Exception is working, the
		 * guide texts will be print.
		 */
		catch (InvalidCommandException e) {
			System.out.println(e.getMessage());
			return null;
		}
		/**
		 * MinimumInputNumberException is Exception - 02. Some engines have to get more
		 * than two values. it's for checking that if input values are more 2 or not.
		 */
		catch (MinimumInputNumberException e) {
			System.out.println(e.getMessage());
			return null;
		}
		/**
		 * NegativeNumberException is Exception - 03. Every engines have to get positive
		 * value without MAX, MIN, it's for checking that if input value is negative or
		 * not.
		 */
		catch (NegativeNumberException e) {
			System.out.println(e.getMessage());
			return null;
		}
		/**
		 * OneInputException is Exception - 04. Some engines have to get a only one
		 * value. it's for checking that if input value is one or not.
		 */
		catch (OneInputException e) {
			System.out.println(e.getMessage());
			return null;
		}
		/**
		 * MyNumberFormatException is Exception - 05. Every engines have to get number
		 * values. it's for checking that if input value is number or String.
		 */
		catch (MyNumberFormatException e) {
			System.out.println(e.getMessage());
			return null;
		}
		return engine;
	}

	/**
	 * This is for Exception - 05 which is MyNumberFormatException. it's for
	 * checking method that the input value is number or String.
	 */
	public boolean isInteger(String args) {
		try {
			Integer.parseInt(args);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
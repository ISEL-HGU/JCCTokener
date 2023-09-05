package edu.handong.csee.java.hw5;

import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.clioptions.engines.Computable;
import edu.handong.csee.java.hw5.clioptions.engines.CubeVolEngine;
import edu.handong.csee.java.hw5.clioptions.engines.FactorialEngine;
import edu.handong.csee.java.hw5.clioptions.engines.FibonacciEngine;
import edu.handong.csee.java.hw5.clioptions.engines.GCDEngine;
import edu.handong.csee.java.hw5.clioptions.engines.LCMEngine;
import edu.handong.csee.java.hw5.clioptions.engines.MaxEngine;
import edu.handong.csee.java.hw5.clioptions.engines.MinEngine;
import edu.handong.csee.java.hw5.clioptions.engines.SQRTEngine;
import edu.handong.csee.java.hw5.clioptions.engines.SphereVolEngine;
import edu.handong.csee.java.hw5.clioptions.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.fileutil.FileManager;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;

import java.io.File;

/**
 * The Calculator class is a program that accepts an input and performs the
 * corresponding mathematical computation based on the input.
 */
public class Calculator {
	/**
	 * The opath is a private String that represents the path to the output file.
	 * 
	 */
	private String opath;

	/**
	 * The ipath is a private String that represents the path to the input file.
	 * 
	 */
	private String ipath;

	/**
	 * The task is a String that represents the type of calculation to be performed.
	 *
	 */
	private String task;

	/**
	 * The values is a private String that contains the raw input values for the
	 * calculation.
	 * 
	 */
	private String values;

	/**
	 * The help is a private boolean that indicates whether the user has requested
	 * help.
	 * 
	 */
	private boolean help;

	/**
	 * Getter for output file path
	 * 
	 * @return output file path as String
	 */
	public String getOpath() {
		return opath;
	}

	/**
	 * Setter for output file path
	 *
	 * @param opath output file path as String
	 */
	public void setOpath(String opath) {
		this.opath = opath;
	}

	/**
	 * Getter for input file path
	 * 
	 * @return input file path as String
	 */

	public String getIpath() {
		return ipath;
	}

	/**
	 * Setter for input file path
	 *
	 * @param ipath input file path as String
	 */
	public void setIpath(String ipath) {
		this.ipath = ipath;
	}

	/**
	 * Getter for task
	 * 
	 * @return task as String
	 */
	public String getTask() {
		return task;
	}

	/**
	 * Setter for task
	 *
	 * @param task task as String
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * Getter for input values
	 * 
	 * @return values as String
	 */
	public String getValues() {
		return values;
	}

	/**
	 * Setter for input values
	 *
	 * @param values input values as String
	 */
	public void setValues(String values) {
		this.values = values;
	}

	/**
	 * Getter for help flag
	 * 
	 * @return help as boolean
	 */
	public boolean isHelp() {
		return help;
	}

	/**
	 * Setter for help flag
	 *
	 * @param help help as boolean
	 */
	public void setHelp(boolean help) {
		this.help = help;
	}

	/**
	 * The main method of the Calculator class creates an instance of the Calculator
	 * class and calls its run method to perform the computation.
	 * 
	 * @param args A String array that contains the command line arguments.
	 */
	public static void main(String[] args) {

		Calculator myCalculator = new Calculator();

		myCalculator.run(args);
	}

	/**
	 * The run method of the Calculator class performs the computation based on on
	 * the input
	 * 
	 * @param args A String array that contains the command line arguments.
	 */

	public void run(String[] args) {

		OptionHandler optionHandler = new OptionHandler();
		FileManager manager = new FileManager();
		Options options = optionHandler.createOptions();

		if (optionHandler.parseOptions(options, args)) {
			if (args == null) {
				optionHandler.printHelp(options);
				return;
			}
			ipath = optionHandler.getDataInputFilePath();
			opath = optionHandler.getDataOutputFilePath();
			task = optionHandler.getTask();
			values = optionHandler.getInputValues();
			help = optionHandler.isHelpRequested();
			if (("SQRT".equalsIgnoreCase(task) || "MIN".equalsIgnoreCase(task) || "MAX".equalsIgnoreCase(task))
					&& ipath != null && opath != null && values == null) {
				CSVFileCalculator csvFileCalculator = new CSVFileCalculator(ipath, opath, task);
				Thread thread = new Thread(csvFileCalculator);
				thread.start();
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("The " + opath + " file has been successfuly written.");
				return;
			}
			if (help) {
				optionHandler.printHelp(options);
				return;
			}
			if (task == null) {
				optionHandler.printHelp(options);
				return;
			}
			if (help == !true && task == null && values == null && opath == null && ipath == null) {
				optionHandler.printHelp(options);
				return;
			}
			if (values == null && ipath == null && opath == null) {
				optionHandler.printHelp(options);
				return;
			}
			if (ipath != null) {
				File inputFile = new File(ipath);
				if (!inputFile.exists() || inputFile.isDirectory()) {
					optionHandler.printHelp(options);
					System.exit(0);
					return;
				}
			}

			if (("SQRT".equalsIgnoreCase(task)) && ipath != null && opath != null && values == null) {
				ArrayList<String> lines = manager.readLinesFromATxtFile(ipath);
				ArrayList<String> results = new ArrayList<>();
				SQRTEngine engine = new SQRTEngine();

				for (String line : lines) {
					String[] words = line.split(",");
					for (String word : words) {
						double num = Double.parseDouble(word);
						engine.setInput(new String[] { Double.toString(num) });
						engine.compute();
						results.add(Double.toString(engine.getResult()));

					}
				}

				manager.writeAtxtFile(opath, results);
				System.out.println("The " + opath + " file has been successfuly written.");
				return;
			}
			if (help == !true && task != null && values != null && opath == null && ipath == null) {
				String[] words = values.split("\\s+");
				String[] newWords = new String[words.length + 1];
				newWords[0] = optionHandler.getTask().toUpperCase();
				System.arraycopy(words, 0, newWords, 1, words.length);

				args = newWords;

				try {
					if (args.length == 0) {
						throw new InvalidCommandException();
					}
					String engineName = args[0].toUpperCase();

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
					engine.setInput(args);
					engine.compute();

					System.out.println("The result of " + engineName + " is " + engine.getResult() + ".");
					return;
				} catch (InvalidCommandException e) {
					System.out.println(e.getMessage());
					return;
				}
			}

		}


	}

}

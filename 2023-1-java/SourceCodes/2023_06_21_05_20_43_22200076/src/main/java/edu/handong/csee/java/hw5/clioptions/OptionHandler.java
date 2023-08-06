package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.DefaultParser;

/**
 * An option handler.
 */
public class OptionHandler{
	private String task;
	private String dataInputFilePath;
	private String dataOutputFilePath;
	private String inputValues;
	private boolean helpRequest;
	
	/**
	 * Gets if the help page is needed.
	 * 
	 * @return boolean value indicating whether the help page is needed or not
	 */
	public boolean getHelpRequested() {
		return helpRequest;
	}
	
	/**
	 * Sets if the help page is needed.
	 * 
	 * @param helpRequest boolean value indicating whether the help page is needed or not
	 */
	public void setHelpRequested(boolean helpRequest) {
		this.helpRequest = helpRequest;
	}
	
	/**
	 * Gets name of the task to be executed.
	 * 
	 * @return name of the task to be executed
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * Sets name of the task to be executed.
	 * 
	 * @param task name of the task to be executed
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	/**
	 * Gets path of the file to be read.
	 * 
	 * @return path of the file to be read
	 */
	public String getDataInputFilePath() {
		return dataInputFilePath;
	}
	
	/**
	 * Sets path of the file to be read.
	 * 
	 * @param dataInputFilePath path of the file to be read
	 */
	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath = dataInputFilePath;
	}
	
	/**
	 * Gets path of the file to be write.
	 * 
	 * @return path of the file to be write
	 */
	public String getDataOutputFilePath() {
		return dataOutputFilePath;
	}
	
	/**
	 * Sets path of the file to be write.
	 * 
	 * @param dataOutputFilePath path of the file to be write
	 */
	public void setDataOutputFilePath(String dataOutputFilePath) {
		this.dataOutputFilePath = dataOutputFilePath;
	}
	
	/**
	 * Gets values to be used for calculations.
	 * 
	 * @return values to be used for calculations
	 */
	public String getInputValues() {
		return inputValues;
	}
	
	/**
	 * Sets values to be used for calculations.
	 * 
	 * @param inputValues values to be used for calculations
	 */
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}
	
	/**
	 * Sets the options and return.
	 * 
	 * @return the set options
	 */
	public Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show the help page")
				.build());
		
		options.addOption(Option.builder("i").longOpt("ipath")
				.desc("Set a path for a data input file. It must work with -t SQRT, -t MAX, or -t MIN and -o options together. e.g., -t SQRT -i file.csv -o output.csv")
				.hasArg()
				.argName("A data file/directory path to read")
				.build());
		
		options.addOption(Option.builder("o").longOpt("opath")
				.desc("Set a path for a data output file.")
				.hasArg()
				.argName("A data file path to write")
				.build());
		
		options.addOption(Option.builder("t").longOpt("task")
				.desc("Set a task. The -t or -i options must be set as well.")
				.hasArg()
				.argName("A task name")
				.required()
				.build());
		
		options.addOption(Option.builder("v").longOpt("values")
				.desc("Set input values (separator: space), e.g. \"23 21 2\"")
				.hasArgs()
				.argName("Input values for a task.")
				.build());
		
		return options;
	}
	
	/**
	 * Parses the created options and returns a Boolean value.
	 * 
	 * @param options the set options
	 * @param args user input value
	 * @return the boolean value obtained after parsing the options
	 */
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		
		try {	
			CommandLine cmd = parser.parse(options, args);
			
			task = cmd.getOptionValue("t");
			String taskUpper = task.toUpperCase();
			if (taskUpper.equals("SQRT") || taskUpper.equals("MAX") || taskUpper.equals("MIN")) {
				if(cmd.hasOption("i")&&cmd.hasOption("o")&&!cmd.hasOption("v")) {
					dataInputFilePath = cmd.getOptionValue("i");
					dataOutputFilePath = cmd.getOptionValue("o");
				}
				else if(!cmd.hasOption("i")&&!cmd.hasOption("o")&&cmd.hasOption("v")) {
					String[] inputValuesArray = cmd.getOptionValues("v");
					inputValues = String.join(" ", inputValuesArray);
					inputValues = inputValues.replaceAll("\\s+", " ");
					helpRequest = cmd.hasOption("h");
				}
				else {
					throw new Exception();
				}
			}
			else {
				String[] inputValuesArray = cmd.getOptionValues("v");
				inputValues = String.join(" ", inputValuesArray);
				inputValues = inputValues.replaceAll("\\s+", " ");
				helpRequest = cmd.hasOption("h");
			}
		} catch(Exception e) {
			printHelp(options);
			return false;
		}
		return true;
	}
	
	/**
	 * Shows the command guideline for HW4.
	 * 
	 * @param options the set options
	 */
	public void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "Math Calculator";
		String footer = "\nThis is the 2023-1 HW5 help page.";
		formatter.printHelp("calculator", header, options, footer, true);
	}
}
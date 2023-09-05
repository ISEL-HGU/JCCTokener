package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;

/**
 * This class handles the options given from the command line. This class uses apache cli-commons dependency.
 */
public class OptionHandler {
	private String task = null;
	private String dataInputFilePath = null;
	private String dataOutputFilePath = null;
	private String inputValues = null;
	private boolean helpRequested = false;
	
	/**
	 * This method returns the private boolean field helpRequested.
	 * @return The private boolean field helpRequested.
	 */
	public boolean getHelpRequested() {
		return helpRequested;
	}
	
	/**
	 * This method sets the value of the private boolean field helpRequested.
	 * @param helpRequested The boolean value that would be set as the private boolean field helpRequested.
	 */
	public void setHelpRequested(boolean helpRequested) {
		this.helpRequested = helpRequested;
	}
	
	/**
	 * This method returns the private string field task.
	 * @return The private string field task.
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * This method sets the value of the private string field task.
	 * @param task The string value that would be set as the private string field task.
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	/**
	 * This method returns the private string field dataInputFilePath.
	 * @return The private string field dataInputFilePath.
	 */
	public String getDataInputFilePath() {
		return dataInputFilePath;
	}
	/**
	 * This method sets the value of the private string field dataInputFilePath.
	 * @param dataInputFilePath The string value that would be set as the private string field dataInputFilePath.
	 */
	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath = dataInputFilePath;
	}
	
	/**
	 * This method sets the value of the private string field dataOutputFilePath.
	 * @return The private string field dataOutputFilePath.
	 */
	public String getDataOutputFilePath() {
		return dataOutputFilePath;
	}
	/**
	 * This method sets the value of the private string field dataOutputFilePath.
	 * @param dataOutputFilePath The string value that would be set as the private string field dataOutputFilePath.
	 */
	public void setDataOutputFilePath(String dataOutputFilePath) {
		this.dataOutputFilePath = dataOutputFilePath;
	}
	
	/**
	 * This method sets the value of the private string field inputValues.
	 * @return The private string field inputValues.
	 */
	public String getInputValues() {
		return inputValues;
	}
	/**
	 * This method sets the value of the private string field inputValues.
	 * @param inputValues The string value that would be set as the private string field inputValues.
	 */
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}
	
	/**
	 * This method creates the option that would be used in the Calculator class for running the program. 
	 * @return The options created in the method.
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
			     .required()
			     .argName("A task name")
			     .build());
		
		options.addOption(Option.builder("v").longOpt("values")
			     .desc("Set input values (separator: space), e.g. \"23 21 2\"")
			     .hasArg()
			     .argName("Input values for a task.")
			     .build());
		
		
		return options;
	}

	
	/**
	 * This method parses the args received from the command line into the appropriate option values.
	 * @param options Options created from the method createOptions.
	 * @param args The string array given from the command line.
	 * @return If the required option is not given, it returns false and other cases true.
	 */
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try{
			CommandLine cmd = parser.parse(options, args);
			helpRequested = cmd.hasOption("h");
			dataInputFilePath = cmd.getOptionValue("i");
			dataOutputFilePath = cmd.getOptionValue("o");
			inputValues = cmd.getOptionValue("v");
			task = cmd.getOptionValue("t");
			
		}

		catch(Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}
	
	/**
	 * This method prints the help message of the CLI. 
	 * @param options The options that would be printed in the help message.
	 */
	public void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "Math Calculator";
		String footer ="\nThis is the 2023-1 HW5 help page.\n\n";
		formatter.printHelp("calculator", header, options, footer, true);
	}
}

package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This is the class that checks for and assigns the options that are entered.  *
 */
public class OptionHandler {
	
	private String task;
	private String dataInputFilePath;
	private String dataOutputFilePath;
	private String inputValues;
	private boolean helpRequested;
	
	/**
	 * This method returns the help option when h is typed. 
	 */
	public boolean getHelpRequested() {
		return helpRequested;
	}
	
	/**
	 * Method to specify the help option when h is typed
	 */
	public void setHelpRequested(boolean helpRequested) {
		this.helpRequested = helpRequested;
	}
	
	/**
	 * This method returns the task(engineName) when t is typed.
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * Method to specify the task(engineName) when t is typed. 
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	/**
	 * This method returns the input file path when i is typed.
	 */
	public String getDataInputFilePath() {
		return dataInputFilePath;
	}
	
	/**
	 * Method to specify the input file path when i is typed.
	 */
	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath = dataInputFilePath;
	}
	
	/**
	 * This method returns the output file path when o is typed.
	 */
	public String getDataOutputFilePath() {
		return dataOutputFilePath;
	}
	
	/**
	 * Method to specify the output file path when o is typed.
	 */
	public void setDataOutputFilePath(String dataOutputFilePath) {
		this.dataOutputFilePath = dataOutputFilePath;
	}
	
	/**
	 * This method returns the values when v is typed.
	 */
	public String getInputValues() {
		return inputValues;
	}
	
	/**
	 * Method to specify the values when v is typed.
	 */
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}
	
	/**
	 * It uses a class called CommandLine and has methods that compare and process incoming information.
	 */
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser(); 
		
		try {
			CommandLine cmd = parser.parse(options, args); 
			
			helpRequested = cmd.hasOption("h");	// help page
			dataInputFilePath = cmd.getOptionValue("i");		// set a path for a a data input file
			dataOutputFilePath = cmd.getOptionValue("o");		// set a path for a data output file
			//task = cmd.getOptionValue("t");		// set a task
			task = cmd.getOptionValue("t", "").toUpperCase();
			inputValues = cmd.getOptionValue("v"); 	// set input values
			
			if(dataInputFilePath !=null) {
				if(!task.equals("SQRT") || dataOutputFilePath == null) {
					printHelp(options);
					return false;
				}
				if(task.equals("SQRT") && inputValues != null) {
					printHelp(options);
					return false;
				}
			}
			
			if(!task.equals("SQRT")) {
				if(inputValues == null) {
					printHelp(options);
					return false;
				}
			}
			

			
		} catch (Exception e) {
			printHelp(options);
			return false;
		}
		return true;		
	}
	
	/**
	 * Methods used after actually applying the option.
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
				//.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("opath")
				.desc("Set a path for a data output file.")
				.hasArg()
				.argName("A data file path to write")
				//.required()
				.build());

		options.addOption(Option.builder("t").longOpt("task")
				.desc("Set a task. The -t or -i options must be set as well.")
				.hasArg()
				.argName("A task name")
				.required()
				.build());

		options.addOption(Option.builder("v").longOpt("values")
				.desc("Set input values (separator: space), e.g. \"23 21 2\"")
				.hasArg()
				.argName("Input values for a task.")
				//.required()
				.build());
		
		return options;
	}
	/**
	 * Methods that output the help option.
	 */
	public void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		
		String header = "Math Calculator";
		String footer = "\nThis is the 2023-1 HW5 help page.";
		formatter.printHelp("calculator", header, options, footer, true);
	}
}

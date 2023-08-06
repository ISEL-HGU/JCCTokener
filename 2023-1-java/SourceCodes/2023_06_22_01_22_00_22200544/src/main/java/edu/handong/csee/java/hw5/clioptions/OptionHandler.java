package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * The OptionHandler class is a handler responsible for processing options.
 * This class provides features such as creating options, parsing options and factors, and outputting help.
 */
public class OptionHandler {
	private String task;
	private String datalnputFilePath;
	private String dataOutputFilePath;
	private String inputValues;
	private boolean helpRequested;
	
	/**
	 * The getter method that returns task for use by other methods.`
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * A setter method that allows task to be obtained from other methods.
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	/**
	 * The getter method that returns datalnputFilePath for use by other methods.`
	 */
	public String getDatalnputFilePath() {
		return datalnputFilePath;
	}
	
	/**
	 * A setter method that allows datalnputFilePath to be obtained from other methods.
	 */
	public void setDatalnputFilePath(String datalnputFilePath) {
		this.datalnputFilePath = datalnputFilePath;
	}
	
	/**
	 * The getter method that returns dataOutputFilePath for use by other methods.`
	 */
	public String getDataOutputFilePath() {
		return dataOutputFilePath;
	}
	
	/**
	 * A setter method that allows dataOutputFilePath to be obtained from other methods.
	 */
	public void setDataOutputFilePath(String dataOutputFilePath) {
		this.dataOutputFilePath = dataOutputFilePath;
	}
	
	/**
	 * The getter method that returns inputValues for use by other methods.`
	 */
	public String getInputValues() {
		return inputValues;
	}
	
	/**
	 * A setter method that allows inputValues to be obtained from other methods.
	 */
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}
	
	/**
	 * The getter method that returns helpRequested for use by other methods.`
	 */
	public boolean getHelpRequested() {
		return helpRequested;
	}
	
	/**
	 * A setter method that allows helpRequested to be obtained from other methods.
	 */
	public void setHelpRequested(boolean helpRequested) {
		this.helpRequested = helpRequested;
	}
	
	/**
	 * Method for generating options.
	 */
	public Options createOptions(){
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("t").longOpt("task")
				.desc("Set a task. The -t or -i options must be set as well.")
				.hasArg()
				.argName("A task name")
				.required()
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("ipath")
				.desc("Set a path for a data input file. It must work with -t SQRT, -t MAX, or -t MIN and -o options together. e.g., -t SQRT -i file.csv -o output.csv\n"
						+ "")
				.hasArg()
				.argName("A data file/directory path to read")
				.build());
				
		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("opath")
				.desc("Set a path for a data output file.")
				.hasArg()
				.argName("A data file path to write")
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("values")
				.desc("Set input values (separator: space), e.g. \"23 21 2\"")
				.hasArg()     // this option is intended not to have an option value but just an option
				.argName("Input values for a task.")
				//.required() // this is an optional option. So disabled required().
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show the help page")
		        .build());

		return options;
		
	}
	
	/**
	 *  Method for Parse the given options and factors.
	 */
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			task = cmd.getOptionValue("t");
			datalnputFilePath = cmd.getOptionValue("i");
			dataOutputFilePath = cmd.getOptionValue("o");
			inputValues = cmd.getOptionValue("v");
			helpRequested = cmd.hasOption("h");


		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
		
	}
	
	/**
	 *  Method for Outputs help.
	 */
	public void printHelp(Options options){
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Math Calculator";
		String footer ="\nThis is the 2023-1 HW5 help page.";
		formatter.printHelp("calculator", header, options, footer, true);
		
		System.exit(0);
		
	}


}

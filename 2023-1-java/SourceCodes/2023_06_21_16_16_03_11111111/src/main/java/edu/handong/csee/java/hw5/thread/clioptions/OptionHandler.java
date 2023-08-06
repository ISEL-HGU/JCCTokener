package edu.handong.csee.java.hw5.thread.clioptions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
/**
 * OptionHandler class is responsible for managing and processing command line options
 * used in Calculator program.
 */
public class OptionHandler {
	/**
	 * A task that is to be executed
	 */
	private String task;

	/**
	 * The file path to the input data file.
	 */
	private String dataInputFilePath;

	/**
	 * The file path to the output data file. 
	 */
	private String dataOutputFilePath;

	/**
	 * The specific input values that are required for the task.
	 */
	private String inputValues;

	/**
	 * A flag that represents whether help is requested or not. If true, help is requested.
	 */
	private boolean helpRequested;

    /**
     * Getter for task option.
     *
     * @return task option value as String.
     */
	public String getTask() {
		return task;
	}
    /**
     * Setter for task option.
     *
     * @param task task option value as String.
     */

	public void setTask(String task) {
		this.task = task;
	}
    /**
     * Getter for data input file path.
     *
     * @return data input file path as String.
     */
	public String getDataInputFilePath() {
		return dataInputFilePath;
	}
    /**
     * Setter for data input file path.
     *
     * @param dataInputFilePath data input file path as String.
     */
	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath = dataInputFilePath;
	}
    /**
     * Getter for data output file path.
     *
     * @return data output file path as String.
     */
	public String getDataOutputFilePath() {
		return dataOutputFilePath;
	}
    /**
     * Setter for data output file path.
     *
     * @param dataOutputFilePath data output file path as String.
     */
	public void setDataOutputFilePath(String dataOutputFilePath) {
		this.dataOutputFilePath = dataOutputFilePath;
	}
    /**
     * Getter for input values.
     *
     * @return input values as String.
     */
	public String getInputValues() {
		return inputValues;
	}
    /**
     * Setter for input values.
     *
     * @param inputValues input values as String.
     */
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}
    /**
     * Getter for help flag.
     *
     * @return help flag as boolean.
     */
	public boolean isHelpRequested() {
		return helpRequested;
	}
    /**
     * Setter for help flag.
     *
     * @param helpRequested help flag as boolean.
     */
	public void setHelpRequested(boolean helpRequested) {
		this.helpRequested = helpRequested;
	}

    /**
     * Parses command line options.
     *
     * @param options Options object which contains command line options.
     * @param args String array which contains command line arguments.
     * @return boolean true if parsing successful, false otherwise.
     */
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			setDataInputFilePath(cmd.getOptionValue("i"));
			setDataOutputFilePath(cmd.getOptionValue("o"));
			setTask(cmd.getOptionValue("t"));
			setInputValues(cmd.getOptionValue("v"));
			setHelpRequested(cmd.hasOption("h"));

		} catch (Exception e) {
			printHelp(options);
			return false;
		}
		return true;
	}
    /**
     * Creates command line options.
     *
     * @return Options object which contains created options.
     */

	public Options createOptions() {
		Options options = new Options();
		options.addOption(Option.builder("i").longOpt("ipath").desc(
				"Set a path for a data input file. It must work with -t SQRT and -o options together. e.g., -t SQRT -i file.csv -o output.csv")
				.hasArg().argName("A data file path to read").build());

		options.addOption(Option.builder("o").longOpt("opath").desc("Set a path for a data output file.").hasArg()
				.argName("A data file path to write").build());
		options.addOption(Option.builder("t").longOpt("task").desc("Set a task. The -t or -i options must be set as well.")
				.hasArg().argName("A task name").required().build());
		options.addOption(
				Option.builder("v").longOpt("values").desc("Set input values (separator: space), e.g. \"23 21 2\"")
						.hasArg().argName("Input values for a task.").build());
		options.addOption(Option.builder("h").longOpt("help").desc("Show the help page").build());
		return options;

	}
    /**
     * Prints the help information.
     *
     * @param options Options object which contains command line options.
     */
	public void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Math Calculator";
		String footer = "\nThis is the 2023-1 HW4 help page.";
		formatter.printHelp(
				"calculator",
				header, options, footer, true);
	}
}

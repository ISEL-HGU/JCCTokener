package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
/**
 * The `OptionHandler` class is a class that makes CLI.
 * it chooses the options according to the input
 */
public class OptionHandler{
	private String task;
	private String dataInputFilePath;
	private String dataOuputFilePath;
	private String inputValues;
	private boolean helpRequested;
    /**
 * Returns the boolean state about helpRequested
 * 
 * @return boolean of helpRequested
 */
	public boolean getHelpRequested() {
		return helpRequested;
	}
	   /**
* Set the boolean state about helpRequested
* @param helpRequested the new value of helpRequested
*/
	public void setHelpRequested(boolean helpRequested ) {
		this.helpRequested=helpRequested;
	}
    /**
 * Returns the value of task
 * 
 * @return the new value of task
 */
	public String getTask() {
		return task;
	}
	   /**
* Set the value of task
* @param task the new value of task
*/
	public void setTask(String task) {
			this.task=task;
	}
    /**
 * Returns the value of dataInputFilePath
 * 
 * @return the new value of dataInputFilePath
 */
	public String getDataInputFilePath() {
		return dataInputFilePath;
	}
	   /**
  * Set the value of dataInputFilePath
  * @param dataInputFilePath the new value of dataInputFilePath
  */
	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath=dataInputFilePath;
	}
    /**
 * Returns the value of dataOutputFilePath
 * 
 * @return the new value of dataOutputFilePath
 */
	public String getDataOutputFilePath() {
		return dataOuputFilePath;
		
	}
	   /**
     * Set the value of dataOutputFilePath
     * @param dataOutputFilePath the new value of dataOuputFilePath
     */
	public void setDataOutputFilePath(String dataOuputFilePath) {
		this.dataOuputFilePath=dataOuputFilePath;
		
	}
    /**
 * Returns the value of inputValues
 * 
 * @return the new value of inputValues
 */
	public String getInputValues() {
		return inputValues;
	}
    /**
     * Set the value of inputValues
     * @param inputValues the new value of inputValues
     */
	public void setInputValues (String inputValues) {
		this.inputValues=inputValues;
		
	}
    /**
 * createOptions
 * Returns the value of inputValues
 * 
 * @return Options
 */

	public Options createOptions() {
		Options options = new Options();
		options.addOption(Option.builder("t").longOpt("task")
				.desc("Set a task. The -t or -i options must be set as well.")
				.hasArg()
				.argName("A task name")
				.required()
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
		options.addOption(Option.builder("v").longOpt("values")
				.desc("Set input values (separator: space), e.g. \"23 21 2\"")
				.hasArg()
				.argName("Input values for a task.")
				.build());
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show the help page")
		        .build());

		return options;
	}
    /**
 * praseOptions
 * Returns the boolean
 * @param options the options class made from createOption
 * @param args the args is a input of the program
 * @return boolean type
 */
	public boolean parseOptions(Options options ,String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);
			task= cmd.getOptionValue("t");
			dataInputFilePath= cmd.getOptionValue("i");
			dataOuputFilePath= cmd.getOptionValue("o");
			inputValues= cmd.getOptionValue("v");
			helpRequested= cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}
	   /**
	 * printHelp
	 * when he help option is selected, print the help lines
	 * @param options the options class made from createOption
	 */
	public void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "Math Calculator";
		String footer ="\nThis is the 2023-1 HW5 help page.";
		formatter.printHelp("calculator", header, options, footer, true);
	}

	
	
	
}



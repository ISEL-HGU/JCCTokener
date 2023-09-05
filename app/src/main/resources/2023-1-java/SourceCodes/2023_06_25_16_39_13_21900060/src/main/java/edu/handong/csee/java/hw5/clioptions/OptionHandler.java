package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.CommandLine; 
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;

/*
 * This class ~ 
 */
public class OptionHandler {
	private String task;
	private String dataInputFilePath;
	private String dataOutputFilePath;
	private String inputValues;
	private boolean helpRequested;
	
	
	/**
	 * @return the task
	 */
	public String getTask() {
		return task;
	}
	/**
	 * @param task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}
	/**
	 * @return the dataInputFilePath
	 */
	public String getDataInputFilePath() {
		return dataInputFilePath;
	}
	/**
	 * @param dataInputFilePath the dataInputFilePath to set
	 */
	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath = dataInputFilePath;
	}
	/**
	 * @return the dataOutputFilePath
	 */
	public String getDataOutputFilePath() {
		return dataOutputFilePath;
	}
	/**
	 * @param dataOutputFilePath the dataOutputFilePath to set
	 */
	public void setDataOutputFilePath(String dataOutputFilePath) {
		this.dataOutputFilePath = dataOutputFilePath;
	}
	/**
	 * @return the inputValues
	 */
	public String getInputValues() {
		return inputValues;
	}
	/**
	 * @param inputValues the inputValues to set
	 */
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}
	/**
	 * @return the helpRequested
	 */
	public boolean getHelpRequested() {
		return helpRequested;
	}
	/**
	 * @param helpRequested the helpRequested to set
	 */
	public void setHelpRequested(boolean helpRequested) {
		this.helpRequested = helpRequested;
	}
	
	/**
	 * @return the Options
	 */
	public Options createOptions() {
		Options options = new Options();
		
		options.addOption("h", "help", false, "Show the help page");
		options.addOption("i", "ipath", true, "Set a path for a data input\n"
				+ "                                          file. It must work with -t SQRT\n"
				+ "                                          and -o options together. e.g.,\n"
				+ "                                          -t SQRT -i file.csv -o\n"
				+ "                                          output.csv");
		options.addOption("o", "opath", true, "Set a path for a data output\n"
				+ "                                          file.");
		options.addOption("t", "task", true, "Set a task. The -t or -i options\n"
				+ "                                          must be set as well.");
		options.addOption("v", "values", true, "Set input values (separator:\n"
				+ "                                          space), e.g. \"23 21 2\"");
		
		return options;
	}
	
	/**
	 * @param option the option object
	 * @param args 
	 * @return whether the correct arguments is entered or not
	 */
	public boolean parseOptions(Options option, String[] args) {
		boolean isTask = false;
		boolean isValue = false;
		// System.out.println("parseOptions : " + args[0] + args[1] + args[2]);
		
		CommandLineParser parser = new DefaultParser();		// Create parser
		try {
			CommandLine line = parser.parse(option, args);
			String inputArgument;
			// System.out.println("Print line: "+ line);
			if(line.hasOption("h")) {
				inputArgument = line.getOptionValue("h");
				printHelp(null);
				return true;
			}
			if(line.hasOption("t")) {
				inputArgument = line.getOptionValue("t");
				//System.out.println("-> getOptionValue : "+ inputArgument);
				this.task = inputArgument;
				isTask = true;
			}
			if(line.hasOption("v")) {
				inputArgument = line.getOptionValue("v");
				this.inputValues = inputArgument;
				isValue = true;
			}
			if(line.hasOption("i")) {
				inputArgument = line.getOptionValue("i");
				this.inputValues = inputArgument;
				// isTask = true;
			}
		}catch(ParseException exp) {
			System.out.println("Parse error");
		}
		
		// task 처리
		// -t 또는 -i 둘중 하나가 있어야함 (둘다 있으면 help page )
		if(isTask && isValue) return true;
		else return false;
		
	}
	
	/**
	 * @param option the option object
	 * */
	public void printHelp(Options option) {
		
		System.out.println("usage: calculator [-h] [-i <A data file path to read>] [-o <A data file\n"
				+ "       path to write>] -t <A task name> [-v <Input values for a task.>]\n"
				+ "Math Calculator\n"
				+ " -h,--help                                Show the help page\n"
				+ " -i,--ipath <A data file path to read>    Set a path for a data input\n"
				+ "                                          file. It must work with -t SQRT\n"
				+ "                                          and -o options together. e.g.,\n"
				+ "                                          -t SQRT -i file.csv -o\n"
				+ "                                          output.csv\n"
				+ " -o,--opath <A data file path to write>   Set a path for a data output\n"
				+ "                                          file.\n"
				+ " -t,--task <A task name>                  Set a task. The -t or -i options\n"
				+ "                                          must be set as well.\n"
				+ " -v,--values <Input values for a task.>   Set input values (separator:\n"
				+ "                                          space), e.g. \"23 21 2\"\n"
				+ "\n"
				+ "This is the 2023-1 HW4 help page.");
	}
}

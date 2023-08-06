package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import edu.handong.csee.java.hw5.exceptions.*;

public class OptionHandler {
	private String task;
	private String dataInputFilePath;
	private String dataOutputFilePath;
	private String[] inputValues;
	private boolean helpRequested;
	
	public boolean getHelpReqeusted() {
		return helpRequested;
	}
	
	public void setHelpRequested(boolean helpRequested) {
		this.helpRequested = helpRequested;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDataInputFilePath() {
		return dataInputFilePath;
	}

	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath = dataInputFilePath;
	}

	public String getDataOutputFilePath() {
		return dataOutputFilePath;
	}

	public void setDataOutputFilePath(String dataOutputFilePath) {
		this.dataOutputFilePath = dataOutputFilePath;
	}

	public String[] getInputValues() {
		return inputValues;
	}

	public void setInputValues(String[] inputValues) {
		this.inputValues = inputValues;
	}
	
	public Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("h").longOpt("help")
			     .desc("Show the help page")
			     .build());
		
		options.addOption(Option.builder("i").longOpt("ipath")
			     .desc("Set a path for a data input file. It must work with -t SQRT, -t MAX, or -t MIN and -o options together. e.g., -t SQRT -i file.csv -o output.csv")
			     .hasArg()
			     .argName("A data file path/directory to read")
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
			     .hasArg()
			     .argName("Input values for a task")
			     .build());
		
		return options;
	}
	
	public boolean parseOptions(Options options, String[] args) throws InvalidOptionException, ParseException{
		CommandLineParser parser = new DefaultParser();
		

			CommandLine cmd = parser.parse(options, args);

			this.task = cmd.getOptionValue("t").toUpperCase();
			if(this.task.compareTo("SQRT") != 0 && this.task.compareTo("MIN") != 0 && this.task.compareTo("MAX") != 0) {
				if(cmd.hasOption("i") || cmd.hasOption("o")) throw new InvalidOptionException();
			}
			if(cmd.hasOption("i") && !cmd.hasOption("o")) throw new InvalidOptionException();
			if(!cmd.hasOption("i") && cmd.hasOption("o")) throw new InvalidOptionException();
			this.dataInputFilePath = cmd.getOptionValue("i");
			this.dataOutputFilePath = cmd.getOptionValue("o");
			if(cmd.hasOption("i") && cmd.hasOption("o") && cmd.hasOption("v")) throw new InvalidOptionException();
			if(cmd.hasOption("v"))this.inputValues = cmd.getOptionValue("v").trim().split("\\s+");
			this.helpRequested = cmd.hasOption("h");


		return true;
	}
	public void printHelp(Options options){
		HelpFormatter formatter = new HelpFormatter();
		String header = "Math Calculator";
		String footer = "This is the 2023-1 HW5 help page.";
		formatter.printHelp("calculator", header, options, footer, true);
	}
}

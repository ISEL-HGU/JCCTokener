package edu.handong.csee.java.hw5.clioptions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;


/**
 * This class is for handling the options from command line.
 * There are five options and option -t(--task) must be required.
 * If user don't follow the right usage, it will show help message
 */
public class OptionHandler extends Options {

    /**
	 * This is a field about task option.
	 */
	private String task;
	
    /**
	 * This is a field about input file option.
	 */
	private String dataInputFilePath;
	
    /**
	 * This is a field about output file option.
	 */
	private String dataOutputFilePath;
	
    /**
	 * This is a field about input values option.
	 */
	private String inputValues;
	
    /**
	 * This is a field about help request option.
	 * This option is boolean type. if it true, the help message will be printed.
	 */
	private boolean helpRequested;
	
	/**
	 * this is a getter of Task
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * this is a setter of Task
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	/**
	 * this is a getter of Input file.
	 */
	public String getDataInputFilePath() {
		return dataInputFilePath;
	}
	
	/**
	 * this is a setter of Input file.
	 */
	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath = dataInputFilePath;
	}
	
	/**
	 * this is a getter of Output file.
	 */
	public String getDataOutputFilePath() {
		return dataOutputFilePath;
	}
	
	/**
	 * this is a setter of Output file.
	 */
	public void setDataOutputFilePath(String dataOutputFilePath) {
		this.dataOutputFilePath = dataOutputFilePath;
	}
	
	/**
	 * this is a getter of Input value
	 */
	public String getInputValues() {
		return inputValues;
	}
	
	/**
	 * this is a setter of Input value
	 */
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}
	
	/**
	 * This public method is for creating the options.
	 * the five options will be set in here.
	 */
	public OptionHandler createOptions() {
		OptionHandler options = new OptionHandler();

		// add -t option by using OptionBuilder
		options.addOption(Option.builder("t").longOpt("task")
				.desc("Set a task. The -t or -i options must be set as well.")
				.hasArg()
				.required()
				.argName("A task name")
				.build());
		
		// add -i option by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("ipath")
				.desc("Set a path for a data input file. It must work with -t SQRT, -t MAX, or -t MIN and -o options together. e.g., -t SQRT -i file.csv -o output.csv")
				.hasArg()
				.argName("A data file/directory path to read")
				.build());

		// add -o option by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("opath")
				.desc("Set a path for a data output file.")
				.hasArg()
				.argName("A data file path to write")
				.build());
		
		// add -v option by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("values")
				.desc("Set input values (separator: space), e.g. \"23 21 2\"")
				.hasArgs()
				.argName("Input values for a task.")
				.build());

		
		
		// add -h option by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show the help page")
		        .build());
//
		return options;
	}
	
	/**
	 * This public method is for parsing the options.
	 * The five options will be matched with arguments which are typed by user.
	 * When the Input file does exist, it must be with output file, and without input value. So, there are conditions for checking input options.
	 */
	public boolean parseOptions(OptionHandler options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		
		try {

			CommandLine cmd = parser.parse(options, args);
			//System.out.println(args[0]);
			//for(int i =1; i<args.length;i++)
    		//	System.out.println(args[i]);
			task = cmd.getOptionValue("t");
			dataInputFilePath = cmd.getOptionValue("i");
			dataOutputFilePath = cmd.getOptionValue("o");
			inputValues = cmd.getOptionValue("v");
			helpRequested = cmd.hasOption("h");
			
			if(dataInputFilePath != null) {
				if(inputValues != null)
					return false;
				
				if(dataOutputFilePath == null)
					return false;
				
				if(!task.toUpperCase().equals("SQRT")&& !task.toUpperCase().equals("MAX") && !task.toUpperCase().equals("MIN"))
					return false;
			}
			
		} catch (Exception e) {
			return false;
			
		}

		return true;
		
	}
	
	/**
	 * This public method is for printing the help massage.
	 * if this method will work, the usage also will be printed automatically.
	 */
	public void printHelp(OptionHandler options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Math Calculator";
		String footer ="\nThis is the 2023-1 HW5 help page.\n";
		formatter.printHelp("calculator", header, options, footer, true);
		
	}
	/**
	 * this is a getter of help request
	 */
	public boolean getHelpRequested() {
		return helpRequested;
	}
	/**
	 * this is a setter of help request
	 */
	public void setHelpRequested(boolean helpRequested) {
		this.helpRequested = helpRequested;
	}
	
	
	
	
}

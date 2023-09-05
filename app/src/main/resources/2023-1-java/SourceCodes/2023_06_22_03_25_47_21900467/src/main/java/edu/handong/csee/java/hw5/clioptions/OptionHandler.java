package edu.handong.csee.java.hw5.clioptions;
import org.apache.commons.cli.*;
import java.io.File;

/**
 * This is class OptionHandler
 */
public class OptionHandler {
    private String task;
    private String dataInputFilePath;
    private String dataOutputFilePath;
    private String inputValues;
    private boolean helpRequested;
    private Options options;
    
	/**
	 * return the helpRequested
	 */
	public boolean getHelpRequested() {
		return helpRequested;
	}
	
	/**
	 * helpRequested the helpRequested to set
	 */
	public void setHelpRequested(boolean helpRequested) {
		this.helpRequested = helpRequested;
	}
	
	/**
	 * return the task
	 */
	public String getTask() {
		return task;
	}
	
	/**
	 * task the task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * return the dataInputFilePath
	 */
	public String getDataInputFilePath() {
		return dataInputFilePath;
	}

	/**
	 * dataInputFilePath the dataInputFilePath to set
	 */
	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath = dataInputFilePath;
	}

	/**
	 * return the dataOuputFilePath
	 */
	public String getDataOutputFilePath() {
		return dataOutputFilePath;
	}

	/**
	 * dataOuputFilePath the dataOuputFilePath to set
	 */
	public void setDataOutputFilePath(String dataOutputFilePath) {
		this.dataOutputFilePath = dataOutputFilePath;
	}

	/**
	 * return the inputValues
	 */
	public String getInputValues() {
		return inputValues;
	}

	/**
	 * inputValues the inputValues to set
	 */
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}
	/**
	 * this is constructor of optionhandler
	 */
	public OptionHandler() {
		setOptions(createOptions());
	}
	/**
	 * this method create options
	 */
	public Options createOptions() {
		Options options = new Options();
		options.addOption(Option.builder("h").longOpt("help")
			     .desc("Show the help page")
			     .build());
		options.addOption(Option.builder("i").longOpt("ipath")
			     .desc("Set a directory path that contains input files")
			     .hasArg()
			     .argName("A data file path to read")
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
			     .build());
		options.addOption(Option.builder("v").longOpt("values")
			     .desc("Set input values (separator space), e.g. \"23 21 2\"")
			     .hasArg()
			     .argName("Input values for a task.")
			     .build());
		return options;
	}
	
	/**
	 * this method setting options
	 */
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                setHelpRequested(true);
            } else {
                setHelpRequested(false);
            }

            if (cmd.hasOption("t")) {
                String taskValue = cmd.getOptionValue("t");
                if (taskValue != null) {
                    setTask(taskValue);
                } else {
                	setHelpRequested(true);
                }

                if (cmd.hasOption("v")) {
                	String inputs = cmd.getOptionValue("v");
                	if(inputs != null) {
                    setInputValues(inputs);
                	}
                	else {
                		setHelpRequested(true);
                	}
                }
            }

            if (cmd.hasOption("i")) {
            	if(cmd.hasOption("v")) {
            		setHelpRequested(true);
            	}
            	if(!cmd.hasOption("o") || !cmd.hasOption("t")) {
            		setHelpRequested(true);
            	}
            	String ifp = cmd.getOptionValue("i");
            	if(ifp != null) {
            		setDataInputFilePath(ifp);
            	}
            	else {
            		setHelpRequested(true);
            	}
            	
            	if (cmd.hasOption("o")) {
                	String ofp = cmd.getOptionValue("o");
                	if(ofp != null) {
                		setDataOutputFilePath(ofp);
                	}
                	else {
                		setHelpRequested(true);
                	}
                }
            	File inputFile = new File(getDataInputFilePath());
                if (!inputFile.exists()) {
                    setHelpRequested(true);
                }
            }


        }  catch (Exception e) {
        	setHelpRequested(true);
            printHelp(options);
            return false;
        }


        return true;
	}
	/**
	 * 
	 *  this method is print help page
	 */
    public void printHelp(Options options) {
    	if(getHelpRequested()) {
        	System.out.println("usage: calculator [-h] [-i <A data file/directory path to read>] [-o <A\n"
        			+ "       data file path to write>] -t <A task name> [-v <Input values for a\n"
        			+ "       task.>]\n"
        			+ "Math Calculator\n"
        			+ " -h,--help                                         Show the help page\n"
        			+ " -i,--ipath <A data file/directory path to read>   Set a path for a data\n"
        			+ "                                                   input file. It must\n"
        			+ "                                                   work with -t SQRT, -t\n"
        			+ "                                                   MAX, or -t MIN and -o\n"
        			+ "                                                   options together. e.g.,\n"
        			+ "                                                   -t SQRT -i file.csv -o\n"
        			+ "                                                   output.csv\n"
        			+ " -o,--opath <A data file path to write>            Set a path for a data\n"
        			+ "                                                   output file.\n"
        			+ " -t,--task <A task name>                           Set a task. The -t or\n"
        			+ "                                                   -i options must be set\n"
        			+ "                                                   as well.\n"
        			+ " -v,--values <Input values for a task.>            Set input values\n"
        			+ "                                                   (separator: space),\n"
        			+ "                                                   e.g. \"23 21 2\"\n"
        			+ "\n"
        			+ "This is the 2023-1 HW5 help page.");
        		
            System.exit(0);
    	}
    }

	/**
	 * return the options
	 */
	public Options getOptions() {
		return options;
	}

	/**
	 * options the options to set
	 */
	public void setOptions(Options options) {
		this.options = options;
	}

	/**
	 * this method is that split inputValues
	 */
	public String[] splitInputValues() {
		if(inputValues != null && !inputValues.isEmpty()) {
			return inputValues.replaceAll("\\s+"," ").trim().split(" ");
		}
		return new String[0];
	}
};
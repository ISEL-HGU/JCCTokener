package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.*;

/**
 * The OptionHandler class is responsible for handling command-line options and arguments.
 * It provides methods to create options, parse options, and print help information.
 */
public class OptionHandler {
	
    private String task="";
    private String dataInputFilePath="";
    private String dataOutputFilePath="";
    private String inputValues="";
    private boolean helpRequested = false;
    /**
     * Check if help is requested.
     *
     * @return true if help is requested, false otherwise.
     */
    public boolean getHelpRequested() {
        return this.helpRequested;
    }
    /**
     * Set whether help is requested or not.
     *
     * @param helpRequested true if help is requested, false otherwise.
     */
    public void setHelpRequested(boolean helpRequested) {
        this.helpRequested = helpRequested;
    }
    /**
     * Get the task name.
     *
     * @return the task name.
     */
    public String getTask() {
        return this.task;
    }
    /**
     * Set the task name.
     *
     * @param task the task name to be set.
     */
    public void setTask(String task) {
        this.task = task;
    }
    /**
     * Get the data input file path.
     *
     * @return the data input file path.
     */
    public String getDataInputFilePath() {
        return this.dataInputFilePath;
    }
    /**
     * Set the data input file path.
     *
     * @param dataInputFilePath the data input file path to be set.
     */
    public void setDataInputFilePath(String dataInputFilePath) {
        this.dataInputFilePath = dataInputFilePath;
    }
    /**
     * Get the data output file path.
     *
     * @return the data output file path.
     */
    public String getDataOutputFilePath() {
        return this.dataOutputFilePath;
    }
    /**
     * Set the data output file path.
     *
     * @param dataOutputFilePath the data output file path to be set.
     */
    public void setDataOutputFilePath(String dataOutputFilePath) {
        this.dataOutputFilePath = dataOutputFilePath;
    }
    /**
     * Get the input values.
     *
     * @return the input values.
     */
    public String getInputValues() {
        return this.inputValues;
    }
    /**
     * Set the input values.
     *
     * @param inputValues the input values to be set.
     */
    public void setInputValues(String inputValues) {
        this.inputValues = inputValues;
    }
    /**
     * Create the options for the math calculator.
     *
     * @return the options object containing different options for the calculator.
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
//                .required()
                .build());
        
        options.addOption(Option.builder("o").longOpt("opath")
                .desc("Set a path for a data output file.")
                .hasArg()
                .argName("A data file path to write")
//                .required()
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
//                .required()
                .build());
        
        return options;
    }
    /**
     * Parse the options and arguments provided to the math calculator.
     *
     * @param options the options object containing different options for the calculator.
     * @param args    the command-line arguments.
     * @return true if options are parsed successfully, false otherwise.
     */
    public boolean parseOptions(Options options, String[] args) {
       CommandLineParser parser = new DefaultParser();

       try {
    	   CommandLine cmd = parser.parse(options, args);

    	   if (cmd.hasOption("h")) {
               setHelpRequested(true);
               return true;
           }

    	   
           setTask(cmd.getOptionValue("t"));
           
           if((cmd.hasOption("i") ^ cmd.hasOption("o")) || cmd.hasOption("v") == cmd.hasOption("i"))
           {
        	   printHelp(options);
               System.exit(0);
           }
           

           if (cmd.hasOption("i")) {
               if(cmd.getOptionValue("i") == null || cmd.getOptionValue("o") == null )
            	   throw new NullPointerException();
               setDataInputFilePath(cmd.getOptionValue("i"));
               setDataOutputFilePath(cmd.getOptionValue("o"));
               
           }

           if (cmd.hasOption("v")) {
        	   if(cmd.getOptionValue("v") == null)
        		   throw new NullPointerException();
        	   setInputValues(cmd.getOptionValue("v"));
           }
           

       } catch (Exception e) {
           printHelp(options);
           return false;
       }
       return true;
    }
    /**
     * Print the help page with available options for the math calculator.
     *
     * @param options the options object containing different options for the calculator.
     */
    public void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "Math Calculator";
        String footer = "\nThis is the 2023-1 HW5 help page.";
        formatter.printHelp("calculator", header, options, footer, true);
    }

    
}

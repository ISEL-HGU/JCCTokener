package edu.handong.csee.java.hw5.clioptions;


import org.apache.commons.cli.*;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;

/**
 * This class handles command-line options for a math calculator program.
 */
public class OptionHandler {
    
    private String task="";
    private String dataInputFilePath="";
    private String dataOutputFilePath="";
    private String inputValues="";
    private boolean helpRequested=false;
    
    /**
     * Getter for the task.
     * @return the task
     */
    public String getTask() {
        return task;
    }
    
    /**
     * Setter for the task.
     * @param task the task to set
     */
    public void setTask(String task) {
        this.task = task;
    }
    
    /**
     * Getter for the data input file path.
     * @return the data input file path
     */
    public String getDataInputFilePath() {
        return dataInputFilePath;
    }
    
    /**
     * Setter for the data input file path.
     * @param dataInputFilePath the data input file path to set
     */
    public void setDataInputFilePath(String dataInputFilePath) {
        this.dataInputFilePath = dataInputFilePath;
    }
    
    /**
     * Getter for the data output file path.
     * @return the data output file path
     */
    public String getDataOutputFilePath() {
        return dataOutputFilePath;
    }
    
    /**
     * Setter for the data output file path.
     * @param dataOutputFilePath the data output file path to set
     */
    public void setDataOutputFilePath(String dataOutputFilePath) {
        this.dataOutputFilePath = dataOutputFilePath;
    }
    
    /**
     * Getter for the input values.
     * @return the input values
     */
    public String getInputValues() {
        return inputValues;
    }
    
    /**
     * Setter for the input values.
     * @param inputValues the input values to set
     */
    public void setInputValues(String inputValues) {
        this.inputValues = inputValues;
    }
    
    /**
     * Getter for the help request flag.
     * @return true if help is requested, false otherwise
     */
    public boolean isHelpRequested() {
        return helpRequested;
    }
    
    /**
     * Setter for the help request flag.
     * @param helpRequested the help request flag to set
     */
    public void setHelpRequested(boolean helpRequested) {
        this.helpRequested = helpRequested;
    }
    
    /**
     * Create command-line options.
     * @return the Options object containing the options
     */
    public Options createOptions() {
        Options options = new Options();
        
        options.addOption(Option.builder("h").longOpt("help")
                .desc("Show the help page")
                .build());

        options.addOption(Option.builder("i").longOpt("ipath")
                .desc("Set a path for a data input\n file. It must work with -t SQRT\n and -o options together. e.g.,\n -t SQRT -i file.csv -o\n output.csv")
                .hasArg()
                .argName("A data file path to read")
                .build());

        options.addOption(Option.builder("o").longOpt("opath")
                .desc("Set a path for a data output\n file.\n")
                .hasArg()
                .argName("A data file path to write")
                .build());

        options.addOption(Option.builder("t").longOpt("task")
                .desc("Set a task. The -t or -i options\n must be set as well.\n")
                .hasArg()
                .argName("A task name")
                .required()
                .build());

        options.addOption(Option.builder("v").longOpt("values")
                .desc("Set input values (separator:\n space), e.g. \"23 21 2\"")
                .hasArg()
                .argName("Input values for a task.")
                .build());
        return options;
    }
    
    /**
     * Parse the command-line options and set the corresponding fields.
     * @param options the Options object containing the available options
     * @param args the command-line arguments to parse
     * @return true if parsing is successful, false otherwise
     */
    public boolean parseOptions(Options options, String[] args) {
        
        CommandLineParser parser = new DefaultParser();
        
        try {
            CommandLine cmd = parser.parse(options, args);
            
            if(cmd.hasOption("h")) {
                setHelpRequested(true);
                return true;
            }
            if(cmd.getOptionValue("t") == null) {
                throw new NullPointerException();
            }
            setTask(cmd.getOptionValue("t"));
            
            if((cmd.hasOption("i") ^ cmd.hasOption("o")) || cmd.hasOption("v") == cmd.hasOption("i"))
                throw new InvalidCommandException();
            
            if(cmd.hasOption("i")) {
                String tempString = getTask().toUpperCase();
                
                if(!tempString.equals("SQRT"))
                    throw new InvalidCommandException();
                
                if(cmd.getOptionValue("i") == null || cmd.getOptionValue("o") == null)
                    throw new NullPointerException();
                else {
                    setDataInputFilePath(cmd.getOptionValue("i"));
                    setDataOutputFilePath(cmd.getOptionValue("o"));
                }
            }
            
            if(cmd.hasOption("v")) {
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
     * Print the help page.
     * @param options the Options object containing the available options
     */
    public void printHelp(Options options) {
        
        HelpFormatter formatter = new HelpFormatter();
        String header = "Math Calculator";
        String footer ="\nThis is the 2023-1 HW4 help page.\n\n";
        formatter.printHelp("calculator", header, options, footer, true);
    }
}
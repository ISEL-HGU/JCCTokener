package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * The OptionHandler class represents a handler for options that can be passed to a program.
 * It provides properties to store various options and their values.
 */
public class OptionHandler {
    private String task;
    private String dataInputFilePath;
    private String dataOutputFilePath;
    private String inputValues;
    private boolean helpRequested;

    /**
     * Get the value indicating whether help was requested.
     * 
     * @return A boolean value indicating whether help was requested.
     */
    public boolean getHelpRequested() {
        return helpRequested;
    }

    /**
     * Set the value indicating whether help was requested.
     * 
     * @param helpRequested A boolean value indicating whether help was requested.
     */
    public void setHelpRequested(boolean helpRequested) {
        this.helpRequested = helpRequested;
    }

    /**
     * Get the task.
     * 
     * @return A string representing the task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Set the task.
     * 
     * @param task A string representing the task.
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Get the data input file path.
     * 
     * @return A string representing the data input file path.
     */
    public String getDataInputFilePath() {
        return dataInputFilePath;
    }

    /**
     * Set the data input file path.
     * 
     * @param dataInputFilePath A string representing the data input file path.
     */
    public void setDataInputFilePath(String dataInputFilePath) {
        this.dataInputFilePath = dataInputFilePath;
    }

    
    /**
     * Get the data output file path.
     * 
     * @return A string representing the data output file path.
     */

    public String getDataOutputFilePath() {
        return dataOutputFilePath;
    }

    /**
     * Set the data output file path.
     * 
     * @param dataOutputFilePath A string representing the data output file path.
     */	
    public void setDataOutputFilePath(String dataOutputFilePath) {
        this.dataOutputFilePath = dataOutputFilePath;
    }

    /**
     * Get the input values.
     * 
     * @return A string representing the input values.
     */
    public String getInputValues() {
        return inputValues;
    }

    /**
     * Set the input values.
     * 
     * @param inputValues A string representing the input values.
     */
    public void setInputValues(String inputValues) {
        this.inputValues = inputValues;
    }

    /**
     * Create the options for the command-line interface.
     * 
     * @return An instance of the Options class containing the defined options.
     */
    public static Options createOptions() {
        Options options = new Options();

        options.addOption(Option.builder("h").longOpt("help")
                .desc("Show the help page")
                .build());

        options.addOption(Option.builder("i").longOpt("ipath")
                .desc("Set a path for a data input file. It must work with -t SQRT and -o options together. e.g., -t SQRT -i file.csv -o output.csv")
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
                .required()
                .build());

        options.addOption(Option.builder("v").longOpt("values")
                .desc("Set input values (separator: space), e.g. \"23 21 2\"")
                .hasArg()
                .argName("Input values for a task.")
                .build());

        return options;
    }



    /**
     * Parse the command-line options.
     * 
     * @param options       An instance of the Options class containing the defined options.
     * @param args          The command-line arguments.
     * @param optionHandler An instance of the OptionHandler class to store the parsed options.
     * @return              A boolean value indicating whether the options were parsed successfully.
     */
    public static boolean parseOptions(Options options, String[] args, OptionHandler optionHandler) {
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            optionHandler.setTask(cmd.getOptionValue("t"));
            optionHandler.setDataInputFilePath(cmd.getOptionValue("i"));
            optionHandler.setDataOutputFilePath(cmd.getOptionValue("o"));
            optionHandler.setInputValues(cmd.getOptionValue("v"));
            optionHandler.setHelpRequested(cmd.hasOption("h"));

        } catch (ParseException e) {
            printHelp(options);
            return false;
        }

        return true;
    }

    /**
     * Print the help page.
     * 
     * @param options An instance of the Options class containing the defined options.
     */
    public static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "Math Calculator";
        String footer = "\nThis is the 2023-1 HW5 help page.\n";
        formatter.printHelp("calculator", header, options, footer, true);
    }
}

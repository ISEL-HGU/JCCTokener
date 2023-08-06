package edu.handong.csee.java.hw5.clioptions;

import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.cli.*;

/**
 * The OptionHandler class is responsible for handling command line arguments using the Apache Commons CLI library.
 */
public class OptionHandler{
    
	/**
     * This field represents the task requested by the user.
     */
    private String task;

    /**
     * This field represents the path to the data input file.
     */
    private String dataInputPath;

    /**
     * This field represents the path to the data output file.
     */
    private String dataOutputFilePath;

    /**
     * This field represents the input values given by the user.
     */
    private String[] inputValues;

    /**
     * This field represents whether the help option was requested by the user or not.
     */
    private boolean helpRequested;

    /**
     * This method returns a boolean indicating whether the help option was requested or not.
     * @return A boolean value indicating whether the help option was requested.
     */
    public boolean getHelpRequested() {
        return helpRequested;
    }
    
    /**
     * This method returns the task requested by the user.
     * @return A String representing the task requested by the user.
     */
    public String getTask() {
        return task;
    }

    /**
     * This method returns the path to the data input file.
     * @return A String representing the path to the data input file.
     */
    public String getDataInputPath() {
        return dataInputPath;
    }

    /**
     * This method returns the path to the data output file.
     * @return A String representing the path to the data output file.
     */
    public String getDataOutputFilePath() {
        return dataOutputFilePath;
    }

    /**
     * This method returns the input values provided by the user.
     * @return An array of Strings representing the input values provided by the user.
     */
    public String[] getInputValues() {
        return inputValues;
    }

    /**
     * This method creates and returns the command line options for the application.
     * @return An Options object representing the command line options for the application.
     */
    public Options createOptions() {
    	Options options = new Options();

        options.addOption(Option.builder("i").longOpt("ipath")
                .desc("Set a path for a data input\r\n"
                		+ "                                          file. It must work with -t SQRT, -t MAX, or -t MIN\r\n"
                		+ "                                          and -o options together. e.g.,\r\n"
                		+ "                                          -t SQRT -i file.csv -o\r\n"
                		+ "                                          output.csv\r\n"
                		+ "")
                .hasArg()
                .argName("A data file/directory path to read")
                .required(false)
                .build());

        options.addOption(Option.builder("o").longOpt("opath")
                .desc("Set a path for a data output\r\n"
                		+ "                                          file.\r\n"
                		+ "")
                .hasArg()
                .argName("A data file path to write")
                .required(false)
                .build());

        options.addOption(Option.builder("t").longOpt("task")
                .desc("Set a task. The -t or -i options\r\n"
                		+ "                                          must be set as well.\r\n"
                		+ "")
                .hasArg()
                .argName("A task name")
                .required()
                .build());

        options.addOption(Option.builder("v").longOpt("values")
                .desc("Set input values (separator:\r\n"
                		+ "                                          space), e.g. \"23 21 2\"\r\n"
                		+ "")
                .hasArg()
                .argName("Input values for a task.")
                .required(false)
                .build());

        options.addOption(Option.builder("h").longOpt("help")
                .desc("Show the help page")
                .build());

        return options;
    }
    
    /**
     * This method parses the command line arguments and sets the corresponding fields.
     * @param options The command line options for the application.
     * @param args The command line arguments provided by the user.
     * @return A boolean value indicating whether the parsing was successful.
     */
    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            
            if (cmd.hasOption("i") && cmd.hasOption("v")) {
                throw new IllegalArgumentException();
            }
            
            if (!cmd.hasOption("i") && !cmd.hasOption("v")) {
                throw new IllegalArgumentException();
            }
            if (!(cmd.hasOption("i") ^ cmd.hasOption("v"))) {
                throw new IllegalArgumentException();
            }
            
            if (!cmd.hasOption("t")) {
                throw new IllegalArgumentException();
            }
           
            if (cmd.hasOption("i")) {
                dataInputPath = cmd.getOptionValue("i");
                if(dataInputPath != null && !Files.exists(Path.of(dataInputPath))) {
                    printHelp(options);
                    return false;
                }
            }
            
            if (cmd.hasOption("v")) {
                inputValues = cmd.getOptionValue("v").split("\\s+");
            }
            dataOutputFilePath = cmd.getOptionValue("o");
            task = cmd.getOptionValue("t");
            helpRequested = cmd.hasOption("h");
        } catch (Exception e) {
            printHelp(options);
            return false;
        }
        return true;
    }
    
    /**
     * This method prints the help page for the application.
     * @param options The command line options for the application.
     */
    public void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "Math Calculator";
        String footer = "\nThis is the 2023-1 HW5 help page.";
        formatter.printHelp("calculator", header, options, footer, true);
    }
}
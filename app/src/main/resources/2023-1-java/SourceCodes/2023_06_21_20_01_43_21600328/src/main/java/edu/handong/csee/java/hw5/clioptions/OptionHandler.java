package edu.handong.csee.java.hw5.clioptions;
import org.apache.commons.cli.*;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
/**
 * This code shows help message when the input is typed in wrong
 */
public class OptionHandler {
    /**
     * make task as private
     */
    private String task;
    /**
     * make dataInputFilePath to give words for txt file
     */
    private String dataInputFilePath;
    /**
     * make dataOutputFilePath to give words for txt file
     */
    private String dataOutputFilePath;
    /**
     * make inputValues to read input value
     */
    private String inputValues;
    /**
     * making helpRequested to see if the word typed in wrong
     */
    private boolean helpRequested;
    /**
     * makes messages that will be sent to printHelp
     */
    public Options createOptions() {
        Options options = new Options();
        options.addOption("h", "help", false, "Show the help page");
        options.addOption("i", "ipath", true, "Set a path for a data input file. It must work with -t SQRT, -t MAX, or -t MIN\r\n and -o options together. e.g.,\r\n -t SQRT -i file.csv -o output.csv\r\n");
        options.addOption("o", "opath", true, "Set a path for a data output file.");
        options.addOption("t", "task", true, "Set a task. The -t or -i options must be set as well.");
        options.addOption("v", "values", true, "Set input values (separator: space), e.g., \"23 21 2\"");
        return options;
    }
    /**
     * paresOption reads the argument and selects the options for processing
     */
    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                helpRequested = true;
            }
            if (cmd.hasOption("i")) {
                dataInputFilePath = cmd.getOptionValue("i");
            }
            if (cmd.hasOption("o")) {
                dataOutputFilePath = cmd.getOptionValue("o");
            }
            if (cmd.hasOption("t")) {
                task = cmd.getOptionValue("t");
            }
            if (cmd.hasOption("v")) {
                inputValues = cmd.getOptionValue("v");
            }
        } catch (ParseException e) {
            System.err.println("Failed to parse command line options. Reason: " + e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * This prints all the options user can use
     */
    public void printHelp(Options options) {
        System.out.println("usage: calculator [-h] [-i <A data file/directory path to read>] [-o <A data file path to write>] -t <A task name> [-v <Input values for a task.>]");
        System.out.println("Math Calculator");
        for (Option option : options.getOptions()) {
            String opt = option.getOpt();
            String longOpt = option.getLongOpt();
            String description = option.getDescription();
            String argName = option.getArgName();
            System.out.println(String.format("  %s,--%s <%s>    %s", (opt != null ? "-" + opt : ""), longOpt, argName, description));
        }
        System.out.println();
        System.out.println("This is the 2023-1 HW5 help page.");
    }
    /**
     * making getter for encapsulation for getHelpRequested
     */
    public boolean getHelpRequested() {
        return helpRequested;
    }
    /**
     * making setter for encapsulation for getTask
     */
    public String getTask() {
        if (task == null) {
            return "";
        }
        return task;
    }
    /**
     * making getter for encapsulation for getDataInputFilePath
     */
    public String getDataInputFilePath() {
        return dataInputFilePath;
    }
    /**
     * making getter for encapsulation for getDataOutputFilePath
     */
    public String getDataOutputFilePath() {
        return dataOutputFilePath;
    }
    /**
     * making getter for encapsulation for getInputValues
     */
    public String getInputValues() {
        return inputValues;
    }
    /**
     * making setter for encapsulation for setTask
     */
    public void setTask(String task) {
        this.task = task;
    }
    /**
     * making setter for encapsulation for setDataInputFilePath
     */
    public void setDataInputFilePath(String dataInputFilePath) {
        this.dataInputFilePath = dataInputFilePath;
    }
    /**
     * making setter for encapsulation for setDataOutputFilePath
     */
    public void setDataOutputFilePath(String dataOutputFilePath) {
        this.dataOutputFilePath = dataOutputFilePath;
    }
    /**
     * making setter for encapsulation for setInputValues
     */
    public void setInputValues(String inputValues) {
        this.inputValues = inputValues;
    }
    /**
     * making setter for encapsulation for setHelpRequested
     */
    public void setHelpRequested(boolean helpRequested) {
        this.helpRequested = helpRequested;
    }
}
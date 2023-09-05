
package edu.handong.csee.java.hw5.clioptions;

import java.io.File;

import org.apache.commons.cli.*;

public class OptionHandler {
    private String task;
    private String dataInputFilePath;
    private String dataOutputFilePath;
    private String inputValues;
    private boolean helpRequested;

    /**
     * Returns whether help was requested.
     */
    public boolean getHelpRequested() {
        return helpRequested;
    }

    /**
     * Sets whether help was requested.
     */
    public void setHelpRequested(boolean helpRequested) {
        this.helpRequested = helpRequested;
    }

    /**
     * Returns the task value.
     */
    public String getTask() {
        return task;
    }

    /**
     * Sets the task value.
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Returns the input values.
     */
    public String getInputValues() {
        return inputValues;
    }

    /**
     * Sets the input values.
     */
    public void setInputValues(String inputValues) {
        this.inputValues = inputValues;
    }

    /**
     * Returns the data input file path.
     */
    public String getDataInputFilePath() {
        return dataInputFilePath;
    }

    /**
     * Sets the data input file path.
     * 
     * param dataInputFilePath the data input file path to set.
     */
    public void setDataInputFilePath(String dataInputFilePath) {
        this.dataInputFilePath = dataInputFilePath;
    }

    /**
     * Returns the data output file path.
     * 
     * return the data output file path.
     */
    public String getDataOutputFilePath() {
        return dataOutputFilePath;
    }

    /**
     * Sets the data output file path.
     * 
     * param dataOutputFilePath the data output file path to set.
     */
    public void setDataOutputFilePath(String dataOutputFilePath) {
        this.dataOutputFilePath = dataOutputFilePath;
    }

    /**
     * This method creates command-line options.
     */
    public Options createOptions() {
        Options options = new Options();

        options.addOption(Option.builder("h")
                .longOpt("help")
                .desc("Show the help page")
                .build());

        options.addOption(Option.builder("t")
                .longOpt("task")
                .desc("Set a task. The -t or -i options must be set as well.")
                .hasArg()
                .argName("A task name")
                .required()
                .build());

        options.addOption(Option.builder("v")
                .longOpt("values")
                .desc("Set input values (separator: space), e.g. \"23 21 2\"")
                .hasArg()
                .argName("Input values for a task.")
                .build());
        options.addOption(Option.builder("i")
                .longOpt("ipath")
                .desc("Set a path for a data input file. It must work with -t SQRT and -o options together. e.g., -t SQRT -i file.csv -o output.csv")
                .hasArg()
                .argName("A data file path to read")
                .build());

        options.addOption(Option.builder("o")
                .longOpt("opath")
                .desc("Set a path for a data output file.")
                .hasArg()
                .argName("A data file path to write")
                .build());

        return options;
    }

    /**
     * This method parses command-line options and sets variables accordingly.
     * It also handles some error situations, such as missing required options or
     * invalid file paths.
     */
    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                setHelpRequested(true);
                return false;
            }

            if (!cmd.hasOption("t")) {
                setHelpRequested(true);
                return false;
            }
            if (cmd.hasOption("v") && cmd.hasOption("i")) {
                setHelpRequested(true);
                return false;
            }
            task = cmd.getOptionValue("t").toUpperCase();
            if (cmd.hasOption("v")) {
                inputValues = cmd.getOptionValue("v").replace("'", "");
            }
            if (cmd.hasOption("i")) {
                dataInputFilePath = cmd.getOptionValue("i");

                File inputFile = new File(dataInputFilePath);
                if (!inputFile.exists()) {
                    setHelpRequested(true);
                    return false;
                }
            }
            if (cmd.hasOption("o")) {
                dataOutputFilePath = cmd.getOptionValue("o");
            }

        } catch (ParseException e) {
            setHelpRequested(true);
            return false;
        }
        return true;
    }

    /**
     * This method prints a help message that includes a list of available
     * command-line options.
     */
    public void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "Math Calculator";
        String footer = "\nThis is the 2023-1 HW4 help page.";
        formatter.printHelp("calculator", header, options, footer, true);
    }
}

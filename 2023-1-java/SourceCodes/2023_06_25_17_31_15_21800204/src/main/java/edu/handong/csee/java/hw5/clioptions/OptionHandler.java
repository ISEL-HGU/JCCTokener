package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.*;

public class OptionHandler {

    private String task;
    private String dataInputFilePath;
    private String dataOutputFilePath;
    private String inputValues;
    private boolean helpRequested;

    public boolean getHelpRequested(){
        return helpRequested;
    }

    public void setHelpRequested(boolean helpRequested){
        this.helpRequested = helpRequested;
    }

    public String getTask(){
        return task;
    }

    public void setTask(String task){
        this.task = task;
    }

    public String getDataInputFilePath(){
        return dataInputFilePath;
    }

    public void setDataInputFilePath(String dataInputFilePath){
        this.dataInputFilePath = dataInputFilePath;
    }

    public String getDataOutputFilePath(){
        return dataOutputFilePath;
    }

    public void setDataOutputFilePath(String dataOutputFilePath){
        this.dataOutputFilePath = dataOutputFilePath;
    }

    public String getInputValues(){
        return inputValues;
    }

    public void setInputValues(String inputValues){
        this.inputValues = inputValues;
    }

    public Options createOptions(){
        Options options = new Options();

        options.addOption(Option.builder("h").longOpt("help")
                .desc("Show the help page").build());
        options.addOption(Option.builder("i").longOpt("ipath")
                .desc("Set a path for a data input file/directory. It must work with -t SQRT, -t MAX, or -t MIN and -o options together. e.g., -t SQRT -i file.csv -o output.csv")
                .hasArg()
                .argName("A data file/directory path to read").build());
        options.addOption(Option.builder("o").longOpt("opath")
                .desc("Set a path for a data output file.")
                .hasArg()
                .argName("A data file path to write").build());
        options.addOption(Option.builder("t").longOpt("task")
                .desc("Set a task. The -t or -i options must be set as well.")
                .hasArg()
                .argName("A task name").build());
        options.addOption(Option.builder("v").longOpt("values")
                .desc("Set input values (separator: space), e.g. \"23 21 2\"")
                .hasArg()
                .argName("Input values for a task.").build());

        return options;
    }



    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            helpRequested = cmd.hasOption("h");
            if (helpRequested) return false;

            if (cmd.hasOption("t")) {
                task = cmd.getOptionValue("t");
            } else {
                printHelp(options);
                System.exit(0);
            }
            if (cmd.hasOption("i")) dataInputFilePath = cmd.getOptionValue("i");
            if (cmd.hasOption("o")) dataOutputFilePath = cmd.getOptionValue("o");
            if (cmd.hasOption("v")) inputValues = cmd.getOptionValue("v");

            return true;
        } catch (ParseException e) {
            printHelp(options);
            return false;
        }
    }


    public void printHelp(Options options){
        HelpFormatter formatter = new HelpFormatter();
        String header = "Math Calculator";
        String footer = "\nThis is the 2023-1 HW5 help page.";
        formatter.printHelp("calculator", header, options, footer, true);
    }
}

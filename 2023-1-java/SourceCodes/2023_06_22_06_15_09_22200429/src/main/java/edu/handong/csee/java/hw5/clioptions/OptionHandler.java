package edu.handong.csee.java.hw5.clioptions;


import java.util.ArrayList;

import org.apache.commons.cli.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.fileutil.FileManager;

/**
 * It is a class that has the main functions of the option
 */
public class OptionHandler {
	
    private String task;
    private String dataInputFilePath;
    private String dataOutputFilePath;
    private String inputValues;
    private boolean helpRequested;

    /**
     * It's a method that returns help
     */
    public boolean getHelpRequested() {
        return helpRequested || (task == null && dataInputFilePath == null && dataOutputFilePath == null && inputValues == null);
    }

/**
 * This is a method for setting the help function of the option
 */
public void setHelpRequested(boolean helpRequested) {
        this.helpRequested = helpRequested;
    }
    
    /**
     * This method is return task.
     */
    public String getTask() throws InvalidCommandException {
        if (task == null) {
            throw new InvalidCommandException("");
        }
        return task;
    }

    /**
     * This method is set task.
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * this method is return inputPath
     */
    public String getDataInputFilePath() {
        return dataInputFilePath;
    }
    
    /**
     * This method is set input data file path.
     */
    public void setDataInputFilePath(String dataInputFilePath) {
        this.dataInputFilePath = dataInputFilePath;
    }
    
    /**
     * This method is return output file path.
     */
    public String getDataOutputFilePath() {
        return dataOutputFilePath;
    }
    
    /**
     * This method is set output file path.
     */
    public void setDataOutputFilePath(String dataOutputFilePath) {
        this.dataOutputFilePath = dataOutputFilePath;
    }
    
    /**
     * This method is return inputValues.
     */
    public String getInputValues() {
        return inputValues;
    }	


    /**
     * This method is set InputValues to use inputValues.
     */
    public void setInputValues(String inputValues) {
      this.inputValues = inputValues;
    }
    
    /**
     *This method is create our option, and return option. 
     */
    public Options createOptions() {
        Options options = new Options();
        
        options.addOption(Option.builder("h").longOpt("help")
                .desc("Show the help page")
                .build());

        options.addOption(Option.builder("i").longOpt("ipath")
                .desc("Set a path for a data input"
                		+ "file. It must work with -t SQRT, -t MAX, or -t MIN"
                		+ " and -o options together. e.g.,"
                		+ "  -t SQRT -i file.csv -o"
                		+ "  output.csv"
                		+ "")
                .hasArg()
                .argName("A data file/directory path to read")
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
                .desc("Set input values (separator:"
                		+ "space), e.g. \"23 21 2\""
                		+ "")
                .hasArg()
                .argName("Input values for a task.")
                .build());

        return options;
    }

    /**
     *This method is boolean parse option
     */
    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            return false;
        }

        if (cmd.hasOption("h") || args.length == 0) {
            helpRequested = true;
        } else {
            if (cmd.hasOption("t")) {
                task = cmd.getOptionValue("t");
            }


            if (cmd.hasOption("i")) {
                dataInputFilePath = cmd.getOptionValue("i");
                ArrayList<String> lines = FileManager.readLinesFromATxtFile(dataInputFilePath);
            }

            
            if (cmd.hasOption("o")) {
                dataOutputFilePath = cmd.getOptionValue("o");
                System.out.println("The " + dataOutputFilePath + " file has been successfully written.");

                ArrayList<String> lines = FileManager.readLinesFromATxtFile(dataInputFilePath);
                FileManager.writeAtxtFile(dataOutputFilePath, lines);
            }

            if (cmd.hasOption("v")) {
                inputValues = cmd.getOptionValue("v");
            }
        }

        return true;
    }





    /**
     * This method is made Help and print help.
     */
    public void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        String header = "calculator [-h] [-i <A data file/directory path to read>] [-o <A data file"
        		+ "       path to write>] -t <A task name> [-v <Input values for a task.>]"
        		+ ""
        		+ "Math Calculator";
        String footer = "\nThis is the 2023-1 HW5 help page.";

        formatter.printHelp(header, options);
        System.out.println(footer);
      	
        
    }
    
    /**
     * This method is get Operands.
     */
	public Object getOperands() {
		
		return null;
	}
}

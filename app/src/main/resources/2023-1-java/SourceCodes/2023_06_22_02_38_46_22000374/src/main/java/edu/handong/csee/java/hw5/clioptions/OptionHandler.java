package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
/**
 * OptionHandler.java
 * Cli Option
 */

public class OptionHandler {
	private String task;
	private String dataInputFilePath;
	private String dataOutFilePath;
	private String inputValues;
	private boolean helpRequested;
	
	/**
     * This method return helpRequested.
     * 
     * @return helpRequested.
     */
	public boolean getHelpRequested(){
		return helpRequested;
	}
    /**
     * @param helpRequested Type : boolean
     */
	public void setHelpRequested(boolean helpRequested){
		this.helpRequested = helpRequested;
	}
	/**
     * This method return task.
     * 
     * @return task.
     */
	public String getTask(){
		return task;
	}
    /**
     * @param task Type : String
     */
	public void setTask(String task){
		this.task = task;
	}
	/**
     * This method return dataInputFilePath.
     * 
     * @return dataInputFilePath.
     */
	public String getDataInputFilePath(){
		return dataInputFilePath;
	}
    /**
     * @param dataInputFilePath Type : String
     */
	public void setDataInputFilePath(String datalInputFilePath){
		this.dataInputFilePath = datalInputFilePath;
	}
	/**
     * This method return dataOutFilePath.
     * 
     * @return dataOutFilePath.
     */
	public String getDataOutFilePath(){
		return dataOutFilePath;
	}
    /**
     * @param dataOutFilePath Type : String
     */
	public void setDataOutFilePath(String datalOutFilePath){
		this.dataOutFilePath = datalOutFilePath;
	}
	/**
     * This method return inputValues.
     * 
     * @return inputValues.
     */
	public String getInputValues(){
		return inputValues;
	}
    /**
     * @param inputValues Type : String
     */
	public void setInputValues(String inputValues){
		this.inputValues = inputValues;
	}
	/**
     * This method is for making Cli Options.
     * 
     * @return options
     */
	public Options createOptions(){
		Options options = new Options();
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show the help page")
		        .build());
		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("ipath")
		        .desc("Set a path for a data input file. It must work with -t SQRT, -t MAX, or -t MIN and -o options together. e.g., -t SQRT -i file.csv -o output.csv")
        		.hasArg()
				.argName("A data file/directory path to read")
		        .build());
		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("opath")
		        .desc("Set a path for a data output file.")
        		.hasArg()
				.argName("A data file path to write")
		        .build());
		// add options by using OptionBuilder
		options.addOption(Option.builder("t").longOpt("task")
		        .desc("Set a task. The -t or -i options must be set as well.")
        		.hasArg()
				.argName("A task name")
				.required()
		        .build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("values")
				.desc("Set input values (separator: space), e.g. \"23 21 2\"")
				.hasArg()
				.argName("Input values for a task.")
				//.required()
				.build());

		return options;
	}
	/**
     * This method return is for checking that Cli options is right.
     * 
     * @return true or fasle
     */
	public boolean parseOptions(Options options, String[] args){
		
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
			
			helpRequested = cmd.hasOption("h");
			//dataInputFilePath = cmd.getOptionValue("i");
			dataOutFilePath = cmd.getOptionValue("o");
			task = cmd.getOptionValue("t");
			inputValues = cmd.getOptionValue("v");
			
			if (cmd.hasOption("i")) {
	            dataInputFilePath = cmd.getOptionValue("i");
	            File dir = new File(dataInputFilePath);
	            File[] csvFiles = dir.listFiles(new FilenameFilter() {
	                @Override
	                public boolean accept(File dir, String name) {
	                    return name.toLowerCase().endsWith(".csv");
	                }
	            });

	            if (csvFiles != null && csvFiles.length > 0) {
	                for (File csvFile : csvFiles) {
	                    Thread thread = new Thread(new Runnable() {
	                        @Override
	                        public void run() {	                        	
	                            dataInputFilePath = csvFiles[0].getAbsolutePath();
	                        }
	                    });
	                    thread.start();
	                }
	            } else {
	                return false;
	            }
	        }
		}catch (Exception e) {
    			printHelp(options);
    			return false;
    		}

                                 		

		return true;
		
	}
	/**
     * This method return is for printing Cli options.
     */
	public void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Math Calculator";
		String footer ="\nThis is the 2023-1 HW5 help page.";
		formatter.printHelp("calculator", header, options, footer, true);
	}
	
}

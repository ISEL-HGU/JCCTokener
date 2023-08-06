package edu.handong.csee.java.hw5.clioptions;

import java.lang.Object;
import org.apache.commons.cli.*;
import org.apache.commons.csv.*;
import java.util.ArrayList;
import java.io.File;
import edu.handong.csee.java.hw5.fileutil.FileManager;
import edu.handong.csee.java.hw5.clioptions.*;
import edu.handong.csee.java.hw5.thread.*;
/**
 * This is a class that control options.
 * @author seogyeongmi
 *
 */
public class OptionHandler {
	private String task;
	private String dataInputFilePath;
	private String dataOutputFilePath;
	private String inputValues;
	private boolean helpRequested;
	/**
	 * This is main function of OptionHandler.
	 * @param args
	 */
	public static void main(String[] args) {
		OptionHandler op = new OptionHandler();
		op.run(args);
	}
	/**
	 * This method help handle option for OptionHandler.
	 * @param args
	 */
	public void run(String[] args) {
		//FileManager fm = new FileManager();
		//fm.run(args);
		
		Options options = createOptions();

		if (parseOptions(options, args)) {
			if (helpRequested) {
				printHelp(options);
				System.exit(0);
			} else if ((dataInputFilePath == null & dataOutputFilePath == null) && inputValues == null) {
				printHelp(options);
				System.exit(0);
			} else if ((dataInputFilePath != null & dataOutputFilePath != null) && inputValues != null) {
				printHelp(options);
				System.exit(0);
			} else if ((dataInputFilePath != null && dataOutputFilePath == null)
					|| (dataInputFilePath == null & dataOutputFilePath != null)) {
				printHelp(options);
				System.exit(0);
			} else if (!task.equals("SQRT") && !task.equals("MIN") && !task.equals("MAX")) {
				if (dataInputFilePath != null || dataOutputFilePath != null) {
					printHelp(options);
					System.exit(0);
				}
			}else {
				if (dataInputFilePath != null && dataOutputFilePath != null) {
					File f = new File(dataInputFilePath);
					if (f.isFile()) {
			        	ArrayList<String> lines = FileManager.readLinesFromATextFile(dataInputFilePath);
			        	ArrayList<String> resultLines = FileManager.calculateSquareRoot(lines);
			        	FileManager.writeAtxtFile(dataOutputFilePath, resultLines);
					}else if (f.isDirectory()) {
						File directory = new File(dataInputFilePath);
				        File[] files = directory.listFiles();

				        if (files != null) {
				            for (File file : files) {
				                if (file.isFile() && file.getName().endsWith(".csv")) {
				                    String fileName = file.getName();
				                    String inputFilePath = file.getAbsolutePath();
				                    //String outputFilePath = "output_directory/" + fileName;
				                    //CSVFileCalculator calculator = new CSVFileCalculator(inputFilePath, outputFilePath);
				                    //Thread thread = new Thread(calculator);
				                    //thread.start();
				                }
				            }
				        }
	               System.exit(0);
	            } else {
	                printHelp(options);
	                System.exit(0);
	            }
			}
		}
	}
		
}
	/**
	 * This method create options.
	 * @return
	 */
	public Options createOptions() {
		Options options = new Options();
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show the help page")
		        .build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("values")
				.desc("Set input values\n"
						+ "                                                   (separator: space),\n"
						+ "                                                   e.g. \"23 21 2\"")
				.hasArg()     // this option is intended not to have an option value but just an option
				.argName("Input values for a task.")
				//.required() // this is an optional option. So disabled required().
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("t").longOpt("task")
				.desc("Set a task. The -t or\n"
						+ "                                                   -i options must be set\n"
						+ "                                                   as well.")
				.hasArg()
				.argName("A task name")
				.required()
				.build());
		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("ipath")
				.desc("Set a path for a data\n"
						+ "                                                   input file. It must\n"
						+ "                                                   work with -t SQRT, -t\n"
						+ "                                                   MAX, or -t MIN and -o\n"
						+ "                                                   options together. e.g.,\n"
						+ "                                                   -t SQRT -i file.csv -o\n"
						+ "                                                   output.csv")
				.hasArg()
				.argName("A data file/directory path to read")
				.build());		
		
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("opath")
				.desc("Set a path for a data\n"
						+ "                                                   output file.")
				.hasArg()
				.argName("A data file path to write")
				.build());
	
		return options;
	}
	/**
	 * This method is parse options.
	 * @param options
	 * @param args
	 * @return
	 */
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, args);
			this.task = cmd.getOptionValue("t").toUpperCase();
			
			this.dataInputFilePath = cmd.getOptionValue("i");
			this.dataOutputFilePath = cmd.getOptionValue("o");
			if(this.dataInputFilePath==null && this.dataOutputFilePath==null)
				this.inputValues = cmd.getOptionValue("v");
			this.helpRequested = cmd.hasOption("h");
			
		} catch (Exception e) {
			return false;
		}

		return true;
	}
	/**
	 * This method print help messege.
	 * @param options
	 */
	public void printHelp(Options options) {
		
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		//String header = "usage: calculator [-h] [-i <A data file path to read>] [-o <A data file\n" + "       path to write>] -t <A task name> [-v <Input values for a task.>]\n";
		String header = "Math Calculator";
		String footer ="\nThis is the 2023-1 HW5 help page.";
		formatter.printHelp("calculator", header, options, footer, true);
	}	
	/**
	 * This is getter of helpRequested.
	 * @return
	 */
	public boolean getHelpRequested() {
		return this.helpRequested;
	}
	/**
	 * This is setter of helpRequested.
	 * @param hr
	 */
	public void setHelpRequested(boolean hr) {
		this.helpRequested = hr;
	}
	/**
	 * This is getter of task.
	 * @return
	 */
	public String getTask() {
		return task;
	}
	/**
	 * This is setter of task.
	 * @param t
	 */
	public void setTask(String t) {
		this.task = t.toUpperCase();
	}
	/**
	 * This is getter of dataInputFilePath.
	 * @return
	 */
	public String getDataInputFilePath() {
		return this.dataInputFilePath;
	}
	/**
	 * This is setter of dataInputFilePath.
	 * @param path
	 */
	public void setDataInputFilePath(String path) {
		this.dataInputFilePath = path;
	}
	/**
	 * This is getter of dataOutputFilePath.
	 * @return
	 */
	public String getDataOutputFilePath() {
		return this.dataOutputFilePath;
	}
	/**
	 * This is setter of dataOutputFilePath.
	 * @param path
	 */
	public void setDataOutputFilePath(String path) {
		this.dataOutputFilePath = path;
	}
	/**
	 * This is getter of inputValues.
	 * @return
	 */
	public String getInputValues() {
		return this.inputValues;
	}
	/**
	 * This is setter of inputValues
	 * @param v
	 */
	public void setInputValues(String v) {
		this.inputValues = v;
	}

}

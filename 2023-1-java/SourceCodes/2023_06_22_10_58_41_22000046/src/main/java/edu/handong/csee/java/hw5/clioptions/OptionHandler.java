package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.* ;
import java.io.File ;
import java.io.FileNotFoundException; 

/**
 * OptionHandler class provides methods for handling command-line options and storing their values.
 */
public class OptionHandler {
	private String task ; 
	private String dataInputFilePath ; 
	private String dataOutputFilePath ; 
	private String inputValues ;
	private boolean helpRequested ; 
	
	/**
	 * Return the value where the help option is requested
	 * @return getHelpRequested 
	 */
	public boolean getHelpRequested() 
	{
		return this.helpRequested ;
	}
	/**
	 * Sets the value for which the help option is requested
	 * @param request
	 */
	public void setHelpRequested(boolean request) 
	{
		this.helpRequested = request ; 
	}
	/**
	 * Return the value of task
	 * @return task
	 */
	public String getTask() 
	{
		return this.task ;
	}
	/**
	 * Set the value of task
	 * @param taskName
	 */
	public void setTask(String taskName) {
		this.task = taskName ; 
	}
	/**
	 * Return the dataInputFilePath
	 * @return dataInputFilePath
	 */
	public String getDataInputFilePath() 
	{
		return this.dataInputFilePath ;
	}
	/**
	 * Set the value of dataInputFilePath
	 * @param filePath
	 */
	public void setDataInputFilePath(String filePath) 
	{
		this.dataInputFilePath = filePath ;
	}
	/**
	 * Return the value of dataOutputFilePath
	 * @return
	 */
	public String getDataOutputFilePath() 
	{
		return this.dataOutputFilePath ;
	}
	/**
	 * Set the value of dataOutputFilePath
	 * @param filePath
	 */
	public void setDataOutputFilePath(String filePath) 
	{
		this.dataOutputFilePath = filePath ;
	}
	/**
	 * Return the value of inputValues
	 * @return
	 */
	public String getInputValues() 
	{
		return this.inputValues ;
	}
	/**
	 * Set the value of inputvalues 
	 * @param value
	 */
	public void setInputValues(String value) {
		this.inputValues = value ; 
	}
	/**
	 * Creates the command-line options.
	 * @return
	 */
	public Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("h")
				.longOpt("help")
				.desc("Show the help page\r")
				.build());

		options.addOption(Option.builder("i")
				.longOpt("ipath")
				.hasArg()
				.argName("A data file/directory path to read")
				.desc("Set a path for a data input file. It must\r\n"
						+ "                                          work with -t SQRT, -t\r\n"
						+ "                                          MAX, or -t MIN and -o\r\n"
						+ "                                          options together. e.g.,\r\n"
						+ "                                          -t SQRT -i file.csv -o\r\n"
						+ "                                          output.csv")
				.build());

		options.addOption(Option.builder("o")
				.longOpt("opath")
				.hasArg()
				.argName("A data file path to write")
				.desc("Set a path for a data output file.\r\n")
				.build());

		options.addOption(Option.builder("t")
				.longOpt("task")
				.hasArg()
				.argName("A task name")
				.required()
				.desc("Set a task. The -t or -i options must be set\r\n"
						+ "                                          as well.\r\n"
						+ "\n")
				.build());

		options.addOption(Option.builder("v")
				.longOpt("values")
				.hasArg()
				.argName("Input values for a task.")
				.desc("Set input values (separator: space),\r\n"
						+ "                                          e.g. \"23 21 2\"\r\n"
						+ "\r\n")
				.build());

		return options ;
	}
	/**
	 * Parses the command-line options and sets values
	 * @param options
	 * @param args
	 * @return
	 */
	public boolean parseOptions(Options options, String[] args) { // FIXED 
		
		CommandLineParser parse = new DefaultParser() ;
		
		try {
			CommandLine cmd = parse.parse(options, args) ;
			
			helpRequested = cmd.hasOption("h") ;

			if(cmd.hasOption("t")) {
				task = cmd.getOptionValue("t") ;
			}                            
			
			if(cmd.hasOption("i")) {
				dataInputFilePath = cmd.getOptionValue("i") ;
			} 
			
			if(cmd.hasOption("o")) {
				dataOutputFilePath = cmd.getOptionValue("o") ;
			}
			
			if(cmd.hasOption("v")) {
				inputValues = cmd.getOptionValue("v") ;
			}

			if(dataInputFilePath != null){
				if(task.equalsIgnoreCase("sqrt") || task.equalsIgnoreCase("min") || task.equalsIgnoreCase("max")){
					if(!(dataOutputFilePath != null && dataInputFilePath != null)){
						
						throw new Exception() ;
					}
				} else {
						
					throw new Exception() ;
				}
				
				File inputFile = new File(dataInputFilePath) ;
				
				if(!inputFile.exists()){
				
					throw new Exception() ;
				}
			}
			
			if(task != null && dataInputFilePath == null){
					if(inputValues == null){

						throw new FileNotFoundException ();
					}
				}
				
			} 
		catch( Exception e){			
			return false ;
		}
		
		return true ; 
		
	}
	/**
	 * Print the help page 
	 * @param options
	 */
	public void printHelp(Options options) {
		
		HelpFormatter formatter = new HelpFormatter();
		String header = "Math Calculator";
		String footer = "\nThis is the 2023-1 HW5 help page.\r\n"
				+ "";
		formatter.printHelp("calculator", header, options, footer, true);
	}
	
}

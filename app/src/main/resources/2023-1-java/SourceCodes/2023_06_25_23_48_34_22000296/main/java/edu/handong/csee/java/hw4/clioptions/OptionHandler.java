package edu.handong.csee.java.hw4.clioptions;


import org.apache.commons.cli.*;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionHandler {
   private String task = "";

   private String dataInputFilePath = "";

   private String dataOutputFilePath = "";

   private String inputValues = "";

   private boolean helpRequested = false;

   public boolean getHelpRequested() {
      return helpRequested;
   }

   public void setHelpRequested(boolean parameterhelp) {
      helpRequested = parameterhelp;
   }

   public String getTask() {
      return task;
   }

   public void setTask(String parametertask) {
      task = parametertask;
   }

   public String getDataInputFilePath() {
      return dataInputFilePath;
   }

   public void setDataInputFilePath(String parameterFilePath) {
      dataInputFilePath = parameterFilePath;
   }

   public String getDataOutputFilePath() {
      return dataOutputFilePath;
   }

   public void setDataOutputFilePath(String parameterFilePath) {
      dataOutputFilePath = parameterFilePath;
   }

   public String getInputValues() {
      return inputValues;
   }

   public void setInputValues(String parameterInputValues) {
      inputValues = parameterInputValues;
   }

   public Options createOptions(){
      Options options = new Options();
      
      ///////////////////////////////////////////////////
      
      options.addOption(Option.builder("h")
              .longOpt("help")
              .desc("Show the help page")
              .hasArg()
              .argName("")
              .required(false)
              .build());
      
      options.addOption(Option.builder("i")
            .longOpt("ipath")
            .desc("Set a path for a data input\r\n"
            		+ "                                          file. It must work with -t SQRT\r\n"
            		+ "                                          and -o options together. e.g.,\r\n"
            		+ "                                          -t SQRT -i file.csv -o\r\n"
            		+ "                                          output.csv\r\n"
            		+ "")
            .hasArg()
            .argName("A data file path to read")
            .required(false)
            .build());
      
      options.addOption(Option.builder("o")
              .longOpt("opath")
              .desc("Set a path for a data output\r\n"
              		+ "                                          file.\r\n"
              		+ "")
              .hasArg()
              .argName("A data file path to write")
              .required(false)
              .build());
      
      options.addOption(Option.builder("t")
              .longOpt("task")
              .desc("Set a task. The -t or -i options\r\n"
              		+ "                                          must be set as well.\r\n"
              		+ "")
              .hasArg()
              .argName("A task name")
              .required(false)
              .build());
      
      options.addOption(Option.builder("v")
              .longOpt("values")
              .desc("Set input values (separator:\r\n"
              		+ "                                          space), e.g. \"23 21 2\"\r\n"
              		+ "")
              .hasArg()
              .argName("Input values for a task")
              .required(false)
              .build());
      
      return options;
   }

   public boolean parseOptions(Options options, String[] args) {
      CommandLineParser parser = new DefaultParser();
      
      try {
    	  CommandLine cmd = parser.parse(options, args);
    	  
    	  if(cmd.hasOption("i")) {
    		  dataInputFilePath = cmd.getOptionValue("i");
    	  } if(cmd.hasOption("ipath")) {
    		  dataInputFilePath = cmd.getOptionValue("ipath");
    	  }
    	  
    	  helpRequested = cmd.hasOption("h");
    	  
    	  if(cmd.hasOption("o")) {
    		  dataOutputFilePath = cmd.getOptionValue("o");
    	  }
    	  if(cmd.hasOption("opath")) {
    		  dataOutputFilePath = cmd.getOptionValue("opath");
    	  }
    	  
    	  if(cmd.hasOption("t")) {
    		  task = cmd.getOptionValue("t");
    	  } if(cmd.hasOption("task")) {
    		  task = cmd.getOptionValue("task");
    	  }
    	  
    	  if(cmd.hasOption("v")) {
    		  inputValues = cmd.getOptionValue("v");
    	  } if(cmd.hasOption("task")) {
    		  inputValues = cmd.getOptionValue("values");
    	  }
    	  
    	  
    	  
      }catch(Exception e){
    	  System.out.println("parsing error");
    	  System.out.println( e.getMessage());
    	  printHelp(options);
    	  return false;
      }
      return true;
      
   }
	   public void printHelp(Options options) {
		   System.out.println("usage: calculator");
		   	for(Option option : options.getOptions()) {
		   		System.out.print("[-");
		   		System.out.print(option.getOpt());
		   		if(option.getArgName() != "") {
		        	 System.out.print("<" + option.getArgName() + "> ");
		         }
		   		System.out.print("]");
		   	}
		   
		      for(Option option : options.getOptions()) {
		    	 System.out.print("-");
		         System.out.print(option.getOpt() + ",--");
		         System.out.print(option.getLongOpt());
		         if(option.getArgName() != "") {
		        	 System.out.print("<" + option.getArgName() + "> ");
		         }
		         System.out.println(option.getDescription());
		      }
		      System.out.println();
		      System.out.println("This is the 2023-1 HW4 help page.");
		      System.out.println();
		   }
}
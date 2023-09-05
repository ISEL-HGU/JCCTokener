/**
 * package
 */
package edu.handong.csee.java.hw5.clioptions;
/**
 * import
 */
import org.apache.commons.cli.*;
/**
 * optionhandler
 */
public class OptionHandler {

    /**
     * task
     */
    private String task;

    /**
     *datainputfilepath
     */
    private String dataInputFilePath;

    /**
     * dataoutputfilepath
     */
    private String dataOutputFilePath;

    /**
     * inputvalues
     */
    private String inputValues;

    /**
     * helprequested
     */
    private boolean helpRequested;

    /**
     * gethelprequested
     * @return
     */
    public boolean getHelpRequested() {
        return helpRequested;
    }

    /**
     * sethelprequested
     * @param helpRequested
     */
    public void setHelpRequested(boolean helpRequested) {
        this.helpRequested = helpRequested;
    }

    /**
     * gettask
     * @return
     */
    public String getTask() {
        return task;
    }

    /**
     * settask
     * @param task
     */
    public void setTask(String task) {
        this.task = task; 
    }

    /**
     * getdatainputfilepath
     * @return
     */
    public String getDataInputFilePath() {
        return dataInputFilePath;
    }


    /**
     * setdatainputfilepath
     * @param dataInputFilePath
     */
    public void setDataInputFilePath(String dataInputFilePath) {
        this.dataInputFilePath = dataInputFilePath;
    }

    /**
     * @return
     * getdataoutputfilepath
     */
    public String getDataOutputFilePath() {
        return dataOutputFilePath;
    }

    /**
     * setdataoutputfilepath
     * @param dataOutputFilePath
     */
    public void setDataOutputFilePath(String dataOutputFilePath) {
        this.dataOutputFilePath = dataOutputFilePath;
    }

    /**
     * getintputvalues
     * @return
     */
    public String getInputValues() {
        return inputValues;
    }

    /**
     * setinputvalues
     * @param inputValues
     */
    public void setInputValues(String inputValues) {
        this.inputValues = inputValues;
    }


    /**
     *  createOptions
     * @return
     */
    public Options createOptions() {

      
        Options options = new Options();

        options.addOption(Option.builder("h").longOpt("help")
                .desc("Show the help page")
                .build());
        options.addOption(Option.builder("t").longOpt("task")
                .desc("Set a task. The -t or -i options must be set\nas well.")
                .hasArg()
                .argName("A task name")
                .required(true)
                .build());
        options.addOption(Option.builder("i").longOpt("ipath")
                .desc("Set a path for a data input file. It must\nwork with -t SQRT, -t\nMAX, or -t MIN and -o\noptions together. e.g.,\n-t SQRT -i file.csv -o\noutput.csv")
                .hasArg()
                .argName("A data file/directory path to read")
                .build());
        options.addOption(Option.builder("o").longOpt("opath")
                .desc("Set a path for a data output file.")
                .hasArg()
                .argName("A data file path to write")
                .build());
        options.addOption(Option.builder("v").longOpt("values")
                .desc("Set input values\n(separator: space), e.g. \"23 21 2\"")
                .hasArg()
                .argName("Input values for a task.")
                .build());

        return options;
    }

    /**
     * parseOptions
     * @param options
     * @param args
     * @return
     */
    public boolean parseOptions(Options options, String[] args) {
        
        CommandLineParser parser = new DefaultParser();

        try {
            
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                helpRequested = true;
                printHelp(options);
                return false;
            }

            if (cmd.hasOption("t")) {
                task = cmd.getOptionValue("t");
                if (!cmd.hasOption("v") && !cmd.hasOption("i") && !cmd.hasOption("o")) {
                    printHelp(options);
                    return false;
                }
                if (cmd.hasOption("i") && cmd.hasOption("o")) {
                    if(!task.equalsIgnoreCase("SQRT") && !task.equalsIgnoreCase("MAX") && !task.equalsIgnoreCase("MIN"))
                    {printHelp(options);
                    return false;}
                }
                if(!cmd.hasOption("v") && !cmd.hasOption("i")) {
                    printHelp(options);
                    return false;
                }
                if(cmd.hasOption("i") && !cmd.hasOption("o")) {
                    printHelp(options);
                    return false;
                }
                if(cmd.hasOption("i") && cmd.hasOption("o") && cmd.hasOption("v")) {
                    printHelp(options);
                    return false;
                }

            } else {
                printHelp(options);
                return false;
            }

            if (cmd.hasOption("i")) {
                dataInputFilePath = cmd.getOptionValue("i");
            }

            if (cmd.hasOption("o")) {
                dataOutputFilePath = cmd.getOptionValue("o");
            }

            if (cmd.hasOption("v")) {
                inputValues = cmd.getOptionValue("v");
            } else {
                inputValues = "";
            }

            return true;
        } catch (ParseException e) {
            printHelp(options);
            return false;
        }
    }

    /**
     * printHelp
     * @param options
     */
    public void printHelp(Options options) {
       
        String header = "calculator [-h] [-i <A data file/directory path to read>] [-o <A\n       data file path to write>] -t <A task name> [-v <Input values for a\n       task.>]\nMath Calculator";
        
        String footer = "\nThis is the 2023-1 HW5 help page.";
     
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(header, options);
        System.out.println(footer); 
    }
}

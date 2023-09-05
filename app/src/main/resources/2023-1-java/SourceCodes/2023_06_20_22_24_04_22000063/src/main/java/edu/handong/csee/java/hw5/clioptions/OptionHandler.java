package edu.handong.csee.java.hw5.clioptions;


import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FilenameFilter;

/**
 * This is OptionHandler class
 */
public class OptionHandler {
    private String task;
    private String dataInputFilePath;
    private String dataOutputFilePath;
    private String inputValues;
    private boolean helpRequested;

    /**
     *
     * This is the getter of task
     * @return
     */
    public String getTask() {
        return task;
    }

    /**
     * This is the setter of task
     * @param task
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * This is the getter of dataInputFilePath
     * @return
     */

    public String getDataInputFilePath() {
        return dataInputFilePath;
    }

    /**
     * This is the setter of dataInputFilePath
     * @param dataInputFilePath
     */

    public void setDataInputFilePath(String dataInputFilePath) {
        this.dataInputFilePath = dataInputFilePath;
    }

    /**
     * This is the getter of dataOutputFilePath
     * @return
     */

    public String getDataOutputFilePath() {
        return dataOutputFilePath;
    }

    /**
     * This is the setter of dataOutputFilePath
     * @param dataOutputFilePath
     */

    public void setDataOutputFilePath(String dataOutputFilePath) {
        this.dataOutputFilePath = dataOutputFilePath;
    }

    /**
     * This is the getter of inputValues
     * @return
     */

    public String getInputValues() {
        return inputValues;
    }

    /**
     * This is the setter of inputValues
     * @param inputValues
     */

    public void setInputValues(String inputValues) {
        this.inputValues = inputValues;
    }

    /**
     * This is the function of request
     * @return
     */

    public boolean isHelpRequested() {
        return helpRequested;
    }

    /**
     * This is the function of request
     * @param helpRequested
     */

    public void setHelpRequested(boolean helpRequested) {
        this.helpRequested = helpRequested;
    }

    /**
     * This is the createOptions
     * @return
     */

    public Options createOptions() {
        Options options = new Options();


        options.addOption(Option.builder("h").longOpt("help").desc("Show the help page").build());

        options.addOption(Option.builder("i").longOpt("ipath").hasArg().argName("A data file/directory path to read").desc("Set a path for a data input file. It must work with -t SQRT, -t MAX, or -t MIN and -o options together. e.g., -t SQRT -i file.csv -o output.csv").build());
        options.addOption(Option.builder("o").longOpt("opath").hasArg().argName("A data file path to write").desc("Set a path for a data output file.").build());
        options.addOption(Option.builder("t").longOpt("task").hasArg().required().argName("A task name").desc("Set a task. The -t or -i options must be set as well.").build());
        options.addOption(Option.builder("v").longOpt("values").hasArgs().argName("Input values for a task.").desc("Set input values (separator: space), e.g. \"23 21 2\"\n").build());

        return options;
    }

    /**
     * This is the parseOptions functions
     * @param options
     * @param args
     * @return
     */

    public boolean parseOptions(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();

        try {

            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("h")) { // option h exist
                helpRequested = cmd.hasOption("h");

            } else { // option h not exist
                helpRequested = cmd.hasOption("h");

                task = cmd.getOptionValue("t"); // if option h not exist, must get option value of "t"


                if (cmd.hasOption("i") || cmd.hasOption("o")) { // if option i or o exist
                    if (cmd.hasOption("i") && cmd.hasOption("o") && ((task.toUpperCase().equals("SQRT")) || task.toUpperCase().equals("MAX")
                            || task.toUpperCase().equals("MIN"))) { // if task is sqrt, max, or min
                        dataInputFilePath = cmd.getOptionValue("i");
                        dataOutputFilePath = cmd.getOptionValue("o"); // v option is not required
                    } else {

                        throw new Exception();
                    }
                }

                if (task.toUpperCase().equals("SQRT") && cmd.hasOption("i") && cmd.hasOption("o")) {

                }

                if (task.toUpperCase().equals("CUBEVOL") || task.toUpperCase().equals("FACTORIAL") || task.toUpperCase().equals("FIBONACCI") ||
                        task.toUpperCase().equals("GCD") || task.toUpperCase().equals("LCM") || task.toUpperCase().equals("MAX") || task.toUpperCase().equals("MIN")
                        || task.toUpperCase().equals("SPHEREVOL") || task.toUpperCase().equals("SQRT")) {
                    if (task.toUpperCase().equals("SQRT") || task.toUpperCase().equals("MAX") || task.toUpperCase().equals("MIN")) { // The case of SQRT, MAX, or MIN
                        if (cmd.hasOption("i")) {

                        } else {
                            if (cmd.hasOption("v")) {
                                inputValues = cmd.getOptionValue("v");
                            } else {
                                throw new Exception();
                            }
                        }
                    } else {
                        if (cmd.hasOption("v")) {
                            inputValues = cmd.getOptionValue("v");
                        } else {
                            throw new Exception();
                        }
                    }
                } else {
                    throw new InvalidCommandException("Exception-01: Invalid command: " + task.toUpperCase() + "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
                }

//                } else {
//                    if(cmd.hasOption("v")) {
//                        inputValues = cmd.getOptionValue("v");
//                    } else {
//                        throw new Exception();
//                    }
//
//                }

                if (!cmd.hasOption("t") && !cmd.hasOption("h")) {
                    throw new Exception();
                }

                if (cmd.hasOption("i") && cmd.hasOption("o") && cmd.hasOption("t") && cmd.hasOption("v")) {
                    throw new Exception();
                }
                if (cmd.hasOption("i") && cmd.hasOption("v")) {
                    throw new Exception();
                }
                if (cmd.hasOption("i") && !cmd.hasOption("o")) {
                    throw new Exception();
                }
                if (!cmd.hasOption("i") && cmd.hasOption("o")) {
                    throw new Exception();
                }

                if (cmd.hasOption("i")) {
                    File checkFile = new File(cmd.getOptionValue("i"));

                    if (checkFile.isDirectory() && !cmd.getOptionValue("t").toUpperCase().equals("SQRT") && !cmd.getOptionValue("t").toUpperCase().equals("MAX")
                            && !cmd.getOptionValue("t").toUpperCase().equals("MIN")) { // directory이지만 sqrt가 아닐 경우

                        throw new Exception();
                    }

                    if (checkFile.isDirectory()) {
                        File directoryPath = new File(dataInputFilePath);
                        File[] fileNameList = directoryPath.listFiles(new FilenameFilter() {
                            @Override
                            public boolean accept(File dir, String name) {
                                return name.endsWith("csv");
                            }
                        });

                        if (fileNameList.length == 0) {
                            throw new Exception();
                        }
                    }

                    if (checkFile.isFile()) {
                        if (!checkFile.exists()) {

                            throw new Exception();
                        }
                    }


                }
            }
        } catch (InvalidCommandException e) {
            System.out.print(e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            printHelp(options);
            return false;
        }


        return true;
    }

    /**
     * This is the printHelp function
     * @param options
     */
    public void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        String header = "Math Calculator";
        String footer = "\nThis is the 2023-1 HW5 help page.";

        formatter.printHelp("calculator", header, options, footer, true);

    }
}


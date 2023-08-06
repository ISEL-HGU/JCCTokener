package edu.handong.csee.java.hw4.clioptions;

import java.lang.reflect.Array;
import org.apache.commons.cil.*;
import edu.handong.csee.java.hw4.*;

/**
 * OptionHandler class handles command-line options for the calculator program.
 */
public class OptionHandler {

    /**
     * The task name.
     */
    String task;

    /**
     * The file path for data input.
     */
    String dataInputFilePath;

    /**
     * The file path for data output.
     */
    String dataOutputFilePath;

    /**
     * The input values for a task.
     */
    String inputValues;

    /**
     * The Options object for command-line options.
     */
    Options option;

    /**
     * Indicates whether help is requested or not.
     */
    boolean helpRequested;

    /**
     * Returns the help requested status.
     * 
     * @return true if help is requested, false otherwise.
     */
    public boolean getHelpRequested() {
        return helpRequested;
    }

    /**
     * Sets the help requested status.
     * 
     * @param b true if help is requested, false otherwise.
     */
    public void setHelpRequested(boolean b) {
        helpRequested = b;
    }

    /**
     * Returns the task name.
     * 
     * @return The task name.
     */
    public String getTask() {
        return task;
    }

    /**
     * Sets the task name.
     * 
     * @param s The task name.
     */
    public void setTask(String s) {
        task = s;
    }

    /**
     * Returns the file path for data input.
     * 
     * @return The file path for data input.
     */
    public String getDataInputFilePath() {
        return dataInputFilePath;
    }

    /**
     * Sets the file path for data input.
     * 
     * @param s The file path for data input.
     */
    public static void setDataInputFilePath(String s) {
        dataInputFilePath = s;
    }

    /**
     * Returns the file path for data output.
     * 
     * @return The file path for data output.
     */
    public String getDataOutputFilePath() {
        return dataOutputFilePath;
    }

    /**
     * Sets the file path for data output.
     * 
     * @param s The file path for data output.
     */
    public void setDataOutputFilePath(String s) {
        dataOutputFilePath = s;
    }

    /**
     * Returns the input values for a task.
     * 
     * @return The input values for a task.
     */
    public String getInputValues() {
        return inputValues;
    }

    /**
     * Sets the input values for a task.
     * 
     * @param s The input values for a task.
     */
    public void setInputValues(String s) {
        inputValues = s;
    }

    /**
     * Creates a new Options object.
     * 
     * @return The created Options object.
     */
    public Options createOptions() {
        option = new Options();
        return option;
    }

    /**
     * Parses the command-line options.
     * 
     * @param o     The Options object to parse.
     * @param args  The command-line arguments.
     * @return true if parsing is successful, false otherwise.
     */
    public boolean parseOptions(Options o, String[] args) {
        if (o == null) {
            return false;
        } else {
            option = o;
            return true;
        }
    }

    /**
     * Prints the help text.
     * 
     * @param options The Options object containing the available options.
     */
    public void printHelp(Options options) {
            System.out.printf("usage: calculator [-h] [-i <A data file path to read>] [-o <A data file\r\n"
                    + "       path to write>] -t <A task name> [-v <Input values for a task.>]\r\n"
                    + "Math Calculator\r\n"
                    + " -h,--help                                             Show the help page\r\n"
                    + " -i,--ipath <A data file path to read>    Set a path for a data input\r\n"
                    + "                                                            file. It works with -t SQRT.\r\n"
                    + "                                                           e.g., -t SQRT -i file.csv -o\r\n"
                    + "                                                            output.csv\r\n"
                    + " -o,--opath <A data file path to write>   Set a path for a data output file.\r\n"
                    + " -t,--task <A task name>                  Set a task. The -t or -i options must be set as well.\r\n"
                    + " -v,--values <Input values for a task.>   Set input values (separator: space), e.g. \"23 21 2\"\r\n"
                    + "\r\n"
                    + "This is the 2023-1 HW4 help page.\r\n"
                    + "", null);
    }
}

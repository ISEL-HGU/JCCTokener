package edu.handong.csee.java.hw5.fileutil;

import java.io.*;
import java.util.ArrayList;
//
//import org.apache.commons.cli.Options;
//import java.util.List;
//import edu.handong.csee.java.hw4.clioptions.OptionHandler;

import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.*;
/**
 * FileManager class provides utility methods for reading from and writing to text files.
 */
public class FileManager {
	/**
     * Read lines from a text file.
     *
     * @param path the path of the text file to read.
     * @return an ArrayList of strings representing the lines read from the file.
     */
    public static ArrayList<String> readLinesFromATxtFile(String path) {
        ArrayList<String> lines = new ArrayList<String>();
        
        try {
        	String[] parse;
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
            	lines.add(line);
            	
            }

            bufferedReader.close();
            fileReader.close();
            
        } catch (IOException e) {
        	OptionHandler option = new OptionHandler();
        	Options options = option.createOptions();
        	option.printHelp(options);
            System.exit(0);
        }

        return lines;
    }
    /**
     * Write data to a text file.
     *
     * @param filePath the file path where the data will be written.
     * @param data     the ArrayList of strings representing the data to write.
     */
    public static void writeToTxtFile(String filePath, ArrayList<String> data) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for (String line : data) {
                	bufferedWriter.write(line);
                	bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
        	OptionHandler option = new OptionHandler();
        	Options options = option.createOptions();
        	option.printHelp(options);
            System.exit(0);
        }
        
    }
}

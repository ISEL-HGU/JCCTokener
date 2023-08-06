package edu.handong.csee.java.hw5.fileutil;

import java.io.*;
import java.util.ArrayList;

import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;

/**
 * This class provides file management functionalities, such as reading lines from a text file and writing to a text file.
 */
public class FileManager {

    /**
     * Read lines from a text file.
     * @param filepath the path of the text file to read
     * @return an ArrayList of strings, each representing a line from the text file
     */
    public static ArrayList<String> readLinesFromATxtFile(String filepath){
        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            } 
            reader.close();
        } catch (IOException e) {
            OptionHandler optionHandler = new OptionHandler();
            Options options = optionHandler.createOptions();
            optionHandler.printHelp(options);
            System.exit(0);
        } return lines;
    }

    /**
     * Write lines to a text file.
     * @param filePath the path of the text file to write
     * @param lines an ArrayList of strings, each representing a line to write to the text file
     */
    public static void writeATxtFile(String filePath, ArrayList<String> lines) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            OptionHandler optionHandler = new OptionHandler();
            Options options = optionHandler.createOptions();
            optionHandler.printHelp(options);
            System.exit(0);
        }
        System.out.println("The "+ filePath +" file has been successfully written.");
        System.exit(0);
    }

}
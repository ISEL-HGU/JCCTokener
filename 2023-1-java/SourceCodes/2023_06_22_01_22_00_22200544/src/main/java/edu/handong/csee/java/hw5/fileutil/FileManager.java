package edu.handong.csee.java.hw5.fileutil;

import java.util.ArrayList;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.nio.file.Path;
import java.nio.file.Paths;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;

//...

/**
 * The class that manages the files.
 * class that has a method for reading and writing files.
 */
public class FileManager {
	
	private static int row;
	
	
	/**
	 * The getter method that returns row for use by other methods.
	 */
	public static int getRow() {
		return row;
	}

	/**
	 * A setter method that allows row to be obtained from other methods.
	 */
	public static void setRow(int row) {
		FileManager.row = row;
	}

	/**
	 * The method by which the file is read.
	 * This method takes the path of the file and reads the file.
	 * Save each number in the file to a string array and return it.
	 */
	public static ArrayList<String> readLinesFromATxtFile(String filePath) {
		OptionHandler optionHandler = new OptionHandler();
		Options options = optionHandler.createOptions();
		ArrayList<String> args = new ArrayList<>();
	
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                row = words.length;
                for (String word : words) {
                	/*
                	try {
                        int num = Integer.parseInt(word);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    */
                	//System.out.println(word);
                	args.add(word);
                }
            }
        } catch (IOException e) {
        	//System.out.println("in========>");
        	optionHandler.printHelp(options);
			System.exit(0);
        }
        
        return args;
		
	}
	
	/**
	 * A method for generating and storing a file.
	 * The method stores the value of the received string array in the received file path.
	 */
	public static void writeAtxtFile(String filePath, ArrayList<String> args) {
		
		OptionHandler optionHandler =new OptionHandler();
		Options options = optionHandler.createOptions();
		
		int count = 0;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			
			
            for (String arg : args) {
            	count++;
                writer.write(arg);
                if(count%row==0) {
                	writer.newLine();
                }
                else {
                	writer.write(",");
                }
                
               
            }
        } catch (IOException e) {
        	//System.out.println("out========>");
        	optionHandler.printHelp(options);
			System.exit(0);
        }
		
		Path path = Paths.get(filePath);
		String fileName = path.getFileName().toString(); 
        
		System.out.println("The " + fileName + " file has been successfully written.");
    		

        
		
		
	}
}

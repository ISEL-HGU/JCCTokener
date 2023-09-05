package edu.handong.csee.java.hw5.fileutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.cli.Options;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.SQRTEngine;

/**
 * This class is used for managing file input and output.
 * This class reads a file and outputs it based on the provided path.
 */
public class FileManager{
	
	/**
	 * Reads the file at the specified path.
	 * 
	 * @param path path of the file to be read
	 * @return Returns the read file as an ArrayList
	 */
	public static ArrayList<String> readLinesFromATxtFile(String path) {
		OptionHandler optionHandler = new OptionHandler();
    	Options options = optionHandler.createOptions();
		Scanner inputStream = null;
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			inputStream = new Scanner (new File (path));
		} catch(FileNotFoundException e){
			optionHandler.printHelp(options);
			System.exit(0);
		}
		while(inputStream.hasNextLine()) {
			String line = inputStream.nextLine();
			list.addAll(Arrays.asList(line.split(",")));
		}
		
		inputStream.close();
		return list;
	}
	
	/**
	 * Saves the processed ArrayList as a file at the provided path.
	 * 
	 * @param path path of the file to be write
	 * @param list ArrayList of values after completing the square root calculation
	 */
	public static void writeAtxtFile(String inputFilePath, String outputFilePath, ArrayList<String> list) {
		OptionHandler optionHandler = new OptionHandler();
    	Options options = optionHandler.createOptions();
    	SQRTEngine sqrt = new SQRTEngine();
		String[] inputArray = new String[1];
		int index1 = 0, index2 = 0;
		int column = 0;
		
		try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))){
			for(String s : list) {
				try {
					double num = Double.parseDouble(s);
					inputArray[0] = s;
					sqrt.setInput(inputArray);
					sqrt.compute();
					list.set(index1, Double.toString(sqrt.getResult()));
				} catch(Exception ex) {
					column++;
				}
				index1++;
			}
			for(String s : list) {
				writer.write(s);
				index2++;
				if(index2==column) {
					index2 = 0;
					writer.write("\n");
				} else {
					writer.write(",");
				}
			}
			System.out.println("The " + outputFilePath + " file has been successfully written.");
			writer.close();
		} catch(IOException e) {
			optionHandler.printHelp(options);
			System.exit(0);
		}
	}
}
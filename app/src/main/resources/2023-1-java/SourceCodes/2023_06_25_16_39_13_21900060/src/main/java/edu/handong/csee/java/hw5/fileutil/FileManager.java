package edu.handong.csee.java.hw5.fileutil;

import java.util.ArrayList;
import java.io.*;

/**
 * This class provides methods for reading and writing CSV files.
 */
public class FileManager {
	/**
	 * Reads lines from a CSV file and returns them as an ArrayList of Strings.
	 * @param filePath the path of the CSV file to read
	 * @return ArrayList<String> an ArrayList containing the lines of the CSV file
	 */
	public static ArrayList<String> readLinesFromATxtFile(String filePath) {
		ArrayList<String> lines = new ArrayList<String>();		// ArrayList to store lines
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));	// Create a BufferedReader to read the file
			
			String line;
			while ((line = reader.readLine()) != null) {	// Read each line of the file
				lines.add(line);							// Add the line to the ArrayList
			}
			reader.close();	
		} catch (IOException e) {
			e.printStackTrace();	
		}
		
		return lines;
		
	}
	
	/**
	 * Writes the contents of an ArrayList to a CSV file.
	 * @param filePathToSave the path of the file to save the CSV data
	 * @param lines the ArrayList of lines to write to the file
	 */
	public static void writeAtxtFile(String filePathToSave, ArrayList<String> lines) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filePathToSave));		// Create a BufferedWriter to write to the file
			
			for(String line : lines) {
				writer.write(line);		// Write the line to the file
				writer.newLine();		
			}
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

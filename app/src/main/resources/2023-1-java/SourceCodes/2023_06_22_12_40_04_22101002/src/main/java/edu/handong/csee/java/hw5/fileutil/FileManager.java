package edu.handong.csee.java.hw5.fileutil;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
/**
 * FileManager class provides functionalities for reading from and writing to text files.
 * It provides static methods to read lines from a text file into an ArrayList and write an ArrayList to a text file.
 */
public class FileManager {
    /**
     * Reads lines from a given text file.
     * 
     * @param path The path of the file to be read.
     * @return ArrayList of Strings
     */
	public static ArrayList<String> readLinesFromATxtFile (String path) {
		
        String fileName = path; // get a file path from CLI arguments
		
		Scanner inputStream = null;
		ArrayList<String> lines = new ArrayList<>();
		try {
			inputStream = new Scanner(new File(fileName));
		}  catch (FileNotFoundException e) {
			System.exit (0);
		}
		
		while (inputStream.hasNextLine ()) {
			String line = inputStream.nextLine ();
			lines.add(line);
		}
		inputStream.close ();
		return lines;
	}
    /**
     * Writes an ArrayList of Strings into a text file.
     * 
     * @param path The path of the file to be written.
     * @param input ArrayList of Strings
     */
	public static void writeAtxtFile(String path, ArrayList<String> input) {
		String fileName = path;
		PrintWriter outputStream = null;
		ArrayList<String> arr = input;
		
		try {
			outputStream = new PrintWriter (new FileOutputStream(fileName, true));
		} catch (FileNotFoundException e) {
			System.exit(0);
		}
		for(String str: arr) {
			outputStream.println (str);
		}
		outputStream.close();
		
		
	}
	
}

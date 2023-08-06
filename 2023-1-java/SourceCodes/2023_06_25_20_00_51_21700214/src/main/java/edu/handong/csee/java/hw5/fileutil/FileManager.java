package edu.handong.csee.java.hw5.fileutil;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This class for read and write csv file for Calculater
 * @author Km Hongchan
 *
 */
public class FileManager {
	
	public ArrayList<String> readLinesFromATxtFiles(String filePath) throws FileNotFoundException{
		ArrayList<String> data = new ArrayList<String>();
		
		// Skip the header line by reading and ignoring it
		Scanner inputStream = new Scanner(new File(filePath)); 
		String line = inputStream.nextLine();
		
		// Read the rest of the file line by line
		while (inputStream.hasNextLine())
		{
			// Contains SKU,Quantity,Price,Description
			line = inputStream.nextLine();

			// Turn the string into an array of strings
			String[] values = line.split(",");
			
			for(String value : values) {
				data.add(value);
			}
		}
		
		inputStream.close( );

		return data;
	}
	
	public void writeAtxtFile(String filePath, ArrayList<String> data) throws FileNotFoundException {
		PrintWriter outputStream = new PrintWriter(new File(filePath));
		String newLine = System.lineSeparator();
		
		outputStream.println("attr1,attr2,attr3,attr4,attr5");
		int count = 0;
		for(String value : data) {
			outputStream.print(value);
			if(count%5 == 4) outputStream.print(newLine);
			else outputStream.print(',');
			count++;
		}
		outputStream.close();
	}
}
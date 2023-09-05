package edu.handong.csee.java.hw5.fileutil;

import java.io.*;
import java.util.*;
import edu.handong.csee.java.hw5.engines.SQRTEngine;

/**
 * Classes that load, read, write, and process files
 */
public class FileManager {
	
	/**
	 * Methods that take a file path and read the file line by line.
	 */
	public static ArrayList<String> readLinesFromATxtFile(String filePath) throws IOException{
		ArrayList<String> values = new ArrayList<String>();
		try {
			Scanner inputStream = new Scanner(new File(filePath));
			String line = inputStream.nextLine();
			values.add(line);
			values.add("\n");
			
			int row = 0;
			//int readRow = 0;

			while(inputStream.hasNextLine()) {
				line = inputStream.nextLine();
				//readRow ++;
				//System.out.println(lineCount);
				String[] ary = line.split(",");
				int i=0;
				int column = ary.length;
				
				for (String value : ary) {
					i++;
					values.add(value);
					if(i==column) {
						values.add("\n");
					}else {
						values.add(",");
					}
				}
			}

		} catch(FileNotFoundException e) {
			System.out.println("file not found");
		}
		return values;
		
	}
	
	/**
	 * Methods to take a file path, write to it, and save the result.
	*/
	public static void writeAtxtFile(String filePath, ArrayList<String> lines) {		// first argument is for the file path to save results.
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filePath));
	        for (String line : lines) {
	            writer.write(line);
	            //writer.newLine();
	        }
	        System.out.println("The "+filePath+" file has been successfully written.");
	        writer.close();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}



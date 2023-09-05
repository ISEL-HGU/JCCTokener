package edu.handong.csee.java.hw5.fileutil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.cli.Options;

import java.io.File;
import java.io.PrintWriter;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;

/**
 * This is a class about managing the FileIO in Calculator. This class is called when CLI option given are i, o, and t.
 * 
 */
public class FileManager {
	/**
	 * This method reads a csv file and creates an ArrayList of string with the information in the csv file.
	 * @param path A path to read a csv file. This is a parameter given from the Calculator class(Originally from OptionHandler).
	 * @return Returns an ArrayList of string that contains all the information in the csv file.
	 */
	public static ArrayList<String> readLinesFromATxtFile(String path){
		ArrayList<String> list = new ArrayList<String>();
		
		OptionHandler oh = new OptionHandler();
		Options options = oh.createOptions();

		
		try
		{
			// Skip the header line by reading and ignoring it
			Scanner inputStream = new Scanner(new File(path)); 
			String line;

			while (inputStream.hasNextLine())
			{
				
				line = inputStream.nextLine();
				
				String[] array = line.split(",");

				for(int i = 0; i < array.length; i++) {
					list.add(array[i]);
				}
				
				
			}
			inputStream.close();
		}
		catch(FileNotFoundException e) {
			oh.printHelp(options);
			System.exit(0);
		}
		
		
		return list;
	}
	/**
	 * This method creates a csv file that contains the header and the SqaureRoot data of numeric information contained in the ArrayList given in the parameter.
	 * @param path The path to create a csv file.
	 * @param list An ArrayList that contains the data from the original csv file.
	 */
	public static void writeATxtFile(String path, ArrayList<String> list) {

		OptionHandler oh = new OptionHandler();
		Options options = oh.createOptions();
		
		try {
			PrintWriter outputStream = new PrintWriter(path);
			
			
			
			int i = 0; 
			int column = 0;
			
			while (i < list.size()) {
				if(list.get(i+1).matches("[0-9]+")){
					outputStream.write(list.get(i) + "\n");
					i++;
					column = i;
					break;
				}
				else {
					outputStream.print(list.get(i) + ",");
				}
				i++;
			}
			
			while (i < list.size())
			{
				SQRTEngine sqrt = new SQRTEngine();
				
				
				sqrt.setInput(Double.parseDouble(list.get(i)));
			
				try {
					if(sqrt.getInput() < 0) throw new NegativeNumberException("SQRT"); 
				}
				catch(NegativeNumberException e) {
		    		System.out.println(e.getMessage());
		    		System.exit(0);
		    	}
				
				sqrt.compute();
				list.set(i, String.valueOf(sqrt.getResult()));
					
				
				
				if((i+1)%column != 0) {
					outputStream.write(list.get(i));
					outputStream.write(",");
				}
				else {
					outputStream.write(list.get(i));
					outputStream.write("\n");
				}
				
				i++;
				
			}
			outputStream.close();
			System.out.println("The " + path + " file has been successfully written.");

		} catch(FileNotFoundException e) {
			oh.printHelp(options);
			System.exit(0);
		}
	}
	
}

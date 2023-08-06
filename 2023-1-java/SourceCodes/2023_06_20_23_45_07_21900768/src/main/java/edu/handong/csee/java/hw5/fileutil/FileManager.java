package edu.handong.csee.java.hw5.fileutil;

import java.util.ArrayList;
import java.util.Scanner;

import edu.handong.csee.java.hw5.engines.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This class is for managing the file by reading and writing.
 * This class has two public methods which are going to read and writing file.
 */
public class FileManager {

	/**
	 * This is a one of the public method of this class.
	 * This class type is ArrayList<String> so it's going to return ArrayList<String> as well. And it get a filename as a argument.
	 * But if file does not exist, it will return null.
	 */
	public ArrayList<String[]> readLinesFromATxtFile(String fileName) {
		
		ArrayList<String[]> inputValue = new ArrayList<String[]>();
	File f = new File(fileName);
	Scanner inputStream;
	
	try {
		if(fileName == f.getAbsolutePath())
			inputStream = new Scanner(f);
		else {
			f = new File("../" + fileName);
			inputStream = new Scanner(f);
		}
		
	String line = inputStream.nextLine();


	// Read the rest of the file line by line
	while (inputStream.hasNextLine())
	{
		line = inputStream.nextLine();
		String[] ary = line.split(",");
		inputValue.add(ary);
		
	}
	inputStream.close( );
	}
	catch (FileNotFoundException e) {
		return null;
	} 
		return inputValue;
		
	}
	
	/**
	 * This is another public method of this class.
	 * This class type is void, so it's going to write the files with calculated value.
	 */
	public void writeATxtFile(String fileName, ArrayList<String[]> data) {
		Computable engine = new SQRTEngine();
		
		PrintWriter outputStream = null;
		
		try {
			outputStream = new PrintWriter(fileName);
			
		} catch(IOException e) {
			System.out.println("Error opening output file " + fileName);
			System.out.println(e.getMessage());
			System.exit(0);
		}
		for(int i=1; i<data.get(1).length+1; i++) {
			if(i == data.get(1).length)
				outputStream.print("attr"+i);
			else
				outputStream.print("attr"+i +",");
		}

		outputStream.println("");
		for(int i=1; i<data.size(); i++) {
			for(int j=0; j<data.get(1).length;j++) {
				String[] valueOfData = {data.get(0)[0], data.get(i)[j]};
				
				engine.setInput(valueOfData);
				engine.compute();
				if(j == data.get(1).length-1)
					outputStream.print(engine.getResult());
				else
					outputStream.print(engine.getResult()+",");
			}
			outputStream.println();
		}
		System.out.println("The "+fileName+ " file has been successfully written.");
		outputStream.close();
	}
}

package edu.handong.csee.java.hw5.fileutil;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import org.apache.commons.cli.Options;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;

public class FileManager {
	
	/**
	 * The main entry point of the program.
	 *
	 * @param args The command-line arguments.
	 */
	public static void run(String[] args) {
	    OptionHandler op = new OptionHandler();
	    Options options = op.createOptions();
	    boolean temp = op.parseOptions(options, args);
	    op.run(args);

	    String inputFilePath = op.getDataInputFilePath();
	    String outputFilePath = op.getDataOutputFilePath();

	    ArrayList<String> lines = readLinesFromATextFile(inputFilePath);
	    ArrayList<String> resultLines = calculateSquareRoot(lines);
	    writeAtxtFile(outputFilePath, resultLines);
	}

	/**
	 * This method is read lines from *.csv file.
	 * @param s
	 * @return
	 */
	public static ArrayList<String> readLinesFromATextFile(String s){
		OptionHandler o = new OptionHandler();
		Options options = o.createOptions();
		ArrayList<String> a = new ArrayList<String>();
		try
		{
			File file = new File(s);
			Scanner inputStream = new Scanner(new File(s));
			String line;// = inputStream.nextLine();
			if (!file.exists()) {
	            // 파일이 존재하지 않을 경우 예외 처리
	            throw new FileNotFoundException("File not found: " + s);
	        }
			while(inputStream.hasNextLine())
			{
				line = inputStream.nextLine();
				String[] tmp = line.split(",");
				for(int i=0; i<tmp.length; i++)
				{
					a.add(tmp[i]);
				}
				a.add("\n");
			}
			//System.out.println("read file succesfully!");
			inputStream.close(); //파일닫기
			
		}
		catch(FileNotFoundException e)
		{
			o.printHelp(options);
			System.exit(0);
		}
		return a;
	}
	
	/**
	 * This method write a *.csv file
	 * @param s
	 * @param a
	 */
	public static void writeAtxtFile(String s, ArrayList<String> a) {
	    OptionHandler o = new OptionHandler();
	    Options options = o.createOptions();

	    try (PrintWriter outputStream = new PrintWriter(new FileWriter(s))) {
	        // Write result lines
	        for (int i =0; i < a.size(); i++) {
	            outputStream.print(a.get(i));
	            if(a.get(i) != "\n" && a.get(i+1) != "\n")
	            	outputStream.print(",");
	        }
	        
	        System.out.println("The " + s + " file has been successfully written.");
	    } catch (IOException e) {
	        o.printHelp(options);
	        System.exit(0);
	    }
	}


/**
 * This method calculates the square root of each value in the given lines.
 * @param lines ArrayList of lines from the CSV file
 * @return ArrayList of lines with square root values
 */
public static ArrayList<String> calculateSquareRoot(ArrayList<String> lines) {
		ArrayList<String> resultLines = new ArrayList<>();
		SQRTEngine sqrtEngine = new SQRTEngine();
	try {
		// header 읽기
					//String[] tmp = header.split(",");
		int k=0;
		while(true)
		{
			if(lines.get(k) == "\n")
				break;
			//String header = lines.get(0);
			//resultLines.add(tmp[i]);
			resultLines.add(lines.get(k));
			k++;
		}
		//resultLines.add("\n");
	// 숫자 읽기
	for (int j=k; j<lines.size(); j++) {
			String value = lines.get(k++);
			if(value == "\n") {
				resultLines.add(value);
				continue;
			}
			double sqrtValue = sqrtEngine.calculateSQRT(Double.parseDouble(value));
			if(Double.isNaN(sqrtValue)) {
				//System.out.println(value);
				throw new NegativeNumberException(sqrtEngine.getEngineName());
			}
			resultLines.add(Double.toString(sqrtValue));
		}
	}catch(NegativeNumberException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
			//resultLines.add("\n");

		return resultLines;	
	}
}
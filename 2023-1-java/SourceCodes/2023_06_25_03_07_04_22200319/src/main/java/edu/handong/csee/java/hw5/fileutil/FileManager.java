package edu.handong.csee.java.hw5.fileutil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * The `FileManager` class is a class that read CSV file and write CSV file.
 */
public class FileManager{
	  /**
     * Reads the CSV file and put the data in ArrayList
     * @param filePath the path of a file
     * @return the new value of ArrayList<String>
     */
	public static ArrayList<String> readLinesFromATxtFile(String filePath) {
		 try {  
	            File file = new File(filePath);
	            Scanner inputStream = new Scanner(file);//파일 받기
	            ArrayList<String> input = new ArrayList<String>();
	            while (inputStream.hasNextLine()) {
	                String line = inputStream.nextLine();
	                String[] array = line.split(","); // 쉽표 기준으로 파일 쭉 받기. 
	                for (String value : array) {
	                    input.add(value);
	                }
	            }

	            inputStream.close();
	            return input;
	        } catch (FileNotFoundException e) {
	        	
	        }

	        return null;
	} 
	  /**
     * Writes the CSV file with the data form ArrayList
     * @param filePath the path of a file
     *  @param data the data to write
     */
	public static void writeAtxtFile(String newFile, ArrayList<String> data) {
		try {
			int row=0;
			int count=0;
			File file = new File(newFile);
            FileWriter writer = new FileWriter(file);
            for (String rowData : data) {
				if(rowData.matches("-?\\d+(\\.\\d+)?")!=true) {
					row++;
				}else
					break;
            }
			for (String rowData : data) {
                writer.append(rowData); 
                count++;
                if (count % row == 0) {
                    writer.append("\n"); 
                } else {
                    writer.append(","); 
                }
            }
			writer.close();
            System.out.println("The "+ newFile+ " file has been successfully written.");
            data.equals(readLinesFromATxtFile(newFile));
            		
            	
        } catch (IOException e) {
        	System.out.println("A file must be created");
        }
    }
		
	}
	
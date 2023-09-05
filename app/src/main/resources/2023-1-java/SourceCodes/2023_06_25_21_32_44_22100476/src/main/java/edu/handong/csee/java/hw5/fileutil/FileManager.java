package edu.handong.csee.java.hw5.fileutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The FileManager class provides methods for reading and writing lines from/to a text file.
 */
public class FileManager {
	
	/**
     * Read lines from a text file.
     * 
     * @param fileName The name of the text file to read.
     * @return An ArrayList of strings containing the lines read from the file.
     */
	public static ArrayList<String> readLinesFromATxtFile(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
	

	/**
     * Write lines to a text file.
     * 
     * @param fileName The name of the text file to write.
     * @param lines    An ArrayList of strings containing the lines to write to the file.
     */
    public static void writeAtxtFile(String fileName, ArrayList<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package edu.handong.csee.java.hw5.fileutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for managing file input and output operations.
 */
public class FileManager {
    /**
     * This method reads a text file and returns its content as an ArrayList of
     * String arrays.
     * 
     * 
     */
    public ArrayList<String[]> readLinesFromATxTFile(String filePath) {
        ArrayList<String[]> lines = new ArrayList<>();

        File file = new File(filePath);
        try {
            Scanner inputStream = new Scanner(file);

            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                String[] data = line.split(",");
                lines.add(data);
            }

            inputStream.close();

            return lines;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    /**
     * This method writes an ArrayList of String arrays into a text file.
     * 
     * 
     */
    public void writeAtxtFile(String filePath, ArrayList<String[]> lines) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(filePath);

            for (String[] data : lines) {
                String line = String.join(",", data);
                writer.write(line);
                writer.write("\n");
            }

            writer.close();
            System.out.println("The " + filePath + " file has been successfully written.");
        } catch (IOException e) {
            System.out.println("Error writing to the file.");
        }
    }
}
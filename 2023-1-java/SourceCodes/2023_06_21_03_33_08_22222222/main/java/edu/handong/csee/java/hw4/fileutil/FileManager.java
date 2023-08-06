package edu.handong.csee.java.hw4.fileutil;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import edu.handong.csee.java.hw4.*;

/**
 * FileManager class provides utility methods for file management.
 */
public class FileManager {

    /**
     * Reads lines from a text file.
     * 
     * @param str The file path of the text file to read.
     * @return An ArrayList containing the lines read from the text file.
     */
    public static ArrayList<String> readLinesFromATxtFile(String str) {
        ArrayList<String> newString = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(str));
            String line;
            while ((line = reader.readLine()) != null) {
                newString.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newString;
    }

    /**
     * Writes an ArrayList of strings to a text file.
     * 
     * @param str The file path of the text file to write.
     * @param arr The ArrayList of strings to write to the text file.
     */
    public static void writeATxtFile(String str, ArrayList<String> arr) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(str));
            for (String line : arr) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

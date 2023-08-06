package edu.handong.csee.java.hw5.fileutil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The FileManager class provides utility methods for reading from and writing to a text file.
 */
public class FileManager {
    /**
     * Reads all lines from a text file.
     * 
     * @param filePath The path of the text file to read.
     * @return An ArrayList containing all the lines read from the file.
     */
    public static ArrayList<String> readLinesFromATxFile(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        
        return lines;
    }
    
    /**
     * Writes lines to a text file.
     * 
     * @param filePath The path of the text file to write.
     * @param lines    The lines to write to the file.
     */
    public static void writeAtxtFile(String filePath, ArrayList<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            int count = 0;
            while (true) {
                if (lines.get(count).matches("-?\\d+(\\.\\d+)?")) {
                    break;
                }
                count++;
            }
            for (int i = 0; i < lines.size(); i++) {
                bw.write(lines.get(i));
                if ((i + 1) % count == 0 && i != 0) {
                    bw.newLine();
                } else {
                    bw.write(",");
                }
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}



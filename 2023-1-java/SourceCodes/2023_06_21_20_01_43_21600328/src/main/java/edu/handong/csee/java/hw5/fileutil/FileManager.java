package edu.handong.csee.java.hw5.fileutil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This code is for making files
 */
public class FileManager {
    /**
     * readLinesFromATxtFile reads line from text file
     */
    public static ArrayList<String> readLinesFromATxtFile(String[] args) {
        ArrayList<String> lines = new ArrayList<>();

        if (args.length > 0) {
            String filePath = args[0];

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                System.err.println("Failed to read lines from the file: " + filePath);
                e.printStackTrace();
            }
        }

        return lines;
    }
    /**
     * writeATxtFile writes words into text file
     */
    public static void writeATxtFile(String[] args, ArrayList<String> lines) {
        if (args.length > 0) {
            String filePath = args[0];

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.err.println("Failed to write lines to the file: " + filePath);
                e.printStackTrace();
            }
        }
    }
}
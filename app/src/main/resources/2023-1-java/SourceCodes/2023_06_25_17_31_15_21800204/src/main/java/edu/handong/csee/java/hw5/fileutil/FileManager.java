package edu.handong.csee.java.hw5.fileutil;

import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;

public class FileManager {
    public static ArrayList<String> readLinesFromATxtFile(String filePath) {
        ArrayList<String> values = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                values.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            // If the file is not found, create it
            File file = new File(filePath);
            try {
                if (file.createNewFile()) {
                    System.out.println("File " + filePath + " has been created.");
                } else {
                    System.out.println("File " + filePath + " already exists.");
                }
            } catch (IOException ioException) {
                System.out.println("Error creating file: " + ioException.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return values;
    }


    public static void writeATxtFile(String filePath, ArrayList<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

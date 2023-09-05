package edu.handong.csee.java.hw5.fileutil;

import java.util.ArrayList;
import java.nio.file.*;
import java.io.IOException;

public class FileManager {
	/**
     * This method reads lines from a text file and returns them as an ArrayList of Strings.
     * @param filePath A String representing the path to the text file to read.
     * @return An ArrayList of Strings representing the lines read from the text file.
     */
    public static ArrayList<String> readLinesFromATxtFile(String filePath) {
        ArrayList<String> lines = new ArrayList<>();

        try {
            lines = (ArrayList<String>) Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * This method writes an ArrayList of Strings to a text file.
     * @param filePath A String representing the path to the text file to write.
     * @param lines An ArrayList of Strings representing the lines to write to the text file.
     */
    public static void writeATxtFile(String filePath, ArrayList<String> lines) {
        Path file = Paths.get(filePath);
        try {
            Files.write(file, lines, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
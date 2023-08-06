package edu.handong.csee.java.hw5.fileutil;

import edu.handong.csee.java.hw5.exceptions.FileException;

import java.io.*;
import java.util.ArrayList;

/**
 * This is the FileManager class
 */

public class FileManager {
    /**
     * This is readLinesFromATxtFile method
     * @param args
     * @return
     * @throws Exception
     */
    public static ArrayList<String> readLinesFromATxtFile(String args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        String line;

        BufferedReader reader = new BufferedReader(new FileReader(args));

        while ((line = reader.readLine()) != null) {
            list.add(line); // In values, there are String arrays.

        }

        reader.close();

        return list;
    }

    /**
     * This is writeLinesFromATxtFile method
     * @param args
     * @param list
     * @throws Exception
     */
    public static void writeATxtFile(String args, ArrayList<String> list) throws FileException { // list's values are String array / args is path
        try {
            FileOutputStream fout = new FileOutputStream(args);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fout));
            int count = 0;

            for(String str : list) {
                writer.write(str);
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new FileException();
        }

    }




}

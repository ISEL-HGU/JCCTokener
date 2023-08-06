/**
 * package fileutil
 */
package edu.handong.csee.java.hw5.fileutil;

import edu.handong.csee.java.hw5.clioptions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * class filemanager
 */
public class FileManager {

    /**
     * readlinesfromtxtfile (filepath)
     * @param filePath
     * @return
     */
    public ArrayList<String> readLinesFromATxtFile(String filePath) {
        /**
         * lines
         */
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        
            String line;
        

            while ((line = br.readLine()) != null) {
                

                lines.add(line);
            }
        } catch (IOException e) {
          
            OptionHandler optionHandler = new OptionHandler();
            optionHandler.printHelp(optionHandler.createOptions());
            return null;


       }

        return lines;
    }
/**
 * write file 
 * @param filePath
 * @param lines
 */
public void writeATxtFile(String filePath, ArrayList<String> lines) {
    if (lines == null) {
        return;
    }
    
    try (PrintWriter writer = new PrintWriter(filePath)) {
for (String line : lines) {
    writer.print(line);
    }

        //System.out.println("File has been successfully written.");
    } catch (FileNotFoundException e) {
        System.out.println("Error occurred while writing the file.");
        e.printStackTrace();
    }
}
    
}

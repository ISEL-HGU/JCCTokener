package edu.handong.csee.java.hw5.fileutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import java.util.StringJoiner;
import org.apache.commons.cli.Options;
/**
 * FileManager.java
 * This class is for reading and writing file.
 */
public class FileManager {
	/**
     * This method return read file ArrayList<String>
     * 
     * 
     * @param filePath Type : String
     * @return lines
     */
	public static ArrayList<String> readLinxesFromATxtFile(String filePath) {

		ArrayList<String> lines = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	
                lines.add(line);
            }
        } catch (IOException e) {
        	OptionHandler optionshandler = new OptionHandler();
        	Options options = new Options();
        	options = optionshandler.createOptions();
			optionshandler.printHelp(options);
			System.exit(0);
            return lines;
        }
        return lines;
		}
	/**
     * This method return write file ArrayList<String>
     * 
     * @param filePath Type : String,  lines Type : ArrayList<String>
     */
		public static void writeAtxFile(String filePath, ArrayList<String> lines){
		    if (Files.exists(Paths.get(filePath))) {
		        System.out.println("A file must be created");
		        return;
		    }
	        
			  try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
		            for (String line : lines) {
		                writer.write(line);
		            }
		        } catch (IOException e) {
		        	OptionHandler optionshandler = new OptionHandler();
		        	Options options = new Options();
		        	options = optionshandler.createOptions();
					optionshandler.printHelp(options);
					System.exit(0);
		        }
	          System.out.println("The "+ filePath +"file has been successfully written.");
			  
		}
}

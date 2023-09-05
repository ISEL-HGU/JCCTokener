package edu.handong.csee.java.hw5.fileutil;

import java.util.* ; 
import java.io.* ;

/**
 * FileManager class provides methods to read contents from a text file and write contents to a text file.
 */
public class FileManager {
	
	/**
	 * Reads contents from a text file.
	  * @param filePath
	  * @return ArrayList<String>
	  * @throws FileNotFoundException
	  */
	public static ArrayList<String> readLinesFromATxtFile(String filePath) throws FileNotFoundException {
		
		ArrayList<String> lines = new ArrayList<>(); 

		try{
			File file = new File(filePath) ;
			Scanner s = new Scanner(file) ;

			String tmp = s.nextLine() ; 

			while(s.hasNextLine()){
				tmp = s.nextLine() ;
				lines.add(tmp) ;
			}
			// System.out.println(lines);
			
			s.close() ;
		} catch (FileNotFoundException e){
			// throw e ; 
		}
		return lines ; 
	}

	/**
	 * Write contents to a text file. 
	 * @param filePath
	 * @param list
	 */
	public static void writeAtxtFile(String filePath, ArrayList<String> list) {
		
		try{
			PrintWriter output = new PrintWriter(filePath);
			
			for (String str : list) {
				output.println(str) ;
			}

			output.close() ;
		}catch(Exception e){
			
		}
	
		
	}
}
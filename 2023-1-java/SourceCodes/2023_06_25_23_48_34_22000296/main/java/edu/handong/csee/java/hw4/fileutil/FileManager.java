package edu.handong.csee.java.hw4.fileutil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FileManager {
   public static ArrayList<String> readLinesFromATxtFile(String filename) {
      Scanner inputStream = null;
      ArrayList<String> returnlist = new ArrayList<String>();
      
      try {
         inputStream = new Scanner(new File(filename));
      } catch(FileNotFoundException e) {
         System.out.println("Error to opening the file : " + filename);
         System.exit(0);
      }
      
      while(inputStream.hasNextLine()) {
         String line = inputStream.nextLine();
         returnlist.add(line);
      }
      
      inputStream.close();
      
      return returnlist;
   }
   
   public static void writeAtxtFile(String filename, ArrayList<String> outputlist) {
      PrintWriter outputstream = null;
      try {
         outputstream = new PrintWriter(filename);
      } catch(FileNotFoundException e) {
         System.out.println("Error to opening the file : " + filename);
         System.exit(0);
      }
      
      for(int i=0; i<outputlist.size(); i++) {
         outputstream.println(outputlist.get(i));
      }
      
      outputstream.close();
   }

}
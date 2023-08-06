package edu.handong.csee.java.hw5.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * The `CSVFileCalculator` class is a class that makes reads,calculates,writes CSV files.
 * it implements Runnable so that it can use thread
 */
public class CSVFileCalculator implements Runnable {
	// 파일 읽어 들이기
	private String inputFilePath;
	private String outputFilePath;
	private ArrayList<ArrayList<String>> csvData;
	private String task;
    /**
     * Constructs a CSVFileCalculator object with the specified file path and task.
     *
     * @param inputFilePath the file path of the CSV file
     * @param task     the task to be performed on the CSV data
     */
	public  CSVFileCalculator(String inputFilePath,String task,String outputFilePath) {
		this.inputFilePath=inputFilePath;
		this.task=task;
		this.outputFilePath=outputFilePath;
	}
	/**
     * Sets the file path of the CSV file.
     *
     * @param inputFilePath the file path of the CSV file
     */
	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath=inputFilePath;
		
	}
	/**
     * Returns the file path of the CSV file.
     *
     * @return the file path of the CSV file
     */
	public String getInputFilePath() {
		return inputFilePath;
	}
	/**
     * Sets the file path of the CSV file.
     *
     * @param outputFilePath the file path of the CSV file
     */
	public void setOutputFilePath(String outputFilePath) {
		this.inputFilePath=outputFilePath;
		
	}
	/**
     * Returns the file path of the CSV file.
     *
     * @return the file path of the CSV file
     */
	public String getOutputFilePath() {
		return outputFilePath;
	}
    /**
     * Sets the CSV data.
     *
     * @param csvData the CSV data to be set
     */
	public void setCSVData(ArrayList<ArrayList<String>> csvData) {
		this.csvData=csvData;
		
	}
	   /**
     * Returns the CSV data.
     *
     * @return the CSV data
     */
	public ArrayList<ArrayList<String>> getCSVData() {
		return csvData;
	}
	/**
     * Sets the task to be performed on the CSV data.
     *
     * @param task the task to be performed
     */
	public void setTask(String task) {
		this.task=task;
		
	}
	/**
     * Returns the task to be performed on the CSV data.
     *
     * @return the task to be performed
     */
	public String getTask() {
		return task;
	}
	/**
     * Reads the CSV file and returns the data as a 2D ArrayList.
     *
     * @param filePath the file path of the CSV file to be read
     * @return the CSV data as a 2D ArrayList, or null if no data is available
     */
	public ArrayList<ArrayList<String>> readCSV(String filePath){
		// 디렉토리에 있는 파일들 읽어 들이기.. 
		 try { 
			 // 첫번째 라인의 넣는 값을 기준으로 row를 설정해야 겠다. 
			 // 쭉 넣고 리턴...  한줄씩 받아서... 넣어 주자. 
	            File file = new File(filePath);
	            Scanner inputStream = new Scanner(file);//파일 받기
	            ArrayList<ArrayList<String>> csvData = new ArrayList<ArrayList<String>>();
	            while (inputStream.hasNextLine()) {
	            	ArrayList<String> ha = new ArrayList<String>();
	                String line = inputStream.nextLine();
	                String[] array = line.split(","); // 쉽표 기준으로 파일 쭉 받기. 
	                for (String value : array) {
	                    ha.add(value);
	                }
	                csvData.add(ha);
	            }
	            inputStream.close();
	            return csvData; // 데이타가 없는우 에러 처리 하기 위해.. 
	        } catch (FileNotFoundException e) {
	        	
	        }
      return null;
	}
	/**
     * Writes the CSV data to the specified file path.
     *
     * @param filePath the file path to write the CSV data
     * @param csvData  the CSV data to be written
     */
	public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
		try {   // 바꾸기. 
			// header 쓰고...
			// data 쓰기... 
			// header는 
			File file = new File(outputFilePath);
            FileWriter writer = new FileWriter(file);
            for(int i=0;i<csvData.size();i++) {
            	for(int j=0;j<csvData.get(i).size();j++) {
            		writer.append(csvData.get(i).get(j));
            		if((csvData.get(i).size()-1)!=j) {
            			writer.append(","); 
            		}
            	}
            	 writer.append("\n");
            }
			writer.close();
            String fileName = file.getName();
            File infile = new File(inputFilePath);
            String infileName = infile.getName();
            String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
            String innameWithoutExtension = infileName.substring(0, infileName.lastIndexOf('.'));
           // System.out.println(outputFilePath);
            String newFileName = innameWithoutExtension + "-" + nameWithoutExtension;
            String parentDirectory = file.getParent();
            String newFilePath = parentDirectory + File.separator + newFileName;
            File newFile = new File(newFilePath);
            boolean result = file.renameTo(newFile);
        } catch (IOException e) {
      
        }
		
	}
	   /**
     * Performs the calculations based on the task.
     */
	public void calculate(){
		// sqrt!
		// 1. 앞에 있는 글씨들 지우기.. -> 숫자 아니면 넘어가기 or 처음 row 빼버리기
		// 2. 전부 한 작은 배열 마다 하나씩 sqrt에 넣었다가 빼기. 결과물 다시 배열에 넣기.  
		try {
		Computable engine =null;
		if(task.equals("SQRT")) {
			String[] num=new String[2];
			num[0]=task;
			engine= new SQRTEngine();
			for(int i=1;i<csvData.size();i++) {
				for(int j=0;j<csvData.get(i).size();j++) {
					num[1]=csvData.get(i).get(j);
					int value = Integer.parseInt(csvData.get(i).get(j));
					if(value<0) {
						throw new NegativeNumberException(task);
					}	
					try {
					    int number = Integer.parseInt(num[1]);
					} catch (NumberFormatException e) {
						throw new MyNumberFormatException(num[j],task);
					}
					engine.setInput(num);
					engine.compute();
					csvData.get(i).set(j,Double.toString(engine.getResult()));
				}
			}
			if(num[1]==null)
				throw new OneInputException(task);
		}else{
			csvData.get(0).add(task);
			if(task.equals("MAX")) 
			engine= new MaxEngine();
			else
			engine= new MinEngine();
			for(int i=1;i<csvData.size();i++) {
				csvData.get(i).add(0,task);
				String[] num=new String[csvData.get(i).size()];
				csvData.get(i).toArray(num);
				if (csvData.get(i).size()<2) 
		    		throw new MinimumInputNumberException(task);
				for(int j=1;j<num.length;j++) {
					try {
					    int number = Integer.parseInt(num[j]);
					} catch (NumberFormatException e) {
						throw new MyNumberFormatException(num[j],task);
					}
				}
			    engine.setInput(num);
			    engine.compute();
			    csvData.get(i).remove(0);
			    int result=(int)(engine.getResult());
			    csvData.get(i).add(Integer.toString(result));
				}
		}
		}catch(NegativeNumberException e){
	    	 System.out.println (e.getMessage ());
	    	 task=null;
	    }catch(MinimumInputNumberException e){
	       	 System.out.println (e.getMessage ());
	       	task=null;
	    }catch(OneInputException e){
	       	 System.out.println (e.getMessage ());
	       	task=null;
	    }catch(MyNumberFormatException e){
	    	System.out.println (e.getMessage ());
	    	task=null;
	    }
	}
    /**
     * Runs the CSVFileCalculator, including reading the CSV file, performing calculations, and writing the results.
     */
	public void run() {
        // 스레드 실행 로직을 구현합니다.
	    try {
	        csvData = readCSV(inputFilePath);
	        calculate();
	        if(task==null)
	        	throw new Exception();
	        writeCSV(inputFilePath, csvData);
	        //System.out.println("out!");
	    } catch (Exception e) {
	        // Exception occurred, terminate the thread
	    	//System.out.println("out?");
	        return; 
	    }
		
    }
	
	

	
}


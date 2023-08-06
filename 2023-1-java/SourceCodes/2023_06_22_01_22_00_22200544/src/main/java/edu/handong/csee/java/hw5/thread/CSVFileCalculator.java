package edu.handong.csee.java.hw5.thread;

import java.util.ArrayList;

import java.io.File;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import edu.handong.csee.java.hw5.engines.*;

import org.apache.commons.cli.Options;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;

/**
 * Calculate the CSVFile
 */
public class CSVFileCalculator implements Runnable {
	
	private OptionHandler optionHandler;
	private Options options;
	private String engineName;
	private Computable engine;
    private String fileName;

    /**
     * get OptionHandler
     */
	public OptionHandler getOptionHandler() {
		return optionHandler;
	}

	/**
	 * set OptionHandler
	 */
	public void setOptionHandler(OptionHandler optionHandler) {
		this.optionHandler = optionHandler;
	}

	/**
	 * get Options
	 */
	public Options getOptions() {
		return options;
	}

	/**
	 * set Options
	 */
	public void setOptions(Options options) {
		this.options = options;
	}

	/**
	 * get EngineName
	 */
	public String getEngineName() {
		return engineName;
	}

	/**
	 * set EngineName
	 */
	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	/**
	 * get Engie
	 */
	public Computable getEngine() {
		return engine;
	}

	/**
	 * set Engine
	 */
	public void setEngine(Computable engine) {
		this.engine = engine;
	}

	/**
	 * get FileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * set FileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * set FilePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	private String filePath;
	
	/**
	 * constructor
	 */
	public String getFilePath() {
		return filePath;
	}

	
	 /**
	  * constructor
	  */
	public CSVFileCalculator() {
		this.filePath = "";

    }
	
	 /**
	  * constructor
	  */
    public CSVFileCalculator(String filePath) {
        this.filePath = filePath;
    }

    /**
	 * 
	 */
    public CSVFileCalculator(String filePath, String fileName, OptionHandler optionHandler, Options options, String engineName, Computable engine) {
        this.filePath = filePath;
        this.optionHandler = optionHandler;
        this.options = options;
        this.engineName = engineName;
        this.engine = engine;
        this.fileName =fileName;
    }

    /**
	 * Implement the contents of thread
	 */
    @Override
    public void run() {
    	//System.out.println(filePath + " 계산에서 아웃풋 파일은 만들어요!");
    	
    	if( (engineName.equals("MAX") || engineName.equals("SQRT") || engineName.equals("MIN") ) && optionHandler.getInputValues() == null) {
        	//System.out.println("=======>");
        	if(engineName.equals("SQRT") && optionHandler.getDatalnputFilePath() != null) {
    			//System.out.println("=======>");
    			
            	if(optionHandler.getDataOutputFilePath() == null) {
            		optionHandler.printHelp(options);
            	}
            	if(optionHandler.getInputValues() != null) {
            		optionHandler.printHelp(options);
            	}; 
            	
            	//System.out.println("=======>");
            	ArrayList<ArrayList<String>> inputData;
            	if(optionHandler.getDatalnputFilePath().charAt(optionHandler.getDatalnputFilePath().length() - 1) == '/') {
            		
            		inputData = readCSV(optionHandler.getDatalnputFilePath()+fileName);
            	}
            	else {
            		
            		inputData = readCSV(optionHandler.getDatalnputFilePath()+'/'+fileName);
            	}
            	
            	
            	ArrayList<ArrayList<String>> outputData = new ArrayList<>();
          
            	int n =0;
				//System.out.println("=======>");
            	for (ArrayList<String> line : inputData) {
            		

            		ArrayList<String> outline = new ArrayList<>();
    	    		for(String word : line) {
    	    			String[] num = word.split("\\s+");
    	                
    	                try {
    	                	//System.out.println(word);
    	                    int IntegerNum = Integer.parseInt(num[0]);
    	                	//Double DoubleNum = Double.parseDouble(num[0]);
    	                } catch (NumberFormatException e) {
    	                	if(n == 0) {
    	                		//System.out.println("=======>" + n + num[0]);
    	                		outline.add(num[0]);
        	                    continue;
    	                	}
    	                	
    	                	
    	                }
    	                n++;
    	                engine.setInput(num);
    	                engine.compute();
    	                
    	                outline.add(Double.toString(engine.getResult()));
    	                
    	    		}
    	    		
                    
                    
    	    		outputData.add(outline);
                    
                    
                }
            	
            	
            	
            	
  
            	writeCSV(optionHandler.getDataOutputFilePath(), outputData);
            }
        	else if( ( engineName.equals("MIN") || engineName.equals("MAX") ) && optionHandler.getDatalnputFilePath() != null) {
        		//System.out.println("=========>");
        		if(optionHandler.getDataOutputFilePath() == null) {
            		optionHandler.printHelp(options);
            	}
            	if(optionHandler.getInputValues() != null) {
            		optionHandler.printHelp(options);
            	}
            	
            	
            	ArrayList<ArrayList<String>> inputData;
            	if(optionHandler.getDatalnputFilePath().charAt(optionHandler.getDatalnputFilePath().length() - 1) == '/') {
            		
            		inputData = readCSV(optionHandler.getDatalnputFilePath()+fileName);
            	}
            	else {
            		//System.out.println(optionHandler.getDatalnputFilePath()+fileName);
            		inputData = readCSV(optionHandler.getDatalnputFilePath()+'/'+fileName);
            		//System.out.println("=========>");
            	}
            	ArrayList<ArrayList<String>> outputData = new ArrayList<>();
            	
            	//System.out.println("=========>");
            	int n = 0;
            	for (ArrayList<String> line : inputData) {
            		
            		ArrayList<String> outline = new ArrayList<>();
            		//System.out.println(line);
            		
    	    		for(String word : line) {
    	    			
    	    			String[] num = word.split("\\s+");
    	    			//System.out.println(num[0]);
    	                
    	                try {
    	                	//System.out.println(word);
    	                    int IntegerNum = Integer.parseInt(num[0]);
    	                	//Double DoubleNum = Double.parseDouble(num[0]);
    	                } catch (NumberFormatException e) {
    	                	if(n==0) {
    	                		
    	                		//System.out.println(num[0]);
    	                		outline.add(num[0]);
        	                    continue;
    	                	}
    	                	
    	                	
    	                }
    	                outline.add(word);
    	               
    	                
    	    		}
    	    		
    	    		try {
                        int IntegerNum = Integer.parseInt(outline.get(0));
                    	//Double DoubleNum = Double.parseDouble(num[0]);
                    } catch (NumberFormatException e) {
                    	if(n == 0) {
                    		if(engineName.equals("MIN")) {
                        		outline.add("MIN");
                        	}
                        	else {
                        		outline.add("MAX");
                        	}
                        	
                        	outputData.add(outline);
                        	n++;
                            continue;
                    	}
                    	
                    	
                    }
    	    		
    	    		
    	    		//System.out.println(outline.toArray(new String[outline.size()]));
    	    		engine.setInput(outline.toArray(new String[outline.size()]));
                    engine.compute();
                    
                    //System.out.println((int)engine.getResult());
                    outline.add(Integer.toString((int)(engine.getResult())));
                    
    	    		outputData.add(outline);
    	    		
                    
                    
                   
                }
            	
            	
            	
            	
            	
            	writeCSV(optionHandler.getDataOutputFilePath(), outputData);
            }
        }
    }
	
    /**
	 * read the CSV file
	 */
	public ArrayList<ArrayList<String>>readCSV(String filePath){
		
		OptionHandler optionHandler =new OptionHandler();
		Options options = optionHandler.createOptions();
		
		//System.out.println(filePath);
		
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		
		
        try {
        	
        	CSVParser parser = CSVFormat.DEFAULT.parse(new FileReader(filePath));
        	//System.out.println(filePath);
            for (CSVRecord record : parser) {
                ArrayList<String> rowData = new ArrayList<>();
                for (String value : record) {
                    rowData.add(value);
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            //e.printStackTrace();
        	//System.out.println("=========>");
        	optionHandler.printHelp(options);
			System.exit(0);
        }

        return data;
		
	}
	
	/**
	 * write the CSV file
	 */
	public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
		OptionHandler optionHandler =new OptionHandler();
		Options options = optionHandler.createOptions();
		
		String[] name = fileName.split(".csv");
        String extractedName = name[0];
        //System.out.println(extractedName);
        
		try {
			FileWriter writerStreamName = new FileWriter(extractedName+ "-" + filePath);
			CSVPrinter csvPrinterInstanceName = new CSVPrinter(writerStreamName, CSVFormat.DEFAULT);
			//try (//FileWriter fileWriter = new FileWriter(filePath);
			//CSVPrinter csvPrinterInstanceName = new CSVPrinter(writerStreamName, CSVFormat.DEFAULT)) {
				int num = 0;
				for (ArrayList<String> row : csvData) {
					if(num ==0) {
						String rowString = String.join(",", row);
						//System.out.println(rowString);
				    	
						/*
				    	StringBuilder sb = new StringBuilder();
				    	for (String element : row) {
				    	    sb.append(element);
				    	    sb.append(",");
				    	}
				    	// 마지막 쉼표와 공백 제거
				    	if (sb.length() > 0) {
				    	    sb.setLength(sb.length() - 1);
				    	}
				    	*/
				    	//String header = sb.toString();
				    	
						String header = rowString;
				    	//System.out.println(header);
				    	writerStreamName.write(header + "\n");
					}
					else {
						//System.out.println(row);
						csvPrinterInstanceName.printRecord(row);
					}
					
					
				    num++;

				}

				csvPrinterInstanceName.flush();
			//}
        } catch (IOException e) {
            //e.printStackTrace();
            optionHandler.printHelp(options);
			System.exit(0);
        }
		 
		//System.out.println("The " + extractedName+ "-" + filePath + " file has been successfully written.");
	}
	
	/**
	 * .
	 */
	public void calculate() {
		

		 
		
		
		
		

        
	}
	
	

}


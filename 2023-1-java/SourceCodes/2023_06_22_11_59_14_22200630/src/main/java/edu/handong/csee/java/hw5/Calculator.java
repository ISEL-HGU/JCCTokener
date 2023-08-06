package edu.handong.csee.java.hw5;

import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.fileutil.*;
import edu.handong.csee.java.hw5.thread.CSVFileCalculator;
import java.util.ArrayList;
/**
 * Class with main method
 */
public class Calculator {

    
    /** 
     * @param args 
     * main method
     */
    public static void main(String[] args) {

        Calculator myCalculator = new Calculator();
        
        myCalculator.run(args);
    }

    
    /** 
     * @param args
     * method that run implements
     */
    public void run(String[] args) {
    		
    	

        OptionHandler optionHandler = new OptionHandler();
        Options option = optionHandler.createOptions();
        
        if(!optionHandler.parseOptions(option, args))
        	System.exit(0);
        
        if(optionHandler.isHelpRequested()){
        	optionHandler.printHelp(option);
        	System.exit(0);
        }
        
        
        
        
        String engineName = optionHandler.getTask().toUpperCase();
        Computable engine =null;
       
        try {
        	switch(engineName) {
            case "LCM":
                engine = new LCMEngine();
                break;
            case "GCD":
                engine = new GCDEngine();
                break;
            case "SQRT":
                engine = new SQRTEngine();
                break;
            case "FACTORIAL":
                engine = new FactorialEngine();
                break;
            case "FIBONACCI":
                engine = new FibonacciEngine();
                break;
            case "MAX":
                engine = new MaxEngine();
                break;
            case "MIN":
                engine = new MinEngine();
                break;
            case "CUBEVOL":
                engine = new CubeVolEngine();
                break;
            case "SPHEREVOL":
                engine = new SphereVolEngine();
                break;
            default:
            	throw new InvalidCommandException(engineName);
        	}
            	
        }catch(InvalidCommandException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
        	
        
        if(optionHandler.getInputValues().length() != 0){
        
        	
        	String a[] = optionHandler.getInputValues().split("\\s+");
        	String b[] = new String[a.length+1];
        	
        	
        	b[0] = optionHandler.getTask();
        	
        	for(int i =0; i < a.length; i++){
        		b[i+1] = a[i];
        	}
        	
        	
        	engine.setInput(b);
        	engine.compute();

        	System.out.println("The result of " +  engineName + " is " + engine.getResult() + ".");
        }else {
        	ArrayList<String> a = FileManager.readLinesFromATxtFile(optionHandler.getDataInputFilePath());
            // ArrayList<String> a = new ArrayList<>();

            // CSV 파일에서 데이터 읽어오기
            if (optionHandler.getTask().equals("SQRT") || optionHandler.getTask().equals("MAX")
                    || optionHandler.getTask().equals("MIN")) {
                CSVFileCalculator csvCalculator = new CSVFileCalculator(optionHandler.getDataInputFilePath(),
                        optionHandler.getTask());
                a = new ArrayList<>();
                ArrayList<ArrayList<String>> csvData = csvCalculator.readCSV();
                for (ArrayList<String> row : csvData) {
                    StringBuilder line = new StringBuilder();
                    for (String element : row) {
                        line.append(element).append(",");
                    }
                    line.deleteCharAt(line.length() - 1); // 마지막 쉼표 제거
                    a.add(line.toString());
                }
            } else {
                a = FileManager.readLinesFromATxtFile(optionHandler.getDataInputFilePath());
            }
        	ArrayList<String> parseArrayList = new ArrayList<>();
        	ArrayList<String> resultArrayList = new ArrayList<>();
        	
        	String temp = a.get(0);
        	
        	int column_num = temp.length() - temp.replace(",", "").length();
        	
        	for(String xString : a){
        		
        		String tmpString[] =  xString.split(",");
        		
        		for(String sString : tmpString){
        			parseArrayList.add(sString);
        		}
        	}
        	for(int i = 0; i < parseArrayList.size(); i++){
        		try {
        			Double.parseDouble(parseArrayList.get(i));
        		}catch (NumberFormatException e) {
					continue;
				}
        		
        		String k[] = new String[2];
        		
        		k[0] = optionHandler.getTask();
        		k[1] = parseArrayList.get(i);
        		
        		engine.setInput(k);
            	engine.compute();
            	
            	parseArrayList.set(i, Double.toString(engine.getResult()));
            	
        	}
        	String result = "";
        	for(int i = 0; i < parseArrayList.size(); i++){
        		result += parseArrayList.get(i) +",";
        		
        		if((i+1) % (column_num+1) ==0) {
        			result = result.substring(0, result.length()-1);
        			
        			resultArrayList.add(result);
        			result = "";
        		}
        	}
        	// CSV 파일에 데이터 쓰기
            if (optionHandler.getTask().equals("SQRT") || optionHandler.getTask().equals("MAX")
                    || optionHandler.getTask().equals("MIN")) {
                CSVFileCalculator csvCalculator = new CSVFileCalculator(optionHandler.getDataOutputFilePath(),
                        optionHandler.getTask());
                csvCalculator.writeCSV(resultArrayList.get(0));
            } else {
                FileManager.writeATxtFile(optionHandler.getDataOutputFilePath(), resultArrayList);
            }
        	System.exit(0);
        }
        
    }


}
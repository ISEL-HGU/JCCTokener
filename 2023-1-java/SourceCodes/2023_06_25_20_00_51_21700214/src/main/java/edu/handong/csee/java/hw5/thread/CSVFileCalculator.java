package edu.handong.csee.java.hw5.thread;

import java.util.ArrayList;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;

import edu.handong.csee.java.hw5.engines.SQRTEngine;
import edu.handong.csee.java.hw5.engines.MaxEngine;
import edu.handong.csee.java.hw5.engines.MinEngine;
import edu.handong.csee.java.hw5.exceptions.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVFileCalculator implements Runnable {
	
	public ArrayList<ArrayList<String>> readCSV(String filePath) throws IOException{
		
		ArrayList<ArrayList<String>> data = new ArrayList<>();
		Reader in = new FileReader(filePath);
		 
		Iterable<CSVRecord> records = CSVFormat.DEFAULT
				.builder()
				.setHeader()
				.setSkipHeaderRecord(true)
				.build()
				.parse(in);
		
		for (CSVRecord record : records) {
			 ArrayList<String> rowData = new ArrayList<>();
             for (String value : record) {
                 rowData.add(value);
             }
             data.add(rowData);
         }
		    		
		return data;
	}
	
	public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

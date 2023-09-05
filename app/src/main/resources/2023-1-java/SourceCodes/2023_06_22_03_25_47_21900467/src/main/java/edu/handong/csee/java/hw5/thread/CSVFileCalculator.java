package edu.handong.csee.java.hw5.thread;
import java.util.ArrayList;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import org.apache.commons.csv.*;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The CSVFileCalculator class is responsible for reading a CSV file, performing calculations based on the specified engine,
 * and writing the results to a new CSV file.
 */
public class CSVFileCalculator implements Runnable{

	private String inputFilePath;
	private String outputFilePath;
	private String engineName;
	private ArrayList<ArrayList<String>> csvData;
	private ArrayList<ArrayList<String>> newData;
    /**
     * Reads a CSV file and returns the data as a 2D ArrayList.
     * 
     * @param filePath The path of the CSV file to read.
     * @return The data from the CSV file as a 2D ArrayList.
     */
	public ArrayList<ArrayList<String>> readCSV(String filePath){
		ArrayList<ArrayList<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                ArrayList<String> row = new ArrayList<>();

                String[] values = line.split(",");
                for (String value : values) {
                    row.add(value.trim());
                }

                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
	}
	/**
     * Writes the CSV data to a file.
     * 
     * @param filePath  The path of the output CSV file.
     * @param csvData   The data to write to the CSV file.
     */
	public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
        String inputFileName = getFileName(filePath);
        String outputFileName = createOutputFileName(inputFileName);
        if(csvData ==null) {
        	return;
        }

        try {
        	FileWriter writer = new FileWriter(outputFileName);
        	CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
               
           ArrayList<String> header = csvData.get(0);
           writer.write(String.join(",", header) + "\n");


        
           for (int i = 1; i < csvData.size(); i++) {
               ArrayList<String> record = csvData.get(i);
               csvPrinter.printRecord(record);
           }
           csvPrinter.close();
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
        /*
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            // Write the header line
            StringBuilder header = new StringBuilder();
            for (String value : csvData.get(0)) {
                header.append(value).append(",");
            }
            header.deleteCharAt(header.length() - 1); // Remove the trailing comma
            writer.write(header.toString());
            writer.newLine();

            // Write the data lines
            for (int i = 1; i < csvData.size(); i++) {
                ArrayList<String> record = csvData.get(i);
                StringBuilder dataLine = new StringBuilder();
                for (String value : record) {
                    dataLine.append(value).append(",");
                }
                dataLine.deleteCharAt(dataLine.length() - 1); // Remove the trailing comma
                writer.write(dataLine.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error occurred while writing the CSV file: " + e.getMessage());
        }
        */
    }
	/**
     * Constructs a CSVFileCalculator instance.
     * 
     * @param inputFilePath The path of the input CSV file.
     * @param outputFilePath The path of the output CSV file.
     * @param engineName The name of the calculation engine.
     */
	public CSVFileCalculator(String inputFilePath,String outputFilePath, String engineName) {
		this.inputFilePath = inputFilePath;
		this.outputFilePath = outputFilePath;
		this.engineName = engineName;
		csvData = new ArrayList<>();
		
	}
	/**
    * Runs the CSV file calculation process.
    */
	public void run() {
        csvData = readCSV(inputFilePath);
        newData = calculate(csvData);
        if(newData == null) {
        	return;
        }
        writeCSV(inputFilePath, newData);
	}
	/**
     * Performs calculations on the CSV data based on the specified engine.
     * 
     * @param csvData The input CSV data.
     * @return The calculated CSV data.
     */
	public ArrayList<ArrayList<String>> calculate(ArrayList<ArrayList<String>> csvData) {
        ArrayList<ArrayList<String>> newCsvData = new ArrayList<>(); // 새로운 2차원 ArrayList
        try {
	        if(!csvData.isEmpty()) {
	        	ArrayList<String> first = csvData.get(0);
	        	if(engineName.equals("MAX")) {
	    			first.add(engineName);
	    		}
	    		else if(engineName.equals("MIN")) {
	    			first.add(engineName);
	    		}
	    		newCsvData.add(first);
	        }
	        else {
	        	return null;
	        }
	        if(engineName.equals("SQRT")&& csvData.size()==1) {
                throw new OneInputException(engineName, 0);
	        }
	        else if((engineName.equals("MAX")||engineName.equals("MIN"))&& csvData.size()==1){
	        	throw new MinimumInputNumberException(engineName,0);
	        }
	        for (int i = 1; i < csvData.size(); i++) {
	            ArrayList<String> row = csvData.get(i);
	          
	            if (engineName.equals("SQRT")) {
	            	ArrayList<String> newRow = new ArrayList<>(row.size()); // 계산된 값을 담을 새로운 행
	                for (String input : row) {
	                    if (input.startsWith("-")) {
	                        throw new NegativeNumberException(engineName, 0);
	                    }
	                    
	                    if (!input.matches("-?\\d+(\\.\\d+)?")) {
	                        throw new MyNumberFormatException(engineName, input);
	                    }
	                    
	                    double num = Double.parseDouble(input);
	                    double sqrt = Math.sqrt(num);
		                newRow.add(String.valueOf(sqrt)); // 새로운 행에 제곱근 값을 문자열로 추가
	                }
		            newCsvData.add(newRow); // newCsvData에 새로운 행 추가

	            }
	            else if (engineName.equals("MAX")) {
	            	ArrayList<String> newRow = new ArrayList<>(row.size()+1); // 계산된 값을 담을 새로운 행
	            	if (row.size() < 2) {
		                throw new MinimumInputNumberException(engineName, 0);
		            }
	            	int max;
	            	if(!row.get(0).matches("-?\\d+(\\.\\d+)?")) {
            			throw new MyNumberFormatException(engineName, row.get(0));
            		}
	            	else {
	            		max = Integer.parseInt(row.get(0));
	            		newRow.add(String.valueOf(row.get(0)));
	            	}
	            	for(int j = 1; j < row.size(); j++) {
	            		String value = row.get(j);
	            		if(!value.matches("-?\\d+(\\.\\d+)?")) {
	            			throw new MyNumberFormatException(engineName, value);
	            		}
	            		int temp = Integer.parseInt(value);
	            		if (max<temp) {
	            			max = temp;
	                    }
	            		newRow.add(String.valueOf(temp));
	            		
	            	}
	                newRow.add(String.valueOf(max));
		            newCsvData.add(newRow); // newCsvData에 새로운 행 추가
	                
	            }
	            else if (engineName.equals("MIN")) {
	            	ArrayList<String> newRow = new ArrayList<>(row.size()+1); // 계산된 값을 담을 새로운 행
	            	if (row.size() < 2) {
		                throw new MinimumInputNumberException(engineName, 0);
		            }
	            	int min;
	            	if(!row.get(0).matches("-?\\d+(\\.\\d+)?")) {
            			throw new MyNumberFormatException(engineName, row.get(0));
            		}
	            	else {
	            		min = Integer.parseInt(row.get(0));
	            		newRow.add(String.valueOf(row.get(0)));
	            	}
	            	for(int j = 1; j < row.size(); j++) {
	            		String value = row.get(j);
	            		if(!value.matches("-?\\d+(\\.\\d+)?")) {
	            			throw new MyNumberFormatException(engineName, value);
	            		}
	            		int temp = Integer.parseInt(value);
	            		if (min>temp) {
	            			min = temp;
	                    }
	            		newRow.add(String.valueOf(temp));
	            		
	            	}
	                newRow.add(String.valueOf(min));
		            newCsvData.add(newRow); // newCsvData에 새로운 행 추가
	                
	            }
	            

	        }	            
		}catch (OneInputException e) {
			System.out.println(e.getMessage());
			return null;
	    } catch (MyNumberFormatException e) {
			System.out.println(e.getMessage());
			return null;
	    } catch (NegativeNumberException e) {
			System.out.println(e.getMessage());
			return null;
        }
    	catch (MinimumInputNumberException e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
        
        
		return newCsvData;
	}
    /**
     * Returns the input file path.
     * @return The input file path.
     */
	public String getInputFilePath() {
		return inputFilePath;
	}
    /**
     * Sets the input file path.
     * @param inputFilePath The input file path.
     */
	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}
    /**
     * Returns the output file path.
     * @return The output file path.
     */
	public String getOutputFilePath() {
		return outputFilePath;
	}
    /**
     * Sets the output file path.
     * @param outputFilePath The output file path.
     */
	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}
    /**
     * Returns the engine name.
     * @return The engine name.
     */
	public String getEngineName() {
		return engineName;
	}
    /**
     * Sets the engine name.
     * @param engineName The engine name.
     */
	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}
	/**
     * Extracts the file name from the given file path.
     * 
     * @param filePath The file path.
     * @return The file name.
     */
    private String getFileName(String filePath) {
        Path path = Paths.get(filePath);
        return path.getFileName().toString();
    }
    /**
     * Creates an output file name based on the input file name.
     * 
     * @param inputFileName The input file name.
     * @return The output file name.
     */
    private String createOutputFileName(String inputFileName) {
        int dotIndex = inputFileName.lastIndexOf('.');
        String outputName = getFileName(outputFilePath);
        String baseName = (dotIndex != -1) ? inputFileName.substring(0, dotIndex) : inputFileName;
        String outputFileName = baseName+"-" + outputName;
        return outputFileName;
    }
    /**
     * Returns the CSV data.
     * @return The CSV data.
     */
	public ArrayList<ArrayList<String>> getCsvData() {
    	return csvData;
    }
    /**
     * Sets the CSV data.
     * @param csvData The CSV data.
     */
    public void setCsvData(ArrayList<ArrayList<String>> csvData) {
    	this.csvData = csvData;
    }
    /**
     * Returns the new data after calculation.
     * @return The new data after calculation.
     */
    public ArrayList<ArrayList<String>> getNewData() {
    	return newData;
    }
    /**
     * Sets the new data after calculation.
     * @param newData The new data after calculation.
     */
    public void setNewData(ArrayList<ArrayList<String>> newData) {
    	this.newData = newData;
    }
}
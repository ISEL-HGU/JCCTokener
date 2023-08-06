package edu.handong.csee.java.hw5.thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.Calculator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is for reading, writing and calculating only csv files. This class
 * has two three methods which are going to read, write and calculate files. You
 * can give file name and directory. If you give a directory path, the all files
 * which are in the directory will be read, written, and calculated. at the same
 * time by using Thread which is implemented Runnable interface.
 */
public class CSVFileCalculator implements Runnable {

	/**
	 * This is a field about Files
	 */
	private File file;
	/**
	 * This is a field about data of file
	 */
	private ArrayList<ArrayList<String>> data;
	/**
	 * This is a field about that the the file path is given as a directory path. If
	 * you give a file path as directory path, token will be true.
	 */
	private boolean token;
	/**
	 * This is a field about options which are given as a parameter.
	 */
	private OptionHandler option;

	/**
	 * This is a constructor about CSVFileCalculator. It has no arguments.
	 */
	public CSVFileCalculator() {
		this.file = null;
		this.setData(null);
		token = false;
	}

	/**
	 * This is a constructor about CSVFileCalculator's option. It has argument about
	 * option.
	 */
	public CSVFileCalculator(OptionHandler option) {
		this.setOption(option);
	}

	/**
	 * This is a constructor about CSVFileCalculator. It has many arguments which
	 * are file name, data, and option. This constructor will be used when you use
	 * threads.
	 */
	public CSVFileCalculator(File file, ArrayList<ArrayList<String>> data, OptionHandler option) {
		this.file = file;
		this.setData(data);
		token = true;
		this.setOption(option);
	}

	/**
	 * This is a getter for file.
	 */
	public File getFile() {
		return file;
	}

	/**
	 * This is a setter for file.
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * This is a getter for data.
	 */
	public ArrayList<ArrayList<String>> getData() {
		return data;
	}

	/**
	 * This is a setter for data.
	 */
	public void setData(ArrayList<ArrayList<String>> data) {
		this.data = data;
	}

	/**
	 * This is a getter for option.
	 */
	public OptionHandler getOption() {
		return option;
	}

	/**
	 * This is a setter for option.
	 */
	public void setOption(OptionHandler option) {
		this.option = option;
	}

	/**
	 * This is a getter for token.
	 */
	public boolean getToken() {
		return token;
	}

	/**
	 * This is a setter for option.
	 */
	public void setToken(boolean token) {
		this.token = token;
	}

	/**
	 * This is a run method from Runnable method. it's overridden
	 * @Override
	 */
	public void run() {
		// TODO Auto-generated method stub
		readCSV(file.getPath());
	}
	/**
	 * 
	 * This public method is for reading csv files. You can give file path or
	 * directory path. If there is no file, the help message will be printed. If
	 * there is no data in file, the exception message will be printed.
	 */
	public ArrayList<ArrayList<String>> readCSV(String filePath) {
		filePath = filePath.replace("/", File.separator).replace("\\", File.separator);
		File f = new File(filePath);

		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

		/**
		 *  if the file path contain ".csv"
		 */
		if (filePath.indexOf(".csv") != -1) {
			/**
			 *  if there is no file. 
			 */
			if (!f.exists()) {
				option.printHelp(option);
				return null;
			}
			/**
			 *  if there is no data in the file. 
			 */
			if (f.length() == 0) {
				Calculator callTheExceptionTroughSetAndCheckEngine = new Calculator();
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.add(option.getTask().toUpperCase());
				callTheExceptionTroughSetAndCheckEngine.setAndCheckEngine(tmp, option);
				return null;
			}
			/**
			 *  if the file path is given with directory path.
			 */
			if (filePath.indexOf(File.separator) == -1) {
				try (FileReader fileReader = new FileReader(".." + File.separator + filePath);
						CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)) {
					for (CSVRecord csvRecord : csvParser) {
						ArrayList<String> row = new ArrayList<>();
						for (String value : csvRecord) {
							if (!value.isEmpty())
								row.add(value);
						}
						data.add(row);
					}
				} catch (IOException e) {
					option.printHelp(option);
					return null;
				}
				if (!token) {
					return data;
				}
			} 
			/**
			 *  if the file path is given without directory path. it means that it's given only file name.
			 */
			else {
				String filePathUpdate = filePath;
				if (!f.isAbsolute()) {
					filePathUpdate = "." + File.separator + filePath;
				}
				try (FileReader fileReader = new FileReader(filePathUpdate);
						CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)) {
					for (CSVRecord csvRecord : csvParser) {
						ArrayList<String> row = new ArrayList<>();
						for (String value : csvRecord) {
							if (!value.isEmpty())
								row.add(value);
						}
						data.add(row);
					}
				} catch (IOException e) {
					option.printHelp(option);
					return null;
				}
				if (!token) {
					return data;
				}
			}
		} 
		/**
		 *  if the file path don't contain ".csv". it means that it's given as a directory path.
		 */
		else {
			if (filePath.lastIndexOf(File.separator) != filePath.length() - 1)
				filePath += File.separator;
			File directory = new File(filePath);

			if (!filePath.equals(directory.getAbsolutePath() + File.separator))
				directory = new File("." + File.separator + filePath);

			File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));
			
			if (files != null) {
				ArrayList<Callable<Object>> calls = new ArrayList<Callable<Object>>();
				/**
				 * if path is a directory path, the thread will be used.
				 */
				ExecutorService executor = Executors.newFixedThreadPool(files.length);

				for (File file : files) {
					executor.execute(new CSVFileCalculator(file, data, option));
					
				}
				/**
				 *  This line will be terminated after all threads are terminated.
				 */
				try {
					executor.invokeAll(calls); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				executor.shutdown();

				while (!executor.isTerminated()) {
					/**
					 *  Wait for all tasks to complete
					 */
				}

			} else {
				option.printHelp(option);
				return null;
			}
		}
		/**
		 * if path is a directory path, the thread will be used.
		 */
		if (token) {
			writeCSV(filePath, data);
			return null;
		}
		return null;
	}

	/**
	 * 
	 * This public method is for writing csv files. 
	 * If the input file path is only file path, the output file will be named as the option.
	 * If the input file path is directory path, the output file will be named as a combined input file's name and output file's name.
	 * for example, input file is "abc.csv" , output file is "new.csv". the output file name is will be "abc-new.csv"
	 */
	public synchronized void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) {
		String inputFilePath = filePath.replace("/", File.separator).replace("\\", File.separator);
		String outputFilePath = option.getDataOutputFilePath().replace("/", File.separator).replace("\\",
				File.separator);

		ArrayList<ArrayList<String>> calculatedCSVData = calculate(csvData);

		if (calculatedCSVData != null) {
			String outPutFileName = null;
			if (token) {
				outPutFileName = inputFilePath.substring((inputFilePath.lastIndexOf(File.separator) + 1),
						(inputFilePath.lastIndexOf("."))) + "-"
						+ outputFilePath.substring(outputFilePath.lastIndexOf(File.separator) + 1,
								(outputFilePath.lastIndexOf(".")))
						+ ".csv";
			} else
				outPutFileName = outputFilePath.substring(outputFilePath.lastIndexOf(File.separator) + 1,
						(outputFilePath.lastIndexOf("."))) + ".csv";

			String outPutFileLocation = null;
			
			if (outputFilePath.indexOf(File.separator) != -1) {
				outPutFileLocation = "." + outputFilePath.substring(0, outputFilePath.lastIndexOf(File.separator) + 1);
			} else
				outPutFileLocation = /* ".." + File.separator + */outputFilePath.substring(0,
						outputFilePath.lastIndexOf(File.separator) + 1);

			try (FileWriter fileWriter = new FileWriter(outPutFileLocation + outPutFileName);
					CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {

				ArrayList<String> header = new ArrayList<String>();
				header.addAll(calculatedCSVData.get(0));

				PrintWriter outputStream = null;

				try {
					outputStream = new PrintWriter(outPutFileLocation + outPutFileName);

				} catch (IOException e) {
					System.out.println("Error opening output file " + outPutFileLocation + outPutFileName);
					System.out.println(e.getMessage());
					System.exit(0);
				}
				for (int i = 0; i < header.size(); i++) {
					if (i == header.size() - 1)
						fileWriter.write(header.get(i));
					else
						fileWriter.write(header.get(i) + ",");
				}
				
				fileWriter.write("\n");

				calculatedCSVData.remove(0);
				//csvPrinter.printRecords(calculatedCSVData);

				if (option.getTask().toUpperCase().equals("SQRT") && !token) {
					for (ArrayList<String> row : calculatedCSVData) {
						for (int i = 0; i < row.size(); i++) {
							if (i == row.size() - 1)
								fileWriter.write(row.get(i));
							else
								fileWriter.write(row.get(i) + ",");
						}
						fileWriter.write("\n");
					}
				} else {
					csvPrinter.printRecords(calculatedCSVData);
				}

				csvPrinter.flush();

				if (!token)
					System.out.println("The " + outPutFileName + " file has been successfully written.");

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			return;
	}

	/**
	 * 
	 * This public method is for calculate data. 
	 * There are three calculators which are "SQRT", "MAX", "MIN". you can use these calculators by call the each Engines class.
	 */
	public synchronized ArrayList<ArrayList<String>> calculate(ArrayList<ArrayList<String>> csvData) {
		String engineName = option.getTask().toUpperCase();
		
		ArrayList<ArrayList<String>> calculatedCSVData = new ArrayList<ArrayList<String>>();
		ArrayList<String> tmp = new ArrayList<>();
		tmp.addAll(csvData.get(0)); // add header to calculatedCSVData
		
		csvData.remove(0);
		Computable engine = null;
		Calculator chekerForEngine = new Calculator();
		ArrayList<String> tmpChecker = new ArrayList<String>();
	
		switch (engineName) {
		case "SQRT":
			calculatedCSVData.add(tmp);// add header to calculatedCSVData
			tmpChecker.add(engineName);
			
			for (ArrayList<String> row : csvData) {
				ArrayList<String> tmpRow = new ArrayList<String>();
				for (String value : row) {
					tmpChecker.add(value);
					engine = chekerForEngine.setAndCheckEngine(tmpChecker, option);
					tmpChecker.remove(1);
					
					if (engine == null)
						return null;
					
					String[] tmpInput = { value };
					engine.setInput(tmpInput);
					engine.compute();
				
					tmpRow.add(String.valueOf(engine.getResult()));
				}
				calculatedCSVData.add(tmpRow);
			}
			break;

		case "MAX":
			tmp.add(engineName);
			calculatedCSVData.add(tmp); // add header to calculatedCSVData
			
			for (ArrayList<String> row : csvData) {
				ArrayList<String> tmpRow = new ArrayList<String>();
				tmpChecker.add(engineName);
				int count = 0;

				String[] tmpInput = new String[row.size()];
				for (String value : row) {
					tmpInput[count++] = value;
					tmpChecker.add(value);
				}

				engine = chekerForEngine.setAndCheckEngine(tmpChecker, option);
				tmpChecker.removeAll(tmpChecker);
				
				if (engine == null)
					return null;

				engine.setInput(tmpInput);
				engine.compute();

				for (String value : row) {
					tmpRow.add(value);
				}
				tmpRow.add(String.valueOf((int) engine.getResult()));

				calculatedCSVData.add(tmpRow);
			}
			break;
		case "MIN":
			tmp.add(engineName);
			calculatedCSVData.add(tmp); // add header to calculatedCSVData
		
			for (ArrayList<String> row : csvData) {
				ArrayList<String> tmpRow = new ArrayList<String>();
				tmpChecker.add(engineName);
				int count = 0;

				String[] tmpInput = new String[row.size()];
				for (String value : row) {
					tmpInput[count++] = value;
					tmpChecker.add(value);
				}

				engine = chekerForEngine.setAndCheckEngine(tmpChecker, option);
				tmpChecker.removeAll(tmpChecker);
				
				if (engine == null)
					return null;

				engine.setInput(tmpInput);
				engine.compute();

				for (String value : row) {
					tmpRow.add(value);
				}
				tmpRow.add(String.valueOf((int) engine.getResult()));

				calculatedCSVData.add(tmpRow);
			}
			break;
		}

		return calculatedCSVData;
	}
}

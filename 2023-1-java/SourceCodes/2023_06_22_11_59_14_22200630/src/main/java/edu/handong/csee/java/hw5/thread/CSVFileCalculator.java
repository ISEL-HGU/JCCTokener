// edu.handong.csee.java.hw5.thread 패키지에 CSVFileCalculator 클래스 생성
package edu.handong.csee.java.hw5.thread;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.Reader;
import java.io.Writer;


public class CSVFileCalculator implements Runnable {
    private String filePath; // 입력 CSV 파일 경로
    private String taskName; // 작업 이름 (SQRT, MAX, MIN)
    private ArrayList<ArrayList<String>> csvData; // CSV 파일 데이터

    public CSVFileCalculator(String filePath, String taskName) {
        this.filePath = filePath;
        this.taskName = taskName;
        this.csvData = new ArrayList<>();
    }

    // CSV 파일에서 데이터 읽기
    public ArrayList<ArrayList<String>> readCSV() {
    	try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
    	         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

    	        for (CSVRecord csvRecord : csvParser) {
    	            ArrayList<String> row = new ArrayList<>();
    	            for (String value : csvRecord) {
    	                row.add(value);
    	            }
    	            csvData.add(row);
    	        }
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }

    	    return csvData;
    	}


    // 작업 실행
    public void run() {
        if (taskName.equals("SQRT")) {
            calculateSQRT();
        } else if (taskName.equals("MAX")) {
            calculateMAX();
        } else if (taskName.equals("MIN")) {
            calculateMIN();
        }
    }

    // 제곱근 계산
    private void calculateSQRT() {
        for (ArrayList<String> row : csvData) {
            for (int i = 0; i < row.size(); i++) {
                String value = row.get(i);
                try {
                    double num = Double.parseDouble(value);
                    double sqrt = Math.sqrt(num);
                    row.set(i, String.valueOf(sqrt));
                } catch (NumberFormatException e) {
                    // 숫자로 변환할 수 없는 경우 무시
                }
            }
        }
    }

    // 최댓값 추가
    private void calculateMAX() {
        for (ArrayList<String> row : csvData) {
            double max = Double.MIN_VALUE;
            for (String value : row) {
                try {
                    double num = Double.parseDouble(value);
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException e) {
                    // 숫자로 변환할 수 없는 경우 무시
                }
            }
            row.add(String.valueOf(max));
        }
    }

    // 최솟값 추가
    private void calculateMIN() {
        for (ArrayList<String> row : csvData) {
            double min = Double.MAX_VALUE;
            for (String value : row) {
                try {
                    double num = Double.parseDouble(value);
                    if (num < min) {
                        min = num;
                    }
                } catch (NumberFormatException e) {
                    // 숫자로 변환할 수 없는 경우 무시
                }
            }
            row.add(String.valueOf(min));
        }
    }

    // CSV 파일에 데이터 쓰기
    public void writeCSV(String outputPath) {
    	try (Writer writer = Files.newBufferedWriter(Paths.get(outputPath));
    	         CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

    	        for (ArrayList<String> row : csvData) {
    	            csvPrinter.printRecord(row);
    	        }
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}
}

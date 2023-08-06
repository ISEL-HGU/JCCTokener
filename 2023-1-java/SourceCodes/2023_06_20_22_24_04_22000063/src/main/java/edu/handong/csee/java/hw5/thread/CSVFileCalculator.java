package edu.handong.csee.java.hw5.thread;

import edu.handong.csee.java.hw5.exceptions.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;

/**
 * This is CSVFileCalculator
 */

public class CSVFileCalculator implements Runnable, Thread.UncaughtExceptionHandler {
    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    private String engine;
    private String filePath;
    private String outFileName;
    private boolean exceptionOccurred = false;

    /**
     * This is the getter of exceptionOccurred
     * @return
     */

    public boolean isExceptionOccurred() {
        return exceptionOccurred;
    }

    /**
     * This is the setter of exceptionOccurred
     * @param exceptionOccurred
     */

    public void setExceptionOccurred(boolean exceptionOccurred) {
        this.exceptionOccurred = exceptionOccurred;
    }

    /**
     * This is the getter of outFileName
     * @return
     */
    public String getOutFileName() {
        return outFileName;
    }

    /**
     * This is the setter of outFileName
     * @param outFileName
     */
    public void setOutFileName(String outFileName) {
        this.outFileName = outFileName;
    }

    /**
     * This is the getter of filePath
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This is the setter of filePath
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This is the getter of engine
     * @return
     */
    public String getEngine() {
        return engine;
    }

    /**
     * This is the setter of engine
     * @param engine
     */

    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * This is the run method
     */
    @Override
    public void run() {
        ArrayList<ArrayList<String>> csvData = null;


        try {
            csvData = readCSV(filePath);
        } catch (IOException e) {
            handleException(e);
            Thread.currentThread().interrupt();
        }


        try {
            writeCSV(filePath, csvData);
        } catch (IOException e) {
            handleException(e);
            Thread.currentThread().interrupt();
        } catch (NegativeNumberException e) {
            handleException(e);
            Thread.currentThread().interrupt();
        } catch (MinimumInputNumberException e) {
            handleException(e);
            Thread.currentThread().interrupt();
        } catch (MyNumberFormatException e) {
            handleException(e);
            Thread.currentThread().interrupt();
        } catch (OneInputException e) {
            handleException(e);
            Thread.currentThread().interrupt();
        }

    }

    /**
     * This is the readCSV method
     * @param filePath
     * @return
     * @throws IOException
     */
    public ArrayList<ArrayList<String>> readCSV(String filePath) throws IOException {
        String line;

//      ArrayList<String> dataList = new ArrayList<>();
        ArrayList<ArrayList<String>> dataList = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);

        for(CSVRecord record : csvParser) {
            ArrayList<String> tempList = new ArrayList<>();
            for(int i = 0; i < record.size(); i++) {
                tempList.add(record.get(i));
            }
            dataList.add(new ArrayList<>(tempList));
        }
        bufferedReader.close();

        return dataList;
    }

    /**
     * This is the writeCSV method
     * @param filePath
     * @param csvData
     * @throws IOException
     * @throws NegativeNumberException
     * @throws MyNumberFormatException
     * @throws MinimumInputNumberException
     */
    public void writeCSV(String filePath, ArrayList<ArrayList<String>> csvData) throws IOException, NegativeNumberException, MyNumberFormatException, MinimumInputNumberException, OneInputException {
        if(engine.equals("SQRT")) {

            if(filePath.endsWith("/")) { // 끝에 / 삭제
                filePath = filePath.substring(0, filePath.length() - 2);
            }

            String[] realTemp = filePath.split("/");

            realTemp[realTemp.length - 1] = realTemp[realTemp.length - 1].replace(".csv", "") + "-";

            String curString[] = outFileName.split("/");
            String changeData = curString[curString.length - 1];

            outFileName = outFileName.replace(changeData, "");
            outFileName += realTemp[realTemp.length - 1] + changeData;

            ArrayList<String> tempList;
            ArrayList<ArrayList<String>> curList = new ArrayList<>();
            ArrayList<String> finalList = new ArrayList<>();
            String number = null;
            String line = null;

            try {
                if (csvData.size() > 0) {
                    for (int i = 0; i < csvData.size(); i++) { // sqrt 계산

                        tempList = new ArrayList<>();
                        if (i == 0) {
                            for (int j = 0; j < csvData.get(i).size(); j++) {
                                tempList.add(csvData.get(i).get(j));
                            }
                        } else {
                            for (int j = 0; j < csvData.get(i).size(); j++) {
                                number = csvData.get(i).get(j);
                                Double num = Double.parseDouble(String.valueOf(csvData.get(i).get(j)));
                                if (num < 0) {
                                    throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engine + ".");
                                }
                                num = Math.sqrt(num);
                                tempList.add(String.valueOf(num));
                            }

                        }
                        curList.add(tempList);
                    }
                } else if(csvData.size() == 1) {
                    throw new NoInputException("no input");
                } else {
                    throw new OneInputException("Exception-04: You need one input value for " + engine + ".");
                }
            } catch (NegativeNumberException e) {
                exceptionOccurred = true;
                throw e;
            } catch (OneInputException e) {
                exceptionOccurred = true;
                throw e;
            } catch (NoInputException e) {
                exceptionOccurred = true;
            } catch (Exception e) {
                exceptionOccurred = true;
                throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + number + " is not a number value for " + engine + ".)");
            }


            if(!exceptionOccurred) {
                FileOutputStream fout = new FileOutputStream(outFileName);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fout));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

                ArrayList<String> first = curList.get(0);
                String header = "";
                for(int i = 0; i < first.size(); i++) {
                    header += first.get(i);
                    if(i < first.size() - 1) {
                        header += ",";
                    }
                }
                writer.write(header + "\n");
                for(int i = 1; i < curList.size(); i++) {
                    csvPrinter.printRecord(curList.get(i));
                }
                csvPrinter.flush();
            }
//            System.out.println("The " + filePath + " file has been successfully written.");
        } else if(engine.equals("MAX")) {
            if(filePath.endsWith("/")) {
                filePath = filePath.substring(0, filePath.length() - 2);
            }

            String[] realTemp = filePath.split("/");

            realTemp[realTemp.length - 1] = realTemp[realTemp.length - 1].replace(".csv", "") + "-";

            String curString[] = outFileName.split("/");
            String changeData = curString[curString.length - 1];

            outFileName = outFileName.replace(changeData, "");
            outFileName += realTemp[realTemp.length - 1] + changeData;

            ArrayList<String> tempList;
            ArrayList<ArrayList<String>> curList = new ArrayList<>();
            ArrayList<String> finalList = new ArrayList<>();
            String number = null;

            StringBuilder stringBuilder = new StringBuilder();


            String line = null;

            try {
                if(csvData.size() > 1) {
                    for(int i = 0; i < csvData.size(); i++) { // sqrt 계산
                        int max = 0;
                        tempList = new ArrayList<>();
                        if (i == 0) {
                            for (int j = 0; j < csvData.get(i).size(); j++) {
                                tempList.add(csvData.get(i).get(j));
                            }
                            tempList.add("MAX");
                        } else {
                            for (int j = 0; j < csvData.get(i).size(); j++) {
                                if (j == 0) {
                                    number = csvData.get(i).get(j);
                                    max = Integer.parseInt(String.valueOf(csvData.get(i).get(j)));
                                    tempList.add(String.valueOf(max));
                                } else {
                                    number = csvData.get(i).get(j);
                                    int num = Integer.parseInt(String.valueOf(csvData.get(i).get(j)));
                                    if (max < num)
                                        max = num;
                                    tempList.add(String.valueOf(num));
                                }
                                if (csvData.get(i).size() < 2) {
                                    throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for " + engine + ".");
                                }
                            }
                            tempList.add(String.valueOf(max));
                        }
                        curList.add(tempList);
                    }
                } else if(csvData.size() == 1) {
                    exceptionOccurred = true;
                    throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for " + engine + ".");
                }
                else {
                    throw new NoInputException("No Input");
                }
            } catch (MinimumInputNumberException e) {
                exceptionOccurred = true;
                throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for " + engine + ".");
            } catch (NoInputException e) {
                exceptionOccurred = true;
            } catch (Exception e) {
                exceptionOccurred = true;
                throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + number + " is not a number value for " + engine + ".)");
            }

            if(!exceptionOccurred) {
                FileOutputStream fout = new FileOutputStream(outFileName);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fout));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

                ArrayList<String> first = curList.get(0);
                String header = "";
                for(int i = 0; i < first.size(); i++) {
                    header += first.get(i);
                    if(i < first.size() - 1) {
                        header += ",";
                    }
                }
                writer.write(header + "\n");
                for(int i = 1; i < curList.size(); i++) {
                    csvPrinter.printRecord(curList.get(i));
                }
                csvPrinter.flush();
            }



//            System.out.println("The " + curString[curString.length - 1] + " file has been successfully written.");
        } else { // min

            if(filePath.endsWith("/")) {
                filePath = filePath.substring(0, filePath.length() - 2);
            }

            String[] realTemp = filePath.split("/");

            realTemp[realTemp.length - 1] = realTemp[realTemp.length - 1].replace(".csv", "") + "-";

            String curString[] = outFileName.split("/");
            String changeData = curString[curString.length - 1];

            outFileName = outFileName.replace(changeData, "");
            outFileName += realTemp[realTemp.length - 1] + changeData;


            ArrayList<String> tempList;
            ArrayList<ArrayList<String>> curList = new ArrayList<>();
            ArrayList<String> finalList = new ArrayList<>();
            String number = null;

            StringBuilder stringBuilder = new StringBuilder();


            String line = null;
            try {
                if(csvData.size() > 1) {
                    for(int i = 0; i < csvData.size(); i++) { // sqrt 계산
                        int min = 0;
                        tempList = new ArrayList<>();
                        if (i == 0) {
                            for (int j = 0; j < csvData.get(i).size(); j++) {
                                tempList.add(csvData.get(i).get(j));
                            }
                            tempList.add("MIN");
                        } else {
                            for (int j = 0; j < csvData.get(i).size(); j++) {
                                if (j == 0) {
                                    number = csvData.get(i).get(j);
                                    min = Integer.parseInt(String.valueOf(csvData.get(i).get(j)));
                                    tempList.add(String.valueOf(min));
                                } else {
                                    number = csvData.get(i).get(j);
                                    int num = Integer.parseInt(String.valueOf(csvData.get(i).get(j)));
                                    if (min > num)
                                        min = num;
                                    tempList.add(String.valueOf(num));
                                }
                                if (csvData.get(i).size() < 2) {
                                    throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for " + engine + ".");
                                }
                            }
                            tempList.add(String.valueOf(min));
                        }
                        curList.add(tempList);
                    }
                } else if(csvData.size() == 1) {
                    throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for " + engine + ".");
                }
                else {
                    throw new NoInputException("no input");
                }
            } catch (MinimumInputNumberException e) {
                exceptionOccurred = true;
                throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for " + engine + ".");
            } catch (NoInputException e) {
                exceptionOccurred = true;
            } catch (Exception e) {
                exceptionOccurred = true;
                throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + number + " is not a number value for " + engine + ".)");
            }
            if(!exceptionOccurred) {
                FileOutputStream fout = new FileOutputStream(outFileName);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fout));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

                ArrayList<String> first = curList.get(0);
                String header = "";
                for(int i = 0; i < first.size(); i++) {
                    header += first.get(i);
                    if(i < first.size() - 1) {
                        header += ",";
                    }
                }
                writer.write(header + "\n");
                for(int i = 1; i < curList.size(); i++) {
                    csvPrinter.printRecord(curList.get(i));
                }
                csvPrinter.flush();
            }



//            System.out.println("The " + curString[curString.length - 1] + " file has been successfully written.");
        }


    }

    /**
     * This is the constructor
     * @param engine
     * @param filePath
     * @param outFileName
     */
    public CSVFileCalculator(String engine, String filePath, String outFileName) {
        this.engine = engine;
        this.filePath = filePath;
        this.outFileName = outFileName;
    }

    private void handleException(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Method invoked when the given thread terminates due to the
     * given uncaught exception.
     * <p>Any exception thrown by this method will be ignored by the
     * Java Virtual Machine.
     *
     * @param t the thread
     * @param e the exception
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.getMessage();
    }
}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.FileException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.fileutil.FileManager;

import java.util.ArrayList;


/**
 * This class is the SQRTEngine for doing calculate square root value.
 */
public class SQRTEngine implements Computable {
    private boolean files = false;
    private String inputPath;
    private boolean isDirectory;

    /**
     * This is the isDirectory
     * @return
     */
    public boolean isDirectory() {
        return isDirectory;
    }

    /**
     * This is the setter of directory
     * @param directory
     */

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    /**
     * This is the getter of inputPath
     * @return
     */

    public String getInputPath() {
        return inputPath;
    }

    /**
     * This is the setter of inputPath
     * @param inputPath
     */

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * This is the isFiles
     * @return
     */

    public boolean isFiles() {
        return files;
    }

    /**
     * This is the setFiles
     * @param files
     */
    public void setFiles(boolean files) {
        this.files = files;
    }

    private ArrayList<String> list;
    private String outPath;

    private static final String engineName ="SQRT";
    private double num;
    private double sqrtNum;

    /**
     * This is the getter of list
     * @return
     */

    public ArrayList<String> getList() {
        return list;
    }

    /**
     * This is the setter of list
     * @param list
     */

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    /**
     * This is the getter of outPath
     * @return
     */
    public String getOutPath() {
        return outPath;
    }

    /**
     * This is the setter of outPath
     * @param outPath
     */

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    /**
     * This is the getter of engineName.
     * @return
     */
    public static String getEnginename() {
        return engineName;
    }
    /**
     * This is the getter of num.
     * @return
     */
    public double getNum() {
        return num;
    }

    /**
     * This is the setter of num.
     * @param num
     */
    public void setNum(double num) {
        this.num = num;
    }

    /**
     * This is the getter of sqrtNum.
     * @return
     */
    public double getSqrtNum() {
        return sqrtNum;
    }

    /**
     * This is the setter of sqrtNum.
     * @param sqrtNum
     */
    public void setSqrtNum(double sqrtNum) {
        this.sqrtNum = sqrtNum;
    }

    /**
     * This is the method of SQRTEngine to get Input data.
     *
     * @param args
     */
    @Override
    public void setInput(String[] args) throws FileException, OneInputException, NegativeNumberException {

        // file인지 directory인지 먼저 구분해야 한다
        // sqrt engine에서 구분할 필요 x / main에서 구분하고 다르게 호출하는 방식 사용

        if(!args[0].matches("[+-]?\\d*(\\.\\d+)?")) { // Args is path
            try { // 파일인 경우
                list = FileManager.readLinesFromATxtFile(args[0]); // file read
                outPath = args[1];
                setFiles(true);
            } catch (Exception e) {
                throw new FileException();
            }
        } else { // Args is not path
            int len = args.length;

            if(len != 1)
                throw new OneInputException("Exception-04: You need one input value for " + engineName + ".");

            if(Double.parseDouble(args[0]) < 0)
                throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName + ".");

//        if(len != 2)
//            InputChecker.printErrorMessageForTheNumberOfRequiredInputsAndExit(engineName, 1);
//        if(Double.parseDouble(args[1]) < 0)
//            InputChecker.printErrorMessageForNegativeInputsAndExit(engineName);

            num = Double.parseDouble(args[0]);
        }
    }

    /**
     * This is the method of SQRTEngine to compute some values.
     */
    @Override
    public void compute() throws MyNumberFormatException, FileException, NegativeNumberException{
        int count = 0;
        double cur;
        StringBuilder stringBuilder = new StringBuilder();

        if(outPath == null) {
            sqrtNum = Math.sqrt(num);
        } else {

            for (int j = 0; j < list.size(); j++) {
                String str = list.get(j);
                String[] values = str.split(",");

                if (!values[0].matches("[+-]?\\d*(\\.\\d+)?")) { // if the line is first

                } else { // the line is not first case
                    for(int i = 0; i < values.length; i++) { // 예외 발생 가능
                        try {
                            if(Double.parseDouble(values[i]) < 0) {
                                throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName + ".");
                            }
                            cur = Math.sqrt(Double.parseDouble(values[i]));
                            values[i] = Double.toString(cur);
                        } catch (NegativeNumberException e) {
                            throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName + ".");
                        } catch (Exception e) {
                            throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + values[i] + " is not a number value for " + engineName + ".)");
                        }

                    }

                    for(String temp : values) {
                        stringBuilder.append(temp).append(",");
                    }

                    str = stringBuilder.substring(0,stringBuilder.length() - 1); // to delete last ','
                    stringBuilder.setLength(0);

                    list.set(j, str);
                }
            }
            try {
                if(isFiles()) {
                    FileManager.writeATxtFile(outPath, list);
                }
            } catch (Exception e) {
                throw new FileException();
            }

        }
    }

    /**
     * This method is the return method that value from SQRTEngine.
     *
     * @return
     */
    @Override
    public double getResult() {
        return sqrtNum;
    }
}

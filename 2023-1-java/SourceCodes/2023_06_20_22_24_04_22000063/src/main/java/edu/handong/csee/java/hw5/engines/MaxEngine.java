package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.FileException;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.fileutil.FileManager;

import java.util.ArrayList;

/**
 * This class is the MaxEngine for doing calculate maximum value
 */
public class MaxEngine implements Computable {
    private static final String engineName ="MAX";
    private double[] nums;
    private double max;
    private String outPath;
    private ArrayList<String> list;

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
     * This is the getter of engineName.
     * @return
     */
    public static String getEnginename() {
        return engineName;
    }
    /**
     * This is the getter of nums.
     * @return
     */
    public double[] getNums() {
        return nums;
    }

    /**
     * This is the setter of nums.
     * @param nums
     */
    public void setNums(double[] nums) {
        this.nums = nums;
    }

    /**
     * This is the getter of max.
     * @return
     */
    public double getMax() {
        return max;
    }

    /**
     * This is the setter of max.
     * @param max
     */
    public void setMax(double max) {
        this.max = max;
    }

    /**
     * This is the method of MaxEngine to get Input data
     *
     * @param args
     */
    @Override
    public void setInput(String[] args) throws MinimumInputNumberException, FileException {
        int maxLength = args.length;

        if(!args[0].matches("[+-]?\\d*(\\.\\d+)?")) { // Args is path
            try {
                list = FileManager.readLinesFromATxtFile(args[0]); // file read
                outPath = args[1];
            } catch (Exception e) {
                throw new FileException();
            }
        } else {
            if(maxLength < 2)
                throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for " + engineName + ".");

            nums = new double[maxLength];
            for(int i = 0; i < maxLength; i++) {
                if(i == 0) {
                    max = Double.parseDouble(args[i]);
                }
                nums[i] = Double.parseDouble(args[i]);
            }
        }

    }

    /**
     * This is the method of MaxEngine to compute some values
     */
    @Override
    public void compute() throws NegativeNumberException, FileException, MinimumInputNumberException{
        int count = 0;
        double cur;
        StringBuilder stringBuilder = new StringBuilder();

        if(outPath == null) {
            for(double num : nums) {
                if(max < num)
                    max = num;
            }
        } else {

            for (int j = 0; j < list.size(); j++) {
                String str = list.get(j);
                String[] values = str.split(",");

                if (!values[0].matches("[+-]?\\d*(\\.\\d+)?")) { // if the line is first

                    for(int k = 0; k < values.length; k++) {
                        stringBuilder.append(values[k]).append(",");
                    }
                    stringBuilder.append("MAX");
                    str = stringBuilder.substring(0,stringBuilder.length()); // to delete last ','
                    stringBuilder.setLength(0);
                    list.set(j, str);

                } else { // the line is not first case

                    if(values.length < 3) {
                        throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for " + engineName + ".");
                    }

                    for(int i = 0; i < values.length; i++) { // 예외 발생 가능
                        try {
                            if(i == 0) {
                                max = Double.parseDouble(values[i]);
                            } else {
                                if(max < Double.parseDouble(values[i])) {
                                    max = Double.parseDouble(values[i]);
                                }
                            }
                        } catch (Exception e) {
                            throw new MyNumberFormatException("Exception-05: The input value should be converted into a number. (" + values[i] + " is not a number value for " + engineName + ".)");
                        }
                    }



                    for(String temp : values) {
                        stringBuilder.append(temp).append(",");
                    }
                    stringBuilder.append(max);


                    str = stringBuilder.substring(0,stringBuilder.length()); // to delete last ','
                    stringBuilder.setLength(0);

                    list.set(j, str);
                }
            }
            try {
                FileManager.writeATxtFile(outPath, list);
            } catch (Exception e) {
                throw new FileException();
            }

        }
    }


    /**
     * This method is the return method that return value from MaxEngine
     *
     * @return
     */
    @Override
    public double getResult() {
        return max;
    }
}

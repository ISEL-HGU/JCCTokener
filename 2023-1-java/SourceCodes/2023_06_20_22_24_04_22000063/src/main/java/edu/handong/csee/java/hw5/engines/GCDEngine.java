package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;


import java.util.Arrays;

/**
 * This class is the GCDEngine for doing calculate the GCD number.
 */
public class GCDEngine implements Computable {
    private static final String engineName ="GCD";
    private double[] nums;
    private double result;

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
     * This is the setter of result.
     * @param result
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This is the method of GCDEngine to get Input data.
     *
     * @param args
     */
    @Override
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException {
        int len = args.length;

        if(len < 2)
            throw new MinimumInputNumberException("Exception-02: You need at least 2 input values for " + engineName + ".");

        for(int i = 0; i < len; i++) {
            if(Double.parseDouble(args[i]) < 0) {
                throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName + ".");
            }
        }

//        if(len < 3)
//            InputChecker.printErrorMessageForTheNumberOfMinimumRequiredInputsAndExit(engineName, 2);
//        if(Double.parseDouble(args[1]) < 0 || Double.parseDouble(args[2]) < 0)
//            InputChecker.printErrorMessageForNegativeInputsAndExit(engineName);
        nums = new double[len];
        for(int i = 0; i < len; i++) {
            nums[i] = Double.parseDouble(args[i]);
        }

    }

    /**
     * This is the method of GCDEngine to compute some values
     */
    @Override
    public void compute() {
        int i = nums.length - 1;
        double temp;

        Arrays.sort(nums);
        setResult(nums[i]);
        while(i >= 0) {
            while (nums[i] != 0) {
                temp = result % nums[i];
                result = nums[i];
                nums[i] = temp;
            }
            i--;
        }
    }

    /**
     * This method is the return method of GCDEngine.
     *
     * @return
     */
    @Override
    public double getResult() {
        return result;
    }
}

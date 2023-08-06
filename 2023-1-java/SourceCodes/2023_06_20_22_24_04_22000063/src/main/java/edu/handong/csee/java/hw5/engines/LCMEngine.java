package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;


import java.util.Arrays;

/**
 * This class is the LCMEngine for doing calculate the least common multiple value.
 */
public class LCMEngine implements Computable{
    private static final String engineName ="LCM";
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
     * This is the method of LCMEngine to get Input data.
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
     * This is the method of LCMEngine to compute some values.
     */
    @Override
    public void compute() {
        int i = nums.length - 1;
        double temp;

        Arrays.sort(nums);

        // num이 최대공약수가 된다
        double num = nums[i];

        setResult(nums[i]);
        while(i >= 0) {
            double n1 = nums[i];
            double n2 = result;


            while (nums[i] != 0) {
                temp = n2 % nums[i];
                n2 = nums[i];
                nums[i] = temp;
            } // num이 최대공약수가 된다
            i--;
            result = n1 * result / n2;


        }


    }

    /**
     * This method is the return method of LCMEngine.
     *
     * @return
     */
    @Override
    public double getResult() {
        return result;
    }
}

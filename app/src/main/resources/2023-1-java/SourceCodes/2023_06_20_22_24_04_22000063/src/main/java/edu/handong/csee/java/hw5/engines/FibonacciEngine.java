package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is the FibonacciEngine for doing calculate the fibonacci nums.
 */
public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";
    private double num;
    private double resultNum;

    /**
     * This is the getter of engineName.
     * @return
     */
    public static String getEnginename() {
        return engineName;
    }
    /**
     * This is the getter of resultNum.
     * @return
     */
    public double getResultNum() {
        return resultNum;
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
     * This is the setter of resultNum.
     * @param result
     */
    public void setResultNum(double result) {
        this.resultNum = result;
    }

    /**
     * This is the method of FibonacciEngine to get Input data.
     *
     * @param args
     */
    @Override
    public void setInput(String[] args) throws OneInputException, NegativeNumberException {
        int len = args.length;



        if(len != 1)
            throw new OneInputException("Exception-04: You need one input value for " + engineName + ".");

        if(Double.parseDouble(args[0]) < 0)
            throw new NegativeNumberException("Exception-03: The input value cannot be negative for " + engineName + ".");

//        if(len != 2)
//            InputChecker.printErrorMessageForTheNumberOfRequiredInputsAndExit(engineName, 1);
//        if(Double.parseDouble(args[1]) < 0)
//            InputChecker.printErrorMessageForNegativeInputsAndExit(engineName);

        num = Integer.parseInt(args[0]);
    }

    /**
     * This is the method of FibonacciEngine to compute some values.
     */
    @Override
    public void compute() {
        double n1 = 0, n2 = 1, temp;
        if(num == 0)
            resultNum = 0;
        else if(num == 1 || num == 2)
            resultNum = 1;
        else {
            for(int i = 0; i < num-1; i++) {
                temp = n2;
                n2 = n1 + n2;
                n1 = temp;
            }
            resultNum = n2;
        }
    }

    /**
     * This method is the return method that value for FibonacciEngine.
     *
     * @return
     */
    @Override
    public double getResult() {
        return resultNum;
    }
}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;

/**
 * MinEngine.java
 * Computing a square root of one number
 */
public class SQRTEngine implements Computable {
    private static final String engineName = "SQRT";
    private double input;
    private double result = 1;

    /**
     * engineName is SQRT
     * 
     * @param engineName Type : String
     */
    public void setEngineName(String engineName) {
    }

    /**
     * This method return engineName.
     * 
     * @return engineName
     */
    public String getEngineName() {
        return engineName;
    }

    /**
     * Set input value.
     * 
     * @param input Type : double
     */
    public void setInput(double input) {
        this.input = input;
    }

    /**
     * This method return input.
     * 
     * @return input.
     */
    public double getInput() {
        return input;
    }

    /**
     * Set result value.
     * 
     * @param result Type : double
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This method return result.
     * 
     * @return result
     */
    public double getResult() {
        return result;
    }

    /**
     * This method is for check input - Double or String
     * 
     * @param String ars
     * @return true, false
     */
    public static boolean check(String args) {
        try {
            Double.parseDouble(args);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Set the result value through radius; the inputs args.
     * # of inputs: one number value
     * The input value must not be a negative value.
     * 
     * @param args Type : String[]
     */
    public void setInput(String[] args) {
        try {
            if (!check(args[0]))
                throw new MyNumberFormatException(args[0], engineName);
            if (args.length == 0)
                throw new MinimumInputNumberException(engineName);
            if (args.length > 1)
                throw new OneInputException(engineName);
            input = Integer.parseInt(args[0]);
            if (input < 0)
                throw new NegativeNumberException(engineName);
        } catch (MinimumInputNumberException e2) {
            System.out.println(e2.getMessage());
            System.exit(0);
        } catch (NegativeNumberException e3) {
            System.out.println(e3.getMessage());
            System.exit(0);
        } catch (OneInputException e4) {
            System.out.println(e4.getMessage());
            System.exit(0);
        } catch (MyNumberFormatException e5) {
            System.out.println(e5.getMessage());
            System.exit(0);
        }

    }

    /**
     * Compute result through input
     * result is a square root of one number
     */
    public void compute() {
        result = Math.sqrt(input);
    }
}

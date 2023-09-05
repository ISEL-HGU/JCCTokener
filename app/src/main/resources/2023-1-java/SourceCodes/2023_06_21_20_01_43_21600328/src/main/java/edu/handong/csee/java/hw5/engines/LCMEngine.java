package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This code calculates the Least Common Multiple of the multiple numbers
 */
public class LCMEngine implements Computable{
    /** 
     * sets the engine name as LCM
     */
    private static final String engineName ="LCM";
    /** 
     * a for the calculating Least Common Multiple
     */
    private int a[];
    /** 
     * returning result 
     */
    private int result;
    
    /** 
     * Read the argument and see if there is error 
     */
    public void setInput(String[] args) {
        try {
            if (args.length < 2) {
                throw new MinimumInputNumberException(2, engineName);
            }
            a = new int[args.length - 1];
            for (int i = 0; i < args.length-1; i++) {
                if (Integer.parseInt(args[i]) < 0) {
                    throw new NegativeNumberException(engineName);
                }
                a[i] = Integer.parseInt(args[i + 1]);
            }
        } catch (MinimumInputNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input value: " + e.getMessage());
            System.exit(0);
        }
    }
    /**
     * computing the result by using while loop to see if foundLCM is false
     */
    public void compute() {
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        int LCM = max;
        boolean foundLCM = false;
        while (!foundLCM) {
            boolean isLCM = true;
            for (int i = 0; i < a.length; i++) {
                if (LCM % a[i] != 0) {
                    isLCM = false;
                    break;
                }
            }
            if (isLCM) {
                foundLCM = true;
            } else {
                LCM += max;
            }
        }
        result = LCM; // Convert LCM to double
    }
    
    /**
     * Set the input array.
     * 
     * @param input array containing the input numbers
     */
    public void setInputArray(int[] input) {
        this.a = input;
    }

    /**
     * Get the input array.
     * 
     * @return input array containing the input numbers
     */
    public int[] getInputArray() {
        return a;
    }
    
    /**
     * Get the result for printing.
     * 
     * @return resulting least common multiple (LCM)
     */
    public double getResult() {
        return result;
    }
}

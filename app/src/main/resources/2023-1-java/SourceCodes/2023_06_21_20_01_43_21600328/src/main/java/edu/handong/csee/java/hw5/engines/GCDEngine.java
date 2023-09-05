package edu.handong.csee.java.hw5.engines;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This code calculates the Greatest Common Division of the multiple numbers
 */
public class GCDEngine implements Computable {
    /** 
     * sets the engine name as GCD
     */
    private static final String engineName ="GCD";
    /** 
     * a for the calculating Greatest Common Division
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
     * computing by finding min max and see if a[j]%i = 0 or not
     */
    public void compute() {
        int min = a[0];
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
            if (min > a[i]) {
                min = a[i];
            }
        }
        int gcd = 1;
        boolean isGcd = true;
        for (int i = 2; i <= min; i++) {
            isGcd = true;
            for (int j = 0; j < a.length; j++) {
                if (a[j] % i != 0) {
                    isGcd = false;
                    break;
                }
            }
            if (isGcd) {
                gcd = i;
            }
        }
        result = gcd;
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
     * returning the result for printing
     */
    public double getResult(){
        return result;
    }
}

package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;

/**
 * This is a class that calculates the Fibonacci sequence.
 */
public class FibonacciEngine implements Computable {

	private static String engineName ="FIBONACCI";
    private int n;
    private double result;
    /**
     * It is a method that sets the value to be calculated.
     */
    public void setInput(String[] args){
    	try {
            if(args.length>1){
                throw new OneInputException(getEnginename(), 0);
            }
            if(! args[0].matches("-?\\d+(\\.\\d+)?")) {
            	throw new MyNumberFormatException(getEnginename(), args[0]);
            }
            n = Integer.parseInt(args[0]);
            if(n<0){
            	throw new NegativeNumberException(getEnginename(), 0);
            }
    	}
    	catch (NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    }
    /**
     * This is the actual calculation method.
     */
    public void compute(){
        if(n >1){
            int first =0, second =1;
            for (int i =2; i<= n ;i++){
                result = first + second;
                first = second;
                second = (int)result;
            }
        }
        else if(n ==1){
            result = 1;
        }
        else if (n ==0){
            result =0;
        }

    }
    /**
     * This is the method of obtaining the result value.
     */
    public double getResult(){
        return result;
    }
    /**
     * It returns enginame.
     */
    public static String getEnginename() {
        return engineName;
    }
    /**
     * It returns n.
     */
    public int getN() {
        return n;
    }
    /**
     * This sets n.
     */
    public void setN(int n) {
        this.n = n;
    }
    /**
     * This sets result.
     */
    public void setResult(double result) {
        this.result = result;
    }
    /**
     * set engineName
     * @param enginename
     */
    public static void setEnginename(String enginename) {
		engineName = enginename;
	}
    
}
package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is computing FibonacciEngine and implement Computable(interface) 
 */
public class FibonacciEngine implements Computable {
    private static final String engineName ="FIBONACCI";
    private int n;
    private double result;
    /**
     * This method is setting input.
     * @param args
     */
    public void setInput(String[] args) {
    	OptionHandler optionhandler = new OptionHandler();
    	optionhandler.run(args); 
    		String inputvalues = optionhandler.getInputValues(); 
    	try {
    		String[] values = inputvalues.split(" ");
    		if(values.length > 1) {
    			throw new OneInputException(engineName);
    		}
    		try {
	    		if(Integer.parseInt(inputvalues) < 0) {
	    			throw new NegativeNumberException(engineName);
	    		}
    			this.n = Integer.parseInt(inputvalues);
    		}
    		catch(NumberFormatException e) {
        		throw new MyNumberFormatException();
        	}
    	}
    	catch(OneInputException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e) {
    		System.out.print(e.getMessage());
    		System.out.println("(" + inputvalues + " is not a number value for "+ engineName + ".)");
    		System.exit(0);
    	}
    	
    }
    int fibonacci(int num) {
        if (num == 0) {
            return 0;
        } else if (num == 1) {
            return 1;
        } else {
            return fibonacci(num-1) + fibonacci(num-2);
        }
    }
    /**
     * This method is compute something by some inputs.
     */
    public void compute() {
        int k = fibonacci(this.n);
        this.result = k;
    }
    /**
     * This method return this.n
     * @return
     */
    public int getNumber() {
    	return this.n;
    }
    /**
     * This method gives result.
     * @return
     */
    public double getResult() {
        return this.result;
    }
    /**
     * This method is return this.engineName.
     * @return
     */
    public String getEngineName() {
        return FibonacciEngine.engineName;
    }

}
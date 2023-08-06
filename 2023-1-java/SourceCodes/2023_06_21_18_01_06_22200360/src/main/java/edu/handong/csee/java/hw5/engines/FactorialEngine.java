package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is computing FactorialEngine and implement Computable(interface) 
 */
public class FactorialEngine implements Computable {
    private static final String engineName ="FACTORIAL";
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
    int factorial(int num) {
        if (num == 0 || num == 1) { // n이 0 또는 1인 경우
            return 1;
        } else { // n이 2 이상인 경우
            return num * factorial(num - 1); // n-1까지의 팩토리얼 값과 n을 곱한다.
        }
    }
    /**
     * This method is compute something by some inputs.
     */
    public void compute() {
        this.result = factorial(this.n);
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
        return FactorialEngine.engineName;
    }
}
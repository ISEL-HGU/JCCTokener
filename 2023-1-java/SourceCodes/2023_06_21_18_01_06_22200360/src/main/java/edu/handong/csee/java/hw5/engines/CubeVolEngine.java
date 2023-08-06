package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.clioptions.*;
import edu.handong.csee.java.hw5.exceptions.*;
/**
 * This class is computing CubeVolEngine and implement Computable(interface) 
 */
public class CubeVolEngine implements Computable {
    private static final String engineName ="CUBEVOL";
    private double sideLength;
    private double volume;
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
    		if(Double.parseDouble(inputvalues) < 0) {
    			throw new NegativeNumberException(engineName);
    		}
    		// 발생된 예외가 없을 시 실행되는 문장
    		this.sideLength = Double.parseDouble(inputvalues);
    	}
    	catch(OneInputException e){
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(NegativeNumberException e) {
    		System.out.println(e.getMessage());
    		System.exit(0);
    	}
    	catch(MyNumberFormatException e) {
    		System.out.println(e.getMessage());
    		System.out.println("(" + inputvalues + " is not a number value for "+ engineName + ".)");
    		System.exit(0);
    	}
    	catch(NumberFormatException e) {
    		throw new MyNumberFormatException();
    	}
    }
    /**
     * This method is compute something by some inputs.
     */
    public void compute() {
        this.volume = (sideLength) * (sideLength) * (sideLength);
    }
    /**
     * This method return this.sideLength.
     * @return
     */
    public double getSideLength() {
    	return this.sideLength;
    }
    /**
     * This method gives result.
     * @return
     */
    public double getResult() {
        return this.volume;
    }
    /**
     * This method return this.engineName.
     * @return
     */
    public String getEngineName() {
        return CubeVolEngine.engineName;
    }
}


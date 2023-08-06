package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.clioptions.OptionHandler;
import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is computing SphereVolEngine and implement Computable(interface) 
 */
public class SphereVolEngine implements Computable {
    private static final String engineName ="SPHEREVOL";
    private double radius;
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
    		if(Double.parseDouble(inputvalues) < 0) {
    			throw new NegativeNumberException(engineName);
    		}
    		// 발생된 예외가 없을 시 실행되는 문장
    		this.radius = Double.parseDouble(inputvalues);
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
        double v = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
        this.result = v;
    }
    /**
     * This method return this.radius
     * @return
     */
    public double getRadius() {
    	return this.radius;
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
        return SphereVolEngine.engineName;
    }
    
}